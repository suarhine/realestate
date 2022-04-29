<%-- 
    Document   : contract_type.input
    Created on : Apr 28, 2022, 3:44:07 AM
    Author     : Pathompong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="index.css" />
    <script type="module" src="index.js"></script>
  </head>
  <body data-page="input">
    <form method="post" target="setting-list-preview">
      <div class="input-box--label">ชื่อสัญญาเช่า</div>
      <input name="label" value="${find.label}" type="text" />
      <div class="input-box--label">รหัสสัญญาเช่า</div>
      <input name="code" value="${find.code}" type="text" />
      <div class="input-box--label">รายละเอียด</div>
      <textarea name="desc" rows="4" cols="20">${find.desc}</textarea>
      <div class="input-box--label">
        สามารถระบุคำอธิบายเพิ่มเติมได้
      </div>
      <label>
        <input name="additional" value="true" type="radio" ${find.additional == true ? 'checked' : ''} />
        ใช้งาน
      </label>
      <label>
        <input name="additional" value="false" type="radio" ${find.additional == false ? 'checked' : ''} />
        ไม่ใช้งาน
      </label>
      <div class="input-box--label">
        สถานะการใช้งาน
      </div>
      <label>
        <input name="active" value="true" type="radio" ${find.active == true ? 'checked' : ''} />
        ใช้งาน
      </label>
      <label>
        <input name="active" value="false" type="radio" ${find.active == false ? 'checked' : ''} />
        ไม่ใช้งาน
      </label>
      <div class="ctrl-button">
        <button>บันทึก</button>
        <button type="reset">คืนค่า</button>
        <button name="del">ลบ</button>
      </div>
    </form>
  </body>
</html>
