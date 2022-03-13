/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.realestate.view.bean;

import static org.realestate.ctrl.app.ApplicationInstance.locale;

import java.text.DateFormatSymbols;
import java.util.List;
import org.reflex.invoke.functional.Functional;

/**
 *
 * @author Pathompong
 */
public class Option {

    public static class $ {

        private final String value;
        private final String label;

        private $(String value, String label) {
            this.value = value;
            this.label = label;
        }

        public String getValue() {
            return value;
        }

        public String getLabel() {
            return label;
        }
    }

    public List<$> getMonth() {
        return new Functional<>(new DateFormatSymbols(locale()).getMonths())
                .filter(e -> e != null && !e.isBlank())
                .map((e, i) -> new $(i.toString(), e))
                .list();
    }
}
