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
import javax.validation.constraints.Size;

/**
 *
 * @author Pathompong
 */
@Entity
@Table(name = "contract_appointment_type")
@NamedQueries({
    @NamedQuery(name = "ContractAppointmentType.findAll", query = "SELECT c FROM ContractAppointmentType c")})
public class ContractAppointmentType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "updated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;
    @Size(max = 2147483647)
    @Column(name = "code")
    private String code;
    @Size(max = 2147483647)
    @Column(name = "label")
    private String label;
    @Size(max = 2147483647)
    @Column(name = "\"desc\"")
    private String desc;
    @Column(name = "additional")
    private Boolean additional;
    @Column(name = "active")
    private Boolean active;
    @JoinColumn(name = "updater", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Users updater;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "type")
    private List<ContractAppointmentDating> contractAppointmentDatingList;

    public ContractAppointmentType() {
    }

    public ContractAppointmentType(Integer id) {
        this.id = id;
    }

    public ContractAppointmentType(Integer id, Date updated) {
        this.id = id;
        this.updated = updated;
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

    public Boolean getAdditional() {
        return additional;
    }

    public void setAdditional(Boolean additional) {
        this.additional = additional;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Users getUpdater() {
        return updater;
    }

    public void setUpdater(Users updater) {
        this.updater = updater;
    }

    public List<ContractAppointmentDating> getContractAppointmentDatingList() {
        return contractAppointmentDatingList;
    }

    public void setContractAppointmentDatingList(List<ContractAppointmentDating> contractAppointmentDatingList) {
        this.contractAppointmentDatingList = contractAppointmentDatingList;
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
        if (!(object instanceof ContractAppointmentType)) {
            return false;
        }
        ContractAppointmentType other = (ContractAppointmentType) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ContractAppointmentType[ id=" + id + " ]";
    }

}
