/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.realestate.db.entity;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 *
 * @author Pathompong
 */
@Entity
@Table(name = "contract_lessee_address")
@NamedQueries({
    @NamedQuery(name = "ContractLesseeAddress.findAll", query = "SELECT c FROM ContractLesseeAddress c")})
public class ContractLesseeAddress implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ContractLesseeAddressPK contractLesseeAddressPK;
    @Size(max = 2147483647)
    @Column(name = "house")
    private String house;
    @Size(max = 2147483647)
    @Column(name = "village")
    private String village;
    @Size(max = 2147483647)
    @Column(name = "soi")
    private String soi;
    @Size(max = 2147483647)
    @Column(name = "road")
    private String road;
    @Size(max = 2147483647)
    @Column(name = "subdistrict")
    private String subdistrict;
    @Size(max = 2147483647)
    @Column(name = "district")
    private String district;
    @Size(max = 2147483647)
    @Column(name = "province")
    private String province;
    @Size(max = 2147483647)
    @Column(name = "zipcode")
    private String zipcode;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 2147483647)
    @Column(name = "phone")
    private String phone;
    @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private ContractLessee contractLessee;

    public ContractLesseeAddress() {
    }

    public ContractLesseeAddress(ContractLesseeAddressPK contractLesseeAddressPK) {
        this.contractLesseeAddressPK = contractLesseeAddressPK;
    }

    public ContractLesseeAddress(int id, int type) {
        this.contractLesseeAddressPK = new ContractLesseeAddressPK(id, type);
    }

    public ContractLesseeAddressPK getContractLesseeAddressPK() {
        return contractLesseeAddressPK;
    }

    public void setContractLesseeAddressPK(ContractLesseeAddressPK contractLesseeAddressPK) {
        this.contractLesseeAddressPK = contractLesseeAddressPK;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getSoi() {
        return soi;
    }

    public void setSoi(String soi) {
        this.soi = soi;
    }

    public String getRoad() {
        return road;
    }

    public void setRoad(String road) {
        this.road = road;
    }

    public String getSubdistrict() {
        return subdistrict;
    }

    public void setSubdistrict(String subdistrict) {
        this.subdistrict = subdistrict;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public ContractLessee getContractLessee() {
        return contractLessee;
    }

    public void setContractLessee(ContractLessee contractLessee) {
        this.contractLessee = contractLessee;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (contractLesseeAddressPK != null ? contractLesseeAddressPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ContractLesseeAddress)) {
            return false;
        }
        ContractLesseeAddress other = (ContractLesseeAddress) object;
        if ((this.contractLesseeAddressPK == null && other.contractLesseeAddressPK != null) || (this.contractLesseeAddressPK != null && !this.contractLesseeAddressPK.equals(other.contractLesseeAddressPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.realestate.db.entity.ContractLesseeAddress[ contractLesseeAddressPK=" + contractLesseeAddressPK + " ]";
    }

}
