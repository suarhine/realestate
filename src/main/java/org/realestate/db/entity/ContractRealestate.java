/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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
@Table(name = "contract_realestate")
@NamedQueries({
    @NamedQuery(name = "ContractRealestate.findAll", query = "SELECT c FROM ContractRealestate c")})
public class ContractRealestate implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
//    @Basic(optional = false)
    @NotNull
//    @Column(name = "id")
//    private Integer id;
    @JoinColumn(name = "id", referencedColumnName = "id")
    @OneToOne(optional = false)
    private Contract id;
    @Size(max = 2147483647)
    @Column(name = "name")
    private String name;
    @Size(max = 2147483647)
    @Column(name = "code")
    private String code;
    @Size(max = 2147483647)
    @Column(name = "location")
    private String location;
    @Size(max = 2147483647)
    @Column(name = "nearby")
    private String nearby;
    @Column(name = "moi_declare")
    @Temporal(TemporalType.DATE)
    private Date moiDeclare;
    @Size(max = 2147483647)
    @Column(name = "deed_code")
    private String deedCode;
    @Size(max = 2147483647)
    @Column(name = "deed_no")
    private String deedNo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "\"space\"")
    private Double space;
    @JoinColumn(name = "type", referencedColumnName = "id")
    @ManyToOne
    private ContractRealestateType type;
    @JoinColumn(name = "address", referencedColumnName = "id")
    @ManyToOne
    private Address address;
//    @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false)
//    @OneToOne(optional = false)
//    private Contract contract;

    public ContractRealestate() {
    }
//
//    public ContractRealestate(Integer id) {
//        this.id = id;
//    }

    public ContractRealestate(Contract id) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getNearby() {
        return nearby;
    }

    public void setNearby(String nearby) {
        this.nearby = nearby;
    }

    public Date getMoiDeclare() {
        return moiDeclare;
    }

    public void setMoiDeclare(Date moiDeclare) {
        this.moiDeclare = moiDeclare;
    }

    public String getDeedCode() {
        return deedCode;
    }

    public void setDeedCode(String deedCode) {
        this.deedCode = deedCode;
    }

    public String getDeedNo() {
        return deedNo;
    }

    public void setDeedNo(String deedNo) {
        this.deedNo = deedNo;
    }

    public Double getSpace() {
        return space;
    }

    public void setSpace(Double space) {
        this.space = space;
    }

    public ContractRealestateType getType() {
        return type;
    }

    public void setType(ContractRealestateType type) {
        this.type = type;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
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
        if (!(object instanceof ContractRealestate)) {
            return false;
        }
        ContractRealestate other = (ContractRealestate) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ContractRealestate[ id=" + id + " ]";
    }

}
