<%@ page contentType="text/html;charset=UTF-8" language="java" %><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %><%@ page session="true"%>

<!-- Форма редактирования документа -->
<div class="row well">
    <h3 class="text-info" style="margin-top:0">Редактирование документа</h3><hr>
    <form class="form-horizontal col-md-8" role="form" id="editfrm" method="post" action="/edit/order/${order.orderId}" enctype="multipart/form-data" accept-charset="utf-8">

        <div class="form-group" id="docnameGroup">
            <label for="docname" class="col-sm-4 control-label">Название документа:</label>
            <div class="col-sm-8">
                <textarea style="height: 216px;" name="docname" id="docname" class="form-control">${order.orderName}</textarea>
            </div>
        </div>

        <div class="form-group" id="ordertypeGroup">
            <label for="ordertype" class="col-sm-4 control-label">Тип документа:</label>
            <div class="col-sm-8">
                <select class="form-control" name="ordertype" id="ordertype"><option value="0"></option></select>
            </div>
        </div>

        <div class="form-group" id="docnumGroup">
            <label for="docnum" class="col-sm-4 control-label">Номер документа:</label>
            <div class="col-sm-3">
                <input type="text" class="form-control" id="docnum" name="docnum" value="${order.documentNum}">
            </div>
            <label for="docdate" class="col-sm-2 control-label">Дата док-та:</label>
            <div class="col-sm-3">
                <div class="input-group">
                <input type="text" class="form-control" id="docdate" name="docdate" value="<fmt:formatDate value="${order.documentDate}" pattern="dd.MM.yyyy" />">
                <span class="input-group-btn">
                  <label class="btn btn-default" for="docdate"><i class="glyphicon glyphicon-calendar"></i></label>
                </span>
                </div>
            </div>
        </div>

        <div class="form-group" id="old_file">
            <label class="col-sm-4 control-label">Файл поручения:</label>
            <div class="col-sm-8">
                <input type="text" disabled="disabled" class="form-control" id="oldfile" name="oldfile" value="${order.filename}">
            </div>
        </div>

        <div class="form-group" id="orderfileGroup">
            <label for="orderfile" class="col-sm-4 control-label">новый файл:</label>
            <div class="col-sm-8">
                <div class="input-group">
                    <input type="file" class="form-control" id="orderfile" name="orderfile">
              <span class="input-group-btn">
                  <label class="btn btn-default" for="orderfile"><i class="glyphicon glyphicon-save"></i></label>
              </span>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-6 pull-right">
                <button type="submit" class="btn btn-primary pull-right">сохранить <i class="glyphicon glyphicon-ok"></i></button>
                <a href="/order/${order.orderId}/items" class="btn btn-warning pull-right" style="margin-right: 6px">отмена</a>
            </div>
        </div>
    </form>
</div>


<script>
var frm = $('#editfrm');
$(document).ready(function(){
    insertDict(dtypes,'#ordertype');
    $('#ordertype').val(${order.documentType});
    frm.on('submit',saveOrder);
    $('#docdate').datepicker({dateFormat:'dd.mm.yy'});
});

function saveOrder(){
    var msg = '', lst = '', allst ='#docnameGroup,#ordertypeGroup,#docnumGroup,#docdateGroup,#orderfileGroup';
    if($('#docname').val() == ''){
        lst='#docnameGroup';
        msg+='Наименование документа<br>';
    }
    if($('#ordertype').val() == 0){
        lst+='#ordertypeGroup';
        msg+='Тип документа';
    }
    if($('#docnum').val() == ''){
        lst+='#docnumGroup';
        msg+='Номер документа';
    }
    if($('#docdate').val() == ''){
        lst+='#docdateGroup';
        msg+='Дата документа';
    }

    if(msg != ''){
        $(allst).removeClass('has-warning');
        $(lst).addClass('has-warning');
        return false;
    }else{
        if(!confirm("Редактирование документа!\n Сохранить изменения?")){
            return false;
        }
    }
    return true;
}

function insertDict(ar,id){
  for(var i = 0; i<ar.length; i++){
    $(id).append($('<option></option>').attr('value',ar[i][0]).html(ar[i][1]));
  }
}
</script>