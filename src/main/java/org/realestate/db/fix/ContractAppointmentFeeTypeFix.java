/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package org.realestate.db.fix;

import static org.realestate.ctrl.app.ApplicationInstance.model;

import org.realestate.db.entity.ContractAppointmentFeeType;

/**
 *
 * @author Pathompong
 */
public enum ContractAppointmentFeeTypeFix {
    monthly(1),
    yearly(2),
    custom(3);
    public final int id;

    private ContractAppointmentFeeTypeFix(int id) {
        this.id = id;
    }

    public ContractAppointmentFeeType find() {
        var find = model(ContractAppointmentFeeType.class).find(id);
        if (find == null) {
            model(ContractAppointmentFeeType.class).add(find = new ContractAppointmentFeeType(id, name(), name()));
        }
        return find;
    }
}
