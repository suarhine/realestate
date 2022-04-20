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
    <div class="headers--name">
      บันทึกสัญญาเช่าทรัพย์สิน
      <c:if test="${not empty ref}">
        บันทึกแนบท้ายของสัญญาเช่า <a href="?id=${ref.id}">${ref.code}</a>
      </c:if>
    </div>
    <br />
    <form method="post">
      <p:declare ref="${empty ref ? find : ref}" is_ref="${not empty ref}">
        <input name="id" value="${find.id}" type="hidden" />
        <input name="ref" value="${param.ref}" type="hidden" />
        <div class="tab-stap" data-rel-active-tab-header>
          <label class="tab-stap--item --active" for="active-tap-contract">
            <div class="stap-number">1</div>
            <div class="stap-detail">
              <div class="stap-detail--title">ข้อมูลสัญญาเช่า</div>
              <!-- <div class="stap-detail--subtitle">ข้อมูลสัญญาเช่า</div> -->
            </div>
          </label>
          <label class="tab-stap--item" for="active-tap-realestate">
            <div class="stap-number">2</div>
            <div class="stap-detail">
              <div class="stap-detail--title">ข้อมูลทรัพย์สินที่เช่า</div>
              <!-- <div class="stap-detail--subtitle">ข้อมูลทรัพย์สินที่เช่า</div> -->
            </div>
          </label>
          <label class="tab-stap--item" for="active-tap-appointment">
            <div class="stap-number">3</div>
            <div class="stap-detail">
              <div class="stap-detail--title">กำหนดการนัดชำระสัญญาเช่า</div>
              <!-- <div class="stap-detail--subtitle">กำหนดการนัดชำระสัญญาเช่า</div> -->
            </div>
          </label>
          <label class="tab-stap--item" for="active-tap-collateral">
            <div class="stap-number">4</div>
            <div class="stap-detail">
              <div class="stap-detail--title">หลักประกันสัญญาเช่า</div>
              <!-- <div class="stap-detail--subtitle">หลักประกันสัญญาเช่า</div> -->
            </div>
          </label>
        </div>
        <input id="active-tap-contract" name="active-tap" value="contract" type="radio" checked>
        <div class="stap-content content-form">
          <div class="content-form--group">
            <div class="row">
              <div class="input-box --flex1">
                <label class="input-box--label">
                  ประเภทสัญญาเช่าทรัพย์สิน
                </label>
                <div class="input-box--field">
                  <select name="type">
                    <option value=""></option>
                    <c:forEach var="i" items="${l.contractType}">
                      <option value="${i.id}" ${ref.type == i ? 'selected' : ''}>${i.label}</option>
                    </c:forEach>
                  </select>
                </div>
              </div>
            </div>
            <div class="row">
              <div class="input-box --flex1 ">
                <label class="input-box--label col-6">
                  เลขที่สัญญา
                </label>
                <div class="input-box--field">
                  <input name="code" value="${find.code}" type="text" />
                </div>
              </div>
              <div class="input-box col-9">
                <label class="input-box--label col-13">
                  ลงวันที่สัญญา
                </label>
                <div class="input-box--field">
                  <input name="dated" value="${f:format(find.dated, 'yyyy-MM-dd')}" type="date" />
                </div>
              </div>
            </div>
            <div class="row">
              <div class="input-box --flex1">
                <label class="input-box--label col-6">
                  บันทึกขัอตกลง
                </label>
                <div class="input-box--field">
                  <input name="note" value="${find.note}" type="text" />
                </div>
              </div>
              <div class="input-box col-9">
                <label class="input-box--label col-13">
                  ลงวันที่บันทึกขัอตกลง
                </label>
                <div class="input-box--field">
                  <input name="note_dated" value="${f:format(find.noteDated, 'yyyy-MM-dd')}" type="date" />
                </div>
              </div>
            </div>
          </div>
          <div class="content-form--group">
            <h3 class="title-form">คู่สัญญาระหว่าง</h3>
            <div class="row">
              <div class="input-box --flex1">
                <label class="input-box--label col-3">ผู้ให้เช่า</label>
                <div class="input-box--field">องค์การบริหารส่วนจังหวัดภูเก็ต</div>
              </div>
            </div>
            <div class="row">
              <div class="input-box --flex1">
                <label class="input-box--label col-6">ผู้ให้เช่าโดย</label>
                <div class="input-box--field"><input name="lessor.name" value="${find.contractLessor.name}" type="text" /></div>
              </div>
              <div class="input-box --flex1">
                <label class="input-box--label">ตำแหน่ง</label>
                <div class="input-box--field"><input name="lessor.role" value="${find.contractLessor.role}" type="text" /></div>
              </div>
            </div>
            <div class="row">
              <div class="input-box --flex1">
                <label class="input-box--label col-3">ผู้เช่า</label>
                <div class="input-box--field"><input name="lessee.name" value="${ref.contractLessee.name}" type="text" /></div>
              </div>
            </div>
            <div class="row">
              <div class="input-box --flex1">
                <label class="input-box--label col-6">ผู้เช่าโดย</label>
                <div class="input-box--field"><input name="lessee.representative" value="${ref.contractLessee.representative}" type="text" /></div>
              </div>
              <div class="input-box --flex1">
                <label class="input-box--label">ตำแหน่ง</label>
                <div class="input-box--field"><input name="lessee.representative_role" value="${ref.contractLessee.representativeRole}" type="text" /></div>
              </div>
            </div>
          </div>
          <div class="content-form--group">
            <h3 class="title-form">ที่อยู่ผู้เช่า</h3>
            <h4 class="subtitle-form">ที่อยู่ตามทะเบียนบ้าน</h4>
            <section class="address-form">
              <div class="row">
                <div class="input-box house-col">
                  <label class="input-box--label">บ้านเลขที่</label>
                  <div class="input-box--field">
                    <input name="lessee.registry.house" value=" ${ref.contractLessee.registry.house}" type="text" />
                  </div>
                </div>
                <div class="input-box village-col">
                  <label class="input-box--label">หมู่ที่</label>
                  <div class="input-box--field">
                    <input name="lessee.registry.village" value=" ${ref.contractLessee.registry.village}" type="text" />
                  </div>
                </div>
                <div class="input-box soi-col">
                  <label class="input-box--label">ตรอก/ซอย</label>
                  <div class="input-box--field">
                    <input name="lessee.registry.soi" value=" ${ref.contractLessee.registry.soi}" type="text" />
                  </div>
                </div>
              </div>
              <div class="row">
                <div class="input-box road-col">
                  <label class="input-box--label">ถนน</label>
                  <div class="input-box--field">
                    <input name="lessee.registry.road" value=" ${ref.contractLessee.registry.road}" type="text" />
                  </div>
                </div>
                <div class="input-box subdistrict-col">
                  <label class="input-box--label">ตำบล/แขวง</label>
                  <div class="input-box--field">
                    <input name="lessee.registry.subdistrict" value=" ${ref.contractLessee.registry.subdistrict}" type="text" />
                  </div>
                </div>
              </div>
              <div class="row">
                <div class="input-box district-col">
                  <label class="input-box--label">อำเภอ/เขต</label>
                  <div class="input-box--field">
                    <input name="lessee.registry.district" value=" ${ref.contractLessee.registry.district}" type="text" />
                  </div>
                </div>
                <div class="input-box province-col">
                  <label class="input-box--label">จังหวัด</label>
                  <div class="input-box--field">
                    <input name="lessee.registry.province" value=" ${ref.contractLessee.registry.province}" type="text" />
                  </div>
                </div>
              </div>
              <div class="row">
                <div class="input-box zipcode-col">
                  <label class="input-box--label">รหัสไปรษณีย์</label>
                  <div class="input-box--field">
                    <input name="lessee.registry.zipcode" value=" ${ref.contractLessee.registry.zipcode}" type="text" />
                    <!--
                    <td><input name="lessee.registry.zipcode" value=" ${ref.contractLessee.registry.zipcode}" type="text" /></td>
                    -->
                  </div>
                </div>
                <div class="input-box phone-col">
                  <label class="input-box--label">หมายเลขโทรศัพท์</label>
                  <div class="input-box--field">
                    <input name="lessee.registry.phone" value=" ${ref.contractLessee.registry.phone}" type="text" />
                  </div>
                </div>
              </div>
            </section>
            <h4 class="subtitle-form">ที่อยู่ปัจจุบัน</h4>
            <section class="address-form">
              <div class="row">
                <div class="input-box house-col">
                  <label class="input-box--label">บ้านเลขที่</label>
                  <div class="input-box--field">
                    <input name="lessee.contact.house" value=" ${ref.contractLessee.contact.house}" type="text" />
                  </div>
                </div>
                <div class="input-box village-col">
                  <label class="input-box--label">หมู่ที่</label>
                  <div class="input-box--field">
                    <input name="lessee.contact.village" value=" ${ref.contractLessee.contact.village}" type="text" />
                  </div>
                </div>
                <div class="input-box soi-col">
                  <label class="input-box--label">ตรอก/ซอย</label>
                  <div class="input-box--field">
                    <input name="lessee.contact.soi" value=" ${ref.contractLessee.contact.soi}" type="text" />
                  </div>
                </div>
              </div>
              <div class="row">
                <div class="input-box road-col">
                  <label class="input-box--label">ถนน</label>
                  <div class="input-box--field">
                    <input name="lessee.contact.road" value=" ${ref.contractLessee.contact.road}" type="text" />
                  </div>
                </div>
                <div class="input-box subdistrict-col">
                  <label class="input-box--label">ตำบล/แขวง</label>
                  <div class="input-box--field">
                    <input name="lessee.contact.subdistrict" value=" ${ref.contractLessee.contact.subdistrict}" type="text" />
                  </div>
                </div>
              </div>
              <div class="row">
                <div class="input-box district-col">
                  <label class="input-box--label">อำเภอ/เขต</label>
                  <div class="input-box--field">
                    <input name="lessee.contact.district" value=" ${ref.contractLessee.contact.district}" type="text" />
                  </div>
                </div>
                <div class="input-box province-col">
                  <label class="input-box--label">จังหวัด</label>
                  <div class="input-box--field">
                    <input name="lessee.contact.province" value=" ${ref.contractLessee.contact.province}" type="text" />
                  </div>
                </div>
              </div>
              <div class="row">
                <div class="input-box zipcode-col">
                  <label class="input-box--label">รหัสไปรษณีย์</label>
                  <div class="input-box--field">
                    <input name="lessee.contact.zipcode" value=" ${ref.contractLessee.contact.zipcode}" type="text" />
                  </div>
                </div>
                <div class="input-box phone-col">
                  <label class="input-box--label">หมายเลขโทรศัพท์</label>
                  <div class="input-box--field">
                    <input name="lessee.contact.phone" value=" ${ref.contractLessee.contact.phone}" type="text" />
                  </div>
                </div>
              </div>
            </section>
          </div>

          <div class="button-control">
            <button type="button" class="btn" data-rel-back>ย้อนกลับ</button>
            <c:if test="${find != null}">
              <button name="del" class="btn">ลบ</button>
            </c:if>
            <button class="btn" type="button" data-rel-reload>คืนค่า</button>
            <button type="button" class="btn" data-rel-ctrl-tab="next">ถัดไป</button>
          </div>
        </div>

        <input id="active-tap-realestate" name="active-tap" value="realestate" type="radio" >
        <div class="stap-content content-form">
          <div class="content-form--group leased-property-form">
            <div class="row">
              <div class="input-box --flex1 type-col">
                <label class="input-box--label">ประเภททรัพย์สิน</label>
                <div class="input-box--field">
                  <select name="realestate.type">
                    <option value=""></option>
                    <c:forEach var="i" items="${l.contractRealestateType}">
                      <option value="${i.id}"  ${ref.contractRealestate.type == i ? 'selected' : ''}>${i.label}</option>
                    </c:forEach>
                  </select>
                </div>
              </div>
              <div class="input-box --flex1 name-col">
                <!-- <label class="input-box--label"></label> -->
                <div class="input-box--field">
                  <input name="realestate.name" value=" ${ref.contractRealestate.name}" type="text" />
                </div>
              </div>
            </div>
            <div class="row">
              <div class="input-box --flex1 location-property-col">
                <label class="input-box--label">ที่ตั้งทรัพย์สินที่เช่า</label>
                <div class="input-box--field">
                </div>
              </div>
              <div class="input-box --flex1 location-col">
                <label class="input-box--label">บริเวณ</label>
                <div class="input-box--field">
                  <input name="realestate.location" value=" ${ref.contractRealestate.location}" type="text" />
                </div>
              </div>
            </div>
            <div class="row">
              <div class="input-box house-col">
                <label class="input-box--label">เลขที่</label>
                <div class="input-box--field">
                  <input name="realestate.address.house" value=" ${ref.contractRealestate.address.house}" type="text" />
                </div>
              </div>
              <div class="input-box village-col">
                <label class="input-box--label">หมู่ที่</label>
                <div class="input-box--field">
                  <input name="realestate.address.village" value=" ${ref.contractRealestate.address.village}" type="text" />
                </div>
              </div>
              <div class="input-box --flex1 soi-col">
                <label class="input-box--label">ตรอก/ซอย</label>
                <div class="input-box--field">
                  <input name="realestate.address.soi" value=" ${ref.contractRealestate.address.soi}" type="text" />
                </div>
              </div>
            </div>
            <div class="row">
              <div class="input-box --flex1 nearby-col">
                <label class="input-box--label">ใกล้เคียงกับ</label>
                <div class="input-box--field">
                  <input name="realestate.nearby" value=" ${ref.contractRealestate.nearby}" type="text" />
                </div>
              </div>
            </div>
            <div class="row">
              <div class="input-box --flex1 road-col">
                <label class="input-box--label">ถนน</label>
                <div class="input-box--field">
                  <input name="realestate.address.road" value=" ${ref.contractRealestate.address.road}" type="text" />
                </div>
              </div>
              <div class="input-box --flex1 subdistrict-col">
                <label class="input-box--label">ตำบล/แขวง</label>
                <div class="input-box--field">
                  <input name="realestate.address.subdistrict" value=" ${ref.contractRealestate.address.subdistrict}" type="text" />
                </div>
              </div>
            </div>
            <div class="row">
              <div class="input-box --flex1 district-col">
                <label class="input-box--label">อำเภอ/เขต</label>
                <div class="input-box--field">
                  <input name="realestate.address.district" value=" ${ref.contractRealestate.address.district}" type="text" />
                </div>
              </div>
              <div class="input-box --flex1 province-col">
                <label class="input-box--label">จังหวัด</label>
                <div class="input-box--field">
                  <input name="realestate.address.province" value=" ${ref.contractRealestate.address.province}" type="text" />
                </div>
              </div>
            </div>
            <div class="row">
              <div class="input-box moi-declare-col">
                <label class="input-box--label">ตั้งอยู่บนที่ดินตรงตามประกาศกระทรวงมหาดไทยลงวันที่</label>
                <div class="input-box--field">
                  <input name="realestate.moi_declare" value="${f:format(find.contractRealestate.moiDeclare, 'yyyy-MM-dd')}" type="date" />
                </div>
              </div>
            </div>
            <div class="row">
              <div class="input-box --flex1 deed-code-col">
                <label class="input-box--label">หมายเลขที่ดิน</label>
                <div class="input-box--field">
                  <input name="realestate.deed_code" value=" ${ref.contractRealestate.deedCode}" type="text" />
                </div>
              </div>
              <div class="input-box --flex1 deed-no-col">
                <label class="input-box--label">โฉนดที่</label>
                <div class="input-box--field">
                  <input name="realestate.deed_no" value=" ${ref.contractRealestate.deedNo}" type="text" />
                </div>
              </div>
            </div>
            <div class="row">
              <div class="input-box --flex1 space-col">
                <label class="input-box--label">จำนวนเนื่อที่ทรัพสินที่เช่าประมาณ</label>
                <div class="input-box--field">
                  <input name="realestate.space_rai" value="${ref.contractRealestate.space >= 400 ? f:format(find.contractRealestate.space / 400, '0') : ''}" type="text" pattern="\d+" /><span
                    class="input-suffix">ไร่</span>
                </div>
                <div class="input-box--field">
                  <input name="realestate.space_ngan" value="${ref.contractRealestate.space >= 100 ? f:format(find.contractRealestate.space % 400 / 100, '0') : ''}" type="text" pattern="[0-3]" /><span
                    class="input-suffix">งาน</span>
                </div>
                <div class="input-box--field">
                  <input name="realestate.space_sqwah" value="${f:format(find.contractRealestate.space % 100, '0.####')}" type="text" pattern="\d\d(\.\d+)?" /><span
                    class="input-suffix">ตรว.</span>
                </div>
              </div>
            </div>
            <div class="row">
              <div class="input-box objective-col">
                <label class="input-box--label">วัตถุประสงค์เพื่อ</label>
                <div class="input-box--field">
                  <select name="objective">
                    <option value=""></option>
                    <c:forEach var="i" items="${l.contractObjective}">
                      <option value="${i.id}"
                              ${i.additional ? 'data-enable="objective_text"' : ''}  ${ref.objective == i ? 'selected' : ''}>${i.label}</option>
                    </c:forEach>
                  </select>
                </div>
              </div>
              <div class="input-box --flex1">
                <!-- <label class="input-box--label"></div> -->
                <div class="input-box--field">
                  <input name="objective_text" value=" ${ref.objectiveText}" type="text" />
                </div>
              </div>
            </div>
          </div>

          <div class="button-control">
            <button type="button" class="btn" data-rel-ctrl-tab="prev">ย้อนกลับ</button>
            <c:if test="${find != null}">
              <button name="del" class="btn">ลบ</button>
            </c:if>
            <button class="btn" type="button" data-rel-reload>คืนค่า</button>
            <button type="button" class="btn" data-rel-ctrl-tab="next">ถัดไป</button>
          </div>
        </div>

        <input id="active-tap-appointment" name="active-tap" value="appointment" type="radio" >
        <div class="stap-content content-form">
          <div class="content-form--group appointment-pay-form">
            <div class="row">
              <div class="input-box">
                <label class="input-box--label">วันที่เริ่มต้นสัญญาเช่า</label>
                <div class="input-box--field">
                  <input name="started" value="${f:format(find.started, 'yyyy-MM-dd')}" type="date" />
                </div>
              </div>
              <div class="input-box">
                <label class="input-box--label">วันที่สิ้นสุดสัญญาเช่า</label>
                <div class="input-box--field">
                  <input name="ended" value="${f:format(find.ended, 'yyyy-MM-dd')}" type="date" />
                </div>
              </div>
              <div class="input-box --flex1">
                <label class="input-box--label">ระยะเวลาการเช่า</label>
                <div class="input-box--field">
                  <label data-pane="rental-period"></label>
                </div>
              </div>
            </div>
            <c:forEach var="i" items="${l.contractAppointmentType}">
              <p:declare appointment="${ui:appointment(find.contractAppointmentList, i)}"
                         dating="${ui:dating(find.contractAppointmentDatingList, i)}">
                <div class="row">
                  <div class="input-box ${i.labelable ? '' : '--flex1'} rental-fee-col">
                    <label class="input-box--label">
                      ${i.label}
                    </label>
                    <div class="input-box--field">
                      <c:if test="${i.labelable}">
                        <div class="input-box--field">
                          <input name="${i.code}.label" value="${appointment.label}" type="text" />
                        </div>
                      </c:if>
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
                    </div>
                    <div></div>
                  </div>
                </div>
                <div class="row">
                  <div class="input-box ${i.labelable ? '' : '--flex1'} rental-fee-col">
                    <label class="input-box--label -blank">
                      --BLANK--
                    </label>
                    <div data-pane="payment-plan"></div>
                  </div>
                </div>
              </p:declare>
            </c:forEach>
          </div>
          <div class="content-form--group pay-rent-advance-form">
            <h3 class="title-form">ชำระค่าเช่าล่วงหน้า ณ วันทำสัญญา</h3>
            <h4 class="subtitle-form">จ่ายล่วงหน้า (ปีที่ 1)</h4>
            <div class="row">
              <div class="input-box">
                <label class="input-box--label">ระหว่างวันที่</label>
                <div class="input-box--field">
                  <input name="plan.prepaid_started" value="${f:format(find.contractPlan.prepaidStarted, 'yyyy-MM-dd')}" type="date" />
                </div>
              </div>
              <div class="input-box">
                <label class="input-box--label">จนถึงวันที่</label>
                <div class="input-box--field">
                  <input name="plan.prepaid_ended" value="${f:format(find.contractPlan.prepaidEnded, 'yyyy-MM-dd')}" type="date" />
                </div>
              </div>
            </div>
            <h4 class="subtitle-form">จ่ายปีถัดไป</h4>
            <div class="row">
              <div class="input-box">
                <label class="input-box--label">ระหว่างวันที่</label>
                <div class="input-box--field">
                  <input name="plan.nextpaid_started" value="${f:format(find.contractPlan.nextpaidStarted, 'yyyy-MM-dd')}" type="date" />
                </div>
              </div>
              <div class="input-box">
                <label class="input-box--label">จนถึงวันที่</label>
                <div class="input-box--field">
                  <input name="plan.nextpaid_ended" value="${f:format(find.contractPlan.nextpaidEnded, 'yyyy-MM-dd')}" type="date" />
                </div>
              </div>
            </div>
            <h4 class="subtitle-form">เงื่อนไขการชำระค่าเช่า</h4>
            <div class="row">
              <div class="input-box deadline-monthly-col">
                <label class="input-box--label">เป็นรายเดือน</label>
                <div class="input-box--field">
                  <span class="input-prefix">ภายในวันที่</span>
                  <input name="plan.deadline_monthly" value="${find.contractPlan.deadlineMonthly}" type="text" placeholder="ค่าเริ่มต้นเป็นวันที่ลงสัญญา" />
                  <span class="input-suffix">ของเดือนถัดไป</span>
                </div>
              </div>
            </div>
            <div class="row">
              <div class="input-box deadline-yearly-date-col">
                <label class="input-box--label">เป็นรายปี</label>
                <div class="input-box--field">
                  <!--ทุกวันเริ่มต้นของสัญญาเช่าในแต่ละปี-->
                  <span class="input-prefix">ภายในวันที่</span>
                  <input name="plan.deadline_yearly_date" value="${find.contractPlan.deadlineYearlyDate}" type="text"
                         placeholder="ค่าเริ่มต้นเป็นวันที่ลงสัญญา" />
                </div>
              </div>
              <div class="input-box">
                <label class="input-box--label">เดือน</label>
                <div class="input-box--field">
                  <select name="plan.deadline_yearly">
                    <option value="">ค่าเริ่มต้นเป็นเดือนที่ลงสัญญา</option>
                    <c:forEach var="i" items="${o.month}">
                      <option value="${i.value}"
                              ${find.contractPlan.deadlineYearly == i.value ? 'selected' : ''}>${i.label}</option>
                    </c:forEach>
                  </select>
                  <span class="input-suffix">ของปีถัดไป</span>
                </div>
              </div>
            </div>
            <div class="row">
              <div class="input-box">
                <label class="input-box--label">เป็นรายงวด (แบ่งชำระเป็นรายงวด ๆ ละเดือน)</label>
                <div class="input-box--field">
                  <span class="input-prefix"> ภายในวันที่</span>
                  <input name="plan.deadline" value="${f:format(find.contractPlan.deadline, 'yyyy-MM-dd')}" type="date" />
                </div>
              </div>
            </div>
            <div class="row">
              <div class="input-box">
                <label class="input-box--label">อัตราชำระเบี้ยปรับ</label>
                <div class="input-box--field">
                  <span class="input-prefix">ร้อยละ</span><c:if test="${find.contractPlan.finerate == 1.25
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
                  </c:if><span class="input-suffix">ต่อเดือน ของเงินค่าเช่าที่ค้างชำระ เศษของเดือนให้นับเป็น 1 เดือน</span>
                </div>
              </div>
            </div>
          </div>

          <div class="button-control">
            <button type="button" class="btn" data-rel-ctrl-tab="prev">ย้อนกลับ</button>
            <c:if test="${find != null}">
              <button name="del" class="btn">ลบ</button>
            </c:if>
            <button class="btn" type="button" data-rel-reload>คืนค่า</button>
            <button type="button" class="btn" data-rel-ctrl-tab="next">ถัดไป</button>
          </div>
        </div>

        <input id="active-tap-collateral" name="active-tap" value="collateral" type="radio" >
        <div class="stap-content content-form">
          <div class="content-form--group lease-collateral">
            <div class="row">
              <div class="input-box cash-col">
                <label class="input-box--label">เป็นเงินสด/จำนวนเงิน</label>
                <div class="input-box--field">
                  <input name="collateral.cash" value="${f:format(find.contractCollateral.cash, '0.##')}" type="text" />
                  <span class="input-suffix">บาท</span>
                </div>
              </div>
              <div class="input-box">
                <!-- <label class="input-box--label"></label> -->
                <div class="input-box--field cash-text-field">
                  (<input name="collateral.cash_text" value="${find.contractCollateral.cashText}" type="text" />)
                </div>
              </div>
            </div>
            <div class="row">
              <div class="input-box">
                <label class="input-box--label">ตามใบเสร็จรับเงิน</label>
                <div class="input-box--field">
                  <span class="input-prefix">เลขที่ RCPT</span>
                  <input name="collateral.cash_rcpt" value="${find.contractCollateral.cashRcpt}" type="text" />
                </div>
              </div>
              <div class="input-box cash-rcpt-dated-col">
                <label class="input-box--label">ลงวันที่</label>
                <div class="input-box--field">
                  <input name="collateral.cash_rcpt_dated" value="${f:format(find.contractCollateral.cashRcptDated, 'yyyy-MM-dd')}" type="date" />
                </div>
              </div>
            </div>
            <div class="row">
              <div class="input-box cashier-cheque-col">
                <label class="input-box--label">แคชเชียร์เช็คธนาคาร/จำนวนเงิน</label>
                <div class="input-box--field">
                  <input name="collateral.cashier_cheque" value="${f:format(find.contractCollateral.cashierCheque, '0.##')}" type="text" />
                  <span class="input-suffix">บาท</span>
                </div>
              </div>
              <div class="input-box">
                <!-- <label class="input-box--label"></label> -->
                <div class="input-box--field cash-text-field">
                  (<input name="collateral.cashier_cheque_text" value="${find.contractCollateral.cashierChequeText}" type="text" />)
                </div>
              </div>
            </div>
            <div class="row">
              <div class="input-box">
                <label class="input-box--label">ตามใบเสร็จรับเงิน</label>
                <div class="input-box--field">
                  <span class="input-prefix">เลขที่ RCPT</span>
                  <input name="collateral.cashier_cheque_rcpt" value="${find.contractCollateral.cashierChequeRcpt}" type="text" />
                </div>
              </div>
              <div class="input-box cashier-cheque-rcpt-date-col">
                <label class="input-box--label">ลงวันที่</label>
                <div class="input-box--field">
                  <input name="collateral.cashier_cheque_rcpt_date" value="${f:format(find.contractCollateral.cashierChequeRcptDated, 'yyyy-MM-dd')}" type="date" />
                </div>
              </div>
            </div>
            <div class="row">
              <div class="input-box bank-collateral-col">
                <label class="input-box--label">เป็นหนังสือค้ำประกันของธนาคาร</label>
                <div class="input-box--field">
                  <input name="collateral.bank_collateral" value="${f:format(find.contractCollateral.bankCollateral, '0.##')}" type="text" />
                  <span class="input-suffix">บาท</span>
                </div>
              </div>
              <div class="input-box">
                <!-- <label class="input-box--label"></label> -->
                <div class="input-box--field cash-text-field">
                  (<input name="collateral.bank_collateral_text" value="${find.contractCollateral.bankCollateralText}" type="text" />)
                </div>
              </div>
            </div>
            <div class="row">
              <div class="input-box bank-collateral-no-col">
                <label class="input-box--label">เลขที่</label>
                <div class="input-box--field">
                  <input name="collateral.bank_collateral_no" value="${find.contractCollateral.bankCollateralNo}" type="text" />
                </div>
              </div>
              <div class="input-box bank-collateral-dated-col">
                <label class="input-box--label">ลงวันที่</label>
                <div class="input-box--field">
                  <input name="collateral.bank_collateral_dated" value="${find.contractCollateral.bankCollateralDated}" type="date" />
                </div>
              </div>
            </div>
            <c:if test="${not empty find.contractCollateralRevoke }">
              <div class="input-box bank-collateral-dated-col">
                <label class="input-box--label" style="color:red;">ถอนคืนหลักประกันสัญญาแล้ว ${f:format(find.contractCollateralRevoke.updated, 'เมื่อEEEE ที่ dd MMMM yyyy เวลา HH:mm น.', 'th-TH')}</label>
              </div>
            </c:if>
          </div>

          <div class="button-control">
            <c:if test="${not empty find && empty find.contractCollateralRevoke }">
              <button name="revoke">ถอนคืนหลักประกันสัญญา</button>
            </c:if>
            <c:if test="${not empty find && not empty find.contractCollateralRevoke }">
              <button name="cancel-revoke">ยกเลิกการถอนคืนหลักประกันสัญญา</button>
            </c:if>
            <c:if test="${not empty find}">
              <button type="button" data-ref="?id&ref=${find.id}">ทำบันทึกแนบท้าย</button>
            </c:if>
            <button type="button" class="btn" data-rel-ctrl-tab="prev">ย้อนกลับ</button>
            <c:if test="${find != null}">
              <button name="del" class="btn">ลบ</button>
            </c:if>
            <button class="btn" type="button" data-rel-reload>คืนค่า</button>
            <button class="btn">บันทึก</button>
          </div>
        </div>
      </p:declare>
    </form>
  </body>

</html>
