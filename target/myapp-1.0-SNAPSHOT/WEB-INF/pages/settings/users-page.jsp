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
                <i class="glyphicon glyphicon-cog pull-left"></i><span>Настройки ${allcount}</span>
            </a>
        </li>
        <li>
            <a class="btn btn-primary" href="#">
                <i class="glyphicon glyphicon-user pull-left"></i><span>Пользователи системы -всего <b>${count}</b></span>
            </a>
        </li>
        <li class="pull-right">
            <a class="btn btn-success" href="/settings/users/add">
                <i class="glyphicon glyphicon-plus pull-left"></i><span>Добавить пользователя ${pager}</span>
            </a>
        </li>
    </ul>

    <table id="mytable" class="table table-bordred table-striped">
        <thead>
        <th>Статус</th>
        <th>Имя пользователя</th>
        <th>ФИО</th>
        <th>Редактировать</th>
        <th>Удалить</th>
        </thead>
        <tbody>
        <c:forEach items="${users}" var="u" >
            <tr>
                <td><i class="glyphicon glyphicon-ok"></i>${u.userId}</td>
                <td>${u.username}</td>
                <td>${u.fullname}</td>
                <td>
                    <p><a href="/settings/users/edit/${u.userId}" class="btn btn-primary btn-xs"><span class="glyphicon glyphicon-pencil"></span></a></p>
                </td>

                <td>
                    <p><button rel="tooltip" data-placement="top" data-target="/settings/users/delete/${u.userId}" data-toggle="modal" data-title="Delete" class="btn btn-danger btn-xs"><span class="glyphicon glyphicon-trash"></span></button></p>
                </td>

            </tr>
        </c:forEach>
        </tbody>
        </table>
 </div>

<div class="row">
    <div class="form-group col-md-1">
        <select class="form-control pagination" id="pageSizeSelect" onchange="location = this.value;">
            <option value="/settings/users/0">0</option>
            <option value="/settings/users/50">50</option>
            <option value="/settings/users/100">10</option>
        </select>
    </div>
            <div class="form-group col-md-1">
    <select class="form-control pagination" id="pageSizeSelect1" onchange="location = this.value;">
    <c:forEach var = "i" begin = "1" end = "${pager}">
        <option value=<c:out value = "${i*count}"/>>

            <c:set var = "salary" scope = "session" value = "${i}"/>
            <c:out value = "${salary}"/>



        </option>
    </c:forEach>
    </select>
    </div>
</div>
