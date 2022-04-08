<%@ page pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="p" uri="/page" %>
<jsp:useBean id="l" class="org.realestate.view.bean.Lookup" />
<jsp:useBean id="o" class="org.realestate.view.bean.Option" />
<p:declare option="${
           not empty param.type
               || not empty param['realestate.location']
               || not empty param['realestate.address.subdistrict']
               || not empty param.period
               || not empty param['period.min']
               || not empty param['period.max']
               || not empty param.objective
           }"
           calsize="${param.colsize == null || empty param.colsize ? 6 : param.colsize}"
           readonly="${param.readonly != null}">
    <tr>
      <td colspan="${calsize}">
        <div class="full-left">
          <input name="q"
                 value="${param.q}"
                 type="search"
                 placeholder="ค้าหา..."
                 class="-search-box"
                 form="search" />
          <button form="search">ค้นหา</button>
          <button type="button" name="option" ${option ? 'data-rel-showed' : ''}>${option ? 'ตัวเลือกน้อยลง' : 'ตัวเลือกอื่นๆ'}</button>
          <c:if test="${!readonly}">
              <button name="id" form="search" >เพิ่มสัญญาใหม่</button>
          </c:if>
        </div>
      </td>
    </tr>
    <tr data-pane="option" ${option ? '' : 'data-rel-hide'}>
      <td colspan="${calsize}">
        ประเภทสัญญาเช่า
        <select name="type" form="search" ${option ? '' : 'disabled'}>
          <option value="">-- ไม่ระบุ --</option>
          <c:forEach var="i" items="${l.contractType}">
              <option value="${i.id}" ${i.id == param.type ? 'selected' : ''}>${i.label}</option>
          </c:forEach>
        </select>
        บริเวณที่เช่า
        <select name="realestate.location" form="search" ${option ? '' : 'disabled'}>
          <option value="">-- ไม่ระบุ --</option>
          <c:forEach var="i" items="${o.contractRealestateLocation}">
              <option value="${i}" ${i == param['realestate.location'] ? 'selected' : ''}>${i}</option>
          </c:forEach>
        </select>
        ตำบลที่เช่า
        <select name="realestate.address.subdistrict" form="search" ${option ? '' : 'disabled'}>
          <option value="">-- ไม่ระบุ --</option>
          <c:forEach var="i" items="${o.contractRealestateAddressSubdistrict}">
              <option value="${i}" ${i == param['realestate.address.subdistrict'] ? 'selected' : ''}>${i}</option>
          </c:forEach>
        </select>
        <br />
        จุดประสงค์ของการเช่า
        <select name="objective" form="search" ${option ? '' : 'disabled'}>
          <option value="">-- ไม่ระบุ --</option>
          <c:forEach var="i" items="${l.contractObjective}">
              <option value="${i.id}" ${i.id == param.objective ? 'selected' : ''}>${i.label}</option>
          </c:forEach>
        </select>
        อายุสัญญาที่เช่า 
        มากกว่า <input name="period.min"
                       value="${param['period.min']}"
                       type="number"
                       form="search"
                       ${option ? '' : 'disabled'} /> ปี
        และน้อยกว่า <input name="period.max"
                           value="${param['period.max']}"
                           type="number"
                           form="search"
                           ${option ? '' : 'disabled'} /> ปี
        <%--select name="period">
          <option value="">-- ไม่ระบุ --</option>
          <option value="-1"  ${param.period == '-1' ? 'selected' : ''}>ไม่เกิน 1 ปี</option>
          <option value="1-2" ${param.period == '1-2' ? 'selected' : ''}>1 - 2 ปี</option>
          <option value="2-5" ${param.period == '2-5' ? 'selected' : ''}>2 - 5 ปี</option>
          <option value="5-10"${param.period == '5-10' ? 'selected' : ''}>5 - 10 ปี</option>
          <option value="10+" ${param.period == '10+' ? 'selected' : ''}>10 ปีขึ้นไป</option>
        </select--%>
      </div>
    </td>
</tr>
</p:declare>