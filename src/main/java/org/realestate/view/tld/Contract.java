/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.realestate.view.tld;

import static org.web.jsp.fn.Functions.format;

import java.util.List;
import org.realestate.db.entity.ContractAppointment;
import org.realestate.db.entity.ContractAppointmentDating;
import org.realestate.db.entity.ContractAppointmentType;
import org.reflex.invoke.functional.Functional;

/**
 *
 * @author Pathompong
 */
public class Contract {

    public static ContractAppointment getContractAppointmentByContractAppointmentType(
            List<ContractAppointment> values,
            ContractAppointmentType type
    ) {
        try {
            for (var value : values) {
                if (value.getType().equals(type)) {
                    return value;
                }
            }
        } catch (NullPointerException x) {
        }
        return null;
    }

    public static List<ContractAppointmentDating> getContractAppointmentDatingByContractAppointmentType(
            List<ContractAppointmentDating> values,
            ContractAppointmentType type
    ) {
        try {
            return new Functional<>(values).filter(e -> e.getType().equals(type)).list();
        } catch (NullPointerException x) {
        }
        return null;
    }

    public static StringBuilder toContractAppointmentDatingJson(List<ContractAppointmentDating> values) {
        var builder = new StringBuilder();
        try {
            for (var value : values) {
                builder.append(", {dating: \"").append(
                        format(value.getPk().getDating(), "yyyy-MM-dd")
                ).append("\", amount: ").append(value.getAmount()).append("}");
            }
        } catch (NullPointerException x) {
        }
        return builder.delete(0, 2).insert(0, '[').append(']');
    }

    public static Double summaryContractAppointmentDatingAmount(List<ContractAppointmentDating> values) {
        try {
            double amount = 0;
            for (var value : values) {
                try {
                    amount += value.getAmount();
                } catch (NullPointerException x) {
                }
            }
            return amount;
        } catch (NullPointerException x) {
            return null;
        }
    }
}
