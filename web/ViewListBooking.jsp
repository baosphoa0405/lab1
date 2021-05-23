<%-- 
    Document   : ViewListBooking
    Created on : May 22, 2021, 4:31:06 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>List Booking View</title>
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
        <h1>View List Booking</h1>
        <form action="DispatchController" method="Post">
            Search Name Product <input type="text" name="nameProduct" value="${requestScope.nameProduct}" />
            <input type="date" name="dateSearch" value="${requestScope.dateSearch}" />
            <input type="submit" value="Search Booking" name="btnAction" />
        </form>
        <div>
            <a href="DispatchController?btnAction=Reset">Go Back To Home</a>
        </div>
        <h1 style="color: green">${requestScope.successDelete}</h1>
        <h1 style="color: green">${requestScope.errorDelete}</h1>
        <table style="border: none; text-align: center">
            <thead>
                <tr>
                    <th>RequestID</th>
                    <th>DateBook</th>
                    <th>email</th>
                    <th>productName</th>
                    <th>status</th>
                    <th>Waiting</th>
                    <th>Request Successfully</th>
                    <th>Delete</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="item" items="${requestScope.listRequestBooking}">
                    <tr>
                        <td>${item.requestID}</td>
                        <td>${item.dateBook}</td>
                        <td>${item.email}</td>
                        <c:forEach var="i" items="${requestScope.listResouces}">
                            <c:if test="${i.productID eq item.productID}">
                                <td>${i.productName}</td> 
                            </c:if>  
                        </c:forEach>
                        <c:forEach var="i" items="${requestScope.listStatusRequest}">
                            <c:if test="${i.statusReqID eq item.statusReqID}">
                                <td>${i.statusReqName}</td> 
                            </c:if>  
                        </c:forEach>
                        <c:if test="${item.statusReqID eq 1}">
                            <td>
                                Waiting admin accept
                            </td>
                            <td>None</td>
                            <td>
                                <form action="DispatchController" method="Post">
                                    <input type="hidden" name="requestID" value="${item.requestID}" />
                                    <input type="submit" value="Delete Request" name="btnAction"/>
                                </form>
                            </td>
                           
                        </c:if>
                        <c:if test="${item.statusReqID ne 1}">
                            <td>None</td>
                            <td style="text-align: center">
                                successFully
                            </td>
                            <td>
                                <form action="DispatchController" method="Post">
                                    <input type="hidden" name="requestID" value="${item.requestID}" />
                                    <input type="submit" value="Delete Request" name="btnAction" ${item.statusReqID ne 1 ? "disabled":""}/>
                                </form>
                            </td>
                             <c:if test="${requestScope.requestID eq item.requestID}">
                                <c:if test="${not empty requestScope.successDelete}">
                                    <td style="color: green">${requestScope.successDelete}</td>    
                                </c:if>
                            </c:if>
                        </c:if>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <div class="pagination">
            <c:forEach  begin="1" end="${requestScope.quanityPageSize}" var="i">
                <a id="${i}" href="DispatchController?btnAction=Search Booking&index=${i}&dateSearch=${requestScope.dateSearch}&nameProduct=${requestScope.nameProduct}">${i}</a>
            </c:forEach>
        </div>
        <script>
            document.getElementById('${index}').style.color = "red";
        </script>
    </body>
</html>
