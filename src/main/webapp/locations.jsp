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
      <form method="GET"  action="${contextPath}/userInfo" modelAttribute="user">
      <h4><a href="${contextPath}/userInfo?username=${pageContext.request.userPrincipal.name}">Personal Information</a></h4>
       </form>
        <h2 class="form-heading">Train Locations</h2>
        <c:if test="${pageContext.request.userPrincipal.name != null}">
            <h3>Welcome ${pageContext.request.userPrincipal.name} , Train locations are  : </h3>
        </c:if>

          <table class="table table-striped table-bordered">
                    <thead class="thead-dark">
                        <tr>
                            <th>Train Number</th>
                            <th>Departure Date</th>
                            <th>Train Location </th>
                            <th>Train Speed </th>
                        </tr>
                    </thead>
                     <c:if test="${not empty trains}">
                     <c:forEach var="train" items="${trains}" begin="0" end="15">
                    <tbody>
                            <td th:value="${train.trainNumber}"><a href="${contextPath}/getTrainInfo?trainNumber=${train.trainNumber}">
                            <form method="GET" action="${contextPath}/getTrainInfo" modelAttribute="trains"/>${train.trainNumber}</a></td>
                            <td th:value="${train.departureDate}">${train.departureDate}</td>
                            <td th:value="${train.location.coordinates}">${train.location.coordinates[0]},${train.location.coordinates[1]}</td>
                            <td th:value="${train.speed}">${train.speed} </td>
                    </tbody>
                    </c:forEach>
                    </c:if>
                </table>
  </div>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js">
  $.ajax({
      type : "GET",
      url : "${pageContext.request.contextPath}/locations",
      data : {
      "train" : ${trains}
      },
      success: function(data){
           alert('I hate tomatoes.');
      }
  });
  </script>
  <script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
