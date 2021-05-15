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
            Email <input type="text" name="email" value="" /><br/>
            Phone <input type="text" name="phone" value="" /><br/>
            Name <input type="text" name="name" value="" /><br/>
            Password <input type="text" name="password" value="" /><br/>
            Address <input type="text" name="address" value="" /><br/>
            <input type="submit" value="Create" name="btnAction"/>
        </form>
    </body>
</html>
