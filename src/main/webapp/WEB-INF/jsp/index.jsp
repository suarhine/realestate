<%-- 
    Document   : index
    Created on : Nov 10, 2021, 5:45:03 PM
    Author     : Pathompong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="index.css" />
    <script type="module" src="index.js"></script>
    <title>ระบบจัดเก็บสัญญาเช่า องค์การบริหารส่วนจังหวัดภูเก็ต</title>
  </head>
  <body>
    <h1 data-ref="">ระบบจัดเก็บสัญญาเช่า องค์การบริหารส่วนจังหวัดภูเก็ต</h1>
    <div class="left-menu-container">
      <div>
        <ul>
          <li>
            <a target="right-pane" href="contract">สัญญาเช่าทรัพย์สิน</a>
          </li>
          <li>
            <a target="right-pane" href="income">การรับเงินค่าเช่า</a>
          </li>
          <li>
            <a target="right-pane" href="overdue">ลูกหนี้ค้างชำระ</a>
          </li>
          <li>
            <a target="right-pane" href="collateral">หลักประกันสัญญา</a>
          </li>
          <li>
            <a target="right-pane" href="setting">ตั้งค่า</a>
          </li>
          <li>
            <a href="logout">ออกจากระบบ</a>
          </li>
        </ul>
      </div>
      <iframe name="right-pane"></iframe>
    </div>
  </body>
</html>
