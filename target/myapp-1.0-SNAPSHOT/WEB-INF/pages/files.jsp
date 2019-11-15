<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container">
    <div class="row">
        <div class="col-md-6">
            <div class="panel panel-success">
                <div class="panel-heading">
                    <h3 class="panel-title">  Файлы к поручениям</h3>
                </div>
                <ul class="list-group">
                    <c:forEach items="${files}" var="m" >
                        <a href="/files/${m.fpath}" class="list-group-item">${m.filename}</a>
                    </c:forEach>
                </ul>
            </div>
        </div>

        <div class="col-md-6">
            <div class="panel panel-info">
                <div class="panel-heading">
                    <h3 class="panel-title">Отчеты по поручениям</h3>
                </div>
                <ul class="list-group">
                    <c:forEach items="${reports}" var="m" >
                        <a href="/files/${m.fpath}" class="list-group-item">${m.filename}</a>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </div>
</div>
<script>
    $('#files').addClass('active');
</script>