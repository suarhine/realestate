<%-- 
    Document   : input
    Created on : Nov 17, 2021, 1:36:09 AM
    Author     : Pathompong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="p" uri="/page" %>
<%@taglib prefix="f" uri="/functions" %>
<%@taglib prefix="ui" uri="/contract" %>
<jsp:useBean id="l" class="org.realestate.view.bean.Lookup" />
<jsp:useBean id="o" class="org.realestate.view.bean.Option" />
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="index.css" />
    <script type="module" src="index.js"></script>
    <title>JSP Page</title>
  </head>
  <body data-page="input">
    <h1>บันทึกสัญญาเช่าทรัพย์สิน</h1>
    <form method="post">
      <input name="id" value="${param.id}" type="hidden" />
      <table class="-no-border">
        <thead>
          <tr>
            <td colspan="4">ข้อมูลสัญญาเช่า</td>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>ประเภทสัญญาเช่าทรัพย์สิน</td>
            <td colspan="3">
              <select name="type">
                <option value=""></option>
                <c:forEach var="i" items="${l.contractType}">
                    <option value="${i.id}" ${find.type == i ? 'selected' : ''}>${i.label}</option>
                </c:forEach>
              </select>
            </td>
          </tr>
          <tr>
            <td>เลขที่สัญญา</td>
            <td><input name="code" value="${find.code}" type="text" /></td>
            <td>ลงวันที่สัญญา</td>
            <td><input name="dated" value="${f:format(find.dated, 'yyyy-MM-dd')}" type="date" /></td>
          </tr>
          <tr>
            <td>บันทึกขัอตกลง</td>
            <td><input name="note" value="${find.note}" type="text" /></td>
            <td>ลงวันที่บันทึกขัอตกลง</td>
            <td><input name="note_dated" value="${f:format(find.noteDated, 'yyyy-MM-dd')}" type="date" /></td>
          </tr>
          <tr>
            <td colspan="4">คู่สัญญาระหว่าง</td>
          </tr>
          <tr>
            <td>ผู้ให้เช่า</td>
            <td colspan="3">องค์การบริหารส่วนจังหวัดภูเก็ต</td>
          </tr>
          <tr>
            <td>ผู้ให้เช่าโดย</td>
            <td><input name="lessor.name" value="${find.contractLessor.name}" type="text" /></td>
            <td>ตำแหน่ง</td>
            <td><input name="lessor.role" value="${find.contractLessor.role}" type="text" /></td>
          </tr>
          <tr>
            <td>ผู้เช่า</td>
            <td colspan="3"><input name="lessee.name" value="${find.contractLessee.name}" type="text" /></td>
          </tr>
          <tr>
            <td>ผู้เช่าโดย</td>
            <td><input name="lessee.representative" value="${find.contractLessee.representative}" type="text" /></td>
            <td>ตำแหน่ง</td>
            <td><input name="lessee.representative_role" value="${find.contractLessee.representativeRole}" type="text" /></td>
          </tr>
          <tr>
            <td colspan="3">หมายเลขบัตรประชาชน / เลขที่จดทะเบียนนิติบุคคล</td>
            <td><input name="lessee.code" value="${find.contractLessee.code}" type="text" /></td>
          </tr>
          <tr>
            <td>ที่อยู่ผู้เช่า</td>
            <td>ที่อยู่ตามทะเบียนบ้าน</td>
            <td></td>
            <td>ที่อยู่ปัจจุบัน</td>
          </tr>
          <tr>
            <td>บ้านเลขที่</td>
            <td><input name="lessee.registry.house" value="${find.contractLessee.registry.house}" type="text" /></td>
            <td></td>
            <td><input name="lessee.contact.house" value="${find.contractLessee.contact.house}" type="text" /></td>
          </tr>
          <tr>
            <td>หมู่ที่</td>
            <td><input name="lessee.registry.village" value="${find.contractLessee.registry.village}" type="text" /></td>
            <td></td>
            <td><input name="lessee.contact.village" value="${find.contractLessee.contact.village}" type="text" /></td>
          </tr>
          <tr>
            <td>ตรอก/ซอย</td>
            <td><input name="lessee.registry.soi" value="${find.contractLessee.registry.soi}" type="text" /></td>
            <td></td>
            <td><input name="lessee.contact.soi" value="${find.contractLessee.contact.soi}" type="text" /></td>
          </tr>
          <tr>
            <td>ถนน</td>
            <td><input name="lessee.registry.road" value="${find.contractLessee.registry.road}" type="text" /></td>
            <td></td>
            <td><input name="lessee.contact.road" value="${find.contractLessee.contact.road}" type="text" /></td>
          </tr>
          <tr>
            <td>ตำบล/แขวง</td>
            <td><input name="lessee.registry.subdistrict" value="${find.contractLessee.registry.subdistrict}" type="text" /></td>
            <td></td>
            <td><input name="lessee.contact.subdistrict" value="${find.contractLessee.contact.subdistrict}" type="text" /></td>
          </tr>
          <tr>
            <td>อำเภอ/เขต</td>
            <td><input name="lessee.registry.district" value="${find.contractLessee.registry.district}" type="text" /></td>
            <td></td>
            <td><input name="lessee.contact.district" value="${find.contractLessee.contact.district}" type="text" /></td>
          </tr>
          <tr>
            <td>จังหวัด</td>
            <td><input name="lessee.registry.province" value="${find.contractLessee.registry.province}" type="text" /></td>
            <td></td>
            <td><input name="lessee.contact.province" value="${find.contractLessee.contact.province}" type="text" /></td>
          </tr>
          <tr>
            <td>รหัสไปรษณีย์</td>
            <td><input name="lessee.registry.zipcode" value="${find.contractLessee.registry.zipcode}" type="text" /></td>
            <td></td>
            <td><input name="lessee.contact.zipcode" value="${find.contractLessee.contact.zipcode}" type="text" /></td>
          </tr>
          <tr>
            <td>หมายเลขโทรศัพท์</td>
            <td><input name="lessee.registry.phone" value="${find.contractLessee.registry.phone}" type="text" /></td>
            <td></td>
            <td><input name="lessee.contact.phone" value="${find.contractLessee.contact.phone}" type="text" /></td>
          </tr>
        </tbody>
        <thead>
          <tr>
            <td colspan="4"><hr/></td>
          </tr>
          <tr>
            <td colspan="4">ทรัพย์สินที่เช่า</td>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td></td>
            <td>
              <select name="realestate.type">
                <option value=""></option>
                <c:forEach var="i" items="${l.contractRealestateType}">
                    <option value="${i.id}" ${find.contractRealestate.type == i ? 'selected' : ''}>${i.label}</option>
                </c:forEach>
              </select>
            </td>
            <td colspan="2"><input name="realestate.name" value="${find.contractRealestate.name}" type="text" /></td>
          </tr>
          <tr>
            <td>ที่ตั้งทรัพย์สินที่เช่า</td>
            <td></td>
            <td>บริเวณ</td>
            <td><input name="realestate.location" value="${find.contractRealestate.location}" type="text" /></td>
          </tr>
          <tr>
            <td>เลขที่</td>
            <td><input name="realestate.address.house" value="${find.contractRealestate.address.house}" type="text" /></td>
            <td>หมู่ที่</td>
            <td><input name="realestate.address.village" value="${find.contractRealestate.address.village}" type="text" /></td>
          </tr>
          <tr>
            <td>ตรอก/ซอย</td>
            <td><input name="realestate.address.soi" value="${find.contractRealestate.address.soi}" type="text" /></td>
            <td>ใกล้เคียงกับ</td>
            <td><input name="realestate.nearby" value="${find.contractRealestate.nearby}" type="text" /></td>
          </tr>
          <tr>
            <td>ถนน</td>
            <td><input name="realestate.address.road" value="${find.contractRealestate.address.road}" type="text" /></td>
            <td>ตำบล/แขวง</td>
            <td><input name="realestate.address.subdistrict" value="${find.contractRealestate.address.subdistrict}" type="text" /></td>
          </tr>
          <tr>
            <td>อำเภอ/เขต</td>
            <td><input name="realestate.address.district" value="${find.contractRealestate.address.district}" type="text" /></td>
            <td>จังหวัด</td>
            <td><input name="realestate.address.province" value="${find.contractRealestate.address.province}" type="text" /></td>
          </tr>
          <tr>
            <td colspan="2">ตั้งอยู่บนที่ดินตรงตามประกาศกระทรวงมหาดไทยลงวันที่</td>
            <td></td>
            <td><input name="realestate.moi_declare" value="${f:format(find.contractRealestate.moiDeclare, 'yyyy-MM-dd')}" type="date" /></td>
          </tr>
          <tr>
            <td>หมายเลขที่ดิน</td>
            <td><input name="realestate.deed_code" value="${find.contractRealestate.deedCode}" type="text" /></td>
            <td>โฉนดที่</td>
            <td><input name="realestate.deed_no" value="${find.contractRealestate.deedNo}" type="text" /></td>
          </tr>
          <tr>
            <td>จำนวนเนื่อที่ทรัพสินที่เช่าประมาณ</td>
            <td><input name="realestate.space_rai"
                       value="${find.contractRealestate.space >= 400 ? f:format(find.contractRealestate.space / 400, '0') : ''}"
                       type="text"
                       pattern="\d+" />ไร่</td>
            <td><input name="realestate.space_ngan"
                       value="${find.contractRealestate.space >= 100 ? f:format(find.contractRealestate.space % 400 / 100, '0') : ''}"
                       type="text"
                       pattern="[0-3]" />งาน</td>
            <td><input name="realestate.space_sqwah"
                       value="${f:format(find.contractRealestate.space % 100, '0.####')}"
                       type="text"
                       pattern="[1-9]?\d(\.\d+)?" />ตรว.</td>
          </tr>
          <tr>
            <td>วัตถุประสงค์เพื่อ</td>
            <td>
              <select name="objective">
                <option value=""></option>
                <c:forEach var="i" items="${l.contractObjective}">
                    <option value="${i.id}"
                            ${i.additional ? 'data-enable="objective_text"' : ''} ${find.objective == i ? 'selected' : ''}>${i.label}</option>
                </c:forEach>
              </select>
            </td>
            <td></td>
            <td><input name="objective_text" value="${find.objectiveText}" type="text" /></td>
          </tr>
        </tbody>
        <thead>
          <tr>
            <td colspan="4"><hr/></td>
          </tr>
          <tr>
            <td colspan="4">นัดชำระสัญญาเช่า</td>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>วันที่เริ่มต้นสัญญาเช่า</td>
            <td colspan="3"><input name="started" value="${f:format(find.started, 'yyyy-MM-dd')}" type="date" /></td>
          </tr>
          <tr>
            <td>วันที่สิ้นสุดสัญญาเช่า</td>
            <td><input name="ended" value="${f:format(find.ended, 'yyyy-MM-dd')}" type="date" /></td>
            <td>ระยะเวลาการเช่า</td>
            <td data-pane="rental-period"></td>
          </tr>
          <c:forEach var="i" items="${l.contractAppointmentType}">
              <p:declare appointment="${ui:appointment(find.contractAppointmentList, i)}"
                         dating="${ui:dating(find.contractAppointmentDatingList, i)}">
                  <tr>
                    <td>
                      ${i.label}
                      <c:if test="${i.labelable}">
                          <input name="${i.code}.label" value="${appointment.label}" type="text" />
                      </c:if>
                    </td>
                    <td>
                      <select name="${i.code}.fee_type" data-fee-detail="${i.code}">
                        <option value=""></option>
                        <c:forEach var="i" items="${l.contractAppointmentFeeType}">
                            <option value="${i.id}"
                                    ${appointment.feeType == i ? f:concat(
                                      'selected data-amount="',
                                      f:format(appointment.amount, '0.##'),
                                      '" data-dating="',
                                      f:parse('html', ui:dating_json(dating), ''),
                                      '"'
                                      ) : ''}>${i.label}</option>
                        </c:forEach>
                      </select>
                    </td>
                    <td></td>
                    <td></td>
                  </tr>
                  <tr>
                    <td></td>
                    <td colspan="3"></td>
                  </tr>
              </p:declare>
          </c:forEach>
          <tr>
            <td colspan="4">ชำระค่าเช่าล่วงหน้า ณ วันทำสัญญา</td>
          </tr>
          <tr>
            <td colspan="4">
              จ่ายล่วงหน้า (ปีที่ 1)
            </td>
          </tr>
          <tr>
            <td>ระหว่างวันที่</td>
            <td><input name="plan.prepaid_started" value="${f:format(find.contractPlan.prepaidStarted, 'yyyy-MM-dd')}" type="date" /></td>
            <td>จนถึงวันที่</td>
            <td><input name="plan.prepaid_ended" value="${f:format(find.contractPlan.prepaidEnded, 'yyyy-MM-dd')}" type="date" /></td>
          </tr>
          <tr>
            <td colspan="4">
              จ่ายปีถัดไป
            </td>
          </tr>
          <tr>
            <td>ระหว่างวันที่</td>
            <td><input name="plan.nextpaid_started" value="${f:format(find.contractPlan.nextpaidStarted, 'yyyy-MM-dd')}" type="date" /></td>
            <td>จนถึงวันที่</td>
            <td><input name="plan.nextpaid_ended" value="${f:format(find.contractPlan.nextpaidEnded, 'yyyy-MM-dd')}" type="date" /></td>
          </tr>
          <tr>
            <td colspan="4">เงื่อนไขการชำระค่าเช่า</td>
          </tr>
          <tr>
            <td>เป็นรายเดือน</td>
            <td colspan="3">ภายในวันที่<input name="plan.deadline_monthly" value="${find.contractPlan.deadlineMonthly}" type="text" placeholder="ค่าเริ่มต้นเป็นวันที่ลงสัญญา" />ของเดือนถัดไป</td>
          </tr>
          <tr>
            <td>เป็นรายปี</td>
            <td colspan="3">
              <!--ทุกวันเริ่มต้นของสัญญาเช่าในแต่ละปี-->
              ภายในวันที่
              <input name="plan.deadline_yearly_date" value="${find.contractPlan.deadlineYearlyDate}" type="text" placeholder="ค่าเริ่มต้นเป็นวันที่ลงสัญญา" />
              เดือน
              <select name="plan.deadline_yearly">
                <option value="">ค่าเริ่มต้นเป็นเดือนที่ลงสัญญา</option>
                <c:forEach var="i" items="${o.month}">
                    <option value="${i.value}"
                            ${find.contractPlan.deadlineYearly == i.value ? 'selected' : ''}>${i.label}</option>
                </c:forEach>
              </select>
              ของปีถัดไป</td>
          </tr>
          <tr>
            <td colspan="2">เป็นรายงวด (แบ่งชำระเป็นรายงวด ๆ ละเดือน)</td>
            <td>ภายในวันที่</td>
            <td><input name="plan.deadline" value="${f:format(find.contractPlan.deadline, 'yyyy-MM-dd')}" type="date" /></td>
          </tr>
          <tr>
            <td>อัตราชำระเบี้ยปรับ</td>
            <td colspan="3">
              ร้อยละ <c:if test="${find.contractPlan.finerate == 1.25
                                   || find.contractPlan.finerate == 1.5
                                   || find.contractPlan.finerate == null}">
                    <select name="plan.finerate">
                      <option value=""></option>
                      <option value="1.25" ${find.contractPlan.finerate == 1.25 ? 'selected' : ''}>1.25</option>
                      <option value="1.50" ${find.contractPlan.finerate == 1.5 ? 'selected' : ''}>1.50</option>
                      <option value="+">...</option>
                    </select>
              </c:if><c:if test="${!(find.contractPlan.finerate == 1.25
                                   || find.contractPlan.finerate == 1.5
                                   || find.contractPlan.finerate == null)}">
                           <input name="plan.finerate" value="${find.contractPlan.finerate}" />
              </c:if>ต่อเดือน ของเงินค่าเช่าที่ค้างชำระ เศษของเดือนให้นับเป็น 1 เดือน
            </td>
          </tr>
        <thead>
          <tr>
            <td colspan="4"><hr/></td>
          </tr>
          <tr>
            <td colspan="4">หลักประกันสัญญาเช่า</td>
          </tr>
        </thead>
        <tr>
          <td colspan="4">ในวันทำสัญญา ได้นำหลักประกันสัญญา</td>
        </tr>
        <tr>
          <td>เป็นเงินสด/จำนวนเงิน</td>
          <td><input name="collateral.cash" value="${f:format(find.contractCollateral.cash, '0.##')}" type="text" />บาท</td>
          <td></td>
          <td>(<input name="collateral.cash_text" value="${find.contractCollateral.cashText}" type="text" />)</td>
        </tr>
        <tr>
          <td>ตามใบเสร็จรับเงิน</td>
          <td>เลขที่ RCPT<input name="collateral.cash_rcpt" value="${find.contractCollateral.cashRcpt}" type="text" /></td>
          <td>ลงวันที่</td>
          <td><input name="collateral.cash_rcpt_dated" value="${f:format(find.contractCollateral.cashRcptDated, 'yyyy-MM-dd')}" type="date" /></td>
        </tr>
        <tr>
          <td>แคชเชียร์เช็คธนาคาร/จำนวนเงิน</td>
          <td><input name="collateral.cashier_cheque" value="${f:format(find.contractCollateral.cashierCheque, '0.##')}" type="text" />บาท</td>
          <td></td>
          <td>(<input name="collateral.cashier_cheque_text" value="${find.contractCollateral.cashierChequeText}" type="text" />)</td>
        </tr>
        <tr>
          <td>ตามใบเสร็จรับเงิน</td>
          <td>เลขที่ RCPT<input name="collateral.cashier_cheque_rcpt" value="${find.contractCollateral.cashierChequeRcpt}" type="text" /></td>
          <td>ลงวันที่</td>
          <td><input name="collateral.cashier_cheque_rcpt_date" value="${f:format(find.contractCollateral.cashierChequeRcptDated, 'yyyy-MM-dd')}" type="date" /></td>
        </tr>
        <tr>
          <td>เป็นหนังสือค้ำประกันของธนาคาร</td>
          <td><input name="collateral.bank_collateral" value="${f:format(find.contractCollateral.bankCollateral, '0.##')}" type="text" />บาท</td>
          <td></td>
          <td>(<input name="collateral.bank_collateral_text" value="${find.contractCollateral.bankCollateralText}" type="text" />)</td>
        </tr>
        <tr>
          <td>เลขที่</td>
          <td><input name="collateral.bank_collateral_no" value="${find.contractCollateral.bankCollateralNo}" type="text" /></td>
          <td>ลงวันที่</td>
          <td><input name="collateral.bank_collateral_dated" value="${f:format(find.contractCollateral.bankCollateralDated, 'yyyy-MM-dd')}" type="date" /></td>
        </tr>
        </tbody>
        <tfoot>
          <tr>
            <td colspan="4">
              <button type="button" data-rel-back>ย้อนกลับ</button>
              <div class="right-f">
                <c:if test="${find != null}">
                    <button name="del" onclick="return confirm('ยืนยันการลบข้อมูล')">ลบ</button>
                </c:if>
                <button type="button" data-rel-reload>คืนค่า</button>
                <button>บันทึก</button>
              </div>
            </td>
          </tr>
        </tfoot>
      </table>
    </form>
  </body>
</html>
