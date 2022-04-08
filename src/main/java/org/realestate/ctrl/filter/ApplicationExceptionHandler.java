/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Filter.java to edit this template
 */
package org.realestate.ctrl.filter;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import org.realestate.ctrl.app.ApplicationException;

/**
 *
 * @author Pathompong
 */
@WebFilter(filterName = "ApplicationExceptionHandler", urlPatterns = {"/*"})
public class ApplicationExceptionHandler implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            chain.doFilter(request, response);
        } catch (ApplicationException x) {
            try {
                ((HttpServletResponse) response).sendError(x.getType().status, x.getType().name());
                return;
            } catch (Throwable x2) {
            }
            throw x;
        }
    }
}
