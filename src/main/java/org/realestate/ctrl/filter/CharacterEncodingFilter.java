/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.realestate.ctrl.filter;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Pathompong
 */
@WebFilter(filterName = "CharacterEncodingFilter", urlPatterns = {"/*"})
public class CharacterEncodingFilter implements Filter {

    private static final String PAGE_CHARSET = "UTF-8";

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain
    ) throws IOException, ServletException {
        try {
            doFilter((HttpServletRequest) request, (HttpServletResponse) response);
        } catch (ClassCastException x) {
        }
        chain.doFilter(request, response);
    }

    public void doFilter(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException, ServletException {
        if (request.getMethod().matches("POST|PUT|DELETE")) {
            request.setCharacterEncoding(PAGE_CHARSET);
        }
        if (!request.getRequestURI().matches(".+[.]css|.+[.]m?js")) {
            response.setCharacterEncoding(PAGE_CHARSET);
        }
    }
}
