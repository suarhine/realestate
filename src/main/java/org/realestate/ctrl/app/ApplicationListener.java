/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.realestate.ctrl.app;

import static org.realestate.ctrl.app.ApplicationInstance.*;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Web application lifecycle listener.
 *
 * @author Pathompong
 */
@WebListener
public class ApplicationListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        if (context != null) {
            throw new IllegalStateException("already deployed");
        }
        context = event.getServletContext();
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        model().close();
        context = null;
        System.gc();
    }
}
