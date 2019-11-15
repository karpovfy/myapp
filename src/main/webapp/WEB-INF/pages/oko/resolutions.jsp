<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ page session="true"%>
<style>
    .tf-pad{ margin-bottom: 6px; }
    .ui-jqgrid tr.ui-row-ltr td { height: 26px; }
    .ui-jqgrid tr.jqgrow td { padding: 0 2px 0 6px; vertical-align: middle; }
    /*.ui-jqgrid tr.jqgrow td { background-color: #E1FFF0; }*/
    .ui-state-hover td { background: none repeat scroll 0 0 #DDDDDD !important; }
    .ui-state-highlight td { background: none repeat scroll 0 0 #5CB85C !important; }

    .doc_dsc {background-color: #E8F4FA !important}
    .checkTime{background-color: #FEEBD4 !important;}
    #contDate {z-index:1051 !important;}
</style>
<div class="row well" style="padding-top: 0; border-radius:0">
    <form id="sfilter">
        <table class="table" id="tfilter">
            <thead>
            <tr>
                <th style="width:250px" class="text-info">Тип поручения / Исполнитель</th>
                <th style="width:250px" class="text-info">Создал / Контролирующий</th>
                <th style="width:170px" class="text-info">Контрольный срок</th>
                <th style="width:170px" class="text-info">Дата документа</th>
                <th style="width:120px" class="text-info">Содержание</th>
                <th>
                    <strong class="text-danger" id="strongCnt"></strong>
                    <a href="/new-resolution" class="btn btn-danger btn-xs">новое поручение</a>
                    <!--<a href="#" id="exportExcell"><span class="glyphicon glyphicon-floppy-save"></span></a>-->
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
                        <select class="form-control" name="dept" id="tdept">
                            <option value="0" selected="">Все отделы</option>
                            <option value="777">Аппарат управления</option>
                            <option value="555">Структурные подразделения</option>
                        </select>
                    </div>
                </td>
                <td>
                    <div class="input-group input-group-sm tf-pad">
                        <select class="form-control" name="creator_dept" id="creator_dept">
                            <option value="0" selected="">Все отделы</option>
                        </select>
                    </div>
                    <div class="input-group input-group-sm">
                        <select class="form-control" name="control_dept" id="control_dept">
                            <option value="0" selected="">Все отделы</option>
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
                    <div class="input-group input-group-sm tf-pad">
                        <input type="text" class="form-control" id="docName" name="docname" placeholder="№ док-та, описание">
                    </div>
                    <div class="input-group input-group-sm">
                    <select id="docArchive" name="docarchive" class="form-control">
                                <option value="0">Все</option>
                                <option value="1">С отчетом</option>
                                <option value="2">Без отчета</option>
                    </select>
                    <!--<input type="checkbox" id="docArchive" name="docarchive" value="1"> c отчетом
                        <input type="text" class="form-control" name="docnumber" id="docNumber" placeholder="номер">-->
                    </div>
                </td>
                <td>
                    <button type="submit" class="btn btn-primary btn-sm btn-block tf-pad">ПОИСК</button>
                    <a id="btnClear" href="#" class="btn btn-default btn-sm btn-block">очистить фильтр <i class="glyphicon glyphicon-remove"></i></a>
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
$('#resolutions').addClass('active');
var grd = $('#grid'),
    sfrm = $('#sfilter'),
    exExcell = $('#exportExcell'),
    owFileId = 0,
    oneDay = 1000*60*60*24,
    bgCol  = 'background-color',
    succCol= '#64FF64',
    warnCol= '#FFFF8C',
    failCol= '#FF6464';

$(document).ready(function(){
    grd.jqGrid({ url:'/allboxopt/list?mode=1', datatype:'json',
        colNames:['Тип документа','№ документа','Дата документа','№ пункта','Содержание поручения(пункта)','','Срок исполнения','Дата загрузки','Отдел исполнитель','Контролирующий отдел','','','','Статус отчета','Дата загр. отчета'],
        colModel:[
            {name:'orderTypeName',index:'orderTypeName',width:'340',sortable:false,title:false},
            {name:'documentNum',index:'documentNum',width:'100',sortable:true,title:false},
            {name:'documentDate',index:'documentDate',width:'100',sortable:true,title:false},
            {name:'itemNum',index:'itemNum',width:'70',sortable:true,title:false},
            {name:'itemDsc',index:'itemDsc',width:'380',sortable:false,title:false},
            {name:'checkTime',index:'checkTime',hidden:true},
            {name:'formatCheckTime',index:'checkTime',width:'110',sortable:true,title:false},
            {name:'reportDate',index:'reportDate',width:'110',sortable:true,title:false},
            {name:'exdeptName',index:'exdeptName',width:'400',sortable:true,title:false},
            {name:'deptName',index:'deptName',width:'400',sortable:true,title:false},
            {name:'orderId',hidden:true},
            {name:'orderItemId',hidden:true},
            {name:'mcurDate',hidden:true},
            {name:'status',hidden:true},
            {name:'reportDate',hidden:true}
        ],
        rowNum:25, sortname: "checkTime", sortorder:'desc', height:580, autowidth:false, viewrecords: true, scroll:true,
        gridComplete : function(){ $('#strongCnt').html('поручений: '+grd.jqGrid('getGridParam', 'records')); },
        ondblClickRow: function(id){
            //window.location = "/order/" + grd.getCell(id,"orderId")  +"/item/"+grd.getCell(id,"orderItemId")+"/"+id;
            window.open("/order/" + grd.getCell(id,"orderId")  +"/item/"+grd.getCell(id,"orderItemId")+"/"+id);
        },
        afterInsertRow : function(rowid, rowdata, rowelem){
            var documentDate = new Date(rowelem.documentDate),
                chDate = new Date(rowelem.checkTime),
                rpDate = new Date(rowelem.mcurDate),
                dayLeft= (chDate - rpDate)/oneDay,
                ctr    = $('tr#'+rowid+' td'),
                curCol = '';

            grd.setCell(rowid, "formatCheckTime", formatDate(chDate));
            grd.setCell(rowid, "documentDate", formatDate(documentDate));
            if(dayLeft<0){
                if(rowelem.status>0){ curCol = succCol
                }else{                curCol = failCol }
            }else{
                if(rowelem.status>0){ curCol = succCol
                }else{
                    if(dayLeft < 8){  curCol = warnCol }
                }
            }
            if(curCol != ''){
                ctr.css(bgCol,curCol)
            }
        }
    });

    sfrm.on('submit',runSearch);
    $('#btnClear').on('click',clearSfilter);
    //$('#docArchive,#tdoc,#tdept,#creator_dept,#control_dept').on('change',loadGrid);
    $('#dsDate,#deDate,#csDate,#ceDate,#contDate').datepicker({dateFormat:'dd.mm.yy'});// .on('change',loadGrid);

    insertOrderTypes(dtypes,'#tdoc,#newTdoc');
    insertDict(depts,'#tdept');
    insertDict(depts,'#creator_dept');
    insertDict(depts,'#control_dept');
});

function runSearch(){
    loadGrid();
    exExcell.attr('href','/list/deptitems?'+sfrm.serialize());
    return false;
}

function loadGrid(){
    grd.setGridParam({page:1,url:'/allboxopt/list?mode=1&'+sfrm.serialize()}).trigger('reloadGrid');
}

function clearSfilter(){
    $('#tdoc,#tdept,#creator_dept,#control_dept').val(0);
    $('#dsDate,#deDate,#csDate,#ceDate,#docName,#docNumber').val('');
    $('#docArchive').prop('checked',false);
    runSearch();
}

function insertDict(ar,id){
    for(var i = 0; i<ar.length; i++){
            $(id).append($('<option></option>').attr('value',ar[i][0]).html(ar[i][1]));
    }
}
function insertOrderTypes(ar,id){
    for(var i = 0; i<ar.length; i++){
        if(ar[i][2] == 1) $(id).append($('<option></option>').attr('value',ar[i][0]).html(ar[i][1]));
    }
}

function formatDate(date) {

    var dd = date.getDate()
    if ( dd < 10 ) dd = '0' + dd;

    var mm = date.getMonth()+1
    if ( mm < 10 ) mm = '0' + mm;

    var yy = date.getFullYear() % 100;
    if ( yy < 10 ) yy = '0' + yy;

    return dd+'.'+mm+'.20'+yy;
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