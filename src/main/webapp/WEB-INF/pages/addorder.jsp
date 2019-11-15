<%@ page contentType="text/html;charset=UTF-8" language="java" %><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ page session="true"%>
<div class="row well">
    <h3 class="text-info">Новый документ</h3><hr>
    <form class="form-horizontal col-md-8" role="form" id="addfrm" method="post" action="/add-order" enctype="multipart/form-data" accept-charset="utf-8">
    <div class="form-group" id="docnameGroup">
        <label for="docname" class="col-sm-4 control-label">Название документа:</label>
        <div class="col-sm-8">
            <!--<input type="text" class="form-control" id="docname" name="docname">-->
            <textarea style="height: 216px;" name="docname" id="docname" class="form-control"></textarea>
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
        <div class="col-sm-2">
            <input type="text" class="form-control" id="docnum" name="docnum">
        </div>
        <label for="docdate" class="col-sm-3 control-label">Дата документа:</label>
        <div class="col-sm-3">
            <div class="input-group">
                <input type="text" class="form-control" id="docdate" name="docdate">
                <span class="input-group-btn">
                  <label class="btn btn-default" for="docdate"><i class="glyphicon glyphicon-calendar"></i></label>
              </span>
            </div>
        </div>
    </div>
    <div class="form-group" id="orderfileGroup">
        <label for="orderfile" class="col-sm-4 control-label">Файл документа:</label>
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
            <button type="submit" class="btn btn-primary pull-right">создать <i class="glyphicon glyphicon-ok"></i></button>
            <a href="/inbox" class="btn btn-warning pull-right" style="margin-right: 6px">отмена</a>
        </div>
    </div>
    </form>
</div>

<script>
    var frm = $('#addfrm');
    $(document).ready(function(){
       insertDict(dtypes,'#ordertype');
       frm.on('submit',addOrder);
       $('#docdate').datepicker({dateFormat:'dd.mm.yy'});
    });

    function addOrder(){
        var msg = '', lst = '', allst ='#docnameGroup,#ordertypeGroup,#docnumGroup,#docdateGroup,#orderfileGroup';
        if($('#docname').val() == ''){
            lst='#docnameGroup,';
            msg+='Наименование документа<br>';
        }
        if($('#ordertype').val() == 0){
            lst+='#ordertypeGroup,';
            msg+='Тип документа';
        }
        if($('#docnum').val() == ''){
            lst+='#docnumGroup,';
            msg+='Номер документа';
        }
        if($('#docdate').val() == ''){
            lst+='#docdateGroup,';
            msg+='Дата документа';
        }

        if(!orderfile.files[0]){
            lst+='#orderfileGroup';
            msg+='Файл документа';
        }

        if(msg != ''){
            $(allst).removeClass('has-warnig');
            $(lst).addClass('has-warning');
            return false;
        }
        return true;

    }

    function insertDict(ar,id){
        for(var i = 0; i<ar.length; i++){
            if(ar[i][2] == 0) $(id).append($('<option></option>').attr('value',ar[i][0]).html(ar[i][1]));
        }
    }
</script>