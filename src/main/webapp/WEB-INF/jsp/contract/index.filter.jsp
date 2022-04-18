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
             || not empty param['collateral.revoke']
           }"
           readonly="${param.readonly != null}"
           optional="${param.optional != null}">
  <form class="search-list">
    <div class="search-list--box">
      <input name="q"
             value="${param.q}"
             type="search"
             class="-search-box"
             placeholder="ค้าหา..." />
      <button class="btn">

        <i class="material-icons">search</i>
        ค้นหา</button>
    </div>
    <div class="search-list--control">
      <button class="btn"
              type="button"
              name="option"
              ${option ? 'data-rel-showed' : ''}>
        <i class="material-icons">add</i>
        <span>${option ? 'ตัวเลือกน้อยลง' : 'ตัวเลือกอื่นๆ'}</span>
      </button>

      <c:if test="${!readonly}">
        <button class="btn" name="id">

          <i class="material-icons">add</i>
          เพิ่มสัญญาใหม่
        </button>
      </c:if>
    </div>
    <div class="-linebreak"></div>
    <div data-pane="option" ${option ? '' : 'data-rel-hide'}>
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
    <div data-pane="option" ${option ? '' : 'data-rel-hide'}>
      <c:if test="${!optional}">
        หลักประกันสัญญา
        <select name="collateral.revoke">
          <option value="">-- ไม่ระบุ --</option>
          <option value="true" ${param['collateral.revoke'] == 'true' ? 'selected' : ''}>ถอนคืนหลักประกันสัญญา</option>
          <option value="false" ${param['collateral.revoke'] == 'false' ? 'selected' : ''}>ยังไม่ได้ถอนคืนหลักประกันสัญญา </option>
        </select>
      </c:if>
    </div>
  </form>
</p:declare>