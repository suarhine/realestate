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
@Table(name = "contract_fee_type")
@NamedQueries({
    @NamedQuery(name = "ContractFeeType.findAll", query = "SELECT c FROM ContractFeeType c")})
public class ContractFeeType implements Serializable {

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
    @OneToMany(mappedBy = "commissionFeeType")
    private List<Contract> contractList;
    @OneToMany(mappedBy = "installmentRentalFeeType")
    private List<Contract> contractList1;
    @OneToMany(mappedBy = "periodRentalFeeType")
    private List<Contract> contractList2;
    @OneToMany(mappedBy = "rentalFeeType")
    private List<Contract> contractList3;
    @OneToMany(mappedBy = "utilizationFeeType")
    private List<Contract> contractList4;

    public ContractFeeType() {
    }

    public ContractFeeType(Integer id) {
        this.id = id;
    }

    public ContractFeeType(int id, String code) {
        this.id = id;
        this.code = code;
        this.label = code;
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

    public List<Contract> getContractList() {
        return contractList;
    }

    public void setContractList(List<Contract> contractList) {
        this.contractList = contractList;
    }

    public List<Contract> getContractList1() {
        return contractList1;
    }

    public void setContractList1(List<Contract> contractList1) {
        this.contractList1 = contractList1;
    }

    public List<Contract> getContractList2() {
        return contractList2;
    }

    public void setContractList2(List<Contract> contractList2) {
        this.contractList2 = contractList2;
    }

    public List<Contract> getContractList3() {
        return contractList3;
    }

    public void setContractList3(List<Contract> contractList3) {
        this.contractList3 = contractList3;
    }

    public List<Contract> getContractList4() {
        return contractList4;
    }

    public void setContractList4(List<Contract> contractList4) {
        this.contractList4 = contractList4;
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
        if (!(object instanceof ContractFeeType)) {
            return false;
        }
        ContractFeeType other = (ContractFeeType) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ContractFeeType[ id=" + id + " ]";
    }

}
