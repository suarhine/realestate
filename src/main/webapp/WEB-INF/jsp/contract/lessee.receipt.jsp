<%-- 
    Document   : lessee.receipt
    Created on : Apr 4, 2022, 1:50:11 AM
    Author     : Pathompong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
  </head>
  <body>
    <h1>รายการรับชำระ</h1>
      <table>
          <thead>
              <tr>
                <td>เลขที่สัญญา</td>
                <td>ลงวันที่</td>
                <td>สัญญาของ</td>
                <td>ค่าชำระ</td>
                <td>วันที่ครบกำหนด</td>
                <td>ยอดชำระ</td>
            </tr>
        </thead>
    </table>
    <c:forEach var="i" items="${finds}">
        ${i.type.label}
    </c:forEach>
  </body>
</html>
