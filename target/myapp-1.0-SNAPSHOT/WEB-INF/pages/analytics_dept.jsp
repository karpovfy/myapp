<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ page session="true"%>
<div class="row well" style="border-radius:0">
    <h3>${d.deptName}</h3>
    <div class="row">
        <table class="table" id="rBody">
            <thead>
              <tr>
                  <th>Исполнено</th>
                  <th>Не исполнено</th>
                  <th>Активные</th>
                  <th>Всего поручений</th>
              </tr>
            </thead>
            <tbody>
            <c:forEach items="${depts}" var="d" >
                <tr>
                    <td>${d.allFinish}</td>
                    <td>${d.allFail}</td>
                    <td>${d.allActive}</td>
                    <td>${d.allItem}</td>
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

<script> $('#analytics').addClass('active'); </script>