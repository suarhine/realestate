/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.realestate.ctrl.page.contract;

import static org.realestate.ctrl.app.ApplicationInstance.model;
import static org.realestate.ctrl.app.Commons.notnull;
import static org.web.jsp.fn.Functions.pivot;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.function.Function;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.persistence.model.Model;
import org.persistence.model.Model.Statement.Criteria;
import org.realestate.ctrl.app.ApplicationException;
import org.realestate.db.entity.*;
import org.realestate.db.fix.UsersFix;
import org.web.ctrl.DefaultPage;

/**
 *
 * @author Pathompong
 */
@WebServlet(name = "contract.IndexPage", urlPatterns = {"/contract/"})
public class IndexPage extends HttpServlet implements DefaultPage {

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    @SuppressWarnings("null")
    protected void doGet(
            HttpServletRequest request, HttpServletResponse response
    ) throws ServletException, IOException {
        if (flag(request, "id")) {
            if (flag(request, int.class, "id")) {
                attr(request, "find", notnull(model(Contract.class).find(
                        param(request, Integer.class, "id")
                ), "id = " + param(request, "id")));
            } else if (flag(request, int.class, "ref")) {
                attr(request, "ref", notnull(model(Contract.class).find(
                        param(request, Integer.class, "ref")
                ), "ref = " + param(request, "ref")));
            }
            jsp(request, response, "input.jsp");
        } else {
            Criteria criteria = new Criteria.Blank();
            if (flag(request, String.class, "q")) {
                for (var q : param(request, "q").split("\\s+")) {
                    criteria = criteria.and((Function<Object, String> arguments) -> {
                        var var = arguments.apply("%" + q + "%");
                        var builder = new StringBuilder();
                        for (var field : new String[]{
                            "code"
                        }) {
                            builder.append(" OR ").append(field).append(" LIKE ").append(var);
                        }
                        for (var entry : new String[][]{
                            {"ContractLessee", "id.id", "name,representative"},
                            {"ContractRealestate", "id.id", "name"}
                        }) {
                            var b = new StringBuilder();
                            for (var field : entry[2].split("\\s*,\\s*")) {
                                b.append(" OR s.").append(field).append(" LIKE ").append(var);
                            }
                            builder.append(" OR id IN (SELECT s.").append(entry[1])
                                    .append(" FROM ").append(entry[0])
                                    .append(" s WHERE ").append(b.delete(0, 4)).append(")");
                        }
                        return builder.delete(0, 4).insert(0, "(").append(")");
                    });
                }
            }
            if (flag(request, Integer.class, "type")) {
                criteria = criteria.and("type.id", param(request, Integer.class, "type"));
            }
            if (flag(request, String.class, "realestate.location")) {
                criteria = criteria.and("contractRealestate.location", param(request, String.class, "realestate.location"));
            }
            if (flag(request, String.class, "realestate.address.subdistrict")) {
                criteria = criteria.and("contractRealestate.address.subdistrict", param(request, String.class, "realestate.address.subdistrict"));
            }
//            if (flag(request, String.class, "period")) {
//                criteria = criteria.and("", "");
//            }
            if (flag(request, Integer.class, "period.min")) {
                criteria = criteria.and("SQL('date_part(''year'', age(?, ?))', ended, started)", ">=", param(request, Integer.class, "period.min"));
            }
            if (flag(request, Integer.class, "period.max")) {
                criteria = criteria.and("SQL('date_part(''year'', age(?, ?))', ended, started)", "<=", param(request, Integer.class, "period.max"));
            }
            if (flag(request, Integer.class, "objective")) {
                criteria = criteria.and("objective.id", param(request, Integer.class, "objective"));
            }
            System.out.println("flag -> " + flag(request, Boolean.class, "collateral.revoke"));
            System.out.println("parse -> " + param(request, Boolean.class, "collateral.revoke"));
            System.out.println("raw -> " + param(request, "collateral.revoke"));
            if (flag(request, Boolean.class, "collateral.revoke")) {
                criteria = criteria.and("id", param(request, Boolean.class, "collateral.revoke") ? "IN" : "NOT IN", Model.Statement.of("SELECT sub.id.id FROM ContractCollateralRevoke sub").blocked());
            }
            attr(request, "finds", model(Contract.class).finds(criteria instanceof Criteria.Blank ? null : criteria));
            jsp(request, response);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(
            HttpServletRequest request, HttpServletResponse response
    ) throws ServletException, IOException {
//        var entity = flag(request, Integer.class, "id")
//                ? notnull(model(Contract.class).find(param(request, Integer.class, "id")), "id = " + request.getParameter("id"))
//                : new Contract();
        if (flag(request, "del")) {
            if (model(Contract.class, Integer.class).del(param(request, Integer.class, "id"))) {
                redirect(response, ".");
                return;
            } else {
                throw ApplicationException.Type.uncommited_transaction.dispatch();
            }
        }
        Contract entity;
        if (flag(request, Integer.class, "id")) {
            entity = notnull(model(Contract.class).find(param(request, Integer.class, "id")), "id = " + request.getParameter("id"));
            if (flag(request, "revoke")) {
                var revokeEntity = new ContractCollateralRevoke(entity);
                revokeEntity.setUpdated(new Date());
                revokeEntity.setUpdater(UsersFix.system.id);
                if (model(ContractCollateralRevoke.class).add(revokeEntity)) {
                    redirect(response, "?id=" + entity.getId());
                    return;
                } else {
                    throw ApplicationException.Type.uncommited_transaction.dispatch();
                }
            }
            if (flag(request, "cancel-revoke")) {
                if (entity.getContractCollateralRevoke() == null) {
                    throw ApplicationException.Type.incomplete_parameter.dispatch("ยังไม่มีการถอนคืนหลักประกันสัญญา");
                }
                entity.setContractCollateralRevoke(null);
                entity.setUpdated(new Date());
                entity.setUpdater(UsersFix.system.id);
                if (model(Contract.class).put(entity)) {
                    redirect(response, "?id=" + entity.getId());
                    return;
                } else {
                    throw ApplicationException.Type.uncommited_transaction.dispatch();
                }
            }
        } else {
            entity = new Contract();
            entity.setRef(flag(request, Integer.class, "ref") ? notnull(model(Contract.class, Integer.class).find(param(request, Integer.class, "ref")), "ref = " + param(request, "ref")) : null);
        }
        if (entity.getContractLessor() == null) {
            entity.setContractLessor(new ContractLessor(entity));
        }
        if (entity.getContractLessee() == null) {
            entity.setContractLessee(new ContractLessee(entity));
        }
        if (entity.getContractLessee().getRegistry() == null) {
            entity.getContractLessee().setRegistry(new Address());
        }
        if (entity.getContractLessee().getContact() == null) {
            entity.getContractLessee().setContact(new Address());
        }
        if (entity.getContractRealestate() == null) {
            entity.setContractRealestate(new ContractRealestate(entity));
        }
        if (entity.getContractRealestate().getAddress() == null) {
            entity.getContractRealestate().setAddress(new Address());
        }
        if (entity.getContractPlan() == null) {
            entity.setContractPlan(new ContractPlan(entity));
        }
        if (entity.getContractCollateral() == null) {
            entity.setContractCollateral(new ContractCollateral(entity));
        }
        entity.setUpdated(new Date());
        entity.setUpdater(UsersFix.system.id);
        entity.setType(flag(request, Integer.class, "type") ? notnull(model(ContractType.class).find(param(request, Integer.class, "type")), "type = " + param(request, "type")) : null);
        entity.setCode(param(request, "code"));
        entity.setDated(param(request, Date.class, "dated", "yyyy-MM-dd"));
        entity.setNote(param(request, "note"));
        entity.setNoteDated(param(request, Date.class, "note_dated", "yyyy-MM-dd"));
        entity.setObjective(flag(request, Integer.class, "objective") ? notnull(model(ContractObjective.class).find(param(request, Integer.class, "objective")), "objective = " + param(request, "objective")) : null);
        entity.setObjectiveText(param(request, "objective_text"));
        entity.setStarted(param(request, Date.class, "started", "yyyy-MM-dd"));
        entity.setEnded(param(request, Date.class, "ended", "yyyy-MM-dd"));
//        entity.setRentalFeeType(flag(request, Integer.class, "rental_fee_type") ? notnull(model(ContractAppointmentFeeType.class).find(param(request, Integer.class, "rental_fee_type")), "rental_fee_type = " + param(request, "rental_fee_type")) : null);
//        entity.setInstallmentRentalFeeType(flag(request, Integer.class, "installment_rental_fee_type") ? notnull(model(ContractAppointmentFeeType.class).find(param(request, Integer.class, "installment_rental_fee_type")), "installment_rental_fee_type = " + param(request, "installment_rental_fee_type")) : null);
//        entity.setCommissionLabel(param(request, "commission_label"));
//        entity.setCommissionFeeType(flag(request, Integer.class, "commission_fee_type") ? notnull(model(ContractAppointmentFeeType.class).find(param(request, Integer.class, "commission_fee_type")), "commission_fee_type = " + param(request, "commission_fee_type")) : null);
//        entity.setUtilizationFeeType(flag(request, Integer.class, "utilization_fee_type") ? notnull(model(ContractAppointmentFeeType.class).find(param(request, Integer.class, "utilization_fee_type")), "utilization_fee_type = " + param(request, "utilization_fee_type")) : null);
//        entity.setPeriodRentalFeeType(flag(request, Integer.class, "period_rental_fee_type") ? notnull(model(ContractAppointmentFeeType.class).find(param(request, Integer.class, "period_rental_fee_type")), "period_rental_fee_type = " + param(request, "period_rental_fee_type")) : null);

        entity.getContractLessor().setName(param(request, "lessor.name"));
        entity.getContractLessor().setRole(param(request, "lessor.role"));

        entity.getContractLessee().setName(param(request, "lessee.name"));
        entity.getContractLessee().setCode(param(request, "lessee.code"));
        entity.getContractLessee().setRepresentative(param(request, "lessee.representative"));
        entity.getContractLessee().setRepresentativeRole(param(request, "lessee.representative_role"));
        entity.getContractLessee().getRegistry().setHouse(param(request, "lessee.registry.house"));
        entity.getContractLessee().getRegistry().setVillage(param(request, "lessee.registry.village"));
        entity.getContractLessee().getRegistry().setSoi(param(request, "lessee.registry.soi"));
        entity.getContractLessee().getRegistry().setRoad(param(request, "lessee.registry.road"));
        entity.getContractLessee().getRegistry().setSubdistrict(param(request, "lessee.registry.subdistrict"));
        entity.getContractLessee().getRegistry().setDistrict(param(request, "lessee.registry.district"));
        entity.getContractLessee().getRegistry().setProvince(param(request, "lessee.registry.province"));
        entity.getContractLessee().getRegistry().setZipcode(param(request, "lessee.registry.zipcode"));
        entity.getContractLessee().getRegistry().setPhone(param(request, "lessee.registry.phone"));
        entity.getContractLessee().getContact().setHouse(param(request, "lessee.contact.house"));
        entity.getContractLessee().getContact().setVillage(param(request, "lessee.contact.village"));
        entity.getContractLessee().getContact().setSoi(param(request, "lessee.contact.soi"));
        entity.getContractLessee().getContact().setRoad(param(request, "lessee.contact.road"));
        entity.getContractLessee().getContact().setSubdistrict(param(request, "lessee.contact.subdistrict"));
        entity.getContractLessee().getContact().setDistrict(param(request, "lessee.contact.district"));
        entity.getContractLessee().getContact().setProvince(param(request, "lessee.contact.province"));
        entity.getContractLessee().getContact().setZipcode(param(request, "lessee.contact.zipcode"));
        entity.getContractLessee().getContact().setPhone(param(request, "lessee.contact.phone"));
        entity.getContractRealestate().setName(param(request, "realestate.name"));
        entity.getContractRealestate().setType(flag(request, Integer.class, "realestate.type") ? notnull(model(ContractRealestateType.class).find(param(request, Integer.class, "realestate.type")), "realestate.type = " + param(request, "realestate.type")) : null);
        entity.getContractRealestate().setCode(param(request, "realestate.code"));
        entity.getContractRealestate().setLocation(param(request, "realestate.location"));
        entity.getContractRealestate().setNearby(param(request, "realestate.nearby"));
        entity.getContractRealestate().setDeedCode(param(request, "realestate.deed_code"));
        entity.getContractRealestate().setDeedNo(param(request, "realestate.deed_no"));
        entity.getContractRealestate().getAddress().setHouse(param(request, "realestate.address.house"));
        entity.getContractRealestate().getAddress().setVillage(param(request, "realestate.address.village"));
        entity.getContractRealestate().getAddress().setSoi(param(request, "realestate.address.soi"));
        entity.getContractRealestate().getAddress().setRoad(param(request, "realestate.address.road"));
        entity.getContractRealestate().getAddress().setSubdistrict(param(request, "realestate.address.subdistrict"));
        entity.getContractRealestate().getAddress().setDistrict(param(request, "realestate.address.district"));
        entity.getContractRealestate().getAddress().setProvince(param(request, "realestate.address.province"));
        double space = 0;
        try {
            space += param(request, Integer.class, "realestate.space_rai") * 400;
        } catch (NullPointerException x) {
        }
        try {
            space += param(request, Integer.class, "realestate.space_ngan") * 100;
        } catch (NullPointerException x) {
        }
        try {
            space += param(request, Double.class, "realestate.space_sqwah");
        } catch (NullPointerException x) {
        }
        entity.getContractRealestate().setSpace(space);

        entity.getContractPlan().setPrepaidStarted(param(request, Date.class, "prepaid_started", "yyyy-MM-dd"));
        entity.getContractPlan().setPrepaidEnded(param(request, Date.class, "prepaid_ended", "yyyy-MM-dd"));
        entity.getContractPlan().setNextpaidStarted(param(request, Date.class, "nextpaid_started", "yyyy-MM-dd"));
        entity.getContractPlan().setNextpaidEnded(param(request, Date.class, "nextpaid_ended", "yyyy-MM-dd"));
        entity.getContractPlan().setDeadlineMonthly(param(request, "plan.deadline_monthly"));
        entity.getContractPlan().setDeadlineYearly(param(request, "plan.deadline_yearly"));
        entity.getContractPlan().setDeadlineYearlyDate(param(request, "plan.deadline_yearly_date"));
        entity.getContractPlan().setDeadline(param(request, Date.class, "plan.deadline", "yyyy-MM-dd"));
        entity.getContractPlan().setFinerate(param(request, Double.class, "plan.finerate"));

        entity.setContractAppointmentList(new ArrayList<>());
        entity.setContractAppointmentDatingList(new ArrayList<>());
        for (var find : model(ContractAppointmentType.class).finds()) {
            if (flag(request, Integer.class, find.getCode() + ".fee_type")) {
                entity.getContractAppointmentList().add(new ContractAppointment(
                        entity,
                        find,
                        model(ContractAppointmentFeeType.class).find(
                                param(request, Integer.class, find.getCode() + ".fee_type")
                        ),
                        param(request, String.class, find.getCode() + ".label"),
                        param(request, Double.class, find.getCode() + ".amount")
                ));
                for (var entry : pivot(new Object[][]{
                    param(request, Date[].class, find.getCode() + ".dating", "yyyy-MM-dd"),
                    param(request, Double[].class, find.getCode() + ".dating.amount")
                })) {
                    if (entry[0] != null && entry[1] != null) {
                        entity.getContractAppointmentDatingList().add(new ContractAppointmentDating(entity, find, (Date) entry[0], (Double) entry[1]));
                    }
                }
            } else if (flag(request, String.class, find.getCode() + ".label")) {
                entity.getContractAppointmentList().add(new ContractAppointment(
                        entity,
                        find,
                        null,
                        param(request, String.class, find.getCode() + ".label"),
                        null
                ));
            }
        }

        entity.getContractCollateral().setCash(param(request, Double.class, "collateral.cash"));
        entity.getContractCollateral().setCashText(param(request, "collateral.cash_text"));
        entity.getContractCollateral().setCashRcpt(param(request, "collateral.cash_rcpt"));
        entity.getContractCollateral().setCashRcptDated(param(request, Date.class, "collateral.cash_rcpt_dated", "yyyy-MM-dd"));
        entity.getContractCollateral().setCashierCheque(param(request, Double.class, "collateral.cashier_cheque"));
        entity.getContractCollateral().setCashierChequeText(param(request, "collateral.cashier_cheque_text"));
        entity.getContractCollateral().setCashierChequeRcpt(param(request, "collateral.cashier_cheque_rcpt"));
        entity.getContractCollateral().setCashierChequeRcptDated(param(request, Date.class, "collateral.cashier_cheque_rcpt_dated", "yyyy-MM-dd"));
        entity.getContractCollateral().setBankCollateral(param(request, Double.class, "collateral.bank_collateral"));
        entity.getContractCollateral().setBankCollateralText(param(request, "collateral.bank_collateral_text"));
        entity.getContractCollateral().setBankCollateralNo(param(request, "collateral.bank_collateral_no"));
        entity.getContractCollateral().setBankCollateralDated(param(request, Date.class, "collateral.bank_collateral_dated", "yyyy-MM-dd"));

        if (model(Contract.class).put(entity)) {
            redirect(response, ".");
        } else {
            throw ApplicationException.Type.uncommited_transaction.dispatch("", null);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
