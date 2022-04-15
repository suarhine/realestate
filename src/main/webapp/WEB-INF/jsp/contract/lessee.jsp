<%-- 
    Document   : lessee
    Created on : Apr 2, 2022, 4:30:44 AM
    Author     : Pathompong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="p" uri="/page" %>
<%@taglib prefix="f" uri="/functions" %>
<%@taglib prefix="ui" uri="/contract" %>
<jsp:useBean id="l" class="org.realestate.view.bean.Lookup" />
<jsp:useBean id="o" class="org.realestate.view.bean.Option" />
<jsp:useBean id="now" class="java.util.Date" />
<!DOCTYPE html>
<html>

  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>สรุปรายชื่อผู้เช่า</title>
    <link rel="stylesheet" href="index.css" />
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <script type="module" src="index.js"></script>
  </head>

  <body data-pane="lessee" class="app-content-preview">
    <div class="headers--name">
      <a data-ref="">สรุปรายชื่อผู้เช่าก่อนจะถึงกำหนดชำระค่าเช่าล่วงหน้าก่อน 20 วัน</a>
    </div>
    <jsp:include page="index.filter.jsp?readonly" />
    <form>
      <table class="tb-list list -border">
        <thead>
          <tr>
            <td colspan="2">เลขที่สัญญา</td>
            <td>ลงวันที่</td>
            <td>สัญญาของ</td>
            <td>ค่าชำระ</td>
            <td>วันที่ครบกำหนด</td>
            <td>ยอดตามสัญญา</td>
            <td>เบี้ยปรับ</td>
            <td>ยอดชำระ</td>
          </tr>
        </thead>
        <c:forEach var="e" items="${group}">
            <tbody>
              <tr>
                <td width="1"><input data-rel-select-all type="checkbox" /></td>
                <td colspan="5">${e.key.name}${empty e.key.representative ? '' : ' โดย '.concat(e.key.representative)}</td>
                <td class="right">${f:format(ui:dating_amount(e.value), ',##0.00')}</td>
                <td></td>
                <td></td>
              </tr>
            </tbody>
            <tbody>
              <c:forEach var="i" items="${e.value}">
                  <tr ${i.pk.dating < now ? 'style="color:red"' : ''}>
                    <td><input name="selected" value="${i.id.id}:${i.type.id}:${f:format(i.pk.dating, 'yyyy-MM-dd')}" type="checkbox" /></td>
                    <td>${i.id.code}</td>
                    <td>${f:format(i.id.dated, 'dd/MM/yyyy', 'th-TH')}</td>
                    <td>${i.id.contractRealestate.name}</td>
                    <td>${i.type.label}${i.ref.label}</td>
                    <td>${f:format(i.pk.dating, 'dd/MM/yyyy', 'th-TH')}</td>
                    <td class="right">${f:format(i.amount, ',##0.00')}</td>
                    <td class="right">
                      <c:if test="${i.pk.dating < now}">
                          ${f:format(i.amount * i.id.contractPlan.finerate / 100, ',##0.00')}
                      </c:if>
                    </td>
                    <td data-pane="receive" data-amount="${i.amount}" ${i.pk.dating < now ? 'data-fine="'.concat(i.amount * i.id.contractPlan.finerate / 100).concat('"') : ''}></td>
                  </tr>
              </c:forEach>
            </tbody>
        </c:forEach>
        <tfoot>
          <tr>
            <td colspan="9" align="right">
              <button>รับชำระ</button>
            </td>
          </tr>
        </tfoot>
      </table>
    </form>
  </body>
</form>

</html>
