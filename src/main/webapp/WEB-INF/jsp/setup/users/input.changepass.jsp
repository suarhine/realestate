<%-- 
    Document   : input
    Created on : Apr 22, 2022, 12:40:11 AM
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
  </head>
  <body>
    <form method="post" target="setting-preview">
      <table>
        <tbody>
          <tr>
            <td>รหัสผู้ใช้งาน</td>
            <td>
              : ${find.code}
            </td>
          </tr>
          <tr>
            <td rowspan="2">รหัสผ่านใหม่</td>
            <td>
              : <input name="password" type="password" />
            </td>
          </tr>
          <tr>
            <td>
              : <input name="password" type="password" placeholder="ยืนยันรหัสผ่าน" />
            </td>
          </tr>
          <tr>
            <td colspan="2">
              <button type="button" data-rel-back>ย้อนกลับ</button>
              <button type="button" data-rel-reload>คืนค่า</button>
              <button>บันทึก</button>
            </td>
          </tr>
        </tbody>
      </table>
    </form>
  </body>
</html>
