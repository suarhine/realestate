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
    @Size(max = 2147483647)
    @Column(name = "commission_label")
    private String commissionLabel;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "id")
    private ContractLessor contractLessor;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "id")
    private ContractPlan contractPlan;
    @JoinColumn(name = "commission_fee_type", referencedColumnName = "id")
    @ManyToOne
    private ContractFeeType commissionFeeType;
    @JoinColumn(name = "installment_rental_fee_type", referencedColumnName = "id")
    @ManyToOne
    private ContractFeeType installmentRentalFeeType;
    @JoinColumn(name = "period_rental_fee_type", referencedColumnName = "id")
    @ManyToOne
    private ContractFeeType periodRentalFeeType;
    @JoinColumn(name = "rental_fee_type", referencedColumnName = "id")
    @ManyToOne
    private ContractFeeType rentalFeeType;
    @JoinColumn(name = "utilization_fee_type", referencedColumnName = "id")
    @ManyToOne
    private ContractFeeType utilizationFeeType;
    @JoinColumn(name = "objective", referencedColumnName = "id")
    @ManyToOne
    private ContractObjective objective;
    @JoinColumn(name = "type", referencedColumnName = "id")
    @ManyToOne
    private ContractType type;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "id")
    private ContractCollateral contractCollateral;
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

    public String getCommissionLabel() {
        return commissionLabel;
    }

    public void setCommissionLabel(String commissionLabel) {
        this.commissionLabel = commissionLabel;
    }

    public ContractLessor getContractLessor() {
        return contractLessor;
    }

    public void setContractLessor(ContractLessor contractLessor) {
        this.contractLessor = contractLessor;
    }

    public ContractPlan getContractPlan() {
        return contractPlan;
    }

    public void setContractPlan(ContractPlan contractPlan) {
        this.contractPlan = contractPlan;
    }

    public ContractFeeType getCommissionFeeType() {
        return commissionFeeType;
    }

    public void setCommissionFeeType(ContractFeeType commissionFeeType) {
        this.commissionFeeType = commissionFeeType;
    }

    public ContractFeeType getInstallmentRentalFeeType() {
        return installmentRentalFeeType;
    }

    public void setInstallmentRentalFeeType(ContractFeeType installmentRentalFeeType) {
        this.installmentRentalFeeType = installmentRentalFeeType;
    }

    public ContractFeeType getPeriodRentalFeeType() {
        return periodRentalFeeType;
    }

    public void setPeriodRentalFeeType(ContractFeeType periodRentalFeeType) {
        this.periodRentalFeeType = periodRentalFeeType;
    }

    public ContractFeeType getRentalFeeType() {
        return rentalFeeType;
    }

    public void setRentalFeeType(ContractFeeType rentalFeeType) {
        this.rentalFeeType = rentalFeeType;
    }

    public ContractFeeType getUtilizationFeeType() {
        return utilizationFeeType;
    }

    public void setUtilizationFeeType(ContractFeeType utilizationFeeType) {
        this.utilizationFeeType = utilizationFeeType;
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

    public ContractCollateral getContractCollateral() {
        return contractCollateral;
    }

    public void setContractCollateral(ContractCollateral contractCollateral) {
        this.contractCollateral = contractCollateral;
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

}
