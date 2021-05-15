<%-- 
    Document   : Register
    Created on : May 15, 2021, 10:25:57 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
    </head>
    <body>
        <h1>Create Account</h1>

        <form method="POST" action="DispatchController">
            Email <input type="text" name="email" value="${requestScope.email}" /><br/>
            <span style="color: red">${requestScope.errorRegister.emailError}</span> <br/>
            <span style="color: red">${requestScope.errorRegister.emailErrorFormat}</span> <br/>
            <span style="color: red">${requestScope.errorRegister.emailDuplicate}</span> <br/>
            Phone <input type="text" name="phone" value="${requestScope.phone}" /><br/>
            <span style="color: red">${requestScope.errorRegister.phoneError}</span> <br/>
            <span style="color: red">${requestScope.errorRegister.phoneErrorFormat}</span>
            Name <input type="text" name="name" value="${requestScope.name}" /><br/>
            <span style="color: red">${requestScope.errorRegister.nameError}</span> <br/>
            Password <input type="text" name="password" value="${requestScope.password}" /><br/>
            <span style="color: red">${requestScope.errorRegister.passwordError}</span> <br/>
            Address <input type="text" name="address" value="${requestScope.address}" /><br/>
            <span style="color: red">${requestScope.errorRegister.addressError}</span> <br/>
            <input type="submit" value="Create" name="btnAction"/>
        </form>
    </body>
</html>
