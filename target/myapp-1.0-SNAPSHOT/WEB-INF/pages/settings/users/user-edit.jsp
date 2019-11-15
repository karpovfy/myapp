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
                <span>Редактирование пользователя</span>
            </a>
        </li>
    </ul><br><br>
    <form method="post" id="userForm" action="/settings/users/edit/${userEdit.userId}">
        <div class="panel panel-default">
            <div class="panel-body form-horizontal payment-form">
                <div class="form-group">
                    <label for="fullname" class="col-sm-3 control-label">ФИО</label>
                    <div class="col-sm-9">
                        <input type="text" class="form-control" id="fullname" name="fullname" value="${userEdit.fullname}">
                    </div>
                </div>
                <div class="form-group">
                    <label for="tdept" class="col-sm-3 control-label">Структурное подразделение</label>
                    <div class="col-sm-9">
                        <select class="form-control" id="tdept" name="dept"><option value="0">&nbsp;</option></select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="username" class="col-sm-3 control-label">Имя пользователя</label>
                    <div class="col-sm-9">
                        <input type="text" class="form-control" id="username" name="username" disabled="disabled" value="${userEdit.username}">
                    </div>
                </div>
                <div class="form-group">
                    <label for="pass" class="col-sm-3 control-label">Пароль</label>
                    <div class="col-sm-9">
                        <input type="password" class="form-control" id="pass" name="pass" value="${userEdit.userpass}">
                    </div>
                </div>
                <div class="form-group">
                    <label for="passrp" class="col-sm-3 control-label">Повторный ввод пароля</label>
                    <div class="col-sm-9">
                        <input type="password" class="form-control" id="passrp" name="passrp" value="${userEdit.userpass}">
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
                            сохранить <span class="glyphicon glyphicon-ok"></span>
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>

<script>
    var deptCurrent = ${userEdit.deptId},
        roleCurrent = 1,
        frm = $('#userForm'); <c:if test="${userEdit.userRole == 'ROLE_OKO'}">roleCurrent = 2;</c:if><c:if test="${userEdit.userRole == 'ROLE_KIP'}">roleCurrent = 3;</c:if>

    $(document).ready(function(){
        insertDict(depts,'#tdept');
        $('#tdept').val(deptCurrent);
        $('#trole').val(roleCurrent);
        frm.on('submit',validForm);
    });

    function validForm(){
        var msg = '', check = true, d = jsonData('#userForm');
        if(d.fullname == ''){
            check = false;
            msg = '- ФИО\n';
        }
        if(d.dept == 0){
            check = false;
            msg+= '- Структурное подразделение\n';
        }
        if(d.pass == ''){
            check = false;
            msg+= '- Пароль не может быть пустым\n';
        }else if(d.pass.length < 6){
            check = false;
            msg+= '- Пароль не может быть меньше 6 символов\n';
        }else if(d.passrp != d.pass){
            check = false;
            msg+= '- Введенные пароли не совпадают\n';
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