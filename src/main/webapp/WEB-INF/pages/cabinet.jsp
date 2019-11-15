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
<form id="sfilter">
        <table class="table" id="tfilter">
            <thead>
            <tr>
                <th style="width:260px" class="text-info">Тип документа</th>
                <th style="width:170px" class="text-info">Дата создания</th>
                <th style="width:170px" class="text-info">Дата документа</th>
                <th style="width:360px" class="text-info">Документ</th>
                <th><c:if test="${user.userRole == 'ROLE_ADMIN' || user.userRole == 'ROLE_OKO'}"><a href="/add-order" class="btn btn-danger btn-sm btn-block">новое поручение</a></c:if></th>
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
                        <span class="input-group-addon" style="width:94px">№ документа</span>
                        <input type="text" class="form-control" name="docnumber" id="docNumber">
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
                    <div class="input-group input-group-sm">
                        <span class="input-group-addon">Название</span>
                        <input type="text" class="form-control" id="docName" name="docname">
                    </div>
                </td>
                <td>
                    <button type="submit" class="btn btn-primary btn-sm btn-block tf-pad">ПРИМЕНИТЬ</button>
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

<script>
    $('#cabinet').addClass('active');
    var grd = $('#grid'),
        sfrm = $('#sfilter'),
        owFileId = 0;
    $(document).ready(function(){
        grd.jqGrid({ url:'/cabinet/list', datatype:'json',
            colNames:['Тип документа','№ документа','Наименование','Дата документа','Отдел','Создал','Дата создания'],
            colModel:[
                {name:'orderTypeName',index:'OrderTypeName',width:'450',sortable:false,title:false},
                {name:'documentNum',index:'documentNum',width:'90',sortable:true,title:false},
                {name:'orderName',index:'orderName',width:'420',sortable:false,title:false,classes:'doc_dsc'},
                {name:'formattedDate',index:'formattedDate',width:'100',sortable:false,title:false},
                {name:'deptName',index:'deptName',width:'440',sortable:true,title:false},
                {name:'fullname',index:'fullname',width:'160',sortable:true,title:false},
                {name:'formattedOrderDate',index:'formattedOrderDate',width:'100',sortable:false,title:false}
            ],
            rowNum:25, sortname: "orderId", sortorder:'desc', height:580, autowidth:false, viewrecords: true, scroll:true,
            gridComplete : function(){ $('#strongCnt').html('документов: '+grd.jqGrid('getGridParam', 'records')); },
            ondblClickRow : function(id){
                //window.open("/order/" + id +"/items");
                window.location = "/order/" + id +"/items";
            }

            /*,
            afterInsertRow : function(rowid, rowdata, rowelem ){
                if(rowdata.reports == 0 && rowdata.daysLeft <= 0){
                    $('tr#'+rowid+' td').css('background-color','#F2DEDE');
                }
            } */
        });
        sfrm.on('submit',runSearch);
        $('#btnClear').on('click',clearSfilter);
        $('#tdoc').on('change',loadGrid);
        $('#dsDate,#deDate,#csDate,#ceDate').datepicker({dateFormat:'dd.mm.yy'}).on('change',loadGrid);

        insertDict(dtypes,'#tdoc,#newTdoc');
    });

    function runSearch(){
        loadGrid();
        return false;
    }

    function loadGrid(){
        grd.setGridParam({page:1,url:'/cabinet/list?'+sfrm.serialize()}).trigger('reloadGrid');
    }

    function clearSfilter(){
        $('#tdoc,#tdept').val(0);
        $('#dsDate,#deDate,#csDate,#ceDate,#docName,#docNumber').val('');
        $('#docArchive').prop('checked',false);
        runSearch();
    }

    function insertDict(ar,id){
        for(var i = 0; i<ar.length; i++){
            $(id).append($('<option></option>').attr('value',ar[i][0]).html(ar[i][1]));
        }
    }
    function jsonData(id){ var arr = $(id).serializeArray(), data ={}; for (var i=0;i<arr.length;i++){ data[arr[i].name]=arr[i].value } return data; }
    (function($){$.jgrid = $.jgrid || {};$.extend($.jgrid,{defaults : {recordtext: "Просмотр {0} - {1} из {2}",emptyrecords: "Нет записей для просмотра",loadtext: "Загрузка...",pgtext : "Стр. {0} из {1}"},search : {caption: "Поиск...",Find: "Найти",Reset: "Сброс",odata : ['равно', 'не равно', 'меньше', 'меньше или равно','больше','больше или равно', 'начинается с','не начинается с','находится в','не находится в','заканчивается на','не заканчивается на','содержит','не содержит'],groupOps: [{ op: "AND", text: "все" },{ op: "OR",text: "любой" }],matchText: " совпадает",rulesText: " правила" },edit : {addCaption: "Добавить запись",editCaption: "Редактировать запись",bSubmit: "Сохранить",bCancel: "Отмена",bClose: "Закрыть",saveData: "Данные были измененны! Сохранить изменения?",bYes : "Да",bNo : "Нет",bExit : "Отмена",msg: {required:"Поле является обязательным",number:"Пожалуйста, введите правильное число",minValue:"значение должно быть больше либо равно",maxValue:"значение должно быть меньше либо равно",email: "некорректное значение e-mail",integer: "Пожалуйста, введите целое число",date: "Пожалуйста, введите правильную дату",url: "неверная ссылка. Необходимо ввести префикс ('http://' or 'https://')",nodefined : " is not defined!",novalue : " return value is required!",customarray : "Custom function should return array!",customfcheck : "Custom function should be present in case of custom checking!"}},view : {caption: "Просмотр записи",bClose: "Закрыть"},del : {caption: "Удалить",msg: "Удалить выбранную запись(и)?",bSubmit: "Удалить",bCancel: "Отмена"},nav : {edittext: " ",edittitle: "Редактировать выбранную запись",addtext:" ",addtitle: "Добавить новую запись",deltext: " ",deltitle: "Удалить выбранную запись",searchtext: " ",searchtitle: "Найти записи",refreshtext: "",refreshtitle: "Обновить таблицу",alertcap: "Внимание",alerttext: "Пожалуйста, выберите запись",viewtext: "",viewtitle: "Просмотреть выбранную запись"},col : {caption: "Показать/скрыть столбцы",bSubmit: "Сохранить",bCancel: "Отмена"},errors : {errcap : "Ошибка",nourl : "URL не установлен",norecords: "Нет записей для обработки",model : "Число полей не соответствует числу столбцов таблицы!"},formatter : {integer : {thousandsSeparator: " ", defaultValue: '0'},number : {decimalSeparator:",", thousandsSeparator: " ", decimalPlaces: 2, defaultValue: '0,00'},currency : {decimalSeparator:",", thousandsSeparator: " ", decimalPlaces: 2, prefix: "", suffix:"", defaultValue: '0,00'},date : {dayNames: ["Вс", "Пн", "Вт", "Ср", "Чт", "Пт", "Сб","Воскресение", "Понедельник", "Вторник", "Среда", "Четверг", "Пятница", "Суббота"],monthNames: ["Янв", "Фев", "Мар", "Апр", "Май", "Июн", "Июл", "Авг", "Сен", "Окт", "Ноя", "Дек","Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"],AmPm : ["am","pm","AM","PM"],S: function (j) {return j < 11 || j > 13 ? ['st', 'nd', 'rd', 'th'][Math.min((j - 1) % 10, 3)] : 'th'},srcformat: 'Y-m-d',newformat: 'd.m.Y',masks : {ISO8601Long:"Y-m-d H:i:s",ISO8601Short:"Y-m-d",ShortDate: "n.j.Y",LongDate: "l, F d, Y",FullDateTime: "l, F d, Y G:i:s",MonthDay: "F d",ShortTime: "G:i",LongTime: "G:i:s",SortableDateTime: "Y-m-d\\TH:i:s",UniversalSortableDateTime: "Y-m-d H:i:sO",YearMonth: "F, Y"},reformatAfterEdit : false},baseLinkUrl: '',showAction: '',target: '',checkbox : {disabled:true},idName : 'id'}});})(jQuery);
</script>