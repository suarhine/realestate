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
@Table(name = "contract_plan")
@NamedQueries({
    @NamedQuery(name = "ContractPlan.findAll", query = "SELECT c FROM ContractPlan c")})
public class ContractPlan implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
//    @Basic(optional = false)
    @NotNull
//    @Column(name = "id")
//    private Integer id;
    @JoinColumn(name = "id", referencedColumnName = "id")
    @OneToOne(optional = false)
    private Contract id;
    @Column(name = "prepaid_started")
    @Temporal(TemporalType.DATE)
    private Date prepaidStarted;
    @Column(name = "prepaid_ended")
    @Temporal(TemporalType.DATE)
    private Date prepaidEnded;
    @Column(name = "nextpaid_started")
    @Temporal(TemporalType.DATE)
    private Date nextpaidStarted;
    @Column(name = "nextpaid_ended")
    @Temporal(TemporalType.DATE)
    private Date nextpaidEnded;
    @Size(max = 2147483647)
    @Column(name = "deadline_monthly")
    private String deadlineMonthly;
    @Size(max = 2147483647)
    @Column(name = "deadline_yearly")
    private String deadlineYearly;
    @Size(max = 2147483647)
    @Column(name = "deadline_yearly_date")
    private String deadlineYearlyDate;
    @Column(name = "deadline")
    @Temporal(TemporalType.DATE)
    private Date deadline;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "finerate")
    private Double finerate;
//    @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false)
//    @OneToOne(optional = false)
//    private Contract contract;

    public ContractPlan() {
    }
//
//    public ContractPlan(Integer id) {
//        this.id = id;
//    }

    public ContractPlan(Contract id) {
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

    public Date getPrepaidStarted() {
        return prepaidStarted;
    }

    public void setPrepaidStarted(Date prepaidStarted) {
        this.prepaidStarted = prepaidStarted;
    }

    public Date getPrepaidEnded() {
        return prepaidEnded;
    }

    public void setPrepaidEnded(Date prepaidEnded) {
        this.prepaidEnded = prepaidEnded;
    }

    public Date getNextpaidStarted() {
        return nextpaidStarted;
    }

    public void setNextpaidStarted(Date nextpaidStarted) {
        this.nextpaidStarted = nextpaidStarted;
    }

    public Date getNextpaidEnded() {
        return nextpaidEnded;
    }

    public void setNextpaidEnded(Date nextpaidEnded) {
        this.nextpaidEnded = nextpaidEnded;
    }

    public String getDeadlineMonthly() {
        return deadlineMonthly;
    }

    public void setDeadlineMonthly(String deadlineMonthly) {
        this.deadlineMonthly = deadlineMonthly;
    }

    public String getDeadlineYearly() {
        return deadlineYearly;
    }

    public void setDeadlineYearly(String deadlineYearly) {
        this.deadlineYearly = deadlineYearly;
    }

    public String getDeadlineYearlyDate() {
        return deadlineYearlyDate;
    }

    public void setDeadlineYearlyDate(String deadlineYearlyDate) {
        this.deadlineYearlyDate = deadlineYearlyDate;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Double getFinerate() {
        return finerate;
    }

    public void setFinerate(Double finerate) {
        this.finerate = finerate;
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
        if (!(object instanceof ContractPlan)) {
            return false;
        }
        ContractPlan other = (ContractPlan) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ContractPlan[ id=" + id + " ]";
    }

}
