/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.realestate.db.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 *
 * @author Pathompong
 */
@Entity
@Table(name = "address")
@NamedQueries({
    @NamedQuery(name = "Address.findAll", query = "SELECT a FROM Address a")})
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
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
    @OneToMany(mappedBy = "address")
    private List<ContractRealestate> contractRealestateList;
    @OneToMany(mappedBy = "contact")
    private List<ContractLessee> contractLesseeList;
    @OneToMany(mappedBy = "registry")
    private List<ContractLessee> contractLesseeList1;

    public Address() {
    }

    public Address(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public List<ContractRealestate> getContractRealestateList() {
        return contractRealestateList;
    }

    public void setContractRealestateList(List<ContractRealestate> contractRealestateList) {
        this.contractRealestateList = contractRealestateList;
    }

    public List<ContractLessee> getContractLesseeList() {
        return contractLesseeList;
    }

    public void setContractLesseeList(List<ContractLessee> contractLesseeList) {
        this.contractLesseeList = contractLesseeList;
    }

    public List<ContractLessee> getContractLesseeList1() {
        return contractLesseeList1;
    }

    public void setContractLesseeList1(List<ContractLessee> contractLesseeList1) {
        this.contractLesseeList1 = contractLesseeList1;
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
        if (!(object instanceof Address)) {
            return false;
        }
        Address other = (Address) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Address[ id=" + id + " ]";
    }

}
