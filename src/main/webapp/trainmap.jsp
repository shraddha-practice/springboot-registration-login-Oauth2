<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Create an account</title>
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
      <style type="text/css">
        #bootstrap-overrides .custom {

                        position: relative;
                        overflow: scroll;
                        height: 500px;
        }

      </style>
  <script>
    function initMap() {
      const mapOptions = {
        zoom: 8,
        center: { lat: -34.397, lng: 150.644 },
      };
      map = new google.maps.Map(document.getElementById("map"), mapOptions);
      const marker = new google.maps.Marker({
        // The below line is equivalent to writing:
        // position: new google.maps.LatLng(-34.397, 150.644)
        position: { lat: -34.397, lng: 150.644 },
        map: map,
      });
      }
         const infowindow = new google.maps.InfoWindow({
            content: "<p>Train Location:" + marker.getPosition() + "</p>",
          });
          google.maps.event.addListener(marker, "click", () => {
            infowindow.open(map, marker);
          });

       </script>
</head>
<body id="bootstrap-overrides">

  <div class="container">
     <form id="logoutForm" method="POST" action="${contextPath}/logout">
                  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
     </form>
     <h4><a onclick="document.forms['logoutForm'].submit()">Logout</a></h4>
      <form method="GET"  action="${contextPath}/userInfo" modelAttribute="user">
      <h4><a href="${contextPath}/userInfo?username=${pageContext.request.userPrincipal.name}">Personal Information</a></h4>
       </form>
       <h4><a href="${contextPath}/locations">Back to Train Locations</a></h4>
        <h2 class="form-heading">Train Details </h2>

          <table class="table table-striped table-bordered">
                    <thead class="thead-dark">
                        <tr>
                            <th>Train Number</th>

                            <th>Departure Date</th>
                            <th>Train Location </th>
                            <th>Train Speed</th>
                        </tr>
                    </thead>
                     <c:if test="${not empty trains}">
                     <c:forEach var="train" items="${trains}" begin="0" end="8">
                    <tbody>
                            <td th:value="${train.trainNumber}"><a href="${contextPath}/getTrainInfo?trainNumber=${train.trainNumber}">
                            <form method="GET" action="${contextPath}/getTrainInfo" modelAttribute="trains" >${train.trainNumber}</a></td>

                            <td th:value="${train.departureDate}">${train.departureDate}</td>
                            <td th:value="${train.location.coordinates}">
                            ${train.location.coordinates[0]},${train.location.coordinates[1]}</td>
                            <td th:value="${train.speed}">${train.speed}</td>
                    </tbody>
                    </c:forEach>
                    </c:if>
                </table>
                           <h3>Train you requested is here at the moment  : </h3>
                              <div id="map" class="custom"></div>
                   </div>

                           <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBIwzALxUPNbatRBj3Xi1Uhp0fFzwWNBkE&callback=initMap&libraries=&v=weekly"
                                    async></script>


  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
  <script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
