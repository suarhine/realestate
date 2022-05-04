/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package org.realestate.db.fix;

import static org.realestate.ctrl.app.ApplicationInstance.model;

import java.util.ArrayList;
import org.realestate.db.entity.UsersFunc;

/**
 *
 * @author Pathompong
 */
public enum UsersFuncFix {
    contract(1),
    contract_create(2, contract),
    contract_read(3, contract),
    contract_update(4, contract_read, contract_create),
    contract_delete(5, contract),
    contract_revoke(6, contract),
    contract_revoke_cancel(7, contract),
    contract_receipt(8),
    contract_receipt_create(9),
    contract_receipt_read(10),
    setup(11),
    setup_users(12, setup),
    setup_users_update(13, setup_users),
    setup_users_delete(14, setup_users),
    setup_users_changepass(15, setup_users),
    setup_roles(16, setup),
    setup_roles_update(17, setup_roles),
    setup_roles_delete(18, setup_roles),
    setup_lookup(19, setup),
    setup_lookup_update(20, setup_lookup),
    setup_lookup_delete(21, setup_lookup);
    public final int id;
    private final ArrayList<UsersFuncFix> applied = new ArrayList<>();

    private UsersFuncFix(int id, UsersFuncFix... inherited) {
        this.id = id;
        try {
            for (var r : inherited) {
                r.applied.add(this);
            }
        } catch (NullPointerException x) {
        }
    }

    public UsersFunc find() {
        var find = model(UsersFunc.class).find(id);
        if (find == null) {
            model(UsersFunc.class).add(find = new UsersFunc(id, name(), name()));
        }
        return find;
    }

    public boolean allow(int id) {
        if (id == this.id) {
            return true;
        }
        for (var r : applied) {
            if (r.allow(id)) {
                return true;
            }
        }
        return false;
    }
}
