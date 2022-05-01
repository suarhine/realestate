/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.realestate.view.tld;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.List;
import org.realestate.db.entity.ContractAppointmentDating;
import org.reflex.invoke.functional.Functional;

/**
 *
 * @author Pathompong
 */
public class ReceiptTld {

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

    public static Double calculateContractAppointmentDatingFine(ContractAppointmentDating value) {
        try {
            var period = Period.between(
                    LocalDate.from(value.getPk().getDating().toInstant().atZone(ZoneId.systemDefault())),
                    LocalDate.now()
            );
            if (period.isNegative() || period.isZero()) {
                return null;
            }
            var month = period.getYears() * 12 + period.getMonths() + (period.getDays() == 0 ? 0 : 1);
            return value.getId().getContractPlan().getFinerate() * value.getAmount() * month / 100d;
        } catch (NullPointerException x) {
            return null;
        }
    }

    public static Double summaryContractAppointmentDatingFine(List<ContractAppointmentDating> values) {
        return new Functional<>(values)
                .map(ReceiptTld::calculateContractAppointmentDatingFine)
                .filter(e -> e != null)
                .reduce((p, e) -> p + e);
    }
}
