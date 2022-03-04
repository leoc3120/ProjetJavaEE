<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Sample Page</title>
</head>

<style>
	h1 {
 	 color : red;
 	 text-align : center;
	}

	@page :first {
 	 margin: 2cm;
	}
	form {
	text-align : center;
	}
</style>

<body>
    <h1>Connectez-vous !</h1>
    <form>
        <b>Login :</b><br>
        <input type="type" name="login" id="login"></input><br><br>
        <b>Mot de passe :</b><br>
        <input type="type" name="mdp" id="mdp"></input><br><br>
        <input type="submit"></input>
    </form>
</body>

</html>