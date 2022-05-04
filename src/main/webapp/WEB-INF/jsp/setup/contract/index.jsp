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
    <link rel="stylesheet" href="../index.css" />
    <script type="module" src="index.js"></script>
    <script type="module" src="../../index.js"></script>
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
      <div class="app-content -sub">
        <div class="main-menu">
          <div class="headers">
            <div class="headers--name headers--center">
              ตัวเลือกสัญญาเช่าทรัพย์สิน
            </div>
          </div>
          <br />
          <ul class="main-menu--content" data-pane="main-menu">
            <li>
              <a target="setting-list-preview" href="contract_type">ประเภทสัญญาเช่า</a>
            </li>
            <li>
              <a target="setting-list-preview" href="contract_realestate_type">ประเภททรัพย์สินที่เช่า</a>
            </li>
            <li>
              <a target="setting-list-preview" href="contract_objective">วัตถุประสงตามสัญญาเช่า</a>
            </li>
            <li>
              <a target="setting-list-preview" href="contract_appointment_type">ประเภทค่าชำระตามสัญญา</a>
            </li>
            <li>
              <a target="setting-list-preview" href="contract_appointment_fee_type">ประเภทงวดชำระ</a>
            </li>
          </ul>
        </div>

        <iframe class="main-content" name="setting-list-preview"></iframe>
      </div>
    </div>
  </body>

</html>
