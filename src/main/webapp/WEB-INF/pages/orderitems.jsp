<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="true"%>

<style>
    #orderItemsTable thead th{font-size: 12px}
</style>

<div class="row well" style="border-radius: 0;padding: 10px 0 0">
    <div class="col-md-5">
    <h4 class="text-primary">&nbsp;Документ</h4>
    <table class="table table-condensed">
        <tbody>
          <tr class="success">
              <td style="padding: 8px 20px" colspan="2">${order.orderName}</td>
          </tr>
          <tr class="success">
              <td ><strong>Тип документа:</strong></td><td>${order.orderTypeName}</td>
          </tr>
          <tr class="success">
              <td><strong>Номер документа:</strong></td><td>${order.documentNum}</td>
          </tr>
          <tr class="success">
              <td><strong>Дата документа:</strong></td><td>${order.formattedDate}</td>
          </tr>
          <tr class="success">
              <td><strong>Файл поручения:</strong></td><td><a href="${order.fpath}" target="_blank">${order.filename} <i class="glyphicon glyphicon-save"></i></a></td>
          </tr>
          <tr class="success">
              <td><strong>Создал:</strong></td><td>${order.fullname}</td>
          </tr>
          <tr class="success">
              <td><strong>Дата создания:</strong></td>
              <td>${order.formattedOrderDate}

                  <sec:authorize access="hasAnyRole('ROLE_KIP')">
                    <a href="/edit/order/${order.orderId}" class="pull-right"><i class="glyphicon glyphicon-edit"></i></a>
                  </sec:authorize>

              </td>
          </tr>
        </tbody>
    </table>
    </div>

    <div class="col-md-7">
        <h4 class="text-primary">Поручения (${fn:length(items)})</h4>
        <table class="table table-condensed table-bordered table-striped" id="orderItemsTable">
            <thead>
              <tr>
                  <th>№</th>
                  <th style="width: 70px">№ пункта</th>
                  <th>Содержание пункта</th>
              </tr>
            </thead>
            <tbody>
            <c:forEach items="${items}" var="i" varStatus="d">
              <tr>
                <td>${d.index + 1}</td>
                <td style="text-align: center"><a href="/order/${order.orderId}/item/${i.orderItemId}">${i.itemNum}</a><br>
                    <span class="text-muted">срок</span><br>
                    <strong class="text-success"><fmt:formatDate value="${i.checkTime}" pattern="dd.MM.yyyy" /></strong>
                </td>
                <td><a href="/order/${order.orderId}/item/${i.orderItemId}"> ${i.itemDsc}</a></td>
              </tr>
            </c:forEach>
        </table>
        <c:if test="${user.deptId == order.creatorDeptId || user.userRole == 'ROLE_OKO'}"><a href="/order/${order.orderId}/additem" class="btn btn-danger">добавить поручение <i class="glyphicon glyphicon-plus"></i></a></c:if>
    </div>
</div>

