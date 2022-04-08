/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.realestate.db.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Pathompong
 */
@Embeddable
public class ContractAppointmentReceiptPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private int id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "type")
    private int type;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dating")
    @Temporal(TemporalType.DATE)
    private Date dating;

    public ContractAppointmentReceiptPK() {
    }

    public ContractAppointmentReceiptPK(int id, int type, Date dating) {
        this.id = id;
        this.type = type;
        this.dating = dating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Date getDating() {
        return dating;
    }

    public void setDating(Date dating) {
        this.dating = dating;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        hash += (int) type;
        hash += (dating != null ? dating.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ContractAppointmentReceiptPK)) {
            return false;
        }
        ContractAppointmentReceiptPK other = (ContractAppointmentReceiptPK) object;
        if (this.id != other.id) {
            return false;
        }
        if (this.type != other.type) {
            return false;
        }
        if ((this.dating == null && other.dating != null) || (this.dating != null && !this.dating.equals(other.dating))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ContractAppointmentReceiptPK[ id=" + id + ", type=" + type + ", dating=" + dating + " ]";
    }

}
