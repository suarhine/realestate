<%-- 
    Document   : index.jsp
    Created on : Nov 11, 2021, 12:04:22 PM
    Author     : Pathompong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="/functions" %>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="index.css" />
    <title>JSP Page</title>
  </head>
  <body>
    <table class="list -full-input">
      <thead>
        <tr>
          <td colspan="6">
            <form class="full-left">
              <input name="q"
                     value="${param.q}"
                     type="search"
                     placeholder="ค้าหา..."
                     class="-search-box" />
              <button>ค้น</button>
              <button name="id">เพิ่มสัญญาใหม่</button>
            </form>
          </td>
        </tr>
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
            <tr onclick="location.href = '?id=${i.id}'" style="cursor: pointer;">
              <td>${i.code}</td>
              <td>${f:format(i.dated, 'yyyy-MM-dd')}</td>
              <td>${i.contractRealestate.name}</td>
              <td>${i.contractLessee.name}</td>
              <td>${f:format(i.started, 'yyyy-MM-dd')}</td>
              <td>${f:format(i.ended, 'yyyy-MM-dd')}</td>
            </tr>
        </c:forEach>
      </tbody>
    </table>
  </body>
</html>
