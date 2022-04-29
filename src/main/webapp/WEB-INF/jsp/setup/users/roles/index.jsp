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
    <link rel="stylesheet" href="../../index.css" />
    <script type="module" src="index.js"></script>
    <script type="module" src="../../../index.js"></script>
  </head>

  <body>
    <div class="app-container">
      <div class="app-content -sub">
        <div class="main-menu">
          <div class="headers">
            <div class="headers--name headers--center">
              สิทธิ์การเข้าถึงระบบ
            </div>
          </div>
          <br />
          <ul class="main-menu--content--link" data-pane="main-menu">
            <c:forEach var="i" items="${finds}">
              <li class="-menu--button">
                <a target="setting-users-preview" href="?id=${i.id}">
                  <c:if test="${not empty i.code}">
                    [${i.code}]
                  </c:if>${i.label}</a>
              </li>
            </c:forEach>
            <li class="-menu--button">
              <a target="setting-users-preview" href="?id">เพิ่มสิทธิ์การเข้าถึงระบบ</a>
            </li>
          </ul>
        </div>

        <iframe class="main-content" name="setting-users-preview"></iframe>
      </div>
    </div>
  </body>

</html>
