/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.realestate.view.bean;

import static org.persistence.model.Model.Statement.Criteria.entry;
import static org.persistence.model.Model.Statement.Expression.order;
import static org.realestate.ctrl.app.ApplicationInstance.model;

import java.util.List;
import org.realestate.db.entity.*;

/**
 *
 * @author Pathompong
 */
public class Lookup {

    public List<ContractType> getContractType() {
        return model(ContractType.class).finds(entry("active", true), order("id"));
    }

    public List<ContractAppointmentType> getContractAppointmentType() {
        return model(ContractAppointmentType.class).finds(entry("active", true), order("id"));
    }

    public List<ContractAppointmentFeeType> getContractAppointmentFeeType() {
        return model(ContractAppointmentFeeType.class).finds(entry("active", true), order("id"));
    }

    public List<ContractRealestateType> getContractRealestateType() {
        return model(ContractRealestateType.class).finds(entry("active", true), order("id"));
    }

    public List<ContractObjective> getContractObjective() {
        return model(ContractObjective.class).finds(entry("active", true), order("id"));
    }
}
