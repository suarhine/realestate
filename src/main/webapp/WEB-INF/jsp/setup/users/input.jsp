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
      <link rel="stylesheet" href="../index.css" />
      <script type="module" src="index.js"></script>
    </head>
    <body>
      <form method="post" target="setting-preview">
        <table>
          <tbody>
            <tr>
              <td>รหัสผู้ใช้งาน</td>
              <td>
              <c:if test="${empty param.id}">
                : <input name="code" />
              </c:if>
              <c:if test="${not empty param.id}">
                : ${find.code}
              </c:if>
            </td>
          </tr>
          <c:if test="${empty param.id}">
            <tr>
              <td rowspan="2">รหัสผ่าน</td>
              <td>
                : <input name="password" type="password" />
              </td>
            </tr>
            <tr>
              <td>
                : <input name="password" type="password" placeholder="ยืนยันรหัสผ่าน" />
              </td>
            </tr>
          </c:if>
          <tr>
            <td>ชื่อ-สกุล</td>
            <td>
              : <input name="pname" value="${find.pname}" placeholder="คำขึ้นต้น" size="3"/>
              <input name="fname" value="${find.fname}" placeholder="ชื่อต้น" size="10" />
              <input name="lname" value="${find.lname}" placeholder="นามสกุล" size="10" />
            </td>
          </tr>
          <tr>
            <td>ตำแหน่ง</td>
            <td>
              : <input name="posit" value="${find.posit}" />
            </td>
          </tr>
          <tr>
            <td>สถานะการใช้งาน</td>
            <td><input name="active" ${empty find || find.active ? 'checked' : ''} type="checkbox" /></td>
          </tr>
          <tr>
            <td>สิทธิ์การเข้าถึงระบบ</td>
            <td>
              <c:forEach var="i" items="${l.usersRoles}">
                <div>
                  <label>
                    <input name="roles" value="${i.id}" type="checkbox" ${find.usersRolesList.contains(i) ? 'checked' : ''} /> ${i.label}
                  </label>
                </div>
              </c:forEach>
            </td>
          </tr>
          <tr>
            <td colspan="2">
              <c:if test="${not empty param.id}">
                <button name="del">ลบ</button>
                <button type="button" data-ref="?id=${param.id}&changepass">เปลี่ยนรหัสผ่าน</button>
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
