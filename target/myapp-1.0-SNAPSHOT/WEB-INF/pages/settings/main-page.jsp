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
            <a class="btn btn-lg btn-primary" href="/settings/users/0">
                <i class="glyphicon glyphicon-user pull-left"></i><span>Пользователи<br><small>системы</small></span>
            </a>
        </li>
        <li>
            <a class="btn btn-lg btn-primary" href="/settings/depts">
                <i class="glyphicon glyphicon-home pull-left"></i><span>Структурные<br><small>подразделения УФССП</small></span>
            </a>
        </li>
        <li>
            <a class="btn btn-lg btn-primary" href="/settings/ordertypes">
                <i class="glyphicon glyphicon-list pull-left"></i><span>Типы документов<br><small></small></span>
            </a>
        </li>

        <li>Путь к файлам: ${path}</li>

    </ul>
</div>



    <div class="row well">
    <form method="post" action="/settings/message">
        <div class="form-group">
            <label for="msg_text">информационное сообщение</label>
            <textarea class="form-control" name="msg_text" id="msg_text">${message.msgText}</textarea>
        </div>
        <button type="submit" class="btn btn-default">сохранить</button>
    </form>
    </div>

</div>