<%-- 
    Document   : checkout
    Created on : Nov 30, 2013, 11:34:33 AM
    Author     : Herman
    Description: this is the view, which lets You review Your order
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Check out</title>
        <link rel='stylesheet' type='text/css' href='css/main.css'>
    </head>
    <body>
        <div id='container'>
            <c:choose>
                <c:when test="${processed == false}">
                    <h1>Something went wrong. Try again later.</h1>
                </c:when>
                <c:otherwise>
                    <h2>Your receipt</h2>
                <p>${customer.name}<br>
                    ${customer.tel} <br>
                    ${customer.address}<br>
                    ${customer.email}<br></p>
                <table border='0'>
                <tr>
                <th>Size</th>
                <th>Toppings</th>
                <th>Price</th>
                </tr>
                  <c:forEach items="${order}" var="pizza">
                    <tr>
                    <td>${pizza.size}</td>
                    <td>${pizza.toppings}</td>
                    <td>$ ${pizza.price} </td>
                    </tr>
                </c:forEach>
                </table>
                <h4>Tax: $<fmt:formatNumber value="${tax}" maxFractionDigits="2"/></h4>
                <h4>Total: $<fmt:formatNumber value="${total}" maxFractionDigits="2"/></h4>
                    <h4>Delivery time: ${time}</h4>
                </c:otherwise>
            </c:choose>
        </div>
    </body>
</html>
