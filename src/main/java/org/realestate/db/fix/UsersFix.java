/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package org.realestate.db.fix;

import static org.realestate.ctrl.app.ApplicationInstance.model;

import java.util.Date;
import org.realestate.db.entity.Users;

/**
 *
 * @author Pathompong
 */
public enum UsersFix {
    system;

    public Users find() {
        var find = model(Users.class).find("code", name());
        if (find == null) {
            if (this == system) {
                model().sql(q -> {
                    return q.executeUpdate() == 1;
                }, """
                INSERT INTO users (updated, updater, code) VALUES (now(), currval('users_id_seq'), ?)
                """, name());
                return find();
            } else {
                find = new Users();
                find.setUpdated(new Date());
                find.setUpdater(system.find());
                find.setCode(name());
            }
        }
        return find;
    }

}
