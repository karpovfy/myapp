<%@ page contentType="text/html;charset=UTF-8" language="java" %><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%><%@ page session="true"%>
<div class="row well" style="border-radius:0">



    <div class="row">

        <form method="get" class="form-inline">
            <div class="form-group">
                <label for="exampleInputName2">Период с</label>
                <input type="text" placeholder="начало периода" value="${stDate}" id="dsDate" class="form-control input-sm" name="start_date" autocomplete="off">
            </div>
            <div class="form-group">
                <label for="exampleInputEmail2">по</label>
                <input type="text" placeholder="конец периода" value="${enDate}" id="deDate" class="form-control input-sm" name="end_date" autocomplete="off">
            </div>
            <div class="radio">
                <label>
                    <input type="radio" name="dept_type" id="optionsRadios1" value="1" <c:if test="${deptType == 1}">checked</c:if>>
                    Аппарат управления
                </label>
            </div>
            <div class="radio">
                <label>
                    <input type="radio" name="dept_type" id="optionsRadios2" value="2" <c:if test="${deptType == 2}">checked</c:if>>
                    Структурные подразделения
                </label>
            </div>
            <button class="btn btn-primary btn-sm" type="submit">применить</button>
        </form>


        <table class="table" id="rBody">
            <thead>
              <tr>
                  <th>Отдел</th>
                  <th>Сроки на контроле</th>
                  <th>Проверено</th>
                  <th>Не проверено</th>
                  <th>Отклонено</th>
              </tr>
            </thead>
            <tbody>
            <!--
            <c:forEach items="${depts}" var="d" >
                <tr>
                    <td><a href="/analytics/${d.deptId}">${d.deptName}</a></td>
                    <td>${d.allFinish}</td>
                    <td>${d.allFail}</td>
                    <td>${d.allActive}</td>
                    <td>${d.allItem}</td>
                </tr>
            </c:forEach>
            -->

            <c:forEach items="${cards}" var="c" >
                <tr>
                    <td>${c[1]}</td>
                    <td>${c[3]}</td>
                    <td>${c[4]}</td>
                    <td>${c[5]}</td>
                    <td>${c[6]}</td>
                </tr>
            </c:forEach>
            </tbody>
<!--
            <tfoot class="bg-success">
            <tr>
                <td>Всего</td>
                <td id="lbAll"></td>
                <td id="lbAct"></td>
                <td id="lbRep"></td>
                <td id="lbRep1"></td>
            </tr>
            </tfoot>
-->
        </table>
    </div>
</div>

<script>
    $('#analytics').addClass('active');
    $('#dsDate,#deDate').datepicker({dateFormat:'dd.mm.yy'});
</script>