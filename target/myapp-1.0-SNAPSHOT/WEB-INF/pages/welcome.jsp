<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page session="true"%>

    <div class="container" style="margin-top: 120px">

        <div class="row" style="display: none">
            <div class="col-md-4 col-md-offset-4">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">Вход в систему0</h3>
                    </div>
                    <div class="panel-body">
                        <form accept-charset="UTF-8" role="form" action="/j_spring_security_check" method="post">
                            <fieldset>
                                <div class="form-group">
                                    <div style="margin-bottom: 25px" class="input-group">
                                        <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                        <input id="login-username" type="text" class="form-control" name="j_username" value="" placeholder="имя пользователя">
                                    </div>
                                    <!--<input class="form-control" placeholder="имя пользователя" name="j_username" type="text">-->
                                </div>
                                <div class="form-group">
                                    <div style="margin-bottom: 25px" class="input-group">
                                        <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                                        <input id="login-password" type="password" class="form-control" name="j_password" placeholder="пароль">
                                    </div>
                                    <!--<input class="form-control" placeholder="пароль" name="j_password" type="password" value="">-->
                                </div>
                                <!--
                                <div class="checkbox">
                                    <label>
                                        <input name="remember" type="checkbox" value="Remember Me"> Запомнить меня
                                    </label>
                                </div>
                                -->
                                <input class="btn btn-lg btn-success btn-block" type="submit" value="Войти">
                            </fieldset>
                        </form>
                        <c:if test="${rp == 'NO'}"> <p class="bg-danger"> неправильный логин или пароль </p> </c:if>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <div class="panel panel-default" style="border-radius: 0">
                    <div class="panel-heading">
                        <h3 class="panel-title" style="text-align: center">УФССП России<br>по Республике Бурят-ия</h3>
                    </div>
                    <div class="panel-body">
                        <img src="/img/m-logo.png" class="img-rounded" style="margin-left: 28%">
                        <form method="post" action="/j_spring_security_check"  accept-charset="UTF-8">
                            <fieldset>
                                <div class="form-group">
                                    <div class="input-group" style="margin-bottom: 25px">
                                        <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                        <input type="text" placeholder="имя пользователя" name="j_username" class="form-control" id="login-username">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="input-group" style="margin-bottom: 25px">
                                        <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                                        <input type="password" placeholder="пароль" name="j_password" class="form-control" id="login-password">
                                    </div>
                                </div>
                                <input type="submit" value="Войти" class="btn btn-lg btn-success btn-block"  style="border-radius: 0">
                            </fieldset>
                        </form>
                    </div>
                </div>
                <c:if test="${status == 1}">
                    <div id="fail" class="alert alert-danger">
                        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                        <strong>Внимание!</strong> неправильное имя пользователя или пароль
                    </div>
                </c:if>
            </div>
        </div>
    </div>

<script>
    $(document).ready(function(){
        var  userLg = $('#login-username'), userPw = $('login-password');
        if(userLg.val() == ''){
            userLg.focus()
        }
    });
</script>