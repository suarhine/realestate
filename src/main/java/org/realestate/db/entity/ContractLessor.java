/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.realestate.db.entity;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Pathompong
 */
@Entity
@Table(name = "contract_lessor")
@NamedQueries({
    @NamedQuery(name = "ContractLessor.findAll", query = "SELECT c FROM ContractLessor c")})
public class ContractLessor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
//    @Basic(optional = false)
    @NotNull
//    @Column(name = "id")
    @JoinColumn(name = "id", referencedColumnName = "id")
    @OneToOne(optional = false)
//    private Integer id;
    private Contract id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "name")
    private String name;
    @Size(max = 2147483647)
    @Column(name = "role")
    private String role;
//    @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false)
//    @OneToOne(optional = false)
//    private Contract contract;

    public ContractLessor() {
    }
//
//    public ContractLessor(Integer id) {
//        this.id = id;
//    }
//
//    public ContractLessor(Integer id, String name) {
//        this.id = id;
//        this.name = name;
//    }

    public ContractLessor(Contract id) {
        this.id = id;
    }

    public ContractLessor(Contract id, String name) {
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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
        if (!(object instanceof ContractLessor)) {
            return false;
        }
        ContractLessor other = (ContractLessor) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.realestate.db.entity.ContractLessor[ id=" + id + " ]";
    }

}
