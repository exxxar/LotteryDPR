<%-- 
    Document   : index1
    Created on : 03.11.2015, 16:45:41
    Author     : Татьяна Юрченко
--%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="ru">
    <head>
        <meta charset="utf-8">

        <link href="<c:url value="/resources/css/reset.css"/>" rel="stylesheet">
        <link href="<c:url value="/resources/css/userRoomStyle.css"/>" rel="stylesheet">
        <link href='https://fonts.googleapis.com/css?family=PT+Sans+Narrow&subset=latin,cyrillic' rel='stylesheet' type='text/css'>
        <script src="<c:url value="/resources/js/jquery-2.1.4.min.js"/>"></script>
        <script src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
        <title>User Room</title>
    </head>
    <script>
        $(document).ready(function() {
            $('.dilers-active').click(function() {
                $('.dilers').css({"display": "block"});
                $('.tikets').css({"display": "none"});
                $('.left-menu').css({"left": "30px"});
                $('.dilers-active').addClass('btn-3-active');
                $('.tikets-active').removeClass('btn-3-active');
            });

            $('.tikets-active').click(function() {
                $('.tikets').css({"display": "block"});
                $('.dilers').css({"display": "none"});
                $('.left-menu').css({"left": "145px"});
                $('.tikets-active').addClass('btn-3-active');
                $('.dilers-active').removeClass('btn-3-active');
            });

            $(".select-all").click(function() {
                $("input[type='checkbox']").each(function(a1, a2) {
                    // alert(a1+" "+a2);

                    $(this).prop({"checked": "true"});
                    //$(a2).attr({"checked":"true"});
                });
            });

            $('.print-all').click(function() {
                var myWin = open("./tiket", "displayWindow",
                        "width=450,height=250,status=no,toolbar=no,menubar=no");
                setTimeout(function() {
                    myWin.print();
                }, 2000);

            });
        });

    </script>
    <body>
        <sec:authentication var="user" property="principal" />
            <sec:authorize access="hasRole('ROLE_USER')">
                <div class="online-block">
                    <input type="hidden" id="isAutorized" value="true"/>
                    <p>Добрый день, <i>${user.username}!</i><br/></p>
                    <a href="<c:url value="/"/>">Перейти на главную</a><br/>
                    <a href="<c:url value="/j_spring_security_logout"/>">Выход</a>                    
                </div>
            </sec:authorize>
                
        <div class="general-background">
            <div class="wrapper">
                <div class="room-top-title">
                    <h1>
                        ЛИЧНЫЙ КАБИНЕТ
                    </h1>
                </div>               

                <div class="left-menu">
                    <div class="btn-3 tikets-active">
                        <p>БИЛЕТЫ</p>
                    </div>

                    <div class="btn-3 dilers-active btn-3-active">
                        <p>ДЛЯ ДИЛЕРА</p>
                    </div>
                </div>

                <div class="tikets">
                    <div class="clear-background">
                        <div class="all-actions-tikets">
                            <div class="button-action select-all">
                                <img class="select" src="<c:url value="/resources/img/select-all.png"/>"/>
                                <h1 class="select-h">
                                    ВЫДЕЛИТЬ ВСЕ
                                </h1>
                            </div>
                            <div class="button-action print-all">
                                <img class="print" src="<c:url value="/resources/img/print.png"/>"/>
                                <h1 class="print-h">
                                    ПЕЧАТАТЬ
                                </h1>
                            </div>
                            <div class="button-action">
                                <img class="send" src="<c:url value="/resources/img/Send.png"/>"/>
                                <h1 class="send-h">
                                    ОТПРАВИТЬ
                                </h1>
                            </div>
                            <div class="button-action">
                                <img class="open" src="<c:url value="/resources/img/open.png"/>"/>
                                <h1 class="open-h">
                                    ОТКРЫТЬ
                                </h1>
                            </div>
                            <div class="button-action">
                                <img class="long" src="<c:url value="/resources/img/long.png"/>"/>
                                <h1 class="long-h">
                                    ПРОДЛИТЬ
                                </h1>
                            </div>
                            <div class="button-action">
                                <img class="delete" src="<c:url value="/resources/img/delete.png"/>"/>
                                <h1>
                                    УДАЛИТЬ
                                </h1>
                            </div>
                        </div>

                        <div class="tikets-background">
                            <div class="second-title">
                                <h1>
                                    ВАШИ БИЛЕТЫ
                                </h1>
                            </div>

                            <div class="visible-block">				
                                <ul>														
                                    <li>
                                        <div class="checkbox">
                                            <input id="check1" type="checkbox" name="check" value="check1">
                                            <label for="check1">
                                                <img src="<c:url value="/resources/img/Tiket.jpg"/>"/>
                                            </label>

                                        </div>
                                    </li>
                                    <li>
                                        <div class="checkbox">
                                            <input id="check2" type="checkbox" name="check" value="check1">
                                            <label for="check2">
                                                <img src="<c:url value="/resources/img/Tiket.jpg"/>"/>
                                            </label>

                                        </div>
                                    </li>
                                    <li>
                                        <div class="checkbox">
                                            <input id="check3" type="checkbox" name="check" value="check1">
                                            <label for="check3">
                                                <img src="<c:url value="/resources/img/Tiket.jpg"/>"/>
                                            </label>	
                                        </div>

                                    </li>
                                </ul>
                            </div>

                            <div class="page-scroll">
                                <div class="page-number">
                                    <h1>
                                        <<
                                    </h1>
                                </div>
                                <div class="page-number">
                                    <h1>
                                        1
                                    </h1>
                                </div>
                                <div class="page-number">
                                    <h1>
                                        2
                                    </h1>
                                </div>
                                <div class="page-number">
                                    <h1>
                                        3
                                    </h1>
                                </div>
                                <div class="page-number">
                                    <h1 class="points">
                                        . . .
                                    </h1>
                                </div>
                                <div class="page-number">
                                    <h1>
                                        >>
                                    </h1>
                                </div>						
                            </div>

                        </div>
                    </div>


                </div>

                <div class="dilers">
                    <div class="clear-background">
                        <div class="bg">
                            <img src="" alt="*">
                            <h1>Информация о пользователя</h1>
                            <div class="lotery">
                                <p>БАЛАНС:</p><p class="balance">2000р</p><br/>
                                <p>ДИСКОНТ:</p><b class="discont">30%</b><p class="user-type">ДИЛЛЕР</p><br/>
                                <p>КОЛИЧЕСТВО БИЛЛЕТОВ:</p><p class="ticket-count">1000шт</p><br/>
                                <p>ВСЕГО БИЛЛЕТОВ:</p><p class="ticket-count">1560шт</p><br/>
                                <a href="" class="btn">ЛОТЕРЕЯ</a>
                            </div>

                            <div class="pouch">
                                <p>ФИО:</p><p class="fio">2000р</p><br/>
                                <p>ВОЗРАСТ:</p><p class="age">30 ЛЕТ</p><br/>
                                <p>ТЕЛЕФОН:</p><p class="tel">8-800-500-0-500</p><br/>
                                <p>E-MAIL:</p><p class="email">IVANOV@GMAIL.COM</p><br/>
                                <a href="" class="btn">КОШЕЛЬКИ</a>
                            </div>

                            <div class="btn-group">
                                <a href="" class="btn-2"><img src="<c:url value="/resources/img/ic-1.png"/>"></a>
                                <a href="" class="btn-2"><img src="<c:url value="/resources/img/ic-2.png"/>"></a>
                                <a href="" class="btn-2"><img src="<c:url value="/resources/img/ic-3.png"/>"></a>
                                <a href="" class="btn-2"><img src="<c:url value="/resources/img/ic-4.png"/>"></a>
                                <a href="" class="btn-2"><img src="<c:url value="/resources/img/ic-5.png"/>"></a>
                                <a href="" class="btn-2"><img src="<c:url value="/resources/img/ic-6.png"/>"></a>
                            </div>

                            <canvas class="diagram">

                            </canvas>
                        </div>				
                    </div>
                </div>
            </div>
        </div>

    </body>



</html>
