<%@ page contentType="text/html;charset=UTF-8" language="java" %><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%><%@ page session="true"%>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><tiles:getAsString name="title" /></title>
    <link type="image/x-icon" rel="icon" href="data:image/png;base64,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" id="page_favicon">
    <link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css" />" >
    <link rel="stylesheet" href="<c:url value="/css/jquery-ui.min.css" />" >
    <link rel="stylesheet" href="<c:url value="/css/ui.jqgrid.css" />" >
    <script type="text/javascript" src="<c:url value="/js/jquery.min.js" />"></script>
    <script type="text/javascript" src="<c:url value="/js/jquery-ui.min.js" />"></script>
    <script type="text/javascript" src="<c:url value="/js/jquery.jqGrid.min.js" />"></script>
    <script type="text/javascript" src="<c:url value="/js/bootstrap.min.js" />"></script>
    <script type="text/javascript" src="<c:url value="/js/jquery.datepicker.ru.js" />"></script>
    <!--<script type="text/javascript" src="<c:url value="/js/dict.js" />"></script>-->
    <script type="text/javascript" src="<c:url value="/app/dict"/>"></script>
    <style>
        .navbar > .container .navbar-brand, .navbar > .container-fluid .navbar-brand {margin-left: -14px}
        .navbar-default .navbar-nav > .active > a, .navbar-default .navbar-nav > .active > a:hover, .navbar-default .navbar-nav > .active > a:focus{background:#006687}
        .navbar-inverse .navbar-nav > .active > a, .navbar-inverse .navbar-nav > .active > a:hover, .navbar-inverse .navbar-nav > .active > a:focus{background:#006687}
        .tf-pad {margin-bottom: 6px;}
        .tx-left {text-align: left; border-radius: 0}
        .navbar-nav > li > a {
            padding-bottom: 5px;
            padding-top: 5px;
        }
        .navbar {
            border: 1px solid transparent;
            margin-bottom: 20px;
            min-height: 31px;
            position: relative;
        }
        .navbar-brand {
            float: left;
            font-size: 18px;
            height: 25px;
            line-height: 20px;
            padding: 5px 8px;
        }

    </style>
</head>
<body>
<nav class="navbar navbar-inverse" role="navigation" style="border-radius: 0px; margin-bottom: 0px; border-bottom-width: 0px;">
    <div class="navbar-header">
        <button data-target="#bs-example-navbar-collapse-5" data-toggle="collapse" class="navbar-toggle" type="button">
            <span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span>
        </button>
        <a href="/inbox" class="navbar-brand disabled">КИП ФССП</a>
    </div>
    <div class="collapse navbar-collapse">
        <ul class="nav navbar-nav">
            <li id="inbox"><a href="/inbox"><i class="glyphicon glyphicon-time"></i>&nbsp; Контроль сроков</a></li>
            <li id="outbox"><a href="/outbox"><i class="glyphicon glyphicon-log-in"></i>&nbsp; Исх. поручения</a></li>
            <li id="allbox"><a href="/allbox"><i class="glyphicon glyphicon-list-alt"></i>&nbsp; Все поручения</a></li>

            <sec:authorize access="hasAnyRole('ROLE_OKO','ROLE_KIP')">
                <li id="resolutions"><a href="/resolutions"><i class="glyphicon glyphicon-briefcase"></i>&nbsp; Внешние сроки</a></li>
                <li id="analytics"><a href="/analytics"><i class="glyphicon glyphicon-stats"></i>&nbsp; Аналитика</a></li>
                <li id="cabinet"><a href="/cabinet"><i class="glyphicon glyphicon-duplicate"></i>&nbsp; Документы</a></li>
            </sec:authorize>

            <sec:authorize access="hasAnyRole('ROLE_KIP')">
                <li id="settings"><a href="/settings" class="btn-lg">&nbsp; <i class="glyphicon glyphicon-cog"></i> &nbsp;</a></li>
            </sec:authorize>
<!--
            <c:if test="${user.userRole == 'ROLE_OKO' || user.userRole == 'ROLE_KIP'}">
                <li id="resolutions"><a href="/resolutions"><i class="glyphicon glyphicon-stats"></i>&nbsp; Внешние сроки</a></li>
                <li id="analytics"><a href="/analytics"><i class="glyphicon glyphicon-stats"></i>&nbsp; Аналитика</a></li>
                <li id="cabinet"><a href="/cabinet"><i class="glyphicon glyphicon-list"></i>&nbsp; Документы</a></li>
                <li id="settings"><a href="/settings" class="btn-lg">&nbsp; <i class="glyphicon glyphicon-cog"></i> &nbsp;</a></li>
            </c:if>
            -->
            <!--<li id="about"><a href="/about"><i class="glyphicon glyphicon-file"></i>&nbsp; О системе</a></li>-->
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <!--<li style="text-align: center">
                <a style="padding-top:5px;padding-bottom:0" href="#"><small><i class="glyphicon glyphicon-user"></i>&nbsp; ${user.fullname}</small></a>
            </li>-->
            <li>
                <a href="#"><small>${user.fullname}</small></a>
            </li>
            <li>
                <a href="/j_spring_security_logout"><small><i class="glyphicon glyphicon-off"></i> выход &nbsp; </small></a>
            </li>
        </ul>
    </div><!-- /.navbar-collapse -->
    </nav>
    <div class="container">
      <tiles:insertAttribute name="content" />
    </div>
</body>
</html>