<%@ page contentType="text/html;charset=UTF-8" language="java" %><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %><%@ page session="true"%>

<div class="row well" style="border-radius: 0;padding: 10px 0 0">
    <div class="col-md-6">
        <h4 class="text-primary">&nbsp;Документ</h4>
        <table class="table table-condensed">
            <tbody>
            <tr class="success"><td style="padding: 8px 20px" colspan="2">${it.orderName}</td></tr>
            <tr class="success"><td style="width:160px"><strong>Тип  документа:</strong></td><td>${it.orderTypeName}</td></tr>
            <tr class="success"><td><strong>Номер документа:</strong></td><td>${it.documentNum}</td></tr>
            <tr class="success"><td><strong>Дата документа:</strong></td><td><fmt:formatDate value="${it.documentDate}" pattern="dd.MM.yyyy" /></td></tr>
            <tr class="success"><td><strong>Файл документа:</strong></td><td><a href="${it.fpath}" target="_blank">${it.filename} <i class="glyphicon glyphicon-save"></i></a></td></tr>
            <tr class="success"><td><strong>Создал:</strong></td><td>${it.fullname}</td></tr>
            <tr class="success"><td><strong>Дата создания:</strong></td><td><fmt:formatDate value="${it.orderDate}" pattern="dd.MM.yyyy" />
                <c:if test="${user.userRole == 'ROLE_KIP'}"></c:if>
                <sec:authorize access="hasAnyRole('ROLE_KIP')">
                   <a href="/edit/order/${it.orderId}" class="pull-right"><i class="glyphicon glyphicon-edit"></i></a>
                </sec:authorize>
                </td>
            </tr>
            </tbody>
        </table>
    </div>

    <div class="col-md-6">
        <h4 class="text-primary">Пункт ${it.itemNum}
            <small class="pull-right">
              <a href="/order/${it.orderId}/item/${it.orderItemId}">исполнители</a> &nbsp; &nbsp;
              <a href="/order/${it.orderId}/items">все поручения</a>
            </small>
        </h4>
        <table class="table table-condensed">
            <tbody>
            <tr class="success">
                <td colspan="2"><strong>Описание:</strong> ${it.itemDsc}</td>
            </tr>
            <tr class="success">
                <td style="width:160px"><strong>Срок:</strong></td>
                <td><fmt:formatDate value="${it.checkTime}" pattern="dd.MM.yyyy" />
                    <c:if test="${user.userRole == 'ROLE_KIP'}"></c:if>
                    <sec:authorize access="hasAnyRole('ROLE_KIP')">
                        <a href="/edit/item/${it.orderItemId}" class="pull-right"><i class="glyphicon glyphicon-edit"></i></a>
                    </sec:authorize>
                </td>
            </tr>
            <tr class="success">
                <td><strong>Контролирующий отдел:</strong></td><td>${it.contDeptName}</td>
            </tr>
            </tbody>
        </table>
    </div>

    <div class="col-md-12">
        <h4 class="text-primary">${dept.deptName}</h4>
        <table class="table table-condensed table-bordered table-striped">
            <thead>
            <tr>
                <th>Наименование файла</th>
                <th>Дата загрузки</th>
                <th>Пользователь</th>
                <th>Статус</th>
             </tr>
            </thead>
            <tbody>

            <c:choose>
                <c:when test="${user.deptId == it.controlDeptId}">
                    <c:forEach items="${files}" var="f" >
                    <tr class="${f.statusClass}">
                        <td><a href="/files${f.fpath}" target="_blank">${f.filename}</a></td>
                        <td>${f.fcreateDate}</td>
                        <td>${f.ufullName}</td>
                        <td>
                            ${f.statusName}
                            <div class="btn-group pull-right">
                                <button class="btn btn-default btn-sm dropdown-toggle" type="button" data-toggle="dropdown">
                                     <span class="caret"></span>
                                </button>
                                <ul role="menu" class="dropdown-menu">
                                    <!--<li class="bg-danger"><a href="/item/file/${f.responseFileId}/2">отклонить</a></li>-->
                                    <li class="bg-success"><a href="/item/file/${f.responseFileId}/3">принять</a></li>
                                    <li class="bg-danger"><a href="#" id="${f.responseFileId}">отклонить</a></li>
                                    <c:if test="${user.userRole == 'ROLE_KIP'}">
                                        <li class="bg-success"><a href="/edit/item/${it.orderItemId}/file/${f.responseFileId}">принять c нарушением срока</a></li>
                                        <li class="bg-warning"><a href="/edit/delete/file/${f.responseFileId}">удалить</a></li>
                                    </c:if>
                                </ul>
                            </div>
                        </td>
                    </tr>
                        <c:if test="${f.statusId == 2}">
                            <tr class="${f.statusClass}">
                                <td colspan="4"><strong>основание: </strong> ${f.statusReason}<br>
                                        ${f.umodfullName} ${f.modStatusDate}
                                </td>
                            </tr>
                        </c:if>
                    </c:forEach>
                </c:when>
                <c:when test="${user.userRole == 'ROLE_OKO' || user.userRole == 'ROLE_KIP'}">
                    <!--user.deptId == cdept.exDeptId ||  -->
                    <c:forEach items="${files}" var="f" >
                    <tr class="${f.statusClass}">
                        <td><a href="/files${f.fpath}" target="_blank">${f.filename}</a></td>
                        <td>${f.fcreateDate}</td>
                        <td>${f.ufullName}</td>
                        <td>${f.statusName}
                            <c:if test="${user.userRole == 'ROLE_KIP'}">
                            <div class="btn-group  pull-right">
                                <button class="btn btn-default btn-sm dropdown-toggle" type="button" data-toggle="dropdown">
                                    <span class="caret"></span>
                                </button>
                                <ul role="menu" class="dropdown-menu">
                                    <li class="bg-success"><a href="/edit/item/${it.orderItemId}/file/${f.responseFileId}">принять c нарушением срока</a></li>
                                    <li class="bg-warning"><a href="/edit/delete/file/${f.responseFileId}">удалить</a></li>
                                </ul>
                            </div>
                            </c:if>
                        </td>
                    </tr>
                        <c:if test="${f.statusId == 2}">
                            <tr class="${f.statusClass}">
                                <td colspan="4"><strong>основание: </strong> ${f.statusReason}<br>
                                        ${f.umodfullName} ${f.modStatusDate}
                                </td>
                            </tr>
                        </c:if>
                    </c:forEach>
                </c:when>
                <c:when test="${user.deptId == dept.deptId}">
                    <c:forEach items="${files}" var="f" >
                        <tr class="${f.statusClass}">
                            <td><a href="/files${f.fpath}" target="_blank">${f.filename}</a></td>
                            <td>${f.fcreateDate}</td>
                            <td>${f.ufullName}</td>
                            <td>${f.statusName}</td>
                        </tr>
                        <c:if test="${f.statusId == 2}">
                            <tr class="${f.statusClass}">
                                <td colspan="4"><strong>основание: </strong> ${f.statusReason}<br>
                                        ${f.umodfullName} ${f.modStatusDate}
                                </td>
                            </tr>
                        </c:if>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <c:forEach items="${files}" var="f" >
                        <tr class="${f.statusClass}">
                            <td>${f.filename}</td>
                            <td>${f.fcreateDate}</td>
                            <td>${f.ufullName}</td>
                            <td>${f.statusName}</td>
                        </tr>
                        <c:if test="${f.statusId == 2}">
                        <tr class="${f.statusClass}">
                            <td colspan="4"><strong>основание: </strong> ${f.statusReason}<br>
                              ${f.umodfullName} ${f.modStatusDate}
                            </td>
                        </tr>
                        </c:if>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
            </tbody>
        </table>

        <!-- DOWNLOAD REPORT FILE -->
        <c:choose>
            <c:when test="${user.deptId == dept.deptId}">
                <br>
                <form class="form-horizontal col-md-8" role="form" id="addfrm" method="post" action="/order/${it.orderId}/item/${it.orderItemId}/${itdept.itemDeptId}" enctype="multipart/form-data" accept-charset="utf-8">
                    <div class="form-group" id="orderfileGroup">
                        <label for="orderfile" class="col-sm-4 control-label">Загрузить отчет:</label>
                        <div class="col-sm-8">
                            <div class="input-group">
                                <input type="file" class="form-control" id="orderfile" name="orderfile">
                        <span class="input-group-btn">
                            <label class="btn btn-default" for="orderfile"><i class="glyphicon glyphicon-save"></i></label>
                            <button class="btn btn-success" type="submit">отправить</button>
                        </span>
                            </div>
                        </div>
                    </div>
                </form>
                <br>
                <p class="bg-warning">внимание размер загружаемых файлов не должен превышать 20 мб!</p>
            </c:when>
            <c:otherwise>
                <sec:authorize access="hasAnyRole('ROLE_OKO','ROLE_KIP')">
                    <br>
                    <form class="form-horizontal col-md-8" role="form" id="addfrm" method="post" action="/order/${it.orderId}/item/${it.orderItemId}/${itdept.itemDeptId}" enctype="multipart/form-data" accept-charset="utf-8">
                        <div class="form-group" id="orderfileGroup">
                            <label for="orderfile" class="col-sm-4 control-label">Загрузить отчет:</label>
                            <div class="col-sm-8">
                                <div class="input-group">
                                    <input type="file" class="form-control" id="orderfile" name="orderfile">
                        <span class="input-group-btn">
                            <label class="btn btn-default" for="orderfile"><i class="glyphicon glyphicon-save"></i></label>
                            <button class="btn btn-success" type="submit">отправить</button>
                        </span>
                                </div>
                            </div>
                        </div>
                    </form>
                    <br>
                    <p class="bg-warning">внимание размер загружаемых файлов не должен превышать 20 мб!</p>
                </sec:authorize>
            </c:otherwise>
        </c:choose>
        <!--
        <c:if test="${user.deptId == dept.deptId}">
        <br>
        <form class="form-horizontal col-md-8" role="form" id="addfrm" method="post" action="/order/${it.orderId}/item/${it.orderItemId}/${itdept.itemDeptId}" enctype="multipart/form-data" accept-charset="utf-8">
            <div class="form-group" id="orderfileGroup">
               <label for="orderfile" class="col-sm-4 control-label">Загрузить отчет:</label>
                <div class="col-sm-8">
                    <div class="input-group">
                        <input type="file" class="form-control" id="orderfile" name="orderfile">
                        <span class="input-group-btn">
                            <label class="btn btn-default" for="orderfile"><i class="glyphicon glyphicon-save"></i></label>
                            <button class="btn btn-success" type="submit">отправить</button>
                        </span>
                    </div>
                </div>
            </div>
        </form>
            <br>
            <p class="bg-warning">внимание размер загружаемых файлов не должен превышать 20 мб!</p>
        </c:if>
        -->
        <!-- END DOWNLOAD REPORT FILE -->

        <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-body" >
                        <form method="post" id="cancelFrm">
                            <div class="form-group" id="reasonBox">
                                <label class="control-label" for="reason">Основание отказа</label>
                                <textarea name="reason" id="reason" class="form-control" style="height: 100px"></textarea>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer" style="margin-top: 0">
                        <button type="button" class="btn btn-default" data-dismiss="modal">назад</button>
                        <button type="button" class="btn btn-primary" id="reasonOk">отправить</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    var reason = $('#reason'),
        reasonBox = $('#reasonBox'),
        cancelFrm = $('#cancelFrm');
    $(document).ready(function(){
        $('li.bg-danger > a').click(function(){
            var id = $(this).attr('id');
            cancelReason(id);
        });
        $('li.bg-warning > a').click(function(){
            return confirm('Удаление файла\n Продолжить?');
        });

        $('#myModal').on('shown.bs.modal', function () { reason.focus() });
        $('#reasonOk').on('click',saveReason);
    });

    function cancelReason(id){
        reasonBox.removeClass('has-error').removeClass('has-success');
        reason.val('');
        cancelFrm.attr('action','/item/file/'+id+'/2');
        $('#myModal').modal('show');
    }

    function saveReason(){
        reasonBox.removeClass('has-error').removeClass('has-success');
        if(reason.val() == ''){
            reasonBox.addClass('has-error');
        }else{
            //console.log(cancelFrm.attr('action'));
            cancelFrm.submit();
        }
    }
</script>