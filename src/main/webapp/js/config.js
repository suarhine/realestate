/*
 * for jsp config
 * <%@page contentType="application/javascript" pageEncoding="UTF-8"%>
 * <%--
 * <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 * --%>
 * <jsp:useBean id="c" class="org.realestate.view.bean.Config" scope="page" />
 * <jsp:useBean id="l" class="org.realestate.view.bean.Lookup" scope="page" />
 */
export default {
  root: '${c.root}',
  build: '${initParam["version.build"]}'
};