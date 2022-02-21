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
        <b>Nom:</b> ${requestScope["nom"]}</b>
        <input type="type" name="nom"></input>
        <br>
        <b>Mot de passe</b>
        <input type="type" name="mdp"></input>
        <input type="submit"></input>
    </form>
</body>

</html>