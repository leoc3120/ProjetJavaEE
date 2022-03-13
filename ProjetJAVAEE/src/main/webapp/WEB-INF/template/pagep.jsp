<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <style><%@include file="/WEB-INF/template/css/style.css"%></style>
    <title>Votre espace</title>
</head>

<body>
    <div id="boiteCentrale" class="boxLogin">
        <h1>Bonjour ${nomU},</h1><br>
        <h1 id="actionPage">Les documents de la Mediatheque :</h1><br>
        <form action="${pageContext.request.contextPath}/affiche" method="get">
        <select name="listeD" id="listeD">
            <option value="">-- Veuillez choisir un document --</option>

            <c:forEach items= "${ documents }" var="document" varStatus="status">
            <option><c:out value="${ status.index+1 } ${ document }" /></option>
            </c:forEach>
        </select><br><br>
        <b><button type="submit" name="BtnEmp" id="BtnEmp">Emprunter</button>
 	    <button type="submit" name="BtnRend" id="BtnRend">Rendre</button></b><br><br>
        <div>${msgAction}</div><br><br><br>
        <button type="submit" name="BtnDeco" id="BtnDeco">Se deconnecter</button>
 	    </form>
    </div>
</body>

</html>