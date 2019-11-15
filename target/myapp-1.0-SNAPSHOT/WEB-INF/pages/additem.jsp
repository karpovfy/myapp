<%@ page contentType="text/html;charset=UTF-8" language="java" %><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page session="true"%>

<div class="row well">
    <h3 class="text-info">Добавить пункт к поручению</h3>
    <form class="form-horizontal" role="form" id="addfrm" method="post" action="/order/${order}/additem" accept-charset="UTF-8">
        <div class="col-md-6">
        <div class="form-group" id="docnumGroup">
            <label for="itemnum" class="col-sm-4 control-label">Номер пункта:</label>
            <div class="col-sm-3">
                <input type="text" class="form-control" id="itemnum" name="itemnum">
            </div>
            <label for="itemdate" class="col-sm-1 control-label">Срок:</label>
            <div class="col-sm-4">
                <div class="input-group">
                    <input type="text" class="form-control" id="itemdate" name="itemdate">
                <span class="input-group-btn">
                  <label class="btn btn-default" for="itemdate"><i class="glyphicon glyphicon-calendar"></i></label>
              </span>
                </div>
            </div>
        </div>
        <div class="form-group" id="orderfileGroup">
            <label for="itemdsc" class="col-sm-4 control-label">Содержание пункта:</label>
            <div class="col-sm-8">
                    <textarea id="itemdsc" name="itemdsc" class="form-control"></textarea>
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
                <button type="submit" class="btn btn-primary pull-right">создать <i class="glyphicon glyphicon-ok"></i></button>
                <a href="/order/${order}/items" class="btn btn-warning pull-right" style="margin-right: 6px">отмена</a>
            </div>
        </div>
        </div>
        <div class="col-md-6">
            <div class="panel panel-success">
                <div class="panel-heading">
                    <h3 class="panel-title">Исполнители
                        <span class="pull-right" id="selAll" style="cursor:pointer">&nbsp;<i class="glyphicon glyphicon-th-list"></i>&nbsp;все</span>
                        <span class="pull-right" id="selRosp" style="cursor:pointer">&nbsp;&nbsp;РОСП</span>
                        <!--<span class="pull-right" id="selSpec" style="cursor:pointer">&nbsp;<i class="glyphicon glyphicon-th-list"></i>&nbsp;Спец.ОСП</span>
                        <span class="pull-right" id="selUprav" style="cursor:pointer">&nbsp;<i class="glyphicon glyphicon-th-list"></i>&nbsp;Управление</span>
                        -->
                    </h3>
                </div>
                <ul class="list-group" id="lbDepts"></ul>
            </div>
        </div>
    </form>
    <span class="bg-danger hide" id="alertP"></span>
</div>

<script>
    var ch = true, frm = $('#addfrm');
    $(document).ready(function(){
        insertDict2(depts,'#contdept');
        insertDict(depts,'#lbDepts');
        $('#contdept').val(${user.deptId});
        $('#selAll').on('click',reCheck);
        $('#selRosp').on('click',function(){ checkDepts(3) });
        frm.on('submit',saveItem);
        $('#itemdate').datepicker({dateFormat:'dd.mm.yy', minDate:1});
    });

    function saveItem(){
        var nak = false;
        $('input[name=depts]').each(function(){
            if($(this).prop('checked')){
                nak = true;
            }
        });

        $('#alertP').empty().addClass('hide');

        if(nak){
            return true;
        }else{
            alert("Выберите исполнителей (отделы) ");
            console.log('select dept');
            return false
        }
        return true;
    }

    function insertDict(ar,id){
        for(var i = 0; i<ar.length; i++){
            var vl = ar[i][0];
            $(id).append($('<input>').val(vl).attr('type','checkbox').attr('name','depts').attr('id','dept_'+vl).addClass('hide'));
            $(id).append($('<label></label>').attr('for','dept_'+vl).html(ar[i][1]).attr('id','lb_dept_'+vl).addClass('list-group-item'));
        }
        $('input[name=depts]').change(function(){
            if($(this).prop('checked')){
                $('#lb_dept_'+$(this).val()).css({color:'white',background:'#428BCA'});
            }else{
                $('#lb_dept_'+$(this).val()).attr('style','');
            }
        });
    }

    function reCheck(){
        var k = 0;
        if(ch){
           k = 1;
           ch = false;
        }else{
           ch = true
        }
        checkAll(k);
    }

    function checkAll(k){
        $('input[name=depts]').each(function(){
            if(k==1){
                $('#lb_dept_'+$(this).val()).css({color:'white',background:'#428BCA'});
                $(this).prop('checked',true);
            }else{
                $('#lb_dept_'+$(this).val()).attr('style','');
                $(this).prop('checked',false);
            }
        });
    }

    function checkDepts(rng){
        var msg= '';
        checkAll(0);
        for(var i = 0; i<depts.length; i++){
            var vl = depts[i];
            if(vl[2]==rng){
                $('#lb_dept_'+vl[0]).css({color:'white',background:'#428BCA'});
                $('#dept_'+vl[0]).prop('checked',true);
            }
        }
    }

    function insertDict2(ar,id){
        for(var i = 0; i<ar.length; i++){
            $(id).append($('<option></option>').attr('value',ar[i][0]).html(ar[i][1]));
        }
    }

</script>