<%-- 
    Document   : ManagementRequest
    Created on : May 19, 2021, 7:00:54 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Management Request</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    </head>
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
    <body>
        <c:if test="${empty sessionScope.admin}">
            <c:redirect url="Login.jsp"/>
        </c:if>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark text-white ">
            <a class="navbar-brand" href="#">Navbar</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item active">
                        <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">${sessionScope.admin.getName()}</a>
                    </li>
                    <li class="nav-item">
                        <form method="POST" action="DispatchController">
                            <input type="submit" value="logout" name="btnAction" />
                        </form>
                    </li>
                </ul>
            </div>
        </nav>
        <label for="requests">Choose a Type Request</label>
        <form method="POST" action="DispatchController">
            <select name="StatusRequest">
                <c:forEach var="i" items="${requestScope.listStatusRequest}">
                    <option value="${i.statusReqName}" ${requestScope.StatusRequest eq i.statusReqName ? "selected" : ""}>
                        ${i.statusReqName}
                    </option>
                </c:forEach>
            </select>
            Search Date Request <input type="date" name="date" value="${requestScope.date}" />
            Search key<input type="text" placeholder="search content" name="key" value="${requestScope.key}" />
            <input type="submit" name="btnAction" value="Search Request" />
        </form>
        <h1 style="color: green">${requestScope.successConfirm}</h1>
        <h1 style="color: green">${requestScope.deleteConfirm}</h1>
        <table border="1">
            <thead>
                <tr>
                    <th>DateBook</th>
                    <th>email</th>
                    <th>productName</th>
                    <th>status</th>
                    <th>Accept</th>
                    <th>Delete</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="item" items="${requestScope.arrayListRequest}">
                    <tr>
                        <td>${item.dateBook}</td>
                        <td>${item.email}</td>
                        <c:forEach var="a" items="${requestScope.listResource}">
                            <c:if test="${item.productID eq a.productID}">
                                <td>${a.productName}</td>   
                            </c:if>
                        </c:forEach>
                        <c:forEach var="i" items="${requestScope.listStatusRequest}">
                            <c:if test="${item.statusReqID eq i.statusReqID}">
                                <td>${i.statusReqName}</td>
                            </c:if>
                        </c:forEach>
                <form method="Post" action="DispatchController" >
                    <td>
                        <input type="hidden" name="productID" value="${item.productID}" />
                        <input type="hidden" name="flag" value="true" />
                        <input type="hidden" name="requestID" value="${item.requestID}" />
                        <c:if test="${item.statusReqID ne 4}">
                            <input type="submit" value="Accept" name="btnAction" />
                        </c:if>
                    </td>
                </form>
                <form method="Post" action="DispatchController" >
                    <td>
                        <input type="hidden" name="productID" value="${item.productID}" />
                        <input type="hidden" name="flag" value="false" />
                        <input type="hidden" name="requestID" value="${item.requestID}" />
                        <c:if test="${item.statusReqID ne 4}">
                            <input type="submit" value="Delete" name="btnAction" />
                        </c:if>
                    </td>
                </form>
            </tr>    
        </c:forEach>
            <c:if test="${empty requestScope.arrayListRequest}">
                <h1>List Request Empty</h1>
        </c:if>
    </tbody>
</table>
<div class="pagination">
    <c:forEach  begin="1" end="${requestScope.countPageSize}" var="i">
        <a id="${i}" href="DispatchController?btnAction=Search Request&index=${i}&key=${requestScope.key}&StatusRequest=${requestScope.StatusRequest}">${i}</a>
    </c:forEach>
</div>
<script>
    document.getElementById('${index}').style.color = "red";
</script>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>
