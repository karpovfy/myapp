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
            <a class="btn btn-primary" href="/settings/ordertypes">
                <i class="glyphicon glyphicon-user pull-left"></i><span>Типы документов</span>
            </a>
        </li>
        <li>
            <a class="btn btn-warning" href="#">
                <span>Редактирование</span>
            </a>
        </li>

    </ul><br><br>
    <form method="post" id="orderTypeForm" action="/settings/ordertypes/edit/${rec.orderTypeId}">
        <div class="panel panel-default">
            <div class="panel-body form-horizontal payment-form">
                <input type="hidden" name="id" value="${rec.orderTypeId}">
                <div class="form-group">
                    <label for="fullname" class="col-sm-3 control-label">Наименование</label>
                    <div class="col-sm-9">
                        <input type="text" class="form-control" id="fullname" name="fullname" value="${rec.orderTypeName}">
                    </div>
                </div>
                <div class="form-group">
                    <label for="sortorder" class="col-sm-3 control-label">Сортировка</label>
                    <div class="col-sm-9">
                        <input type="text" class="form-control" id="sortorder" name="sortorder" value="${rec.sortOrder}">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">Статус</label>
                    <div class="col-sm-9">
                        <label class="control-label"><input type="radio" id="status1" name="status" value="0"> Не заблокирован</label>
                        <label class="control-label"><input type="radio" id="status2" name="status" value="1"> Заблокирован</label>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">Категория</label>
                    <div class="col-sm-9">
                        <label class="control-label"><input type="radio" id="status11" name="catstatus" value="0"> Внутренний</label>
                        <label class="control-label"><input type="radio" id="status22" name="catstatus" value="1"> Внешний</label>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-3 text-right pull-right">
                        <a href="/settings/ordertypes" class="btn btn-warning">отмена</a>
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
    var deptCurrent = 1,
        roleCurrent = 1,
        tStatus = ${rec.activeCheck},
        catStatus = ${rec.orderCategory},
        frm = $('#userForm'); <c:if test="${userEdit.userRole == 'ROLE_OKO'}">roleCurrent = 2;</c:if><c:if test="${userEdit.userRole == 'ROLE_KIP'}">roleCurrent = 3;</c:if>

    $(document).ready(function(){
        insertDict(depts,'#tdept');
        $('#tdept').val(deptCurrent);
        $('#trole').val(roleCurrent);
        frm.on('submit',validForm);

        if(tStatus == 0){
            $('#status1').prop("checked",true);
        }else{
            $('#status2').prop("checked",true);
        }

        if(catStatus == 0){
            $('#status11').prop("checked",true);
        }else{
            $('#status22').prop("checked",true);
        }

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