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
    @Size(max = 2147483647)
    @Column(name = "objective_text")
    private String objectiveText;
    @Column(name = "started")
    @Temporal(TemporalType.DATE)
    private Date started;
    @Column(name = "ended")
    @Temporal(TemporalType.DATE)
    private Date ended;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "id", orphanRemoval = true)
    private ContractCollateralRevoke contractCollateralRevoke;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "id")
    private ContractPlan contractPlan;
    @JoinColumn(name = "objective", referencedColumnName = "id")
    @ManyToOne
    private ContractObjective objective;
    @JoinColumn(name = "type", referencedColumnName = "id")
    @ManyToOne
    private ContractType type;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "id", orphanRemoval = true)
    private List<ContractAppointment> contractAppointmentList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "id", orphanRemoval = true)
    @OrderBy("pk.dating")
    private List<ContractAppointmentDating> contractAppointmentDatingList;
    @OneToMany(mappedBy = "ref")
    private List<Contract> contractList;
    @JoinColumn(name = "ref", referencedColumnName = "id")
    @ManyToOne
    private Contract ref;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "id")
    private ContractCollateral contractCollateral;
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

    public ContractCollateralRevoke getContractCollateralRevoke() {
        return contractCollateralRevoke;
    }

    public void setContractCollateralRevoke(ContractCollateralRevoke contractCollateralRevoke) {
        this.contractCollateralRevoke = contractCollateralRevoke;
    }

    public ContractPlan getContractPlan() {
        return contractPlan;
    }

    public void setContractPlan(ContractPlan contractPlan) {
        this.contractPlan = contractPlan;
    }

    public ContractObjective getObjective() {
        return objective;
    }

    public void setObjective(ContractObjective objective) {
        this.objective = objective;
    }

    public ContractType getType() {
        return type;
    }

    public void setType(ContractType type) {
        this.type = type;
    }

    public List<ContractAppointment> getContractAppointmentList() {
        return contractAppointmentList;
    }

    public void setContractAppointmentList(List<ContractAppointment> contractAppointmentList) {
        this.contractAppointmentList = contractAppointmentList;
    }

    public List<ContractAppointmentDating> getContractAppointmentDatingList() {
        return contractAppointmentDatingList;
    }

    public void setContractAppointmentDatingList(List<ContractAppointmentDating> contractAppointmentDatingList) {
        this.contractAppointmentDatingList = contractAppointmentDatingList;
    }

    public List<Contract> getContractList() {
        return contractList;
    }

    public void setContractList(List<Contract> contractList) {
        this.contractList = contractList;
    }

    public Contract getRef() {
        return ref;
    }

    public void setRef(Contract ref) {
        this.ref = ref;
    }

    public ContractCollateral getContractCollateral() {
        return contractCollateral;
    }

    public void setContractCollateral(ContractCollateral contractCollateral) {
        this.contractCollateral = contractCollateral;
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
        return "Contract[ id=" + id + " ]";
    }

    @PostPersist
    @SuppressWarnings("unused")
    private void postPersist() {
        try {
            for (var e : contractAppointmentList) {
                e.getPk().setId(id);
            }
        } catch (NullPointerException x) {
        }
        try {
            for (var e : contractAppointmentDatingList) {
                e.getPk().setId(id);
            }
        } catch (NullPointerException x) {
        }
    }
}
