/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package org.realestate.ctrl.page.receipt;

import static org.persist.model.Model.Statement.Criteria.*;
import static org.persist.model.Model.Statement.Expression.order;
import static org.realestate.ctrl.app.ApplicationInstance.model;
import static org.realestate.ctrl.app.Commons.*;
import static org.realestate.db.fix.UsersFuncFix.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.function.Function;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.realestate.db.entity.ContractAppointmentReceipt;
import org.realestate.db.entity.Receipt;
import org.web.ctrl.PageServlet;

/**
 *
 * @author Pathompong
 */
@WebServlet(name = "receipt.IndexPage", urlPatterns = {"/receipt/"})
public class IndexPage extends HttpServlet implements PageServlet {

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
        var criteria = allow(request, contract_receipt_read)
                ? of() : entry("receipt.updater", access(request, contract_receipt));
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
        var group = new LinkedHashMap<Receipt, ArrayList<ContractAppointmentReceipt>>();
        for (var carFind : model(ContractAppointmentReceipt.class).finds(criteria, order("receipt.updated DESC NULLS LAST"))) {
            var get = group.get(carFind.getReceipt());
            if (get == null) {
                group.put(carFind.getReceipt(), get = new ArrayList<>());
            }
            get.add(carFind);
        }
        var finds = new ArrayList<Receipt>();
        for (var entry : group.entrySet()) {
            finds.add(entry.getKey());
            entry.getKey().setContractAppointmentReceiptList(entry.getValue());
        }
        attr(request, "finds", finds);
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
