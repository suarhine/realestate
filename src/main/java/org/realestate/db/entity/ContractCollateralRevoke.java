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
@Entity
@Table(name = "contract_collateral_revoke")
@NamedQueries({
    @NamedQuery(name = "ContractCollateralRevoke.findAll", query = "SELECT c FROM ContractCollateralRevoke c")})
public class ContractCollateralRevoke implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
//    @Basic(optional = false)
    @NotNull
//    @Column(name = "id")
//    private Integer id;
    @JoinColumn(name = "id", referencedColumnName = "id")
    @OneToOne(optional = false)
    private Contract id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "updated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;
//    @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false)
//    @OneToOne(optional = false)
//    private Contract contract;
    @JoinColumn(name = "updater", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Users updater;

    public ContractCollateralRevoke() {
    }
//
//    public ContractCollateralRevoke(Integer id) {
//        this.id = id;
//    }

    public ContractCollateralRevoke(Contract id) {
        this.id = id;
    }
//
//    public ContractCollateralRevoke(Integer id, Date updated) {
//        this.id = id;
//        this.updated = updated;
//    }

    public ContractCollateralRevoke(Contract id, Date updated) {
        this.id = id;
        this.updated = updated;
    }
//
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }

    public Contract getId() {
        return id;
    }

    public void setId(Contract id) {
        this.id = id;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
//
//    public Contract getContract() {
//        return contract;
//    }
//
//    public void setContract(Contract contract) {
//        this.contract = contract;
//    }

    public Users getUpdater() {
        return updater;
    }

    public void setUpdater(Users updater) {
        this.updater = updater;
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
        if (!(object instanceof ContractCollateralRevoke)) {
            return false;
        }
        ContractCollateralRevoke other = (ContractCollateralRevoke) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ContractCollateralRevoke[ id=" + id + " ]";
    }

}
