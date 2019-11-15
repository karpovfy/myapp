<%@ page contentType="text/html;charset=UTF-8" language="java" %><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %><%@ page session="true"%>

<div class="row well" style="border-radius: 0;padding: 10px 0 0">
    <div class="col-md-6">
    <h4 class="text-primary">&nbsp;Документ</h4>
    <table class="table table-condensed">
        <tbody>
          <tr class="success">
              <td style="padding: 8px 20px" colspan="2">${it.orderName}</td>
          </tr>
          <tr class="success">
              <td style="width:160px"><strong>Тип документа:</strong></td><td>${it.orderTypeName}</td>
          </tr>
          <tr class="success">
              <td><strong>Номер документа:</strong></td><td>${it.documentNum}</td>
          </tr>
          <tr class="success">
              <td><strong>Дата документа</strong></td>
              <td><fmt:formatDate value="${it.documentDate}" pattern="dd.MM.yyyy" /></td>
          </tr>
          <tr class="success">
              <td><strong>Файл поручения:</strong></td><td><a href="${it.fpath}" target="_blank">${it.filename} <i class="glyphicon glyphicon-save"></i></a></td>
          </tr>
          <tr class="success">
              <td><strong>Создал:</strong></td><td>${it.fullname}</td>
          </tr>
          <tr class="success">
              <td><strong>Дата создания:</strong></td>
              <td><fmt:formatDate value="${it.orderDate}" pattern="dd.MM.yyyy" />
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
        <h4 class="text-primary">Пункт ${it.itemNum} <small class="pull-right"><a href="/order/${it.orderId}/items">все пункты</a></small></h4>
        <table class="table table-condensed">
            <tbody>
            <tr class="success">
                <td colspan="2"><strong>Описание:</strong> ${it.itemDsc}</td>
            </tr>
            <tr class="success">
                <td style="width:160px"><strong>Срок:</strong></td>
                <td>
                    <fmt:formatDate value="${it.checkTime}" pattern="dd.MM.yyyy" />

                    <c:if test="${user.userRole == 'ROLE_KIP'}">                    </c:if>
                    <sec:authorize access="hasAnyRole('ROLE_KIP')">
                        <a href="/edit/item/${it.orderItemId}" class="pull-right"><i class="glyphicon glyphicon-edit"></i></a>
                        <a href="/control/off/${it.orderItemId}" class="pull-right"><i class="glyphicon glyphicon-ok"></i></a>
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
        <table class="table table-condensed table-bordered table-striped">
            <thead>
            <tr>
                <th>Исполнители</th>
                <th>Просмотрено</th>
                <th>Отчет</th>
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
                <td>
                    <c:if test="${d.viewed == 1 }">
                        <i class="glyphicon glyphicon-ok"></i>
                    </c:if>
                </td>
                <td><i class="glyphicon glyphicon-file"></i> <fmt:formatDate value="${d.reportDate}" pattern="dd.MM.yyyy" /></td>
               </tr>
            </c:if>

            <c:if test="${d.status != 1}">
            <tr>
                <td><a href="/order/${d.orderId}/item/${d.orderItemId}/${d.itemDeptId}"> ${d.exdeptName} </a></td>
                <td>
                    <c:if test="${d.viewed == 1 }">
                    <i class="glyphicon glyphicon-ok"></i>
                    </c:if>
                </td>
                <td>
                    <c:if test="${d.fileStatus != 0 }">
                        <i class="glyphicon glyphicon-file"></i> <fmt:formatDate value="${d.reportDate}" pattern="dd.MM.yyyy" />
                    </c:if>
                    <c:if test="${d.fileStatus == 0 }">
                        <i class="glyphicon glyphicon-minus"></i>
                    </c:if>
                </td>
            </tr>
            </c:if>
            </c:forEach>
        </table>
    </div>
</div>

