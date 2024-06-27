<%-- 
    Document   : JptList
    Created on : 27 Jun 2024, 6:57:00 am
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
    /* Styles for Edit and Delete buttons */
    .edit-button, .delete-button, .add-button {
      color: white;
      border: none;
      padding: 5px 10px;
      cursor: pointer;
      text-decoration: none;
      display: inline-block;
    }
    .edit-button {
      background-color: #007bff; /* Blue color for edit button */
      margin-right: 5px; /* Add some spacing */
    }
    .delete-button {
      background-color: #dc3545; /* Red color for delete button */
    }
    .add-button {
      background-color: #28a745; /* Green color for add button */
      margin-bottom: 10px; /* Add some spacing below */
    }
    .actions {
      display: flex;
      align-items: center;
    }
    .delete-form {
      display: inline;
      margin: 0;
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

  <!-- Main content of the web page starts here -->
  <div class="content">
    <div class="table-container">
      <br>
      <center><h2>Jpt List</h2>
      <button style="background-color: #00cc00; padding: 10px; border-radius: 3px; border: 0px">
                    <a href="<%=request.getContextPath()%>/Jpt.jsp" style="display: inline-block; width: 100%; height: 100%; text-decoration: none; color: white;">
                    Add New JPT Document
                    </a></button>
      </center>
                    <br>
                    <br>
      <table border="1" width="100%">
        <thead>
          <tr>
            <th>Doc ID</th>
            <th>Doc Type</th>
            <th>File Name</th>
            <th>Date</th>
            <th>Person In Charge</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="jpt" items="${jptList}">
            <tr>
              <td><c:out value="${jpt.docid}" /></td>
              <td><c:out value="${jpt.doctype}" /></td>
              <td><a href="${pageContext.request.contextPath}/jpt/download?docid=${jpt.docid}"><c:out value="${jpt.filename}" /></a></td>
              <td><c:out value="${jpt.date}" /></td>
              <td><c:out value="${jpt.pic}" /></td>
              <td class="actions">
                <a href="${pageContext.request.contextPath}/jpt/edit?docid=${jpt.docid}" class="edit-button">Edit</a> 
                <form action="${pageContext.request.contextPath}/jpt/delete" method="post" class="delete-form">
                  <input type="hidden" name="docid" value="${jpt.docid}">
                  <input type="submit" value="Delete" class="delete-button">
                </form>
              </td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
      <br>
    </div>
        <body>
    <div class="container">
        <table id="sessionTable">
            <thead>
                <tr>
                    <th>AccessTime</th>
                </tr>
            </thead>
            <tbody>
                <!-- Rows will be added here by JavaScript -->
            </tbody>
        </table>
    </div>
    <script>
        // Function to get the current timestamp
        function getCurrentTimestamp() {
            const now = new Date();
            return now.toLocaleString(); // Returns the date in a readable format (e.g., 6/27/2024, 10:00:00 AM)
        }

        // Function to add a new row to the table
        function addTimestampRow() {
            const tableBody = document.querySelector('#sessionTable tbody');
            const newRow = document.createElement('tr');

            const timestampCell = document.createElement('td');
            timestampCell.textContent = getCurrentTimestamp();

            newRow.appendChild(timestampCell);
            tableBody.appendChild(newRow);
        }

        // Function to periodically add a new timestamp row
        function startAddingTimestamps(interval) {
            addTimestampRow(); // Add the initial timestamp immediately
            setInterval(addTimestampRow, interval);
        }

        // Start adding timestamps when the page loads
        window.onload = function() {
            startAddingTimestamps(600000); // Add a new row every 10 minutes (600,000 milliseconds)
        };
    </script>
</body>
  </div>

  <!-- Footer of the page -->
  <div class="footer">
    <footer>&copy; Pusat Pengurusan dan Penjaminan Kualiti UMT 2024</footer>
  </div>

  <!-- Javascript sources -->
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/script.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/script2.js"></script>
</body>
</html>
