<%-- 
    Document   : input
    Created on : Apr 22, 2022, 12:40:11 AM
    Author     : Pathompong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="l" class="org.realestate.view.bean.Lookup"></jsp:useBean>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="../../index.css" />
    <script type="module" src="index.js"></script>
  </head>
  <body data-page="input" class="app-content-preview">
    <form method="post" target="setting-preview">
      <table>
        <tbody>
          <tr>
            <td>สิทธิ์การใช้งาน</td>
            <td>
              <input name="label" value="${find.label}" type="text" />
            </td>
          </tr>
          <tr>
            <td>หมายเลข / รหัสสิทธิ์</td>
            <td>
              <input name="code" value="${find.code}" type="text" />
            </td>
          </tr>
          <tr>
            <td>คำอธิบาย / รายละเอียด</td>
            <td>
              <textarea name="desc">${find.desc}</textarea>
            </td>
          </tr>
          <tr>
            <td>สถานะการใช้งาน</td>
            <td><input name="active" ${empty find || find.active ? 'checked' : ''} type="checkbox" /></td>
          </tr>
          <tr>
            <td>การทำงาน</td>
            <td>
              <c:forEach var="i" items="${l.usersFunc}">
                <div>
                  <label>
                    <input name="func" value="${i.id}" type="checkbox" ${find.usersFuncList.contains(i) ? 'checked' : ''} /> ${i.label}
                  </label>
                </div>
              </c:forEach>
            </td>
          </tr>
          <tr>
            <td colspan="2">
              <c:if test="${not empty param.id}">
                <button name="del">ลบ</button>
              </c:if>
              <button type="button" data-rel-reload>คืนค่า</button>
              <button>บันทึก</button>
            </td>
          </tr>
        </tbody>
      </table>
    </form>
  </body>
</html>
