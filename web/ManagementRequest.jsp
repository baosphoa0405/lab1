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
        <ul class="nav nav-tabs" id="myTab" role="tablist">
            <li class="nav-item">
                <a class="nav-link active" id="home-tab" data-toggle="tab" href="#home" role="tab" aria-controls="home" aria-selected="true">Request Pending</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" id="profile-tab" data-toggle="tab" href="#profile" role="tab" aria-controls="profile" aria-selected="false">Requesrt Active </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" id="contact-tab" data-toggle="tab" href="#contact" role="tab" aria-controls="contact" aria-selected="false">Request History</a>
            </li>
        </ul>
        <div class="tab-content" id="myTabContent">
            <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
                Lorem ipsum dolor sit amet consectetur adipisicing elit. Adipisci commodi amet tempore voluptate odio porro sint ad dolorem impedit quae.
            </div>
            <div class="tab-pane fade" id="profile" role="tabpanel" aria-labelledby="profile-tab">
                Lorem ipsum dolor sit amet consectetur adipisicing elit. Deleniti nisi excepturi, vel similique quae laboriosam placeat quidem eaque eligendi veniam est debitis deserunt! Ullam error tenetur nemo eius laborum quia minus fuga et expedita hic? Pariatur, accusantium alias vero sunt modi recusandae voluptatem veritatis mollitia laborum sit culpa voluptatibus corporis.
            </div>
            <div class="tab-pane fade" id="contact" role="tabpanel" aria-labelledby="contact-tab">
                Lorem ipsum, dolor sit amet consectetur adipisicing elit. Fuga, enim numquam. Commodi corrupti modi doloremque ea blanditiis eveniet nostrum, dolorum eum ipsam in, corporis optio aliquam iure et repellat perspiciatis labore ab eaque tempora. Earum asperiores aliquid perspiciatis totam recusandae. Aliquam quidem hic, quod soluta itaque animi eaque ipsam cum sit veritatis est, incidunt expedita aut id odio quam temporibus veniam dolores ipsa exercitationem fugit modi earum laboriosam! Perspiciatis quos, aspernatur doloribus expedita est facere, quibusdam fugit eum esse cum nulla quo nam nihil hic accusamus ex blanditiis! Mollitia autem quo animi aliquam doloremque ad reiciendis, aperiam aspernatur expedita a?
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    </body>
</html>
