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
        <input type="type" name="nom"></input>
        <input type="submit"></input>
    </form>
    <b>Le nom:</b> ${requestScope["nom"]}</b>
</body>

</html>