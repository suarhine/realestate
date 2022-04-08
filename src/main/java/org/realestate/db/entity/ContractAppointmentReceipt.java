/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.realestate.db.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author Pathompong
 */
@Entity
@Table(name = "contract_appointment_receipt")
@NamedQueries({
    @NamedQuery(name = "ContractAppointmentReceipt.findAll", query = "SELECT c FROM ContractAppointmentReceipt c")})
public class ContractAppointmentReceipt implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ContractAppointmentReceiptPK pk;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "amount")
    private Double amount;
    @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Contract id;
    @JoinColumns({
        @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false),
        @JoinColumn(name = "type", referencedColumnName = "type", insertable = false, updatable = false),
        @JoinColumn(name = "dating", referencedColumnName = "dating", insertable = false, updatable = false)})
    @OneToOne(optional = false)
    private ContractAppointmentDating dating;
    @JoinColumn(name = "type", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private ContractAppointmentType type;
    @JoinColumn(name = "receipt", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Receipt receipt;

    public ContractAppointmentReceipt() {
    }

    public ContractAppointmentReceipt(ContractAppointmentReceiptPK pk) {
        this.pk = pk;
    }

    public ContractAppointmentReceipt(ContractAppointmentReceiptPK pk, Receipt receipt) {
        this.pk = pk;
        this.receipt = receipt;
    }

    public ContractAppointmentReceipt(int id, int type, Date dating) {
        this.pk = new ContractAppointmentReceiptPK(id, type, dating);
    }

    public ContractAppointmentReceiptPK getPk() {
        return pk;
    }

    public void setPk(ContractAppointmentReceiptPK pk) {
        this.pk = pk;
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

    public ContractAppointmentDating getDating() {
        return dating;
    }

    public void setDating(ContractAppointmentDating dating) {
        this.dating = dating;
    }

    public ContractAppointmentType getType() {
        return type;
    }

    public void setType(ContractAppointmentType type) {
        this.type = type;
    }

    public Receipt getReceipt() {
        return receipt;
    }

    public void setReceipt(Receipt receipt) {
        this.receipt = receipt;
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
        if (!(object instanceof ContractAppointmentReceipt)) {
            return false;
        }
        ContractAppointmentReceipt other = (ContractAppointmentReceipt) object;
        if ((this.pk == null && other.pk != null) || (this.pk != null && !this.pk.equals(other.pk))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ContractAppointmentReceipt[ " + pk + " ]";
    }

}
