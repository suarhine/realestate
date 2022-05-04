<%-- 
    Document   : index
    Created on : Nov 10, 2021, 5:45:03 PM
    Author     : Pathompong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
              ประเภทค่าชำระ
            </div>
          </div>
          <br />
          <ul class="main-menu--content" data-pane="main-menu">
            <c:forEach var="i" items="${finds}">
              <li>
                <a target="setting-item-preview" href="?id=${i.id}">${i.label}</a>
              </li>
            </c:forEach>
            <li>
              <a target="setting-item-preview" href="?id=">เพิ่มประเภทการชำระ</a>
            </li>
          </ul>
        </div>

        <iframe class="main-content" name="setting-item-preview"></iframe>
      </div>
    </div>
  </body>

</html>
