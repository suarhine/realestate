/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package org.realestate.ctrl.page.setup;

import static org.persist.model.Model.Statement.Criteria.blank;
import static org.persist.model.Model.Statement.Expression.order;
import static org.realestate.ctrl.app.ApplicationInstance.model;
import static org.realestate.ctrl.app.Commons.*;
import static org.realestate.db.fix.UsersFuncFix.*;
import static org.reflex.invoke.functional.Functional.list;

import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.realestate.ctrl.app.ApplicationException;
import org.realestate.db.entity.UsersFunc;
import org.realestate.db.entity.UsersRoles;
import org.web.ctrl.PageServlet;

/**
 *
 * @author Pathompong
 */
@WebServlet(name = "setting.UsersRolesPage", urlPatterns = {"/setup/users/roles/"})
public class UsersRolesPage extends HttpServlet implements PageServlet {

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
        access(request, setup_roles);
        if (flag(request, "id")) {
            if (flag(request, int.class, "id")) {
                attr(request, "find", notnull(model(UsersRoles.class, Integer.class).find(param(request, Integer.class, "id")), "id = " + param(request, "id")));
            }
            jsp(request, response, "input.jsp");
        } else {
            attr(request, "finds", model(UsersRoles.class).finds(blank(), order("label, code")));
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
        UsersRoles entity;
        if (flag(request, Integer.class, "id")) {
            entity = notnull(model(UsersRoles.class, Integer.class)
                    .find(param(request, Integer.class, "id")), "id = " + param(request, "id"));
            if (flag(request, "del")) {
                entity.setUpdated(new Date());
                entity.setUpdater(access(request, setup_roles_delete));
                if (model(UsersRoles.class, Integer.class).del(true, entity)) {
                    redirect(response, ".");
                } else {
                    throw ApplicationException.Type.uncommited_transaction.dispatch();
                }
                return;
            }
        } else {
            entity = new UsersRoles();
        }
        entity.setUpdated(new Date());
        entity.setUpdater(access(request, setup_roles_update));
        entity.setCode(param(request, String.class, "code"));
        entity.setLabel(param(request, String.class, "label"));
        entity.setDesc(param(request, String.class, "desc"));
        entity.setActive(flag(request, "active"));
        if (flag(request, "func")) {
            try {
                entity.setUsersFuncList(list(model(UsersFunc.class, Integer.class).finds(param(request, Integer[].class, "func"))));
            } catch (NullPointerException x) {
                throw ApplicationException.Type.incomplete_parameter.dispatch(x);
            }
        } else {
            entity.setUsersFuncList(null);
        }
        if (model(UsersRoles.class).put(entity)) {
            redirect(response, ".");
        } else {
            throw ApplicationException.Type.uncommited_transaction.dispatch();
        }
    }

    /**
     * Returns a short description blank the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
