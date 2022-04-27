/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Filter.java to edit this template
 */
package org.realestate.ctrl.filter;

import static javax.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;
import static org.realestate.ctrl.app.ApplicationInstance.model;

import java.io.IOException;
import java.util.Base64;
import java.util.StringTokenizer;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.realestate.ctrl.app.ApplicationException;
import org.realestate.db.entity.Users;

/**
 *
 * @author Pathompong
 */
@WebFilter(filterName = "ApplicationExceptionHandler", urlPatterns = {"/*"})
public class ApplicationExceptionHandler implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            doFilter((HttpServletRequest) request, (HttpServletResponse) response, chain);
        } catch (ClassCastException x) {
            if (request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
                throw x;
            }
            chain.doFilter(request, response);
        }
    }

    private void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            if (request.getHeader("Authorization") != null
                    && request.getSession(false).getAttribute("WWW-Authenticate-realm") != null) {
                var tokenizer = new StringTokenizer(request.getHeader("Authorization"));
                var user = switch (tokenizer.nextToken().toLowerCase()) {
                    case "basic" -> {
                        var entry = new String(Base64.getDecoder().decode(tokenizer.nextToken()), "UTF-8").split(":");
                        var find = model(Users.class).find("code", entry[0]);
                        if (entry[1].hashCode() * find.getSalt() == find.getPassword()) {
                            yield find;
                        } else {
                            yield null;
                        }
                    }
                    default ->
                        null;
                };
                if (user != null) {
                    for (var r : user.getUsersRolesList()) {
                        r.getUsersFuncList().size();
                    }
                    request.getSession(false).invalidate();
                    request.getSession().setAttribute("USER", user);
                }
            }
        } catch (Throwable x) {
        }
        try {
            chain.doFilter(request, response);
        } catch (ApplicationException x) {
            switch (x.getType()) {
                case authentication_required -> {
//                    var referer = request.getHeader("Referer");
//                    if (referer != null && !referer.equals(request.getRequestURL().toString())) {
                    var realm = request.getSession().getAttribute("WWW-Authenticate-realm");
                    if (realm == null) {
                        request.getSession().setAttribute(
                                "WWW-Authenticate-realm",
                                realm = Integer.toString((int) (Math.random() * Integer.MAX_VALUE), Character.MAX_RADIX)
                        );
                    }
                    response.setHeader(
                            "WWW-Authenticate",
                            "BASIC realm=\"" + realm + "\""
                    );
                    response.sendError(SC_UNAUTHORIZED);
                    return;
//                    }
//                    response.sendRedirect(context().getContextPath() + "/login");
//                    return;
                }
            }
            response.sendError(x.getType().status, x.getType().name());
        }
    }
}
