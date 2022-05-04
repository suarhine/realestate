/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package org.realestate.ctrl.page.receipt;

import static org.persist.model.Model.Statement.Criteria.of;
import static org.persist.model.Model.Statement.Expression.order;
import static org.realestate.ctrl.app.ApplicationInstance.model;
import static org.realestate.ctrl.app.Commons.*;
import static org.realestate.db.fix.UsersFuncFix.*;
import static org.web.jsp.fn.Functions.parse;

import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.realestate.ctrl.app.ApplicationException;
import org.realestate.db.entity.*;
import org.reflex.invoke.functional.Functional;
import org.web.ctrl.PageServlet;

/**
 *
 * @author Pathompong
 */
@WebServlet(name = "receipt.InputPage", urlPatterns = {"/receipt/input"})
public class InputPage extends HttpServlet implements PageServlet {

    public record ContractLesseeKey(
            String code,
            String name,
            String representative) {

        public ContractLesseeKey(ContractLessee value) {
            this(value.getCode(), value.getName(), value.getRepresentative());
        }

        public String getCode() {
            return code;
        }

        public String getName() {
            return name;
        }

        public String getRepresentative() {
            return representative;
        }
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(
            HttpServletRequest request, HttpServletResponse response
    ) throws ServletException, IOException {
        var criteria = of(
                """
                SQL(
                'age(?, now()) < (? || '' day'')::interval AND (?, ?, ?) NOT IN (SELECT s.id, s.type, s.dating FROM contract_appointment_receipt s)',
                pk.dating, ?1, pk.id, pk.type, pk.dating
                )
                """,
                flag(request, Integer.class, "early") ? param(request, "early") : "20"
        );
        if (!allow(request, contract_receipt_create, contract_read)) {
            criteria = criteria.and("id.updater", access(request, contract));
        }
        if (flag(request, String.class, "q")) {
            for (var q : param(request, "q").split("\\s+")) {
                criteria = criteria.and((Function<Object, String> arguments) -> {
                    var var = arguments.apply("%" + q + "%");
                    var builder = new StringBuilder();
                    for (var field : new String[]{
                        "id.code"
                    }) {
                        builder.append(" OR ").append(field).append(" LIKE ").append(var);
                    }
                    for (var entry : new String[][]{
                        {"ContractLessee", "id.id", "name,representative"},
                        {"ContractRealestate", "id.id", "name"}
                    }) {
                        var b = new StringBuilder();
                        for (var field : entry[2].split("\\s*,\\s*")) {
                            b.append(" OR s.").append(field).append(" LIKE ").append(var);
                        }
                        builder.append(" OR id.id IN (SELECT s.").append(entry[1])
                                .append(" FROM ").append(entry[0])
                                .append(" s WHERE ").append(b.delete(0, 4)).append(")");
                    }
                    return builder.delete(0, 4).insert(0, "(").append(")");
                });
            }
        }
        if (flag(request, Integer.class, "type")) {
            criteria = criteria.and("id.type.id", param(request, Integer.class, "type"));
        }
        if (flag(request, String.class, "realestate.location")) {
            criteria = criteria.and("id.contractRealestate.location", param(request, String.class, "realestate.location"));
        }
        if (flag(request, String.class, "realestate.address.subdistrict")) {
            criteria = criteria.and("id.contractRealestate.address.subdistrict", param(request, String.class, "realestate.address.subdistrict"));
        }
//            if (flag(request, String.class, "period")) {
//                criteria = criteria.and("", "");
//            }
        if (flag(request, Integer.class, "period.min")) {
            criteria = criteria.and("SQL('date_part(''year'', age(?, ?))', id.ended, id.started)", ">=", param(request, Integer.class, "period.min"));
        }
        if (flag(request, Integer.class, "period.max")) {
            criteria = criteria.and("SQL('date_part(''year'', age(?, ?))', id.ended, id.started)", "<=", param(request, Integer.class, "period.max"));
        }
        if (flag(request, Integer.class, "objective")) {
            criteria = criteria.and("id.objective.id", param(request, Integer.class, "objective"));
        }
        var group = new LinkedHashMap<ContractLesseeKey, List<ContractAppointmentDating>>();
        for (var find : model(ContractAppointmentDating.class).finds(criteria, order("pk.dating NULLS LAST", "pk.type NULLS LAST"))) {
            try {
                var key = new ContractLesseeKey(find.getId().getContractLessee());
                var list = group.get(key);
                if (list == null) {
                    group.put(key, list = new ArrayList<>());
                }
                list.add(find);
            } catch (NullPointerException x) {
            }
        }
        attr(request, "group", group);
        jsp(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(
            HttpServletRequest request, HttpServletResponse response
    ) throws ServletException, IOException {
        var entity = new Receipt();
        entity.setUpdated(new Date());
        entity.setUpdater(access(request, contract_receipt_create));
        entity.setContractAppointmentReceiptList(new Functional<>(request.getParameterValues("selected")).map(e -> {
            var entry = e.split(":");
            var carEntity = new ContractAppointmentReceipt(
                    parse(int.class, entry[0]),
                    parse(int.class, entry[1]),
                    parse(Date.class, entry[2], "yyyy-MM-dd")
            );
            carEntity.setReceipt(entity);
            carEntity.setAmount(param(request, double.class, e));
            return carEntity;
        }).list());
        if (entity.getContractAppointmentReceiptList() == null
                || entity.getContractAppointmentReceiptList().isEmpty()) {
            throw ApplicationException.Type.incomplete_parameter.dispatch("selected");
        }
        if (model(Receipt.class).add(entity)) {
            redirect(response, ".");
        } else {
            throw ApplicationException.Type.uncommited_transaction.dispatch();
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
