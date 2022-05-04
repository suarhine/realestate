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
@Table(name = "users_roles")
@NamedQueries({
    @NamedQuery(name = "UsersRoles.findAll", query = "SELECT u FROM UsersRoles u")})
public class UsersRoles implements Serializable {

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
    @JoinTable(name = "users_roles_func_list", joinColumns = {
        @JoinColumn(name = "roles", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "func", referencedColumnName = "id")})
    @ManyToMany
    private List<UsersFunc> usersFuncList;
    @JoinTable(name = "users_roles_list", joinColumns = {
        @JoinColumn(name = "roles", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "users", referencedColumnName = "id")})
    @ManyToMany
    private List<Users> usersList;
    @JoinColumn(name = "updater", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Users updater;

    public UsersRoles() {
    }

    public UsersRoles(Integer id) {
        this.id = id;
    }

    public UsersRoles(Integer id, Date updated) {
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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public List<UsersFunc> getUsersFuncList() {
        return usersFuncList;
    }

    public void setUsersFuncList(List<UsersFunc> usersFuncList) {
        this.usersFuncList = usersFuncList;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsersRoles)) {
            return false;
        }
        UsersRoles other = (UsersRoles) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "UsersRoles[ id=" + id + " ]";
    }

}
