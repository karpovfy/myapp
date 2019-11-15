<%@ page contentType="text/html;charset=UTF-8" language="java" %><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %><%@ page session="true"%>

<div class="well col-sm-8">
<div class="panel panel-warning">
    <div class="panel-heading"><h3 class="panel-title">Удаление исполнителя</h3></div>
    <div class="panel-body">${dept.deptName}</div>
    <div class="panel-footer">
        <a class="btn btn-success" href="/edit/delete/itemdept/${itemDept.itemDeptId}">подтвердить</a>
        <a class="btn btn-warning" href="/order/${item.orderId}/item/${item.orderItemId}">отмена</a>
    </div>
</div>
</div>