<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Create an account</title>
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
  <div class="container">
          <form id="logoutForm" method="POST" action="${contextPath}/logout">
              <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
          </form>
          <h4><a onclick="document.forms['logoutForm'].submit()">Logout</a></h4>
  <h4><a href="${contextPath}/locations">Train Locations</a></h4>
  <form method="GET" action="${contextPath}/userInfo" class="form-personalInfo" >
       <h2 class="form-heading">Personal Information</h2>
    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <h3>Welcome ${pageContext.request.userPrincipal.name} , Your Personal Information is : </h3>
    </c:if>
    <div>
        <table class="table table-striped table-bordered">
            <thead class="thead-dark">
                <tr>
                    <th>User ID</th>
                    <th>E-mail</th>
                    <th>Full Name</th>
                </tr>
            </thead>
            <tbody>
                    <td th:value="${user.username}">${user.username}</td>
                    <td th:value="${user.email}">${user.email} </td>
                    <td th:value="${user.firstname}">${user.firstname} ${user.lastname}</td>
            </tbody>
        </table>
    </div>
   </form>
  </div>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
  <script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
