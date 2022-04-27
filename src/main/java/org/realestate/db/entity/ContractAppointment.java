/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.realestate.db.entity;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 *
 * @author Pathompong
 */
@Entity
@Table(name = "contract_appointment")
@NamedQueries({
    @NamedQuery(name = "ContractAppointment.findAll", query = "SELECT c FROM ContractAppointment c")})
public class ContractAppointment implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ContractAppointmentPK pk;
    @Size(max = 2147483647)
    @Column(name = "label")
    private String label;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "amount")
    private Double amount;
    @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Contract id;
    @JoinColumn(name = "fee_type", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ContractAppointmentFeeType feeType;
    @JoinColumn(name = "type", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private ContractAppointmentType type;

    public ContractAppointment() {
    }

    public ContractAppointment(ContractAppointmentPK pk) {
        this.pk = pk;
    }

    public ContractAppointment(int id, int type) {
        this.pk = new ContractAppointmentPK(id, type);
    }

    public ContractAppointment(Contract id, ContractAppointmentType type) {
        try {
            this.pk = new ContractAppointmentPK(id.getId(), type.getId());
        } catch (NullPointerException x) {
            if (id == null || id.getId() != null) {
                throw x;
            }
            this.pk = new ContractAppointmentPK(0, type.getId());
        }
        this.id = id;
        this.type = type;
    }

    public ContractAppointment(Contract id, ContractAppointmentType type, ContractAppointmentFeeType feeType, String label, Double amount) {
        this(id, type);
        this.feeType = feeType;
        this.label = label;
        this.amount = amount;
    }

    public ContractAppointmentPK getPk() {
        return pk;
    }

    public void setPk(ContractAppointmentPK pk) {
        this.pk = pk;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Contract getId() {
        return id;
    }

    public void setId(Contract id) {
        this.id = id;
    }

    public ContractAppointmentType getType() {
        return type;
    }

    public void setType(ContractAppointmentType type) {
        this.type = type;
    }

    public ContractAppointmentFeeType getFeeType() {
        return feeType;
    }

    public void setFeeType(ContractAppointmentFeeType feeType) {
        this.feeType = feeType;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pk != null ? pk.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ContractAppointment)) {
            return false;
        }
        ContractAppointment other = (ContractAppointment) object;
        if ((this.pk == null && other.pk != null) || (this.pk != null && !this.pk.equals(other.pk))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ContractAppointment[ " + pk + " ]";
    }

}
