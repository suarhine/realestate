<%-- 
    Document   : index
    Created on : Apr 4, 2022, 6:02:15 PM
    Author     : Pathompong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="/functions" %>
<!DOCTYPE html>
<html>

  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>การรับเงินค่าเช่า</title>
    <link rel="stylesheet" href="index.css" />
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <script type="module" src="index.js"></script>
  </head>

  <body data-page="index" class="app-content-preview">
    <div class="headers--name">
      <a data-ref="">การรับเงินค่าเช่า</a>
    </div>
    <jsp:include page="../contract/index.filter.jsp?readonly&optional" />
    <table class="tb-list list -border">
      <thead>
        <tr>
          <td>วันที่รับเงิน</td>
          <td>ผู้รับเงิน</td>
          <td>เลขที่สัญญา</td>
          <td>ลงวันที่</td>
          <td>สัญญาของ</td>
          <td>ค่าชำระ</td>
          <td>วันที่ครบกำหนด</td>
          <td>ยอดตามสัญญา</td>
          <td>ยอดชำระ</td>
        </tr>
      </thead>
      <tbody>
        <c:forEach var="i" items="${finds}">
            <c:forEach var="ii" varStatus="s" items="${i.contractAppointmentReceiptList}">
                <tr>
                  <c:if test="${s.first}">
                      <td rowspan="${i.contractAppointmentReceiptList.size()}">
                        ${f:format(i.updated, 'dd/MM/yyyy', 'th-TH')}
                      </td>
                      <td rowspan="${i.contractAppointmentReceiptList.size()}">
                        ${i.updater}
                      </td>
                  </c:if>
                  <td>${ii.id.code}</td>
                  <td>${f:format(ii.id.dated, 'dd/MM/yyyy', 'th-TH')}</td>
                  <td>${ii.id.contractRealestate.name}</td>
                  <td>${ii.type.label}${ii.dating.ref.label}</td>
                  <td>${f:format(ii.pk.dating, 'dd/MM/yyyy', 'th-TH')}</td>
                  <td>${f:format(ii.dating.amount, ',###.##')}</td>
                  <td>${f:format(ii.amount, ',###.##')}</td>
                </tr>
            </c:forEach>
        </c:forEach>
      </tbody>
    </table>
  </body>
</html>
