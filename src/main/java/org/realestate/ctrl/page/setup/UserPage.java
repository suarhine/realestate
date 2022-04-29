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
import org.realestate.db.entity.Users;
import org.realestate.db.entity.UsersRoles;
import org.web.ctrl.PageServlet;

/**
 *
 * @author Pathompong
 */
@WebServlet(name = "setup.UserPage", urlPatterns = {"/setup/users/"})
public class UserPage extends HttpServlet implements PageServlet {

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
        access(request, setup_users);
        if (flag(request, "id")) {
            if (flag(request, int.class, "id")) {
                attr(request, "find", notnull(model(Users.class, Integer.class).find(param(request, Integer.class, "id")), "id = " + param(request, "id")));
            }
            if (flag(request, "changepass")) {
                jsp(request, response, "input.changepass.jsp");
                return;
            }
            jsp(request, response, "input.jsp");
        } else {
            attr(request, "finds", model(Users.class).finds(blank(), order("fname, lname, code")));
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
        Users entity;
        if (flag(request, int.class, "id")) {
            entity = notnull(model(Users.class, Integer.class).find(param(request, Integer.class, "id")), "id = " + param(request, "id"));
            if (flag(request, "del")) {
                entity.setUpdated(new Date());
                entity.setUpdater(access(request, setup_users_delete));
                if (model(Users.class, Integer.class).del(true, entity)) {
                    redirect(response, ".");
                } else {
                    throw ApplicationException.Type.uncommited_transaction.dispatch();
                }
                return;
            }
            if (flag(request, "changepass")) {
                var password = require(param(request, String[].class, "password"), 2, "password");
                conflict(password[0], password[1], () -> ApplicationException.Type.verifier_mismatch.dispatch());
                var salt = (int) (Math.random() * Integer.MAX_VALUE);
                entity.setUpdated(new Date());
                entity.setUpdater(access(request, setup_users_changepass));
                entity.setPassword(password[0].hashCode() * salt);
                entity.setSalt(salt);
                return;
            }
        } else {
            var password = require(param(request, String[].class, "password"), 2, "password");
            conflict(password[0], password[1], () -> ApplicationException.Type.verifier_mismatch.dispatch());
            var salt = (int) (Math.random() * Integer.MAX_VALUE);
            entity = new Users();
            entity.setCode(param(request, "code"));
            entity.setPassword(password[0].hashCode() * salt);
            entity.setSalt(salt);
        }
        entity.setUpdated(new Date());
        entity.setUpdater(access(request, setup_users_update));
        entity.setPname(param(request, String.class, "pname"));
        entity.setFname(param(request, String.class, "fname"));
        entity.setLname(param(request, String.class, "lname"));
        entity.setPosit(param(request, String.class, "posit"));
        entity.setActive(flag(request, "active"));
        if (flag(request, "roles")) {
            try {
                entity.setUsersRolesList(list(model(UsersRoles.class, Integer.class).finds(param(request, Integer[].class, "roles"))));
            } catch (NullPointerException x) {
                throw ApplicationException.Type.incomplete_parameter.dispatch(x);
            }
        } else {
            entity.setUsersRolesList(null);
        }
        if (model(Users.class).put(entity)) {
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
