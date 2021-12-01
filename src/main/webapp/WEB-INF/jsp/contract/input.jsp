<%-- 
    Document   : input
    Created on : Nov 17, 2021, 1:36:09 AM
    Author     : Pathompong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="/functions" %>
<jsp:useBean id="l" class="org.realestate.view.bean.LookupBean" />
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
  </head>
  <body>
    <form method="post">
      <input name="id" value="${param.id}" type="hidden" />
      <table>
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
                    <option value="${i.id}">${i.label}</option>
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
            <td><input name="lessee.registry.house" valuex="\${find.contractLessee.registry.house}" type="text" /></td>
            <td></td>
            <td><input name="lessee.contact.house" valuex="\${find.contractLessee.contact.house}" type="text" /></td>
          </tr>
          <tr>
            <td>หมู่ที่</td>
            <td><input name="lessee.registry.village" valuex="\${find.contractLessee.registry.village}" type="text" /></td>
            <td></td>
            <td><input name="lessee.contact.village" valuex="\${find.contractLessee.contact.village}" type="text" /></td>
          </tr>
          <tr>
            <td>ตรอก/ซอย</td>
            <td><input name="lessee.registry.soi" valuex="\${find.contractLessee.registry.soi}" type="text" /></td>
            <td></td>
            <td><input name="lessee.contact.soi" valuex="\${find.contractLessee.contact.soi}" type="text" /></td>
          </tr>
          <tr>
            <td>ถนน</td>
            <td><input name="lessee.registry.road" valuex="\${find.contractLessee.registry.road}" type="text" /></td>
            <td></td>
            <td><input name="lessee.contact.road" valuex="\${find.contractLessee.contact.road}" type="text" /></td>
          </tr>
          <tr>
            <td>ตำบล/แขวง</td>
            <td><input name="lessee.registry.subdistrict" valuex="\${find.contractLessee.registry.subdistrict}" type="text" /></td>
            <td></td>
            <td><input name="lessee.contact.subdistrict" valuex="\${find.contractLessee.contact.subdistrict}" type="text" /></td>
          </tr>
          <tr>
            <td>อำเภอ/เขต</td>
            <td><input name="lessee.registry.district" valuex="\${find.contractLessee.registry.district}" type="text" /></td>
            <td></td>
            <td><input name="lessee.contact.district" valuex="\${find.contractLessee.contact.district}" type="text" /></td>
          </tr>
          <tr>
            <td>จังหวัด</td>
            <td><input name="lessee.registry.province" valuex="\${find.contractLessee.registry.province}" type="text" /></td>
            <td></td>
            <td><input name="lessee.contact.province" valuex="\${find.contractLessee.contact.province}" type="text" /></td>
          </tr>
          <tr>
            <td>รหัสไปรษณีย์</td>
            <td><input name="lessee.registry.zipcode" valuex="\${find.contractLessee.registry.zipcode}" type="text" /></td>
            <td></td>
            <td><input name="lessee.contact.zipcode" valuex="\${find.contractLessee.contact.zipcode}" type="text" /></td>
          </tr>
          <tr>
            <td>หมายเลขโทรศัพท์</td>
            <td><input name="lessee.registry.phone" valuex="\${find.contractLessee.registry.phone}" type="text" /></td>
            <td></td>
            <td><input name="lessee.contact.phone" valuex="\${find.contractLessee.contact.phone}" type="text" /></td>
          </tr>
          <tr>
            <td>ทรัพย์สินที่เช่า</td>
            <td>
              <select name="realestate.type">
                <option value=""></option>
                <option value="1">ที่ดิน</option>
                <option value="2">อาคารหรือสิ่งก่อสร้าง</option>
                <option value="3">อื่นๆ</option>
              </select>
            </td>
            <td></td>
            <td><input name="realestate.name" value="${find.contractRealestate.name}" type="text" /></td>
          </tr>
          <tr>
            <td>ที่ตั้งทรัพย์สินที่เช่า</td>
            <td></td>
            <td>บริเวณ</td>
            <td><input name="realestate.location" value="${find.contractRealestate.location}" type="text" /></td>
          </tr>
          <tr>
            <td>เลขที่</td>
            <td><input name="realestate.house" value="${find.contractRealestate.house}" type="text" /></td>
            <td>หมู่ที่</td>
            <td><input name="realestate.village" value="${find.contractRealestate.village}" type="text" /></td>
          </tr>
          <tr>
            <td>ตรอก/ซอย</td>
            <td><input name="realestate.soi" value="${find.contractRealestate.soi}" type="text" /></td>
            <td>ใกล้เคียงกับ</td>
            <td><input name="realestate.nearby" value="${find.contractRealestate.nearby}" type="text" /></td>
          </tr>
          <tr>
            <td>ถนน</td>
            <td><input name="realestate.road" value="${find.contractRealestate.road}" type="text" /></td>
            <td>ตำบล/แขวง</td>
            <td><input name="realestate.subdistrict" value="${find.contractRealestate.subdistrict}" type="text" /></td>
          </tr>
          <tr>
            <td>อำเภอ/เขต</td>
            <td><input name="realestate.district" value="${find.contractRealestate.district}" type="text" /></td>
            <td>จังหวัด</td>
            <td><input name="realestate.province" value="${find.contractRealestate.province}" type="text" /></td>
          </tr>
          <tr>
            <td colspan="2">ตั้งอยู่บนที่ดินตรงตามประกาศกระทรวงมหาดไทย</td>
            <td>ลงวันที่</td>
            <td><input name="realestate." value="" type="date" /></td>
          </tr>
          <tr>
            <td>หมายเลขที่ดิน</td>
            <td><input name="realestate.deed_code" value="${find.contractRealestate.deedCode}" type="text" /></td>
            <td>โฉนดที่</td>
            <td><input name="realestate.deed_no" value="${find.contractRealestate.deedNo}" type="text" /></td>
          </tr>
          <tr>
            <td>จำนวนเนื่อที่ทรัพสินที่เช่าประมาณ</td>
            <td><input name="realestate.space_rai" value="${find.contractRealestate.spaceRai}" type="text" pattern="\d+" />ไร่</td>
            <td><input name="realestate.space_ngan" value="${find.contractRealestate.spaceNgan}" type="text" pattern="[0-4]" />งาน</td>
            <td><input name="realestate.space_sqwah" value="${find.contractRealestate.spaceSqwah}" type="text" pattern="\d\d(\.\d+)?" />ตรว.</td>
          </tr>
          <tr>
            <td>วัตถุประสงค์เพื่อ</td>
            <td>
              <select name="objective">
                <option value=""></option>
                <option value="1">เพื่ออยู่อาศัย</option>
                <option value="2">เพื่อประกอบกิจการต่างๆ</option>
                <option value="3">เพื่ออื่นๆ</option>
              </select>
            </td>
            <td></td>
            <td><input name="objective_text" value="${find.objectiveText}" type="text" /></td>
          </tr>
          <tr>
            <td>วันที่เริ่มต้นสัญญาเช่า</td>
            <td><input name="started" value="${f:format(find.started, 'yyyy-MM-dd')}" type="date" /></td>
            <td colspan="2">ระยะเวลาการเช่า (จำนวนปี/จำนวนเดือน)</td>
          </tr>
          <tr>
            <td>วันที่สิ้นสุดสัญญาเช่า</td>
            <td><input name="ended" value="${f:format(find.ended, 'yyyy-MM-dd')}" type="date" /></td>
            <td></td>
            <td><input name="" value="" type="text" placeholder="คำนวนอัตโนมัติ" /></td>
          </tr>
          <tr>
            <td>ค่าเช่า</td>
            <td>
              <select name="">
                <option value=""></option>
                <option value="1">เท่ากันทุกเดือน</option>
                <option value="2">เท่ากันทุกปี</option>
                <option value="3">จำนวนเงินมีการเปลี่ยนแปลง</option>
              </select>
            </td>
            <td>จำนวนเงิน</td>
            <td>
              <input name="" value="" type="tel" />
            </td>
          </tr>
          <tr>
            <td>ค่าผ่อนชำระค่าเช่า</td>
            <td>
              <select name="">
                <option value=""></option>
                <option value="1">เท่ากันทุกเดือน</option>
                <option value="2">เท่ากันทุกปี</option>
                <option value="3">จำนวนเงินมีการเปลี่ยนแปลง</option>
              </select>
            </td>
            <td>จำนวนเงิน</td>
            <td>
              <input name="" value="" type="tel" />
            </td>
          </tr>
          <tr>
            <td>ค่าตอบแทน <input name="" value="" type="text" /></td>
            <td>
              <select name="">
                <option value=""></option>
                <option value="1">เท่ากันทุกเดือน</option>
                <option value="2">เท่ากันทุกปี</option>
                <option value="3">จำนวนเงินมีการเปลี่ยนแปลง</option>
              </select>
            </td>
            <td>จำนวนเงิน</td>
            <td>
              <input name="" value="" type="tel" />
            </td>
          </tr>
          <tr>
            <td>ค่าใช้ประโยชน์</td>
            <td>
              <select name="">
                <option value=""></option>
                <option value="1">เท่ากันทุกเดือน</option>
                <option value="2">เท่ากันทุกปี</option>
                <option value="3">จำนวนเงินมีการเปลี่ยนแปลง</option>
              </select>
            </td>
            <td>จำนวนเงิน</td>
            <td>
              <input name="" value="" type="tel" />
            </td>
          </tr>
          <tr>
            <td>ค่าเช่าช่วง</td>
            <td>
              <select name="">
                <option value=""></option>
                <option value="1">เท่ากันทุกเดือน</option>
                <option value="2">เท่ากันทุกปี</option>
                <option value="3">จำนวนเงินมีการเปลี่ยนแปลง</option>
              </select>
            </td>
            <td>จำนวนเงิน</td>
            <td>
              <input name="" value="" type="tel" />
            </td>
          </tr>
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
            <td><input name="" value="" type="date" /></td>
            <td>จนถึงวันที่</td>
            <td><input name="" value="" type="date" /></td>
          </tr>
          <tr>
            <td colspan="4">
              จ่ายปีถัดไป
            </td>
          </tr>
          <tr>
            <td>ระหว่างวันที่</td>
            <td><input name="" value="" type="date" /></td>
            <td>จนถึงวันที่</td>
            <td><input name="" value="" type="date" /></td>
          </tr>
          <tr>
            <td colspan="4">เงื่อนไขการชำระค่าเช่า</td>
          </tr>
          <tr>
            <td>เป็นรายเดือน</td>
            <td colspan="3">ภายในวันที่<input name="" value="" type="text" />ของเดือนถัดไป</td>
          </tr>
          <tr>
            <td>เป็นรายปี</td>
            <td colspan="3">
              <!--ทุกวันเริ่มต้นของสัญญาเช่าในแต่ละปี-->
              ภายในวันที่<input name="" value="" type="text" placeholder="ค่าเริ่มต้นเป็นวันที่ลงสัญญา" />เดือน<input name="" value="" type="text" placeholder="ค่าเริ่มต้นเป็นเดือนที่ลงสัญญา" />ของปีถัดไป</td>
          </tr>
          <tr>
            <td colspan="2">เป็นรายงวด (แบ่งชำระเป็นรายงวด ๆ ละเดือน)</td>
            <td>ภายในวันที่</td>
            <td><input name="" value="" type="text" /></td>
          </tr>
          <tr>
            <td>อัตราชำระเบี้ยปรับ</td>
            <td colspan="3">
              ร้อยละ <select onchange="this.value === '+' && this.replaceWith(document.createElement('input'))">
                <option value=""></option>
                <option value="1.25">1.25</option>
                <option value="1.50">1.50</option>
                <option value="+">...</option>
              </select> ต่อเดือน ของเงินค่าเช่าที่ค้างชำระ เศษของเดือนให้นับเป็น 1 เดือน
            </td>
          </tr>
          <tr>
            <td colspan="4">ในวันทำสัญญา ได้นำหลักประกันสัญญา</td>
          </tr>
          <tr>
            <td>เป็นเงินสด/จำนวนเงิน</td>
            <td><input name="" value="" type="text" />บาท</td>
            <td></td>
            <td>(<input name="" value="" type="text" />)</td>
          </tr>
          <tr>
            <td>ตามใบเสร็จรับเงิน</td>
            <td>เลขที่ RCPT<input name="" value="" type="text" /></td>
            <td>ลงวันที่</td>
            <td><input name="" value="" type="text" /></td>
          </tr>
          <tr>
            <td>แคชเชียร์เช็คธนาคาร/จำนวนเงิน</td>
            <td><input name="" value="" type="text" />บาท</td>
            <td></td>
            <td>(<input name="" value="" type="text" />)</td>
          </tr>
          <tr>
            <td>ตามใบเสร็จรับเงิน</td>
            <td>เลขที่ RCPT<input name="" value="" type="text" /></td>
            <td>ลงวันที่</td>
            <td><input name="" value="" type="text" /></td>
          </tr>
          <tr>
            <td>เป็นหนังสือค้ำประกันของธนาคาร</td>
            <td><input name="" value="" type="text" />บาท</td>
            <td></td>
            <td>(<input name="" value="" type="text" />)</td>
          </tr>
          <tr>
            <td>เลขที่</td>
            <td><input name="" value="" type="text" /></td>
            <td>ลงวันที่</td>
            <td><input name="" value="" type="text" /></td>
          </tr>
        </tbody>
        <tfoot>
          <tr>
            <td colspan="4" align="right">
              <button type="button" onclick="location.reload()">คืนค่า</button>
              <button>บันทึก</button>
            </td>
          </tr>
        </tfoot>
      </table>
    </form>
  </body>
</html>
