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
@Table(name = "users")
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u")})
public class Users implements Serializable {

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
    @Size(min = 1, max = 2147483647)
    @Column(name = "code")
    private String code;
    @Column(name = "password")
    private Integer password;
    @Column(name = "salt")
    private Integer salt;
    @Size(max = 2147483647)
    @Column(name = "pname")
    private String pname;
    @Size(max = 2147483647)
    @Column(name = "fname")
    private String fname;
    @Size(max = 2147483647)
    @Column(name = "lname")
    private String lname;
    @Size(max = 2147483647)
    @Column(name = "posit")
    private String posit;
    @Column(name = "active")
    private Boolean active;
    @JoinTable(name = "users_roles_list", joinColumns = {
        @JoinColumn(name = "users", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "roles", referencedColumnName = "id")})
    @ManyToMany
    private List<UsersRoles> usersRolesList; // users's roles
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "updater")
    private List<ContractCollateralRevoke> contractCollateralRevokeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "updater")
    private List<Address> addressList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "updater")
    private List<Contract> contractList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "updater")
    private List<UsersRoles> usersRolesList1; // who updated
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "updater")
    private List<ContractObjective> contractObjectiveList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "updater")
    private List<ContractAppointmentType> contractAppointmentTypeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "updater")
    private List<Users> usersList;
    @JoinColumn(name = "updater", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Users updater;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "updater")
    private List<ContractType> contractTypeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "updater")
    private List<Receipt> receiptList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "updater")
    private List<ContractRealestateType> contractRealestateTypeList;

    public Users() {
    }

    public Users(Integer id) {
        this.id = id;
    }

    public Users(Integer id, Date updated, String code) {
        this.id = id;
        this.updated = updated;
        this.code = code;
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

    public Integer getPassword() {
        return password;
    }

    public void setPassword(Integer password) {
        this.password = password;
    }

    public Integer getSalt() {
        return salt;
    }

    public void setSalt(Integer salt) {
        this.salt = salt;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getPosit() {
        return posit;
    }

    public void setPosit(String posit) {
        this.posit = posit;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public List<UsersRoles> getUsersRolesList() {
        return usersRolesList;
    }

    public void setUsersRolesList(List<UsersRoles> usersRolesList) {
        this.usersRolesList = usersRolesList;
    }

    public List<ContractCollateralRevoke> getContractCollateralRevokeList() {
        return contractCollateralRevokeList;
    }

    public void setContractCollateralRevokeList(List<ContractCollateralRevoke> contractCollateralRevokeList) {
        this.contractCollateralRevokeList = contractCollateralRevokeList;
    }

    public List<Address> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
    }

    public List<Contract> getContractList() {
        return contractList;
    }

    public void setContractList(List<Contract> contractList) {
        this.contractList = contractList;
    }

    public List<UsersRoles> getUsersRolesList1() {
        return usersRolesList1;
    }

    public void setUsersRolesList1(List<UsersRoles> usersRolesList1) {
        this.usersRolesList1 = usersRolesList1;
    }

    public List<ContractObjective> getContractObjectiveList() {
        return contractObjectiveList;
    }

    public void setContractObjectiveList(List<ContractObjective> contractObjectiveList) {
        this.contractObjectiveList = contractObjectiveList;
    }

    public List<ContractAppointmentType> getContractAppointmentTypeList() {
        return contractAppointmentTypeList;
    }

    public void setContractAppointmentTypeList(List<ContractAppointmentType> contractAppointmentTypeList) {
        this.contractAppointmentTypeList = contractAppointmentTypeList;
    }

    public List<Users> getUsersList() {
        return usersList;
    }

    public void setUsersList(List<Users> usersList) {
        this.usersList = usersList;
    }

    public Users getUpdater() {
        return updater;
    }

    public void setUpdater(Users updater) {
        this.updater = updater;
    }

    public List<ContractType> getContractTypeList() {
        return contractTypeList;
    }

    public void setContractTypeList(List<ContractType> contractTypeList) {
        this.contractTypeList = contractTypeList;
    }

    public List<Receipt> getReceiptList() {
        return receiptList;
    }

    public void setReceiptList(List<Receipt> receiptList) {
        this.receiptList = receiptList;
    }

    public List<ContractRealestateType> getContractRealestateTypeList() {
        return contractRealestateTypeList;
    }

    public void setContractRealestateTypeList(List<ContractRealestateType> contractRealestateTypeList) {
        this.contractRealestateTypeList = contractRealestateTypeList;
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
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Users[ id=" + id + ", code=" + code + " ]";
    }

}
