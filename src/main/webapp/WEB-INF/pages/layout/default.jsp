<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title><tiles:getAsString name="title" /></title>

    <!-- Bootstrap -->
    <link href="../css/yeti-bootstrap.min.css" rel="stylesheet">
    <link href="../css/bootstrap-datepicker3.min.css" rel="stylesheet">
    <link href="../css/font-awesome.min.css" rel="stylesheet">
    <link href="../css/jquery.bootgrid.min.css" rel="stylesheet">
    <link href="../css/main.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="../js/html5shiv.min.js"></script>
    <script src="../js/respond.min.js"></script>
    <![endif]-->
    <!-- style="background:#1b1b1b" -->
    <style>
        .navbar > .container .navbar-brand, .navbar > .container-fluid .navbar-brand {margin-left: -14px}
        .navbar-default .navbar-nav > .active > a, .navbar-default .navbar-nav > .active > a:hover, .navbar-default .navbar-nav > .active > a:focus{background:#006687}
        .tf-pad {margin-bottom: 6px;}
        .tx-left {text-align: left}
    </style>
</head>
<body style="background:#1b1b1b">
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <button data-target="#bs-example-navbar-collapse-1" data-toggle="collapse" class="navbar-toggle collapsed" type="button">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a href="#" class="navbar-brand disabled">КИП ФССП</a>
        </div>
        <div id="bs-example-navbar-collapse-1" class="collapse navbar-collapse">

            <ul class="nav navbar-nav">
                <li id="inbox" class="active"><a href="/inbox"><i class="fa fa-hourglass"></i>&nbsp; КОНТРОЛЬ СРОКОВ</a></li>
                <li id="outbox"><a href="/outbox"><i class="fa fa-sign-out"></i>&nbsp; ИСХОДЯЩИЕ СРОКИ</a></li>
                <li id="outbox"><a href="/outbox"><i class="fa fa-align-justify"></i>&nbsp; ВСЕ СРОКИ</a></li>
                <li id="outbox"><a href="/outbox"><i class="fa fa-indent"></i>&nbsp; ВНЕШНИЕ СРОКИ</a></li>
                <li id="outbox"><a href="/outbox"><i class="fa fa-pie-chart"></i>&nbsp; АНАЛИТИКА</a></li>
                <li id="outbox"><a href="/outbox"><i class="fa fa-clipboard"></i>&nbsp; ДОКУМЕНТЫ</a></li>
                <li id="settings"><a href="/settings" class="btn-lg">&nbsp; <i class="fa fa-cogs"></i> &nbsp;</a></li>
            </ul>

            <ul class="nav navbar-nav navbar-right">



                <li style="text-align:center">
                    <a href="/logout" style="height: 39px;padding-bottom: 0;padding-top: 0;line-height:19px"><%= $user_name %><br>
                        <small><i class="glyphicon glyphicon-off"></i> выход</small>
                    </a>
                </li>
            </ul>


        </div>
    </div>
</nav>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="../js/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="../js/bootstrap.min.js"></script>
<script src="../js/bootstrap-datepicker.min.js"></script>
<script src="../js/jquery.bootgrid.min.js"></script>

<div class="container">
    <div class="row well" style="padding-left: 0; padding-right:0; margin-bottom: 0;">
        <tiles:insertAttribute name="content" />
    </div>
</div>

</body>
</html>
