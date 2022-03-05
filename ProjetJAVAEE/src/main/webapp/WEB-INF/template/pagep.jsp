<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <style><%@include file="/WEB-INF/template/css/style.css"%></style>
    <title>Votre espace</title>
</head>

<body>
    <h1>bonjour ${nomU}</h1><br>
    <div id="boiteCentrale">
        <h1>Vos documents actuels :</h1><br>
        <select name="listeD" id="listeD">
            <c:forEach items="${ documents }" var="document" varStatus="status">
            <option><c:out value="${ document }" /></option>
            </c:forEach>
        </select><br>
 	    <button type="button" id="BtnEmp">Emprunter</button> <br>
 	    <button type="button" id="BtnRend">Rendre</button><br>
    </div>
</body>

</html>