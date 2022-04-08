/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.realestate.db.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Pathompong
 */
@Entity
@Table(name = "receipt")
@NamedQueries({
    @NamedQuery(name = "Receipt.findAll", query = "SELECT r FROM Receipt r")})
public class Receipt implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "updated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;
    @Basic(optional = false)
    @NotNull
    @Column(name = "updater")
    private int updater;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "receipt")
    private List<ContractAppointmentReceipt> contractAppointmentReceiptList;

    public Receipt() {
    }

    public Receipt(Integer id) {
        this.id = id;
    }

    public Receipt(Integer id, Date updated, int updater) {
        this.id = id;
        this.updated = updated;
        this.updater = updater;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public int getUpdater() {
        return updater;
    }

    public void setUpdater(int updater) {
        this.updater = updater;
    }

    public List<ContractAppointmentReceipt> getContractAppointmentReceiptList() {
        return contractAppointmentReceiptList;
    }

    public void setContractAppointmentReceiptList(List<ContractAppointmentReceipt> contractAppointmentReceiptList) {
        this.contractAppointmentReceiptList = contractAppointmentReceiptList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Receipt)) {
            return false;
        }
        Receipt other = (Receipt) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Receipt[ id=" + id + " ]";
    }

}
