<%-- 
    Document   : List
    Created on : 25 Jun 2024, 10:18:30 pm
    Author     : USER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style1.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style2.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  
  <title>UMT Academic Program Development Tracking System</title>
  <style>
    .btn {
        display: inline-block;
        padding: 10px 15px;
        margin: 5px 0;
        border-radius: 5px;
        text-decoration: none;
        color: white;
        font-weight: bold;
        text-align: center;
    }

    .btn-mqa {
        background-color: #2196F3; /* Blue */
    }

    .btn-jpt {
        background-color: #2196F3; /* Blue */
    }

    .btn-edit {
        background-color: #4CAF50; /* Green */
    }

    .btn-delete {
        background-color: #f44336; /* Red */
    }

    .btn:hover {
        opacity: 0.8;
    }
</style>

   <script>
            window.onload = function() {
               var urlParams = new URLSearchParams(window.location.search);
               if (urlParams.has('success')) {
                 alert('Document successfully updated');
               }
            };
    </script>
</head>

<body>
  <!-- This the header of the page-->
  <div class="header">
    <header>
      <table>
        <tr>
          <td class="logoumtcol"><img class="logoumt" src="https://upload.wikimedia.org/wikipedia/commons/3/3e/Logo_Rasmi_UMT.png"></td>
          <td><h1>UMT Academic Program Development Tracking System</h1></td>
        </tr>
      </table>
    </header>
  </div>

  <!-- Navigation bar of the page -->
  <div class="navbar">
    <ul>
      <li><a href="${pageContext.request.contextPath}/index.jsp">Home</a></li>
      <li><a href="#programlist.html">Program List</a></li>
      <li><a href="contact.html">Contact</a></li>
      <li class="dropdown">
        <a href="javascript:void(0)" class="dropbtn">Manage Application</a>
        <div class="dropdown-content">
          <a href="jppu.jsp">Internal Application</a>
          <a href="javascript:void(0)" class="subdropbtn">Provisional Accreditation ></a>
            <div class="sub-dropdown-content">
              <a href="${pageContext.request.contextPath}/module2.jsp">MQA-01</a>
              <a href="#">JPT</a>
            </div>
          <a href="/task2/task2upgraded.html">Program Offering</a>
          <a href="module4.jsp">Full Accreditation</a>
          <a href="module5.html">MQA Certification</a>
        </div>
      </li>
      <li class="profile">
        <a href="javascript:void(0)" class="dropbtn"><img class="iconProfile" src="https://static.vecteezy.com/system/resources/previews/019/879/186/original/user-icon-on-transparent-background-free-png.png">Profile</a>
        <div class="dropdown-content">
            <a href="Login.jsp">Logout</a>
        </div>
      </li>
    </ul>
  </div>
  <br>
  <br>
  <br>
  <br>

  <!-- here where the main content of the web page started-->
  <div class="content">
     <div class="row">
            <div class="container">
                <h3 class="container">Provisional Accreditation Documents List</h3>
                <hr>
                <div class="container text-left">
                <button style="background-color: #00cc00; padding: 10px; border-radius: 3px; border: 0px">
                    <a href="<%=request.getContextPath()%>/Home.jsp" style="display: inline-block; width: 100%; height: 100%; text-decoration: none; color: white;">
                    Add New Course
                    </a>
                </button>
                </div>

                <br>
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Course Name</th>
                            <th>Course ID</th>
                            <th>Person In Charge</th>
                            <th>Date</th>
                            <th>Document For</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="home" items="${listHome}">
                        <tr>
                        <td><c:out value="${home.id}" /></td>
                        <td><c:out value="${home.ncr}" /></td>
                        <td><c:out value="${home.courseid}" /></td>
                        <td><c:out value="${home.pic}" /></td>
                        <td><c:out value="${home.date}" /></td>
                        <td>
                            <a class="btn btn-mqa" href="${pageContext.request.contextPath}/Mqa01.jsp?id=<c:out value='${home.id}' />">MQA-01</a>
                            &nbsp;&nbsp;&nbsp;&nbsp;
                            <a class="btn btn-jpt" href="${pageContext.request.contextPath}/Jpt.jsp?id=<c:out value='${home.id}' />">JPT</a>
                        </td>
                        <td>
                            <a class="btn btn-edit" href="${pageContext.request.contextPath}/home/edit?id=<c:out value='${home.id}' />">Edit</a>
                                &nbsp;&nbsp;&nbsp;&nbsp;
                            <a class="btn btn-delete" href="${pageContext.request.contextPath}/home/delete?id=<c:out value='${home.id}' />">Delete</a>
                        </td>
                        </tr>
                        </c:forEach>
                    </tbody>

                </table>
            </div>
        </div>  
  </div>

  <!-- footer of the page-->
  <div class="footer">
    <footer>&copy; Pusat Pengurusan dan Penjaminan Kualiti UMT 2024</footer>
  </div>

  <!-- javascript sources -->
  <script type="text/javascript" src="js/script.js"></script>
  <script type="text/javascript" src="js/script2.js"></script>
</body>
</html>
