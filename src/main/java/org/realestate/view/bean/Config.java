/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.realestate.view.bean;

import static org.realestate.ctrl.app.ApplicationInstance.context;

/**
 *
 * @author Pathompong
 */
public class Config {

    public String getRoot() {
        return context().getContextPath();
    }
}
