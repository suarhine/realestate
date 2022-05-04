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
@Table(name = "users_func")
@NamedQueries({
    @NamedQuery(name = "UsersFunc.findAll", query = "SELECT u FROM UsersFunc u")})
public class UsersFunc implements Serializable {

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
    @JoinTable(name = "users_roles_func_list", joinColumns = {
        @JoinColumn(name = "func", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "roles", referencedColumnName = "id")})
    @ManyToMany
    private List<UsersRoles> usersRolesList;

    public UsersFunc() {
    }

    public UsersFunc(Integer id) {
        this.id = id;
    }

    public UsersFunc(Integer id, String code, String label) {
        this.id = id;
        this.code = code;
        this.label = label;
        this.active = true; //for dev
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

    public List<UsersRoles> getUsersRolesList() {
        return usersRolesList;
    }

    public void setUsersRolesList(List<UsersRoles> usersRolesList) {
        this.usersRolesList = usersRolesList;
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
        if (!(object instanceof UsersFunc)) {
            return false;
        }
        UsersFunc other = (UsersFunc) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "UsersFunc[ id=" + id + " ]";
    }

}
