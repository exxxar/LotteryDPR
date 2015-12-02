<%-- 
    Document   : index
    Created on : 03.11.2015, 16:34:24
    Author     : Татьяна Юрченко
--%>
<%@ page import="net.tanesha.recaptcha.ReCaptcha" %>
<%@ page import="net.tanesha.recaptcha.ReCaptchaFactory" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="ru">
    <html>
        <head>
            <meta charset="utf-8">

            <link href="<c:url value="/resources/css/reset.css"/>" rel="stylesheet">
            <link href="<c:url value="/resources/css/mainPageStyle.css"/>" rel="stylesheet">
            <link href='https://fonts.googleapis.com/css?family=PT+Sans+Narrow&subset=latin,cyrillic' rel='stylesheet' type='text/css'>
            <script src="<c:url value="/resources/js/jquery-2.1.4.min.js"/>"></script>
            <script src="https://code.jquery.com/jquery-1.11.3.min.js"></script>

            <script>
                $(document).ready(function() {
                    if (window.location.href.indexOf("login") != -1 && $("#isAutorized").val() != "true") {
                        $(".win-2").css({"display": "block"});
                    }
                    else
                    if (window.location.href.indexOf("#signup") != -1) {
                        $(".win-1").css({"display": "block"});
                    }

                    $('.black').css({"transform": "translateX(0px)", "transition": "1s", "transition-delay": "1s", "opacity": "1"});
                    $('.red').css({"transform": "translateX(0px)", "transition": "1s", "transition-delay": "1.5s", "opacity": "1"});
                    $('.blue').css({"transform": "translateY(0px)", "transition": "1s", "transition-delay": "2s", "opacity": "1"});
                    $('.eagle').css({"opacity": "1", "transition": "1s", "transition-delay": "2.5s"});
                    $('.top-title').css({"transform": "translateY(0px)", "transition": "1s", "transition-delay": ".5s"});
                    $('.eagle-right').css({"transform": "rotateZ(0deg)", "transition": "2s", "transition-delay": "2.7s"});
                    $('.eagle-left').css({"transform": "rotateZ(0deg)", "transition": "2s", "transition-delay": "2.7s"});

                    $(".left-item").click(function() {
                        $(".win-1").css({"display": "block"});
                    });

                    $(".right-item").click(function() {
                        $(".win-2").css({"display": "block"});
                    });


                    $(".exit-modal").click(function() {
                        $(".shadow-box").css({"display": "none"});
                    });

                    $(".takeWin-btn").click(function() {
                        $(".win-3").css({"display": "block"});
                    });

                    $(".contact-btn").click(function() {
                        $(".win-5").css({"display": "block"});
                    });

                    $(".rules-btn").click(function() {
                        $(".win-4").css({"display": "block"});
                    });

                    $(".rules-btn-from-reg").click(function() {
                        $(".win-1").css({"display": "none"});
                        $(".win-4").css({"display": "block"});
                    });


                    $(".remark-w2").click(function() {
                        $(".w3").val("Напомнить");
                        $("#remembermymail").val("");
                        $(".win-2").css({"display": "none"});
                        $(".win-6").css({"display": "block"});
                    });

                    $(".w3").click(function() {
                        if ($("#remembermymail").val().length < 5 && $("#remembermymail").val().indexOf("@") == -1) {
                            $(".w3").val("Ошибка ввода email");
                            return false;
                        }
                        $.post("./public/remeberMyPassword",
                                {
                                    mail: $("#remembermymail").val(),
                                    _csrf: $("#_csrf").val()
                                },
                        function(data, status) {
                           
                            if (data == "ok") {
                                $(".w3").val("Запрос отправлен");
                            }
                            else
                                $(".w3").val("Ошибка отправки");

                            setTimeout(function() {
                                $(".win-6").css({"display": "none"});
                                $(".win-2").css({"display": "block"});
                            }, 3000);
                        });
                        $(".w3").val("Запрос в обработке");

                    });
                });


            </script>
            <title>National Lottery DPR</title>
        </head>

        <body>
            <sec:authentication var="user" property="principal" />
            <sec:authorize access="hasRole('ROLE_USER')">
                <div class="online-block">
                    <input type="hidden" id="isAutorized" value="true"/>
                    <p>Добрый день, <i>${user.username}!</i><br/></p>
                    <a href="<c:url value="/userroom"/>">Перейти в личный кабине</a><br/>
                    <a href="<c:url value="/j_spring_security_logout"/>">Выход</a><br/> 
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                         <a href="<c:url value="/admin/stock"/>">Административаня панель</a>
                    </sec:authorize>
                </div>
            </sec:authorize>

            <div id="background-theme">
                <div class="wrapper">
                    <div class="top-title">
                        <h1>НАЦИОНАЛЬНАЯ ЛОТЕРЕЯ ДНР</h1>
                    </div>
                    <div class="tricolor">
                        <div class="black"></div>
                        <div class="red"></div>
                        <div class="blue"></div>	
                        <div class="eagle">
                            <div class="eagle-body"></div>
                            <div class="eagle-left"></div>
                            <div class="eagle-right"></div>
                        </div>
                    </div>			
                </div>


                <nav class="bottom-menu">
                    <ul>
                        <li class="lotery-btn"><a href="#lotery">ЛОТЕРЕЯ</a></li>
                        <li class="rules-btn"><a href="#rules">ПРАВИЛА</a></li>
                        <li class="takeWin-btn"><a href="#takeWin">ПОЛУЧИТЬ ВЫИГРЫШ</a></li>
                        <li class="contact-btn"><a href="#contact">КОНТАКТЫ</a></li>
                    </ul>
                </nav>	

            </div>
            <sec:authorize access="!hasRole('ROLE_USER')">    
                <div class="left-item">
                    <img alt="*" src="<c:url value="/resources/img/key_d.png"/>"/>
                    <p>РЕГИСТРАЦИЯ</p>
                </div>

                <div class="right-item">
                    <img alt="*" src="<c:url value="/resources/img/System-Login-icon.png"/>"/>
                    <p>АВТОРИЗАЦИЯ</p>
                </div>
            </sec:authorize> 

            <div class="shadow-box win-1"  style="display:none" >
                <div class="window-1">
                    <img class="exit-modal" src="<c:url value="/resources/img/close.png"/>"/>
                    <div class="window-top-title">
                        <h1>
                            РЕГИСТРАЦИЯ
                        </h1>
                    </div>
                    <c:url value="/public/signup_confirm" var="regUrl"/>

                    <form:form modelAttribute="user" method="POST" action="${regUrl}" class="all-fields">    
                        <div class="field w1-login">
                            <h1>
                                ЛОГИН<b>*</b><br/>
                                <p>(не менее 6 символов)</p>
                            </h1>
                            <form:input type="text" placeholder="Введите логин" path="username" value="" 
                                        name="login" class="paint-field" required="true" pattern="^[a-zA-Z0-9]{6,}"/>    
                        </div>
                        <div class="field w1-email">
                            <h1>
                                АДРЕС ЭЛЕКТРОННОЙ ПОЧТЫ<b>*</b><br/>
                                <p>(example@example.com)</p>
                            </h1>
                            <form:input type="email" placeholder="Введите e-mail" path="email" value=""
                                        name="email" class="paint-field" required="true"/>
                        </div>
                        <div class="field w1-surname">
                            <h1>
                                ФАМИЛИЯ<b>*</b><br/>
                                <p>(только на русском)</p>

                            </h1>
                            <form:input type="text" placeholder="Введите фамилию" path="sname" value=""
                                        name="surname" class="paint-field" required="true" pattern="^[А-Яа-яЁё\s]+$"/>
                        </div>
                        <div class="field w1-name">
                            <h1>
                                ИМЯ<b>*</b><br/>
                                <p>(только на русском)</p>
                            </h1>
                            <form:input type="text" placeholder="Введите имя" value=""
                                        path="fname" name="name" class="paint-field" required="true" pattern="^[А-Яа-яЁё\s]+$"/>
                        </div>
                        <div class="field w1-third-name">
                            <h1>
                                ОТЧЕСТВО<br/>
                                <p>(только на русском)</p>
                            </h1>
                            <form:input type="text" placeholder="Введите отчество" path="tname" value=""
                                        name="thirdname" class="paint-field" pattern="^[А-Яа-яЁё\s]+$"/>
                        </div>
                        <div class="field w1-password">
                            <h1>
                                ПАРОЛЬ<b>*</b><br/>
                                <p>(не менее 6 символов)</p>
                            </h1>
                            <form:input type="password" placeholder="Введите пароль" value=""
                                        path="password" name="password" class="paint-field" required="true" pattern="[A-Za-z0-9]{6,}"/>
                        </div>
                        <div class="field w1-password-true">
                            <h1>
                                ПОДТВЕРЖДЕНИЕ ПАРОЛЯ<b>*</b><br/>
                                <p>(не менее 6 символов)</p>
                            </h1>
                            <form:input type="password" placeholder="Повторите пароль" value=""
                                        path="confirmPassword" name="passwordtrue" class="paint-field" required="true" pattern="[A-Za-z0-9]{6,}"/>
                        </div>
                        <div class="capcha">
                            <%
                                ReCaptcha c = ReCaptchaFactory.newReCaptcha("6LePpQoTAAAAALfpVXPBsMAb_WB3LaW3lLP9MPAV", "6LePpQoTAAAAAPuvxMIXlWYbWScYOGXSC9dJR20M", false);
                                out.print(c.createRecaptchaHtml(null, null));
                            %>
                        </div>          
                        <a href="#" class="rules rules-btn-from-reg">Правила использования</a>
                        <div class="remark">
                            <h1>
                                ПОЛЯ, ОТМЕЧЕННЫЕ ЗВЕЗДОЧКОЙ<b>*</b> - ОБЯЗАТЕЛЬНЫ ДЛЯ ЗАПОЛНЕНИЯ
                            </h1>
                        </div>

                        <input class="button-1 w2" type="submit" value="ВОЙТИ"/>
                        <input id="token" type="hidden" value="${sessionScope.csrfToken}" />
                    </form:form>

                </div>
            </div>



            <div class="shadow-box win-2" style="display:none"  >
                <div class="window-2">
                    <img class="exit-modal" src="<c:url value="/resources/img/close.png"/>" />
                    <div class="window-top-title">
                        <h1>
                            ВХОД
                        </h1>
                    </div>

                    <form class="all-fields-2" action="<c:url value="/login"/>" method="post">
                        <div class="field w2-login-mail">
                            <h1>
                                ЛОГИН/ЭЛЕКТРОННАЯ ПОЧТА
                            </h1>
                            <input type="text" placeholder="Введите логин или почту" value="" name="username" class="paint-field">
                        </div>
                        <div class="field w2-password">
                            <h1>
                                ПАРОЛЬ
                            </h1>
                            <input type="password" placeholder="Введите пароль" value="" name="password" class="paint-field">
                        </div>

                        <div class="check">
                            <input type="checkbox" class="checkbox" id="rememberMe"/>
                            <label for="rememberMe"><h1> ЗАПОМНИТЬ МЕНЯ</h1></label>                               
                        </div>

                        <a href="#rememberMe" class="remark-w2">
                            <h1>
                                ЗАБЫЛИ ПАРОЛЬ?
                            </h1>
                        </a>
                        <input class="button-1 w2" type="submit" value="ВОЙТИ"/>
                        <input type="hidden" id="_csrf_entrance" name="_csrf" value="${_csrf.token}"/>
                        <input id="token" type="hidden" value="${sessionScope.csrfToken}" />

                    </form>

                    <div class="after-form">
                        <hr/>
                        <h1>
                            ВХОД ЧЕРЕЗ СОЦИАЛЬНЫЕ СЕТИ
                        </h1>
                        <div class="all-img-w2">
                            <a href="<c:url value="/public/vk_connection_url"/>"><img src="<c:url value="/resources/img/vk.jpg"/>"/></a>
                            <a href="<c:url value="/public/tw_connection_url"/>"><img src="<c:url value="/resources/img/twi.jpg"/>"/></a>
                            <a href="<c:url value="/public/fb_connection_url"/>"><img src="<c:url value="/resources/img/fb.jpg"/>"/></a>
                            <a href="<c:url value="/public/ok_connection_url"/>"><img src="<c:url value="/resources/img/ok.jpg"/>"/></a>
                        </div>
                    </div>

                </div>
            </div>

            <div class="shadow-box win-3" style="display:none"  >
                <div class="window">
                    <img class="exit-modal" src="<c:url value="/resources/img/close.png"/>"/>
                    <h1>ПОЛУЧЕНИЕ ВЫИГРЫША</h1>
                    <p>

                        Lorem Ipsum - это текст-"рыба", часто используемый в печати и вэб-дизайне. Lorem Ipsum является стандартной "рыбой" для текстов на латинице с начала XVI века. В то время некий безымянный печатник создал большую коллекцию размеров и форм шрифтов, используя Lorem Ipsum для распечатки образцов. Lorem Ipsum не только успешно пережил без заметных изменений пять веков, но и перешагнул в электронный дизайн. Его популяризации в новое время послужили публикация листов Letraset с образцами Lorem Ipsum в 60-х годах и, в более недавнее время, программы электронной вёрстки типа Aldus PageMaker, в шаблонах которых используется Lorem Ipsum.

                        Давно выяснено, что при оценке дизайна и композиции читаемый текст мешает сосредоточиться. Lorem Ipsum используют потому, что тот обеспечивает более или менее стандартное заполнение шаблона, а также реальное распределение букв и пробелов в абзацах, которое не получается при простой дубликации "Здесь ваш текст.. Здесь ваш текст.. Здесь ваш текст.." Многие программы электронной вёрстки и редакторы HTML используют Lorem Ipsum в качестве текста по умолчанию, так что поиск по ключевым словам "lorem ipsum" сразу показывает, как много веб-страниц всё ещё дожидаются своего настоящего рождения. За прошедшие годы текст Lorem Ipsum получил много версий. Некоторые версии появились по ошибке, некоторые - намеренно (например, юмористические варианты).


                        Многие думают, что Lorem Ipsum - взятый с потолка псевдо-латинский набор слов, но это не совсем так. Его корни уходят в один фрагмент классической латыни 45 года н.э., то есть более двух тысячелетий назад. Ричард МакКлинток, профессор латыни из колледжа Hampden-Sydney, штат Вирджиния, взял одно из самых странных слов в Lorem Ipsum, "consectetur", и занялся его поисками в классической латинской литературе. В результате он нашёл неоспоримый первоисточник Lorem Ipsum в разделах 1.10.32 и 1.10.33 книги "de Finibus Bonorum et Malorum" ("О пределах добра и зла"), написанной Цицероном в 45 году н.э. Этот трактат по теории этики был очень популярен в эпоху Возрождения. Первая строка Lorem Ipsum, "Lorem ipsum dolor sit amet..", происходит от одной из строк в разделе 1.10.32

                        Классический текст Lorem Ipsum, используемый с XVI века, приведён ниже. Также даны разделы 1.10.32 и 1.10.33 "de Finibus Bonorum et Malorum" Цицерона и их английский перевод, сделанный H. Rackham, 1914 год.

                    </p>                     
                </div>
            </div>       

            <div class="shadow-box win-4" style="display:none"  >
                <div class="window">
                    <img class="exit-modal" src="<c:url value="/resources/img/close.png"/>"/>
                    <h1>ПРАВИЛА</h1>
                    <p>
                        Для входа в систему необходимо зарегистрироваться. Следует заполнить все необходимые поля в форме, а также капчу в определенном поле. При успешном вводе Вы будете перенаправлены на страницу с оповещением о регистрации. <br/>
                    <p>    
                        При желании Вы можете пройти упрощенную регистрацию – вход средствами социальных сетей.<br/>
                    </p>    
                    <p>
                        Для дальнейших действий нужно войти в систему, введя личные данные указанные при регистрации: логин и пароль. При успешном входе осуществляется переход н главную страницу сайта уже без окон регистрации и входа, появится возможность входа в личный кабинет пользователя.<br/>
                    </p> 
                    <p>
                        На странице личного кабинета отображаются личные данные, среди которых и баланс. Для того чтоб купить лотерейный билет и участвовать в лотерее необходимо либо завести деньги в систему через одну из популярных платежных систем, при этом платежная система, вносимая сумма и дата внесения будут отображаться в логе снизу окна, а общая сумма по всем платежам вынесена в суммарный баланс пользователя, либо, если средств нет, при розыгрыше будет предложено оплатить билет. Купленный билет появится в личном кабинете, во вкладке «Билеты». Для того чтобы узнать выигрыш по билету следует выбрать билет и нажать на кнопку «Открыть», далее появится окно с результатом розыгрыша.<br/>

                    </p>                     
                </div>
            </div>  

            <div class="shadow-box win-5" style="display:none"  >
                <div class="window">
                    <img class="exit-modal" src="<c:url value="/resources/img/close.png"/>"/>
                    <h1>КОНТАКТЫ</h1>
                    <div class="block-contact">
                        <img src="">
                        <p></p>
                    </div>           
                </div>
            </div>     

            <div class="shadow-box win-6" style="display:none"  >
                <div class="window-3">
                    <img class="exit-modal" src="<c:url value="/resources/img/close.png"/>"/>
                    <h1>Напоминание пароля</h1>
                    <h2>Введите адрес электронной почты</h2>
                    <input type="email" value="" name="email" id="remembermymail" placeholder="Ваш e-mail"/>
                    <input type="button" class="button-1 w3" value="Напомнить">
                </div>
            </div>     

            <input type="hidden" id="_csrf" name="_csrf" value="${_csrf.token}" />
        </body>
    </html>
