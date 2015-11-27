<%-- 
    Document   : askRequest
    Created on : 24.11.2015, 23:49:40
    Author     : Aleks
--%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="<c:url value="/resources/css/reset.css"/>" rel="stylesheet">
        <link href="<c:url value="/resources/css/userRoomStyle.css"/>" rel="stylesheet">
        <link href='https://fonts.googleapis.com/css?family=PT+Sans+Narrow&subset=latin,cyrillic' rel='stylesheet' type='text/css'>
        <script src="<c:url value="/resources/js/jquery-2.1.4.min.js"/>"></script>
        <script src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
        <link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css">
    </head>
    <body>
        <div class="shadow-box win-12">
            <div class="window-1">
                <h1>Вы потверждаете запрос на сумму:</h1>    
                <h1>${OutSum} р. ?</h1>
                <form action="${action}" method="POST">
                    <input type=hidden name=MrchLogin value="${MrchLogin}">
                    <input type=hidden name=OutSum value="${OutSum}">
                    <input type=hidden name=InvId value="${InvId}">
                    <input type=hidden name=Desc value="${Desc}">
                    <input type=hidden name=SignatureValue value="${SignatureValue}">
                    <input type=hidden name=Shp_item value="${Shp_item}">
                    <input type=hidden name=IncCurrLabel value="${IncCurrLabel}">
                    <input type=hidden name=Culture value="${Culture}">
                    <input type=hidden name=Email value="${Email}">
                    <input type=hidden name=ExpirationDate value="${ExpirationDate}">
                    <input type=hidden name=OutSumCurrency value="${OutSumCurrency}">
                    <input type=submit class="button-1 w2" value='Подтвердить'>
                </form>
            </div>
        </div> 
    </body>
</html>
