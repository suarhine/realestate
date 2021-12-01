/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
@Table(name = "contract")
@NamedQueries({
    @NamedQuery(name = "Contract.findAll", query = "SELECT c FROM Contract c")})
public class Contract implements Serializable {

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
    @Column(name = "updater")
    private int updater;
    @Column(name = "type")
    private Integer type;
    @Size(max = 2147483647)
    @Column(name = "code")
    private String code;
    @Column(name = "dated")
    @Temporal(TemporalType.DATE)
    private Date dated;
    @Size(max = 2147483647)
    @Column(name = "note")
    private String note;
    @Column(name = "note_dated")
    @Temporal(TemporalType.DATE)
    private Date noteDated;
    @Column(name = "objective")
    private Integer objective;
    @Size(max = 2147483647)
    @Column(name = "objective_text")
    private String objectiveText;
    @Column(name = "started")
    @Temporal(TemporalType.DATE)
    private Date started;
    @Column(name = "ended")
    @Temporal(TemporalType.DATE)
    private Date ended;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "id")
    private ContractLessor contractLessor;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "id")
    private ContractRealestate contractRealestate;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "id")
    private ContractLessee contractLessee;

    public Contract() {
    }

    public Contract(Integer id) {
        this.id = id;
    }

    public Contract(Integer id, Date updated, int updater) {
        this.id = id;
        this.updated = updated;
        this.updater = updater;
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

    public int getUpdater() {
        return updater;
    }

    public void setUpdater(int updater) {
        this.updater = updater;
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

    public Date getDated() {
        return dated;
    }

    public void setDated(Date dated) {
        this.dated = dated;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getNoteDated() {
        return noteDated;
    }

    public void setNoteDated(Date noteDated) {
        this.noteDated = noteDated;
    }

    public Integer getObjective() {
        return objective;
    }

    public void setObjective(Integer objective) {
        this.objective = objective;
    }

    public String getObjectiveText() {
        return objectiveText;
    }

    public void setObjectiveText(String objectiveText) {
        this.objectiveText = objectiveText;
    }

    public Date getStarted() {
        return started;
    }

    public void setStarted(Date started) {
        this.started = started;
    }

    public Date getEnded() {
        return ended;
    }

    public void setEnded(Date ended) {
        this.ended = ended;
    }

    public ContractLessor getContractLessor() {
        return contractLessor;
    }

    public void setContractLessor(ContractLessor contractLessor) {
        this.contractLessor = contractLessor;
    }

    public ContractRealestate getContractRealestate() {
        return contractRealestate;
    }

    public void setContractRealestate(ContractRealestate contractRealestate) {
        this.contractRealestate = contractRealestate;
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
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Contract)) {
            return false;
        }
        Contract other = (Contract) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.realestate.db.entity.Contract[ id=" + id + " ]";
    }

}
