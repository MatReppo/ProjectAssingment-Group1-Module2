<%-- 
    Document   : Home
    Created on : 25 Jun 2024, 10:15:42 pm
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
        .form-container {
            margin-top: 300px; /* Adjust as needed to avoid overlapping with the navbar */
            width: 300px;
            margin: 0 auto;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 10px;
            background-color: #f9f9f9;
        }

        .form-container label, 
        .form-container input, 
        .form-container select {
            display: block;
            width: 100%;
            margin-bottom: 10px;
        }

        .form-container button {
            width: 48%;
            padding: 10px;
            margin-top: 10px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        .form-container .submit-btn {
            background-color: #4CAF50;
            color: white;
        }

        .form-container .cancel-btn {
            background-color: #f44336;
            color: white;
        }

        .form-container .button-group {
            display: flex;
            justify-content: space-between;
        }
    </style>
</head>

<body>
  <!-- This is the header of the page-->
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
      <li><a href="#home">Home</a></li>
      <li><a href="#list">Program List</a></li>
      <li><a href="#contact">Contact</a></li>
      <li class="dropdown">
        <a href="javascript:void(0)" class="dropbtn">Manage Application</a>
        <div class="dropdown-content">
          <a href="#">Internal Application</a>
          <a href="javascript:void(0)" class="subdropbtn">Provisional Accreditation ></a>
            <div class="sub-dropdown-content">
              <a href="#">MQA-01</a>
              <a href="#">JPT</a>
            </div>
          <a href="#">Program Offering</a>
          <a href="#">Full Accreditation</a>
          <a href="#">MQA Certification</a>
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
  <br>
  <br>
  <br>
  <br>

  <!-- Main content of the web page -->
  <div class="form-container">
    <form id="sampleForm" action="${pageContext.request.contextPath}/home/${home != null ? 'update' : 'insert'}" method="post">
        <h1>${home != null ? 'Edit Course Registration' : 'New Course Registration'}</h1>

        <c:if test="${home != null}">
            <input type="hidden" id="id" name="id" value="${home.id}">
        </c:if>

        <label for="name">Name:</label>
        <input type="text" id="name" name="ncr" value="${home != null ? home.ncr : ''}" required>

        <label for="id">ID:</label>
        <input type="text" id="courseid" name="courseid" value="${home != null ? home.courseid : ''}" required>

        <label for="pic">Person In Charge:</label>
        <select name="pic" id="pic">
            <option value="" ${home == null ? 'selected="selected"' : ''}>Select PIC</option>
            <option value="Team 1" ${home != null && home.pic == 'Team 1' ? 'selected="selected"' : ''}>Team 1</option>
            <option value="Team 2" ${home != null && home.pic == 'Team 2' ? 'selected="selected"' : ''}>Team 2</option>
            <option value="Team 3" ${home != null && home.pic == 'Team 3' ? 'selected="selected"' : ''}>Team 3</option>
            <option value="Team 4" ${home != null && home.pic == 'Team 4' ? 'selected="selected"' : ''}>Team 4</option>
            <option value="Team 5" ${home != null && home.pic == 'Team 5' ? 'selected="selected"' : ''}>Team 5</option>
        </select>

        <label for="date">Date:</label>
        <input type="date" id="date" name="date" value="${home != null ? home.date : ''}" required>

        <div class="button-group">
            <button type="submit" class="submit-btn">Submit</button>
            <button type="button" class="cancel-btn" onclick="cancelEdit()">Cancel</button>
        </div>
    </form>
</div>

  <!-- Footer of the page -->
  <div class="footer">
    <footer>&copy; Pusat Pengurusan dan Penjaminan Kualiti UMT 2024</footer>
  </div>

  <!-- JavaScript sources -->
  <script>
      function submitForm() {
    alert('Form submitted successfully!');
    window.location.href = 'http://localhost:8080/Group1Module2/List.jsp'; // Redirect to another page
}
function cancelEdit() {
    window.location.href = "${pageContext.request.contextPath}/home/list";
}
  </script>
  <script type="text/javascript" src="js/script.js"></script>
  <script type="text/javascript" src="js/script2.js"></script>

</body>
</html>

