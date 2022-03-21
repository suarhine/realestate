/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.realestate.db.entity;

import java.io.Serializable;
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
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Size(max = 2147483647)
    @Column(name = "code")
    private String code;
    @Size(max = 2147483647)
    @Column(name = "label")
    private String label;
    @Size(max = 2147483647)
    @Column(name = "\"desc\"")
    private String desc;
    @Column(name = "active")
    private Boolean active;
    @Column(name = "labelable")
    private Boolean labelable;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "type")
    private List<ContractAppointmentDating> contractAppointmentDatingList;

    public ContractAppointmentType() {
    }

    public ContractAppointmentType(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Boolean getLabelable() {
        return labelable;
    }

    public void setLabelable(Boolean labelable) {
        this.labelable = labelable;
    }

    public List<ContractAppointmentDating> getContractAppointmentList() {
        return contractAppointmentDatingList;
    }

    public void setContractAppointmentList(List<ContractAppointmentDating> contractAppointmentDatingList) {
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
