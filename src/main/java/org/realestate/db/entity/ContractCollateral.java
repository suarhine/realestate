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
@Table(name = "contract_collateral")
@NamedQueries({
    @NamedQuery(name = "ContractCollateral.findAll", query = "SELECT c FROM ContractCollateral c")})
public class ContractCollateral implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
//    @Basic(optional = false)
    @NotNull
//    @Column(name = "id")
//    private Integer id;
    @JoinColumn(name = "id", referencedColumnName = "id")
    @OneToOne(optional = false)
    private Contract id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "cash")
    private Double cash;
    @Size(max = 2147483647)
    @Column(name = "cash_text")
    private String cashText;
    @Size(max = 2147483647)
    @Column(name = "cash_rcpt")
    private String cashRcpt;
    @Column(name = "cash_rcpt_dated")
    @Temporal(TemporalType.DATE)
    private Date cashRcptDated;
    @Column(name = "cashier_cheque")
    private Double cashierCheque;
    @Size(max = 2147483647)
    @Column(name = "cashier_cheque_text")
    private String cashierChequeText;
    @Size(max = 2147483647)
    @Column(name = "cashier_cheque_rcpt")
    private String cashierChequeRcpt;
    @Column(name = "cashier_cheque_rcpt_dated")
    @Temporal(TemporalType.DATE)
    private Date cashierChequeRcptDated;
    @Column(name = "bank_collateral")
    private Double bankCollateral;
    @Size(max = 2147483647)
    @Column(name = "bank_collateral_text")
    private String bankCollateralText;
    @Size(max = 2147483647)
    @Column(name = "bank_collateral_no")
    private String bankCollateralNo;
    @Column(name = "bank_collateral_dated")
    @Temporal(TemporalType.DATE)
    private Date bankCollateralDated;
//    @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false)
//    @OneToOne(optional = false)
//    private Contract contract;

    public ContractCollateral() {
    }
//
//    public ContractCollateral(Integer id) {
//        this.id = id;
//    }

    public ContractCollateral(Contract id) {
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

    public Double getCash() {
        return cash;
    }

    public void setCash(Double cash) {
        this.cash = cash;
    }

    public String getCashText() {
        return cashText;
    }

    public void setCashText(String cashText) {
        this.cashText = cashText;
    }

    public String getCashRcpt() {
        return cashRcpt;
    }

    public void setCashRcpt(String cashRcpt) {
        this.cashRcpt = cashRcpt;
    }

    public Date getCashRcptDated() {
        return cashRcptDated;
    }

    public void setCashRcptDated(Date cashRcptDated) {
        this.cashRcptDated = cashRcptDated;
    }

    public Double getCashierCheque() {
        return cashierCheque;
    }

    public void setCashierCheque(Double cashierCheque) {
        this.cashierCheque = cashierCheque;
    }

    public String getCashierChequeText() {
        return cashierChequeText;
    }

    public void setCashierChequeText(String cashierChequeText) {
        this.cashierChequeText = cashierChequeText;
    }

    public String getCashierChequeRcpt() {
        return cashierChequeRcpt;
    }

    public void setCashierChequeRcpt(String cashierChequeRcpt) {
        this.cashierChequeRcpt = cashierChequeRcpt;
    }

    public Date getCashierChequeRcptDated() {
        return cashierChequeRcptDated;
    }

    public void setCashierChequeRcptDated(Date cashierChequeRcptDated) {
        this.cashierChequeRcptDated = cashierChequeRcptDated;
    }

    public Double getBankCollateral() {
        return bankCollateral;
    }

    public void setBankCollateral(Double bankCollateral) {
        this.bankCollateral = bankCollateral;
    }

    public String getBankCollateralText() {
        return bankCollateralText;
    }

    public void setBankCollateralText(String bankCollateralText) {
        this.bankCollateralText = bankCollateralText;
    }

    public String getBankCollateralNo() {
        return bankCollateralNo;
    }

    public void setBankCollateralNo(String bankCollateralNo) {
        this.bankCollateralNo = bankCollateralNo;
    }

    public Date getBankCollateralDated() {
        return bankCollateralDated;
    }

    public void setBankCollateralDated(Date bankCollateralDated) {
        this.bankCollateralDated = bankCollateralDated;
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
        if (!(object instanceof ContractCollateral)) {
            return false;
        }
        ContractCollateral other = (ContractCollateral) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ContractCollateral[ id=" + id + " ]";
    }

}
