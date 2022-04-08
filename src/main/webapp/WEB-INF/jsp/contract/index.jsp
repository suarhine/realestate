<%-- 
    Document   : index.jsp
    Created on : Nov 11, 2021, 12:04:22 PM
    Author     : Pathompong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="p" uri="/page" %>
<%@taglib prefix="f" uri="/functions" %>
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
  <body data-page="index">
    <h1>สัญญาเช่าทรัพย์สิน</h1>
    <form id="search"></form>
    <table class="list -border">
      <thead>
        <jsp:include page="index.filter.jsp" />
        <tr>
          <td>เลขที่สัญญา</td>
          <td>ลงวันที่</td>
          <td>สัญญาของ</td>
          <td>ผู้เช่า</td>
          <td>วันที่เริ่มต้นสัญญา</td>
          <td>วันที่สิ้นสุดสัญญา</td>
        </tr>
      </thead>
      <tbody>
        <c:forEach var="i" items="${finds}">
            <tr data-ref="?id=${i.id}">
              <td>${i.code}</td>
              <td>${f:format(i.dated, 'dd/MM/yyyy', 'th-TH')}</td>
              <td>${i.contractRealestate.name}</td>
              <td>${i.contractLessee.name}</td>
              <td>${f:format(i.started, 'dd/MM/yyyy', 'th-TH')}</td>
              <td>${f:format(i.ended, 'dd/MM/yyyy', 'th-TH')}</td>
            </tr>
        </c:forEach>
      </tbody>
    </table>
  </body>
</html>
