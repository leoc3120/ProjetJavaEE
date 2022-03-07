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
        <h1>Les documents de la Mediatheque :</h1><br>
        <form action="${pageContext.request.contextPath}/affiche" method="get">
        <select name="listeD" id="listeD">
            <option value="">-- Veuillez choisir un document --</option>

            <c:forEach items= "${ documents }" var="document" varStatus="status">
            <option><c:out value="${ status.index+1 } ${ document }" /></option>
            </c:forEach>
        </select><br>
 	    	<input type="submit" name="BtnEmp" id="BtnEmp">Emprunter</input><br>
 	    	<input type="submit" name="BtnRend" id="BtnRend">Rendre</input><br>
 	    </form>

         <div>${msgAction}</div>
    </div>

    <input type="submit" name="BtnDeco" id="BtnDeco"> Se deconnecter</input><br>

</body>

</html>