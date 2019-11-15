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
        <a class="btn btn-primary" href="/settings/users">
            <i class="glyphicon glyphicon-user pull-left"></i><span>Пользователи системы</span>
        </a>
    </li>
    <li>
        <a class="btn btn-warning" href="#">
            <span>новый пользователь</span>
        </a>
    </li>
</ul><br><br>
    <form method="post" action="/settings/users/add" id="userForm">
    <div class="panel panel-default">
        <div class="panel-body form-horizontal payment-form">
            <div class="form-group">
                <label for="concept" class="col-sm-3 control-label">ФИО</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" id="concept" name="fullname">
                </div>
            </div>
            <div class="form-group">
                <label for="tdept" class="col-sm-3 control-label">Структурное подразделение</label>
                <div class="col-sm-9">
                    <select class="form-control" id="tdept" name="dept"><option>&nbsp;</option></select>
                </div>
            </div>
            <div class="form-group">
                <label for="username" class="col-sm-3 control-label">Имя пользователя</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" id="username" name="username">
                </div>
            </div>
            <div class="form-group">
                <label for="pass" class="col-sm-3 control-label">Пароль</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" id="pass" name="pass">
                </div>
            </div>
            <div class="form-group">
                <label for="passdb" class="col-sm-3 control-label">Повторный ввод пароля</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" id="passdb" name="passrp">
                </div>
            </div>
            <div class="form-group">
                <label for="trole" class="col-sm-3 control-label">Роль</label>
                <div class="col-sm-9">
                    <select class="form-control" id="trole" name="role">
                        <option value="1">Сотрудник отдела</option>
                        <option value="2">Сотрудник ОКРиВСМИ</option>
                        <option value="3">Администратор системы</option>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-3 text-right pull-right">
                    <a href="/settings/users" class="btn btn-warning">отмена</a>
                    <button type="submit" class="btn btn-primary">
                        создать <span class="glyphicon glyphicon-ok"></span>
                    </button>
                </div>
            </div>
            <c:if test="${error == 1}">
                <div class="form-group">
                    <div class="col-sm-3 text-right pull-right">
                        <p class="bg-danger">пользователь с данным логином уже существует</p>
                    </div>
                </div>
            </c:if>

        </div>
    </div>
    </form>
</div>

<script>
    var frm = $('#userForm');
    $(document).ready(function(){
        insertDict(depts,'#tdept');
        frm.on('submit',validForm);
    });

    function validForm(){
        var msg = '', check = true, d = jsonData('#userForm');
        if(d.fullname == ''){
            check = false;
            msg = ' - ФИО\n';
        }
        if(d.dept == 0){
            check = false;
            msg+= ' - Структурное подразделение\n';
        }
        if(d.username == ''){
            check = false;
            msg+= ' - Имя пользователя\n';
        }else if(d.username.length < 3){
            check = false;
            msg+= ' - Имя пользователя не может быть меньше 3 символов\n';
        }
        if(d.pass == ''){
            check = false;
            msg+= ' - Пароль не может быть пустым\n';
        }else if(d.pass.length < 6){
            check = false;
            msg+= '- Пароль не может быть меньше 6 символов\n';
        }else if(d.passrp != d.pass){
            check = false;
            msg+= ' - Введенные пароли не совпадают\n';
        }
        if(!check){
            alert("Заполните обязательные поля:\n"+msg);
        }
        return check;
    }

    function insertDict(ar,id){
        for(var i = 0; i<ar.length; i++){
            $(id).append($('<option></option>').attr('value',ar[i][0]).html(ar[i][1]));
        }
    }
    function jsonData(id){ var arr = $(id).serializeArray(), data ={}; for (var i=0;i<arr.length;i++){ data[arr[i].name]=arr[i].value } return data; }
</script>