<%-- 
    Document   : index
    Created on : Apr 22, 2022, 12:21:08 AM
    Author     : Pathompong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="../index.css" />
    <script type="module" src="index.js"></script>
    <script type="module" src="../../index.js"></script>
  </head>

  <body>
    <div class="app-container">
      <div class="app-content -sub">
        <div class="main-menu">
          <ul class="main-menu--content--link" data-pane="main-menu">
            <c:forEach var="i" items="${finds}">
              <li class="-menu--button">
                <a target="setting-users-preview" href="?id=${i.id}">[${i.code}] ${i.fname}${i.pname} ${i.lname}</a>
              </li>
            </c:forEach>
            <li class="-menu--button">
              <a target="setting-users-preview" href="?id">เพิ่มผู้ใช้งาน</a>
            </li>
          </ul>
        </div>

        <iframe class="main-content" name="setting-users-preview"></iframe>
      </div>
    </div>
  </body>

</html>
