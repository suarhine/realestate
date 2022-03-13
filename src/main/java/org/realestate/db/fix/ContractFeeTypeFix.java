/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package org.realestate.db.fix;

import static org.realestate.ctrl.app.ApplicationInstance.model;

import org.realestate.db.entity.ContractFeeType;

/**
 *
 * @author Pathompong
 */
public enum ContractFeeTypeFix {
    monthly(1),
    yearly(2),
    custom(3);
    public final int id;

    private ContractFeeTypeFix(int id) {
        this.id = id;
    }

    public ContractFeeType find() {
        var find = model(ContractFeeType.class).find(id);
        if (find == null) {
            model(ContractFeeType.class).add(find = new ContractFeeType(id, name()));
        }
        return find;
    }
}
