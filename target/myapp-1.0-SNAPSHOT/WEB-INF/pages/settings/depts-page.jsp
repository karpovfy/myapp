<%@ page contentType="text/html;charset=UTF-8" language="java" %><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true"%>
<style>
    .ds-btn li{ list-style:none; float:left; padding:10px; }
    .ds-btn li a {border-radius: 0}
    .ds-btn li a span{padding-left:15px;padding-right:5px;width:100%;display:inline-block; text-align:left;}
    .ds-btn li a span small{width:100%; display:inline-block; text-align:left;}
</style>
<div class="row">
    <ul class="ds-btn">
        <li>
            <a class="btn btn-primary" href="/settings">
                <i class="glyphicon glyphicon-cog pull-left"></i><span>настройки</span>
            </a>
        </li>
        <li>
            <a class="btn btn-primary" href="/settings/depts">
                <i class="glyphicon glyphicon-home pull-left"></i><span>Структурные подразделения УФССП</span>
            </a>
        </li>
        <li class="pull-right">
            <a href="/settings/depts/add" class="btn btn-success">
                <i class="glyphicon glyphicon-plus pull-left"></i><span>добавить подразделение</span>
            </a>
        </li>
    </ul>
    <table id="mytable" class="table table-bordred table-striped">
        <thead>
        <th>Подразделение</th>
        <th>Статус</th>
        <th>Редактировать</th>
        </thead>
        <tbody>
        <c:forEach items="${depts}" var="d" >
            <tr>
                <td>${d.deptName}</td>
                <td>
                    <c:choose>
                        <c:when test="${d.viewOn==1}">
                            <i class="glyphicon glyphicon-ban-circle"></i>
                        </c:when>
                    </c:choose>
                </td>
                <td>
                    <!--<p><button rel="tooltip" data-placement="top" data-target="#edit" data-toggle="modal" data-title="Edit" class="btn btn-primary btn-xs"><span class="glyphicon glyphicon-pencil"></span></button></p>-->
                    <a data-title="Edit" class="btn btn-primary btn-xs" href="/settings/depts/edit/${d.deptId}"><span class="glyphicon glyphicon-pencil"></span></a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</div>