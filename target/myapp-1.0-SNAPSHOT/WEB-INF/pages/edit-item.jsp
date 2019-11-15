<%@ page contentType="text/html;charset=UTF-8" language="java" %><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %><%@ page session="true"%>

<div class="row well">
    <h3 class="text-info" style="margin-top:0">Редактирование пункта поручения</h3><hr>
    <form class="form-horizontal" role="form" id="editfrm" method="post" action="/edit/item/${item.orderItemId}" accept-charset="UTF-8">
        <div class="col-md-6">
        <div class="form-group" id="docnumGroup">
            <label for="itemnum" class="col-sm-4 control-label">Номер пункта:</label>
            <div class="col-sm-3">
                <input type="text" class="form-control" id="itemnum" name="itemnum" value="${item.itemNum}">
            </div>
            <label for="itemdate" class="col-sm-1 control-label">Срок:</label>
            <div class="col-sm-4">
                <div class="input-group">
                    <input type="text" class="form-control" id="itemdate" name="itemdate" value="<fmt:formatDate value="${item.checkTime}" pattern="dd.MM.yyyy" />">
                <span class="input-group-btn">
                  <label class="btn btn-default" for="itemdate"><i class="glyphicon glyphicon-calendar"></i></label>
              </span>
                </div>
            </div>
        </div>
        <div class="form-group" id="orderfileGroup">
            <label for="itemdsc" class="col-sm-4 control-label">Содержание пункта:</label>
            <div class="col-sm-8">
                <textarea id="itemdsc" name="itemdsc" class="form-control" style="height: 250px">${item.itemDsc}</textarea>
            </div>
        </div>
        <div class="form-group">
            <label for="contdept" class="col-sm-4 control-label">Контролирующий отдел:</label>
            <div class="col-sm-8">
                <select name="contdept" id="contdept" class="form-control"><option value="0"></option></select>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-6 pull-right">
                <button type="submit" class="btn btn-primary pull-right">сохранить <i class="glyphicon glyphicon-ok"></i></button>
                <a href="/order/${item.orderId}/item/${item.orderItemId}" class="btn btn-warning pull-right" style="margin-right: 6px">отмена</a>
            </div>
        </div>
        </div>
    </form>
        <div class="col-md-6">
            <table class="table table-condensed table-bordered table-striped">
                <thead>
                <tr>
                    <th>Исполнители</th>
                    <th>Отчет</th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${itemdepts}" var="d" >
                    <c:if test="${d.status == 1}">
                        <c:if test="${d.fileStatus == 3}">
                            <tr class="success">
                        </c:if>
                        <c:if test="${d.fileStatus == 1}">
                            <tr class="warning">
                        </c:if>
                        <td><a href="/order/${d.orderId}/item/${d.orderItemId}/${d.itemDeptId}"> ${d.exdeptName} </a></td>
                        <td><i class="glyphicon glyphicon-file"></i> <fmt:formatDate value="${d.reportDate}" pattern="dd.MM.yyyy" /></td>
                        <td><a href="/edit/item/dept/${d.itemDeptId}"><i class="glyphicon glyphicon-remove"></i></a></td>
                    </tr>
                    </c:if>

                    <c:if test="${d.status != 1}">
                        <tr>
                            <td><a href="/order/${d.orderId}/item/${d.orderItemId}/${d.itemDeptId}"> ${d.exdeptName} </a></td>
                            <td><i class="glyphicon glyphicon-minus"></i></td>
                            <td><a href="/edit/item/dept/${d.itemDeptId}"><i class="glyphicon glyphicon-remove"></i></a></td>
                        </tr>
                    </c:if>
                </c:forEach>
            </table>
            <form class="form-horizontal pull-right" role="form" id="addfrm" method="post" action="/edit/additem/${item.orderItemId}" accept-charset="UTF-8">
                <select name="exdept" id="exdept" class="form-control">
                    <option value="0"></option>
                </select>
                <button type="submit" id="addDeptBtn" class="btn btn-danger">Добавить исполнителя</button>
            </form>
        </div>
    <span class="bg-danger hide" id="alertP"></span>
</div>

<script>
    var ch = true, frm = $('#editfrm'), addfrm = $('#addfrm'), nowEx = {};

    <c:forEach items="${itemdepts}" var="d" >
      nowEx[${d.deptId}] = '${d.exdeptName}';
    </c:forEach>

    $(document).ready(function(){
        insertDict(depts,'#contdept');
        insertDict2(depts,'#exdept');
        $('#contdept').val(${item.controlDeptId});
        $('#itemdate').datepicker({dateFormat:'dd.mm.yy', minDate:'-1m'});
        frm.on('submit',saveItem);
        addfrm.on('submit',addDept);
    });

    function saveItem(){
        var nak = false, msg = '';
        if($('#itemdate').val() == ''){
            msg = '- Дата контрольного срока не задана!<br>';
            nak = true;
        }
        if($('#itemdsc').val() == ''){
            msg+= '- отсутствует содержание пункта';
            nak = true;
        }

        $('#alertP').empty().addClass('hide');

        if(nak){
            $('#alertP').html(msg).removeClass('hide');
            return false;
        }else{
            if(confirm("Редактирование пункта поручения!\n Сохранить изменения?")){
               return true;
            }
        }
        return false;
    }

    function addDept(){
        if($('#exdept').val() != 0){
            if(confirm("Добаввление исполнителя!\n Продолжить?")){
                return true;
            }
        }
        return false;
    }

    function insertDict(ar,id){
        for(var i = 0; i<ar.length; i++){
            $(id).append($('<option></option>').attr('value',ar[i][0]).html(ar[i][1]));
        }
    }
    function insertDict2(ar,id){
        for(var i = 0; i<ar.length; i++){
            if(!nowEx[ar[i][0]]){
              $(id).append($('<option></option>').attr('value',ar[i][0]).html(ar[i][1]));
            }
        }
    }

</script>