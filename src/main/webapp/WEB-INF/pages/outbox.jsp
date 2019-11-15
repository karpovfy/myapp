<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true"%>

<style>
    .tf-pad{ margin-bottom: 6px; }
    .ui-jqgrid tr.ui-row-ltr td { height: 26px; }
    .ui-jqgrid tr.jqgrow td { padding: 0 2px 0 6px; vertical-align: middle; }
    .ui-jqgrid tr.jqgrow td { background-color: #E1FFF0; }
    .ui-state-hover td { background: none repeat scroll 0 0 #DDDDDD !important; }
    .ui-state-highlight td { background: none repeat scroll 0 0 #5CB85C !important; }

    .doc_dsc {background-color: #E8F4FA !important}
    #contDate {z-index:1051 !important;}

    .timeline {
        list-style: none;
        padding: 20px 0 20px;
        position: relative;
    }
    .timeline:before {
        top: 0;
        bottom: 0;
        position: absolute;
        content: " ";
        width: 3px;
        background-color: #eeeeee;
        right: 25px;
        margin-left: -1.5px;
    }
    .timeline > li {
        margin-bottom: 20px;
        position: relative;
    }
    .timeline > li:before,
    .timeline > li:after {
        content: " ";
        display: table;
    }
    .timeline > li:after {
        clear: both;
    }
    .timeline > li:before,
    .timeline > li:after {
        content: " ";
        display: table;
    }
    .timeline > li:after {
        clear: both;
    }
    .timeline > li > .timeline-panel {
        width: calc( 100% - 75px );
        float: left;
        border: 1px solid #d4d4d4;
        border-radius: 2px;
        padding: 20px;
        position: relative;
        -webkit-box-shadow: 0 1px 6px rgba(0, 0, 0, 0.175);
        box-shadow: 0 1px 6px rgba(0, 0, 0, 0.175);
    }
    .timeline > li > .timeline-panel:before {
        position: absolute;
        top: 26px;
        right: -15px;
        display: inline-block;
        border-top: 15px solid transparent;
        border-left: 15px solid #ccc;
        border-right: 0 solid #ccc;
        border-bottom: 15px solid transparent;
        content: " ";
    }
    .timeline > li > .timeline-panel:after {
        position: absolute;
        top: 27px;
        right: -14px;
        display: inline-block;
        border-top: 14px solid transparent;
        border-left: 14px solid #fff;
        border-right: 0 solid #fff;
        border-bottom: 14px solid transparent;
        content: " ";
    }
    .timeline > li > .timeline-badge {
        color: #fff;
        width: 50px;
        height: 50px;
        line-height: 50px;
        font-size: 1.4em;
        text-align: center;
        position: absolute;
        top: 16px;
        right: 0px;
        margin-left: -25px;
        background-color: #999999;
        z-index: 100;
        border-top-right-radius: 50%;
        border-top-left-radius: 50%;
        border-bottom-right-radius: 50%;
        border-bottom-left-radius: 50%;
    }
    .timeline > li.timeline-inverted > .timeline-panel {
        float: right;
    }
    .timeline > li.timeline-inverted > .timeline-panel:before {
        border-left-width: 0;
        border-right-width: 15px;
        left: -15px;
        right: auto;
    }
    .timeline > li.timeline-inverted > .timeline-panel:after {
        border-left-width: 0;
        border-right-width: 14px;
        left: -14px;
        right: auto;
    }
    .timeline-badge.primary {
        background-color: #2e6da4 !important;
    }
    .timeline-badge.success {
        background-color: #3f903f !important;
    }
    .timeline-badge.warning {
        background-color: #f0ad4e !important;
    }
    .timeline-badge.danger {
        background-color: #d9534f !important;
    }
    .timeline-badge.info {
        background-color: #5bc0de !important;
    }
    .timeline-title {
        margin-top: 0;
        color: inherit;
    }
    .timeline-body > p,
    .timeline-body > ul {
        margin-bottom: 0;
    }
    .timeline-body > p + p {
        margin-top: 5px;
    }
</style>
<div class="row well" style="padding-top: 0; border-radius:0">
    <div class="row" style="padding-left: 15px; padding-right:15px; padding-top: 12px">
        <div class="col-md-4">
            <a class="btn btn-danger btn-block btn-sm disabled tx-left" href="#">&nbsp;В статусе "на проверке":
                <strong class="pull-right" style="font-size: 16px; color: rgb(0, 0, 0); margin-top: -21px;">${card.checkCount}&nbsp;</strong>
            </a>
            <a class="btn btn-danger btn-block btn-sm disabled tx-left" href="#">&nbsp;Проверено
                <strong class="pull-right" style="font-size: 16px; color: rgb(0, 0, 0); margin-top: -21px;">${card.successCount}&nbsp;</strong>
            </a>
        </div>
        <div class="col-md-4">
            <a class="btn btn-warning btn-block btn-sm disabled tx-left" href="#">&nbsp;Истек срок проверки:
                <strong class="pull-right" style="font-size: 16px; color: rgb(0, 0, 0); margin-top: -21px;">${card.nocheckCount}&nbsp;</strong>
            </a>
            <a class="btn btn-warning btn-block btn-sm disabled tx-left" href="#">&nbsp;Отклонено:
                <strong class="pull-right" style="font-size: 16px; color: rgb(0, 0, 0); margin-top: -21px;">${card.cancelCount}&nbsp;</strong>
            </a>
        </div>
        <div class="col-md-4">
            <a class="btn btn-default btn-block btn-sm disabled tx-left" href="#" style="white-space: pre-line; overflow-y: hidden; height: 65px; min-height: 65px; padding-left: 4px;">${message.msgText}</a>
        </div>
    </div>
    <form id="sfilter">
        <table class="table" id="tfilter">
            <thead>
            <tr>
                <th style="width:260px" class="text-info">Тип документа / Состояние</th>
                <th style="width:170px" class="text-info">Контрольный срок</th>
                <th style="width:360px" class="text-info">Документ / Поручение</th>
                <th style="width:170px" class="text-info">Дата документа</th>
                <th>
                    <c:if test="${udept.deptType == 1}">
                        <a href="/add-order" class="btn btn-danger btn-sm btn-block">новое поручение</a>
                    </c:if>
                    <!--<a href="/list/excel" class="btn btn-default btn-sm btn-block"><i class="glyphicon glyphicon-floppy-save"></i> загрузить в Excel</a> -->
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>
                    <div class="input-group input-group-sm tf-pad">
                        <select class="form-control" name="tdoc" id="tdoc">
                            <option value="0" selected="">Все</option>
                        </select>
                    </div>
                    <div class="input-group input-group-sm">
                        <select class="form-control" name="period" id="tperiod">
                            <option value="0" selected=""></option>
                            <option value="1">Контрольный срок в ближайшие 7 дней</option>
                            <option value="2">Контрольный срок в ближайшие 14 дней</option>
                            <option value="3">Контрольный срок в этом месяце</option>
                            <option value="4">Контрольный срок истек</option>
                            <option value="5">Просроченные</option>
                            <option value="6">Выполненные</option>
                        </select>
                    </div>
                </td>
                <td>
                    <div class="input-group input-group-sm tf-pad">
                        <span class="input-group-addon" style="width:35px">c</span>
                        <input type="text" class="form-control" name="dsdate" id="dsDate">
                        <label for="dsDate" class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></label>
                    </div>
                    <div class="input-group input-group-sm">
                        <span class="input-group-addon">по</span>
                        <input type="text" class="form-control" name="dedate" id="deDate">
                        <label for="deDate" class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></label>
                    </div>
                </td>
                <td>
                    <div class="input-group input-group-sm tf-pad">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-align-left"></i></span>
                        <input type="text" class="form-control" id="docName" name="docname">
                    </div>
                    <div class="input-group input-group-sm">
                        <span class="input-group-addon">№</span>
                        <input type="text" class="form-control" name="docnumber" id="docNumber">
                    </div>
                </td>
                <td>
                    <div class="input-group input-group-sm tf-pad">
                        <span class="input-group-addon" style="width:35px">c</span>
                        <input type="text" class="form-control" name="csdate" id="csDate">
                        <label for="csDate" class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></label>
                    </div>
                    <div class="input-group input-group-sm">
                        <span class="input-group-addon">по</span>
                        <input type="text" class="form-control" name="cedate" id="ceDate">
                        <label for="ceDate" class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></label>
                    </div>
                </td>
                <td>
                    <button type="submit" class="btn btn-primary btn-sm btn-block tf-pad">ПОИСК</button>
                    <div class="input-group input-group-sm pull-right">
                        <strong class="text-primary" id="strongCnt"></strong>
                        <a id="btnClear" href="#" class="btn btn-default btn-sm"><i class="glyphicon glyphicon-remove"></i></a>
                    </div>

                </td>
            </tr>
            </tbody>
        </table>
    </form>
</div>
<div class="row" style="overflow-x: scroll; margin-top: -50px;">
    <table id="grid"></table>
</div>

<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header" style="padding:2px 15px;background:#0088CC;color:#fff;margin-bottom:0">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <strong class="modal-title" id="myModalLabel">Новое поручение</strong>
            </div>
            <div class="modal-body well" style="border-radius:0;margin-bottom:0;">
                <div class="row">
                    <form id="newOrder" onsubmit="return false">
                        <input type="hidden" name="fileid" id="fileId">
                        <div class="col-md-12">
                            <div class="form-group">
                                <label for="newDocname">Название</label>
                                <input type="text" class="form-control" id="newDocname" required="required" name="docname"/>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="newTdoc">Тип поручения</label>
                                <select id="newTdoc" name="tdock" class="form-control" required="required">
                                    <option value="0" selected="">Выбрать</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="newDept">Отдел</label>
                                <select id="newDept" name="dept" class="form-control" required="required">
                                    <option value="0" selected="">Всем</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="cexId">Ответственный</label>
                                <select id="cexId" name="cexid" class="form-control" required="required">
                                    <option value="0" selected="">Все</option>
                                </select>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="newDsc">Описание</label>
                                <textarea name="dsc" id="newDsc" class="form-control" rows="9" cols="25" style="height: 108px;" required="required"></textarea>
                            </div>
                            <div class="form-group">
                                <label for="contDate">Срок выполнения</label>
                                <div class="input-group">
                                    <span class="input-group-addon">до</span>
                                    <input type="text" id="contDate" name="contdate" class="form-control">
                                    <label class="input-group-addon" for="contDate"><i class="glyphicon glyphicon-calendar"></i></label>
                                </div>
                            </div>

                        </div>
                    </form>
                    <form onsubmit="return false">
                        <div class="col-md-12">
                            <div class="input-group input-group-sm">
                                <span class="input-group-addon">Файл поручения</span>
                                <input type="text" class="form-control" name="fileName" id="fileName" readonly="true">
                                    <span class="input-group-btn" id="procLoad">
                                        <label for="file" class="btn btn-default" id="procBar"><i class="glyphicon glyphicon-save"></i> прикрепить</label>
                                    </span>
                            </div>
                            <input type="file" class="hide" id="file" required="required" />
                        </div>
                    </form>
                </div>
            </div>
            <div class="modal-footer" style="margin-top:0;">
                <button type="button" class="btn btn-default" data-dismiss="modal">Отмена</button>
                <button type="button" class="btn btn-primary" id="btnNewOrder">Создать</button>
            </div>
        </div>
    </div>
</div>

<!-- Modal VIEW-->
<div class="modal fade" id="myModalView" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content" style=" border-radius:0">
            <div class="modal-header" style="padding:2px 15px;background:#0088CC;color:#fff;margin-bottom:0">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <strong class="modal-title"><i class="glyphicon glyphicon-th-list" style="font-size: 0.84em;"></i> <span id="lbTdoc"></span></strong>
            </div>
            <div class="modal-body" style="border-radius:0;margin-bottom:0;">
                <div class="timeline-panel">
                    <div class="timeline-heading">
                        <p><small class="text-muted"><i class="glyphicon glyphicon-time"></i> 14.03.2014 </small><small id="lbOwner" class="pull-right"></small></p>
                    </div>
                    <div class="timeline-body well">
                        <p id="dsc"></p>
                    </div>
                </div>
                <span class="label label-primary">СРОК ИСПОЛНЕНИЯ ДО:</span> &nbsp;<strong id="lbContdate">04.05.2014</strong>
                <span class="label label-danger" id="lbLdays">ОСТАЛОСЬ:</span> &nbsp;<strong id="lbLeftdays"> 43 дня</strong><br>
                <strong>Отдел :</strong> &nbsp;<span class="text-primary" id="lbDept"></span><br>
                <strong>Файл поручения :</strong> &nbsp;<span class="text-primary" id="lbFile"></span><a href="#"><i class="glyphicon glyphicon-file"></i></a><br>
                <table class="table" id="orderReports">
                    <thead>
                    <tr>
                        <th>Отчет</th>
                        <th>Пользователь</th>
                    </tr>
                    </thead>
                    <tbody>

                    </tbody>
                </table>
                <form onsubmit="return false">
                    <input type="hidden" name="order" id="repOrderId">
                    <input type="file" class="hide" id="file2" required="required" />
                </form><br>
            </div>
            <div class="modal-footer" style="margin:0; padding:10px;">
                <label class="btn btn-default btn-sm pull-left" for="file2" id="lbFile2"><i class="glyphicon glyphicon-save"></i> Загрузить отчет</label>
                <button type="button" class="btn btn-primary btn-sm" data-dismiss="modal">Закрыть</button>
            </div>
        </div>
    </div>
</div>

<script>
$('#outbox').addClass('active');
var grd = $('#grid'),
        sfrm = $('#sfilter'),
        owFileId = 0;
$(document).ready(function(){
    grd.jqGrid({ url:'/cabinet/outitems', datatype:'json',
        colNames:['№ пункта','Описание','Срок','Тип документа','Контролирующий отдел','','','',''],
        colModel:[
            {name:'itemNum',index:'itemNum',width:'70',sortable:true,title:false},
            {name:'itemDsc',index:'itemDsc',width:'440',sortable:false,title:false},
            {name:'fcheckTime',index:'checkTime',width:'80',sortable:true,title:false,classes:'checkTime'},
            {name:'orderTypeName',index:'orderTypeID',width:'440',sortable:true,title:false},
            {name:'contDeptName',index:'contDeptName',width:'200',sortable:true,title:false},
            {name:'itemDeptId',index:'itemDeptId',width:'70',sortable:false,title:false,hidden:true},
            {name:'orderId',index:'itemDeptId',width:'70',sortable:false,title:false,hidden:true},
            {name:'orderItemId',index:'itemDeptId',width:'70',sortable:false,title:false,hidden:true},
            {name:'exDeptId',index:'exDeptId',width:'70',sortable:false,title:false,hidden:true}
        ],
        rowNum:25, sortname: "checkTime", sortorder:'desc', height:580, autowidth:false, viewrecords: true, scroll:true,
        gridComplete : function(){ $('#strongCnt').html('поручений: '+grd.jqGrid('getGridParam', 'records')); },
        ondblClickRow : function(id){
            //window.open("/order/" + grd.getCell(id,"orderId")  +"/item/"+grd.getCell(id,"orderItemId"));
            window.location = "/order/" + grd.getCell(id,"orderId")  +"/item/"+grd.getCell(id,"orderItemId");
            //itemDeptId exDeptId orderId orderItemId
        },
        afterInsertRow : function(rowid, rowdata, rowelem ){
            if(rowdata.reports == 0 && rowdata.daysLeft <= 0){
                $('tr#'+rowid+' td').css('background-color','#F2DEDE');
            }
        }

    });
    sfrm.on('submit',runSearch);
    $('#btnClear').on('click',clearSfilter);
    $('#btnNewOrder').on('click',createOrder);
    //$('#docArchive,#tdoc,#tperiod').on('change',loadGrid);
    $('#dsDate,#deDate,#csDate,#ceDate,#contDate').datepicker({dateFormat:'dd.mm.yy'}); //.on('change',loadGrid);

    $('#openNewOrder').on('click',function(){
        clearNewOrder();
        $('#myModal').modal('show');
    });
    $('#file').on('change',addFile);
    $('#file2').on('change',addReportFile);
    insertDict(dtypes,'#tdoc,#newTdoc');
});

function runSearch(){
    loadGrid();
    return false;
}

function loadGrid(){
    grd.setGridParam({page:1,url:'/cabinet/outitems?'+sfrm.serialize()}).trigger('reloadGrid');
}

function createOrder(){
    var dt  = jsonData('#newOrder'),
            msg = '';
    if(dt.docname == ''){
        msg = 'Название\n';
    }
    if(dt.tdock == 0){
        msg += 'Тип поручения\n';
    }
    if(dt.contdate == ''){
        msg += 'Срок выполнения\n';
    }
    if(dt.fileid == ''){
        msg += 'Файл поручения';
    }
    if(msg != ''){
        alert('Не заполнены поля\n'+msg)
    }else{
        $.post('/cabinet/addorder',$('#newOrder').serialize(), function(data){ $('#myModal').modal('hide'); loadGrid(); alert('Поручение создано') });
    }

}

function clearSfilter(){
    $('#tdoc,#tdept').val(0);
    $('#dsDate,#deDate,#csDate,#ceDate,#docName,#docNumber').val('');
    $('#docArchive').prop('checked',false);
    runSearch();
}

function clearNewOrder(){
    $('#newDocname,#fileId,#newDsc,#contDate,#fileName').val('');
    $('#newTdoc,#newDept,#cexId').val(0);
    $('#procBar').removeClass('progress-bar').css('width','100%').removeClass('btn-success');
}

function addFile(){
    if(file.files[0]){
        $('#procLoad').addClass('progress progress-striped active');
        $('#procBar').addClass('progress-bar').css('width','100%').addClass('btn-success');
        $('#fileName').val(file.files[0].name);
        uploadFormData(1);
    }
}
function addReportFile(){
    if(file2.files[0]){
        uploadFormData(2);
        $('#myModalView').modal('hide');
    }
}

function insertDict(ar,id){
    for(var i = 0; i<ar.length - 1; i++){
        $(id).append($('<option></option>').attr('value',ar[i][0]).html(ar[i][1]));
    }
}

//===== AJAX FILE UPLOAD BLOCK
//using FormData() object
function uploadFormData(k){
    var oMyForm = new FormData(),
            urlS = 'test';
    if(k == 1){
        oMyForm.append("file", file.files[0]);
    }else{
        oMyForm.append("file", file2.files[0]);
        oMyForm.append("order", $('#repOrderId').val());
        urlS += '2';
    }

    $.ajax({
        url: '/upload/'+urlS,
        data: oMyForm,
        dataType: 'json',
        processData: false,
        contentType: false,
        type: 'POST',
        success: function(data){
            owFileId = data.id;
            $('#fileId').val(owFileId);
            $('#procLoad').removeClass('progress progress-striped active');
            $('#procBar').removeClass('progress-bar');
        }
    });
}
//===== END AJAX FILE UPLOAD BLOCK

function jsonData(id){ var arr = $(id).serializeArray(), data ={}; for (var i=0;i<arr.length;i++){ data[arr[i].name]=arr[i].value } return data; }
(function($){$.jgrid = $.jgrid || {};$.extend($.jgrid,{defaults : {recordtext: "Просмотр {0} - {1} из {2}",emptyrecords: "Нет записей для просмотра",loadtext: "Загрузка...",pgtext : "Стр. {0} из {1}"},search : {caption: "Поиск...",Find: "Найти",Reset: "Сброс",odata : ['равно', 'не равно', 'меньше', 'меньше или равно','больше','больше или равно', 'начинается с','не начинается с','находится в','не находится в','заканчивается на','не заканчивается на','содержит','не содержит'],groupOps: [{ op: "AND", text: "все" },{ op: "OR",text: "любой" }],matchText: " совпадает",rulesText: " правила" },edit : {addCaption: "Добавить запись",editCaption: "Редактировать запись",bSubmit: "Сохранить",bCancel: "Отмена",bClose: "Закрыть",saveData: "Данные были измененны! Сохранить изменения?",bYes : "Да",bNo : "Нет",bExit : "Отмена",msg: {required:"Поле является обязательным",number:"Пожалуйста, введите правильное число",minValue:"значение должно быть больше либо равно",maxValue:"значение должно быть меньше либо равно",email: "некорректное значение e-mail",integer: "Пожалуйста, введите целое число",date: "Пожалуйста, введите правильную дату",url: "неверная ссылка. Необходимо ввести префикс ('http://' or 'https://')",nodefined : " is not defined!",novalue : " return value is required!",customarray : "Custom function should return array!",customfcheck : "Custom function should be present in case of custom checking!"}},view : {caption: "Просмотр записи",bClose: "Закрыть"},del : {caption: "Удалить",msg: "Удалить выбранную запись(и)?",bSubmit: "Удалить",bCancel: "Отмена"},nav : {edittext: " ",edittitle: "Редактировать выбранную запись",addtext:" ",addtitle: "Добавить новую запись",deltext: " ",deltitle: "Удалить выбранную запись",searchtext: " ",searchtitle: "Найти записи",refreshtext: "",refreshtitle: "Обновить таблицу",alertcap: "Внимание",alerttext: "Пожалуйста, выберите запись",viewtext: "",viewtitle: "Просмотреть выбранную запись"},col : {caption: "Показать/скрыть столбцы",bSubmit: "Сохранить",bCancel: "Отмена"},errors : {errcap : "Ошибка",nourl : "URL не установлен",norecords: "Нет записей для обработки",model : "Число полей не соответствует числу столбцов таблицы!"},formatter : {integer : {thousandsSeparator: " ", defaultValue: '0'},number : {decimalSeparator:",", thousandsSeparator: " ", decimalPlaces: 2, defaultValue: '0,00'},currency : {decimalSeparator:",", thousandsSeparator: " ", decimalPlaces: 2, prefix: "", suffix:"", defaultValue: '0,00'},date : {dayNames: ["Вс", "Пн", "Вт", "Ср", "Чт", "Пт", "Сб","Воскресение", "Понедельник", "Вторник", "Среда", "Четверг", "Пятница", "Суббота"],monthNames: ["Янв", "Фев", "Мар", "Апр", "Май", "Июн", "Июл", "Авг", "Сен", "Окт", "Ноя", "Дек","Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"],AmPm : ["am","pm","AM","PM"],S: function (j) {return j < 11 || j > 13 ? ['st', 'nd', 'rd', 'th'][Math.min((j - 1) % 10, 3)] : 'th'},srcformat: 'Y-m-d',newformat: 'd.m.Y',masks : {ISO8601Long:"Y-m-d H:i:s",ISO8601Short:"Y-m-d",ShortDate: "n.j.Y",LongDate: "l, F d, Y",FullDateTime: "l, F d, Y G:i:s",MonthDay: "F d",ShortTime: "G:i",LongTime: "G:i:s",SortableDateTime: "Y-m-d\\TH:i:s",UniversalSortableDateTime: "Y-m-d H:i:sO",YearMonth: "F, Y"},reformatAfterEdit : false},baseLinkUrl: '',showAction: '',target: '',checkbox : {disabled:true},idName : 'id'}});})(jQuery);
</script>