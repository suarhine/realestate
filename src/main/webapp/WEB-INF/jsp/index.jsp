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
    <title>ระบบจัดเก็บสัญญาเช่า องค์การบริหารส่วนจังหวัดภูเก็ต</title>
    <link rel="stylesheet" href="index.css" />
    <link rel="stylesheet" href="/web/js/jquery-ui-1.12.1/jquery-ui.plugin.css" />
    <script type="module" src="index.js"></script>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Kanit:wght@100;200;300;400;500;600;700;800;900&display=swap"
          rel="stylesheet">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Sarabun:wght@100;200;300;400;500;600;700;800&display=swap"
          rel="stylesheet">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
  </head>

  <body>
    <div class="app-container">
      <div class="app-headers" data-ref="">
        <div class="headers">
          <div class="headers--logo">
            <img src="asset/images/logo-app.png" alt="ระบบจัดเก็บสัญญาเช่า องค์การบริหารส่วนจังหวัดภูเก็ต">
          </div>
          <div class="headers--name">
            ระบบจัดเก็บสัญญาเช่า องค์การบริหารส่วนจังหวัดภูเก็ต
          </div>
        </div>
      </div>
      <div class="app-content">
        <div class="main-menu">
          <ul class="main-menu--content" data-pane="main-menu">
            <li>
              <a target="content-preview" href="contract/">สัญญาเช่าทรัพย์สิน</a>
            </li>
            <!--
            <li>
              <a target="content-preview" href="contract/lessee">สรุปรายชื่อผู้เช่า</a>
            </li>
            -->
            <li>
              <a target="content-preview" href="receipt">การรับชำระ</a>
            </li>
            <!--
            <li>
              <a target="content-preview" href="overdue">ลูกหนี้ค้างชำระ</a>
            </li>
            <li>
              <a target="content-preview" href="collateral">หลักประกันสัญญา</a>
            </li>
            -->
            <li>
              <a target="content-preview" href="report">รายงาน</a>
            </li>
            <li>
              <a target="content-preview" href="setup">ตั้งค่า</a>
            </li>
            <li>
              <a href="logout">ออกจากระบบ</a>
            </li>
          </ul>
        </div>

        <iframe class="main-content" name="content-preview"></iframe>
      </div>
    </div>
  </body>

</html>
