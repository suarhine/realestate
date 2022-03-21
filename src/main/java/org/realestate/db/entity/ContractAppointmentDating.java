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
@Table(name = "contract_appointment_dating")
@NamedQueries({
    @NamedQuery(name = "ContractAppointmentDating.findAll", query = "SELECT c FROM ContractAppointmentDating c")})
public class ContractAppointmentDating implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ContractAppointmentDatingPK pk;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "amount")
    private Double amount;
    @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Contract id;
    @JoinColumn(name = "type", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private ContractAppointmentType type;

    public ContractAppointmentDating() {
    }

    public ContractAppointmentDating(ContractAppointmentDatingPK pk) {
        this.pk = pk;
    }

    public ContractAppointmentDating(int id, int type, Date dating) {
        this.pk = new ContractAppointmentDatingPK(id, type, dating);
    }

    public ContractAppointmentDating(Contract id, ContractAppointmentType type, Date dating) {
        try {
            this.pk = new ContractAppointmentDatingPK(id.getId(), type.getId(), dating);
        } catch (NullPointerException x) {
            if (id == null || id.getId() != null) {
                throw x;
            }
            this.pk = new ContractAppointmentDatingPK(0, type.getId(), dating);
        }
        this.id = id;
        this.type = type;
    }

    public ContractAppointmentDating(Contract id, ContractAppointmentType type, Date dating, Double amount) {
        this(id, type, dating);
        this.amount = amount;
    }

    public ContractAppointmentDatingPK getPk() {
        return pk;
    }

    public void setPk(ContractAppointmentDatingPK pk) {
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

    public ContractAppointmentType getType() {
        return type;
    }

    public void setType(ContractAppointmentType type) {
        this.type = type;
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
        if (!(object instanceof ContractAppointmentDating)) {
            return false;
        }
        ContractAppointmentDating other = (ContractAppointmentDating) object;
        if ((this.pk == null && other.pk != null) || (this.pk != null && !this.pk.equals(other.pk))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ContractAppointmentDating[ " + pk + " ]";
    }

}
