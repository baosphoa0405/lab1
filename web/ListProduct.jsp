<%-- 
    Document   : ListProduct
    Created on : May 12, 2021, 10:45:32 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ListProduct</title>
        <style>
            .pagination a {
                color: black;
                float: left;
                padding:  12px 18px;
                text-decoration: none;
            }
            /* thiết lập style cho class active */
            .pagination a.active {
                background-color: dodgerblue;
                color: white;
                /*Thiết kế hình tròn với CSS*/
                border-radius: 50%;
            }
            /* thêm màu nền khi người dùng hover vào class không active */
            .pagination a:hover:not(.active) {
                background-color: #ddd;
                /*Thiết kế hình tròn với CSS*/
                border-radius: 50%;
            }
        </style>
    </head>
    <body>
        <c:if test="${empty sessionScope.user}">
            <c:redirect url="Login.jsp"/>
        </c:if>
        <div style="display: flex; align-items: center">
            <h1>hello ${sessionScope.user.getName()}</h1>
            <form action="DispatchController">
                <input type="submit" value="logout" name="btnAction" />
            </form>
        </div>
        <h1>ListProduct</h1>
        <label for="categories">Choose a categories</label>
        <form action="DispatchController" method="POST">
            <select id="categories" name="category">
                <c:forEach var="item" items="${requestScope.listCategories}">
                    <option value="${item.getCategoryID()}" ${requestScope.categorySelect eq item.getCategoryID() ? "selected" : ""}>${item.getCategoryName()}</option>
                </c:forEach>
            </select>
            <input type="submit" name="btnAction" value="search" />
        </form>
        <form action="DispatchController" method="Post">
            Search <input type="text" name="nameSearch" value="${requestScope.nameSearch}" />
            <input type="submit" value="Search Name" name="btnAction"/>
        </form>

        <table border="1">
            <thead>
                <tr>
                    <th>ProductID</th>
                    <th>Name</th>
                    <th>Color</th>
                    <th>CategoryName</th>
                    <th>Quanlity</th>
                </tr>
            </thead>
            <tbody>
                <!--ko seach gì hết--> 
                <c:if test="${empty requestScope.listProductByCategory && empty requestScope.listProductByName}">
                    <c:forEach var="item" items="${requestScope.listProducts}">
                        <tr>
                            <td>${item.getProductID()}</td>
                            <td>${item.getProductName()}</td>
                            <td>${item.getColor()}</td>
                            <c:forEach var="itemCategory" items="${requestScope.listCategories}">
                                <c:if test="${item.getCategoryID() eq itemCategory.getCategoryID()}">
                                    <td>${itemCategory.getCategoryName()}</td>       
                                </c:if>
                            </c:forEach>
                            <td>${item.getQuanlity()}</td>
                        </tr>
                    </c:forEach>
                </c:if>
                <!--search by category-->
                <c:if test="${not empty requestScope.listProductByCategory}">
                    <c:forEach var="item" items="${requestScope.listProductByCategory}">
                        <tr>
                            <td>${item.getProductID()}</td>
                            <td>${item.getProductName()}</td>
                            <td>${item.getColor()}</td>
                            <c:forEach var="itemCategory" items="${requestScope.listCategories}">
                                <c:if test="${item.getCategoryID() eq itemCategory.getCategoryID()}">
                                    <td>${itemCategory.getCategoryName()}</td>       
                                </c:if>
                            </c:forEach>
                            <td>${item.getQuanlity()}</td>
                        </tr>
                    </c:forEach>
                </c:if>
                <!--search by name-->
                <c:if test="${not empty requestScope.listProductByName}">
                    <c:forEach var="item" items="${requestScope.listProductByName}">
                        <tr>
                            <td>${item.getProductID()}</td>
                            <td>${item.getProductName()}</td>
                            <td>${item.getColor()}</td>
                            <c:forEach var="itemCategory" items="${requestScope.listCategories}">
                                <c:if test="${item.getCategoryID() eq itemCategory.getCategoryID()}">
                                    <td>${itemCategory.getCategoryName()}</td>       
                                </c:if>
                            </c:forEach>
                            <td>${item.getQuanlity()}</td>
                        </tr>
                    </c:forEach>
                </c:if>
            </tbody>
        </table>
        <c:if test="${not empty requestScope.pageSize}">
            <div class="pagination">
                <c:forEach  begin="1" end="${requestScope.pageSize}" var="i">
                    <a href="SearchServlet?index=${i}&nameSearch=${nameSearch}">${i}</a>
                </c:forEach>
            </div>
        </c:if>
        ${requestScope.listReourcesPagnination}
    </body>
</html>
