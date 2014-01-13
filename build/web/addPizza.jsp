<%-- 
    Document   : addPizza
    Created on : Nov 30, 2013, 11:34:12 AM
    Author     : Herman
    Description: this is the view, which lets You add pizzas to Your order
                 and shows live preview
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add pizza</title>
        <link rel='stylesheet' type='text/css' href='css/main.css'>
    </head>
    <body>
        <div  id='container'>
            <h1>Order for ${customer.name}</h1>
            <div id='newPizza'>
                <h2>Add a pizza:</h2>
                <form method='POST' action='AddToOrder'>
                    <h4 id='pizzaSizeHeader'>Size: </h4>
                    <select name='size'>
                        <option value='s'>small</option>
                        <option value='m'>medium</option>
                        <option value='l'>large</option>
                        <option value='x'>extra large</option>
                    </select>
                    <h4>Toppings: </h4>
                    <c:forEach items="${toppingList}" var="topping">
                        ${topping} <input type='checkbox' name='${topping}' value='true'/>&nbsp;&nbsp;&nbsp;&nbsp;
                    </c:forEach>
                    <br><button>Add</button>
                </form>
            </div>
            <div id='preview'>
                <c:choose>
                    <c:when test="${empty order}">
                        <h1>Every fourth topping is free!</h1>                        
                    </c:when>
                    <c:otherwise>
<h3>Your order so far (${customer.delivery?"delivery":"pick up"})</h3>
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
                <a href='CompleteOrder'>Checkout</a>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </body>
</html>
