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
import org.realestate.db.fix.ContractFeeTypeFix;
import org.realestate.db.fix.UsersFuncFix;

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
        new Thread(() -> {
            try {
                for (var value : ContractFeeTypeFix.values()) {
                    value.find();
                }
                for (var value : UsersFuncFix.values()) {
                    value.find();
                }
            } catch (Throwable x) {
            }
        }).start();
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        model().close();
        context = null;
        System.gc();
    }
}
