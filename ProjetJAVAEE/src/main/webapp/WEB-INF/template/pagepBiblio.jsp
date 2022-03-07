<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <style><%@include file="/WEB-INF/template/css/style.css"%></style>
    <title>Votre espace</title>
</head>

<body>
    <h1>Bonjour ${nomU} bibliothecaire</h1><br>
	<div class="boxLogin">
        <h1 id="co">Ajouter un nouveau document :</h1>
        <form>

            <b>Type :</b><br>
            <select name="type" id="type">
                <option value="Livre">Livre</option>
                <option value="CD">CD</option>
                <option value="DVD">DVD</option>
            </select><br><br>

            <b>Titre :</b><br>
            <input type="type" name="titre" id="titre"></input><br><br>

            <b>Auteur :</b><br>
            <input type="type" name="auteur" id="auteur"></input><br><br>

            <input type="submit" name="BtnAjou" id="BtnAjou"></input>
        </form>
        <div>${msgAction}</div>
    </div>
</body>

</html>