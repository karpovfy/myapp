<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ page session="true"%>
var dtypes = [<c:forEach items="${doctypes}" var="m" >[${m.orderTypeId},'${m.orderTypeName}',${m.orderCategory}],</c:forEach>], depts = [<c:forEach items="${depts}" var="d" >[${d.deptId},'${d.deptName}','${d.deptType}'],</c:forEach>];