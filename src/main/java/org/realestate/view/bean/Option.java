/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.realestate.view.bean;

import static org.realestate.ctrl.app.ApplicationInstance.*;

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

    public List<String> getContractRealestateLocation() {
        return model().jpql(
                query -> query.getResultList(),
                String.class,
                "SELECT DISTINCT e.location FROM ContractRealestate e WHERE e.location IS NOT NULL AND TRIM(e.location) != '' ORDER BY e.location"
        );
    }

    public List<String> getContractRealestateAddressSubdistrict() {
        return model().jpql(
                query -> query.getResultList(),
                String.class,
                "SELECT DISTINCT e.address.subdistrict FROM ContractRealestate e WHERE e.address.subdistrict IS NOT NULL AND TRIM(e.address.subdistrict) != '' ORDER BY e.address.subdistrict"
        );
    }
}
