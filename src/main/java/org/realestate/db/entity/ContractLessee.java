/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
@Table(name = "contract_lessee")
@NamedQueries({
    @NamedQuery(name = "ContractLessee.findAll", query = "SELECT c FROM ContractLessee c")})
public class ContractLessee implements Serializable {

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
    @Size(min = 1, max = 2147483647)
    @Column(name = "name")
    private String name;
    @Size(max = 2147483647)
    @Column(name = "representative")
    private String representative;
    @Size(max = 2147483647)
    @Column(name = "representative_role")
    private String representativeRole;
    @Size(max = 2147483647)
    @Column(name = "code")
    private String code;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contractLessee")
    private List<ContractLesseeAddress> contractLesseeAddressList;
//    @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false)
//    @OneToOne(optional = false)
//    private Contract contract;

    public ContractLessee() {
    }
//
//    public ContractLessee(Integer id) {
//        this.id = id;
//    }
//
//    public ContractLessee(Integer id, String name) {
//        this.id = id;
//        this.name = name;
//    }

    public ContractLessee(Contract id) {
        this.id = id;
    }

    public ContractLessee(Contract id, String name) {
        this.id = id;
        this.name = name;
    }

    public Contract getId() {
        return id;
    }

    public void setId(Contract id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRepresentative() {
        return representative;
    }

    public void setRepresentative(String representative) {
        this.representative = representative;
    }

    public String getRepresentativeRole() {
        return representativeRole;
    }

    public void setRepresentativeRole(String representativeRole) {
        this.representativeRole = representativeRole;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<ContractLesseeAddress> getContractLesseeAddressList() {
        return contractLesseeAddressList;
    }

    public void setContractLesseeAddressList(List<ContractLesseeAddress> contractLesseeAddressList) {
        this.contractLesseeAddressList = contractLesseeAddressList;
    }
//
//    public Contract getContract() {
//        return contract;
//    }
//
//    public void setContract(Contract contract) {
//        this.contract = contract;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ContractLessee)) {
            return false;
        }
        ContractLessee other = (ContractLessee) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.realestate.db.entity.ContractLessee[ id=" + id + " ]";
    }

}
