/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.realestate.ctrl.page.contract;

import static org.realestate.ctrl.app.ApplicationInstance.model;
import static org.realestate.ctrl.app.Commons.notnull;
import static org.web.jsp.fn.Functions.parse;

import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.persist.model.Model;
import org.realestate.ctrl.app.ApplicationException;
import org.realestate.db.entity.*;
import org.reflex.invoke.functional.Functional;
import org.web.ctrl.DefaultPage;

/**
 *
 * @author Pathompong
 */
@WebServlet(name = "contract.IndexPage", urlPatterns = {"/contract/"})
public class IndexPage extends HttpServlet implements DefaultPage {

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    @SuppressWarnings("null")
    protected void doGet(
            HttpServletRequest request, HttpServletResponse response
    ) throws ServletException, IOException {
        if (flag(request, "id")) {
            request.setAttribute("find", notnull(model(Contract.class).find(param(request, Integer.class, "id")), "id = " + param(request, "id")));
            jsp(request, response, "input.jsp");
        } else {
            Model.Statement.Criteria criteria = null;
            if (flag(request, String.class, "q")) {
                for (var q : param(request, "q").split("\\s+")) {
                    Model.Statement.Criteria subcriteria = null;
                    for (var field : new String[]{
                        "code",
                        "contractLessee.name",
                        "contractLessee.representative",
                        "contractRealestate.name"
                    }) {
                        try {
                            subcriteria = subcriteria.or(field, "LIKE", "%" + q + "%");
                        } catch (NullPointerException x) {
                            subcriteria = Model.Statement.Criteria.entry(field, "LIKE", "%" + q + "%");
                        }
                    }
                    try {
                        criteria = criteria.and(subcriteria);
                    } catch (NullPointerException x) {
                        criteria = subcriteria;
                    }
                }
            }
//            model(Contract.class).f
            request.setAttribute("finds", model(Contract.class).finds(criteria));
            jsp(request, response);
        }
    }

    public <T> T param(HttpServletRequest request, Class<T> type, String name, Object... options) {
        if (options != null && options.length > 0) {
            return parse(type, new Functional<>((Object) param(request, name)).join(options).values());
        }
        return parse(type, param(request, name));
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
        var entity = flag(request, Integer.class, "id")
                ? notnull(model(Contract.class).find(param(request, Integer.class, "id")), "id = " + request.getParameter("id"))
                : new Contract();
        entity.setUpdated(new Date());
        entity.setUpdater(1);
        entity.setType(param(request, Integer.class, "type"));
        entity.setCode(param(request, "code"));
        entity.setDated(param(request, Date.class, "dated", "yyyy-MM-dd"));
        entity.setNote(param(request, "note"));
        entity.setNoteDated(param(request, Date.class, "note_dated", "yyyy-MM-dd"));
        entity.setObjective(param(request, Integer.class, "objective"));
        entity.setObjectiveText(param(request, "objective_text"));
        entity.setStarted(param(request, Date.class, "started", "yyyy-MM-dd"));
        entity.setEnded(param(request, Date.class, "ended", "yyyy-MM-dd"));

        entity.setContractLessor(new ContractLessor(entity, param(request, "lessor.name")));
        entity.getContractLessor().setName(param(request, "lessor.role"));

        entity.setContractLessee(new ContractLessee(entity, param(request, "lessee.name")));
        entity.getContractLessee().setCode(param(request, "lessee.code"));
        entity.getContractLessee().setRepresentative(param(request, "lessee.representative"));
        entity.getContractLessee().setRepresentativeRole(param(request, "lessee.representative_role"));

        entity.setContractRealestate(new ContractRealestate(entity));
        entity.getContractRealestate().setName(param(request, "realestate.name"));
        entity.getContractRealestate().setType(param(request, Integer.class, "realestate.type"));
        entity.getContractRealestate().setCode(param(request, "realestate.code"));
        entity.getContractRealestate().setLocation(param(request, "realestate.location"));
        entity.getContractRealestate().setNearby(param(request, "realestate.nearby"));
        entity.getContractRealestate().setHouse(param(request, "realestate.house"));
        entity.getContractRealestate().setVillage(param(request, "realestate.village"));
        entity.getContractRealestate().setSoi(param(request, "realestate.soi"));
        entity.getContractRealestate().setRoad(param(request, "realestate.road"));
        entity.getContractRealestate().setSubdistrict(param(request, "realestate.subdistrict"));
        entity.getContractRealestate().setDistrict(param(request, "realestate.district"));
        entity.getContractRealestate().setProvince(param(request, "realestate.province"));
        entity.getContractRealestate().setDeedCode(param(request, "realestate.deed_code"));
        entity.getContractRealestate().setDeedNo(param(request, "realestate.deed_no"));
        entity.getContractRealestate().setSpaceRai(param(request, Integer.class, "realestate.space_rai"));
        entity.getContractRealestate().setSpaceNgan(param(request, Integer.class, "realestate.space_ngan"));
        entity.getContractRealestate().setSpaceSqwah(param(request, Double.class, "realestate.space_sqwah"));
        if (model(Contract.class).put(entity)) {
            response.sendRedirect(".");
        } else {
            throw ApplicationException.Type.internal_server_error.dispatch("", null);
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
