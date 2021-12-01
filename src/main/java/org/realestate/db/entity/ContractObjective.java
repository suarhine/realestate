/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.realestate.db.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Pathompong
 */
@Entity
@Table(name = "contract_objective")
@NamedQueries({
    @NamedQuery(name = "ContractObjective.findAll", query = "SELECT c FROM ContractObjective c")})
public class ContractObjective implements Serializable {

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
    @Size(max = 2147483647)
    @Column(name = "code")
    private String code;
    @Size(max = 2147483647)
    @Column(name = "label")
    private String label;
    @Size(max = 2147483647)
    @Column(name = "desc")
    private String desc;
    @Column(name = "active")
    private Boolean active;
    @Column(name = "additional")
    private Boolean additional;

    public ContractObjective() {
    }

    public ContractObjective(Integer id) {
        this.id = id;
    }

    public ContractObjective(Integer id, Date updated, int updater) {
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getAdditional() {
        return additional;
    }

    public void setAdditional(Boolean additional) {
        this.additional = additional;
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
        if (!(object instanceof ContractObjective)) {
            return false;
        }
        ContractObjective other = (ContractObjective) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.realestate.db.entity.ContractObjective[ id=" + id + " ]";
    }

}
