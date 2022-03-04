<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Sample Page</title>
</head>

<body>

    <h1>bonjour ${nomU}</h1><br>
     <h1>Vos documents actuels :</h1><br>
    <select name="my_html_select_box">
        
            <c:forEach items="${ documents }" var="document" varStatus="status">
            <option><c:out value="${ document }" /></option>
            </c:forEach>
    </select>
 	<input type="submit" id="BtnEmp">Emprunter</input> <br>
 	<input type="submit" id="BtnRend">Rendre</input><br>
    </form>
</body>

</html>