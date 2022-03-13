<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <style><%@include file="/WEB-INF/template/css/style.css"%></style>
    <title>Votre espace</title>
</head>

<body>
	<div class="boxLogin">
        <h1>Bonjour ${nomU} le bibliothecaire,</h1><br>
        <h1 id="actionPage">Ajouter un nouveau document :</h1><br>
        <form>

            <b>Type du document : 
            <select name="type" id="type">
                <option value="Livre">Livre</option>
                <option value="CD">CD</option>
                <option value="DVD">DVD</option>
            </select></b><br><br>

            <b>Titre : <input type="type" name="titre" id="titre"></input></b><br><br>

            <b>Auteur : <input type="type" name="auteur" id="auteur"></input></b><br><br>

            <button type="submit" name="BtnAjou" id="BtnAjou">Ajouter</button><br><br>
            <div>${msgAction}</div><br><br><br>
            <button type="submit" name="BtnDeco" id="BtnDeco">Se deconnecter</button>
        </form>
    </div>
</body>

</html>