/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package org.realestate.ctrl.page.setup.contract;

import static org.persist.model.Model.Statement.Expression.order;
import static org.realestate.ctrl.app.ApplicationInstance.model;
import static org.realestate.ctrl.app.Commons.*;
import static org.realestate.db.fix.UsersFuncFix.*;

import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.realestate.ctrl.app.ApplicationException;
import org.realestate.db.entity.ContractObjective;
import org.web.ctrl.PageServlet;

import static org.persist.model.Model.Statement.Criteria.of;

/**
 *
 * @author Pathompong
 */
@WebServlet(name = "setup.contract.ContractObjectivePage", urlPatterns = {"/setup/contract/contract_objective"})
public class ContractObjectivePage extends HttpServlet implements PageServlet {

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
        access(request, setup_lookup);
        if (flag(request, "id")) {
            if (flag(request, Integer.class, "id")) {
                attr(request, "find", notnull(model(ContractObjective.class, Integer.class).find(param(request, Integer.class, "id")), "id = " + param(request, "id")));
            }
            jsp(request, response, ".input.jsp");
        } else {
            attr(request, "finds", model(ContractObjective.class).finds(of(), order("id")));
            jsp(request, response);
        }
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
        ContractObjective entity;
        if (flag(request, Integer.class, "id")) {
            entity = notnull(model(ContractObjective.class, Integer.class).find(param(request, Integer.class, "id")), "id = " + param(request, "id"));
            if (flag(request, "del")) {
                entity.setUpdated(new Date());
                entity.setUpdater(access(request, setup_lookup_delete));
                if (model(ContractObjective.class, Integer.class).del(true, entity)) {
                    redirect(response, "contract_type");
                    return;
                } else {
                    throw ApplicationException.Type.uncommited_transaction.dispatch();
                }
            }
        } else {
            entity = new ContractObjective();
        }
        entity.setUpdated(new Date());
        entity.setUpdater(access(request, setup_lookup_update));
        entity.setCode(param(request, String.class, "code"));
        entity.setLabel(param(request, String.class, "label"));
        entity.setDesc(param(request, String.class, "desc"));
        entity.setAdditional(param(request, Boolean.class, "additional"));
        entity.setActive(param(request, Boolean.class, "active"));
        if (model(ContractObjective.class, Integer.class).put(entity)) {
            redirect(response, "contract_type");
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