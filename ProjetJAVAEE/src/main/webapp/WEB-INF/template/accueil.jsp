<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Sample Page</title>
</head>

<body>
    <h1>${requestScope["title"]} big boss</h1>
    <form>
        <b>Login :</b><br>
        <input type="type" name="login" id="login"></input><br><br>
        <b>Mot de passe :</b><br>
        <input type="type" name="mdp" id="mdp"></input>
        <input type="submit"></input>
    </form>
</body>

</html>