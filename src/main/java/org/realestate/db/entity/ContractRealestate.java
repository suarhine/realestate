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
    @Column(name = "type")
    private Integer type;
    @Size(max = 2147483647)
    @Column(name = "code")
    private String code;
    @Size(max = 2147483647)
    @Column(name = "location")
    private String location;
    @Size(max = 2147483647)
    @Column(name = "nearby")
    private String nearby;
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
    @Column(name = "deed_code")
    private String deedCode;
    @Size(max = 2147483647)
    @Column(name = "deed_no")
    private String deedNo;
    @Column(name = "space_rai")
    private Integer spaceRai;
    @Column(name = "space_ngan")
    private Integer spaceNgan;
    @Column(name = "space_sqwah")
    private Double spaceSqwah;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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

    public Integer getSpaceRai() {
        return spaceRai;
    }

    public void setSpaceRai(Integer spaceRai) {
        this.spaceRai = spaceRai;
    }

    public Integer getSpaceNgan() {
        return spaceNgan;
    }

    public void setSpaceNgan(Integer spaceNgan) {
        this.spaceNgan = spaceNgan;
    }

    public Double getSpaceSqwah() {
        return spaceSqwah;
    }

    public void setSpaceSqwah(Double spaceSqwah) {
        this.spaceSqwah = spaceSqwah;
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
        return "org.realestate.db.entity.ContractRealestate[ id=" + id + " ]";
    }

}
