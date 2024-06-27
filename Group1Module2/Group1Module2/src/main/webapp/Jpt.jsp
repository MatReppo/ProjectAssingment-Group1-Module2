<%-- 
    Document   : Jpt
    Created on : 25 Jun 2024, 10:17:53 pm
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
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/formStyle.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  <title>UMT Academic Program Development Tracking System</title>
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

  <!-- Main content of the web page starts here -->
  <div class="content">
    <center><h2>JPT Application</h2>
    <button style="background-color: #00cc00; padding: 10px; border-radius: 3px; border: 0px">
                    <a href="<%=request.getContextPath()%>/Home.jsp" style="display: inline-block; width: 100%; height: 100%; text-decoration: none; color: white;">
                    Add New Course
                    </a></button>
    </center>
    <form id="JPT" action="${pageContext.request.contextPath}/jpt/${jpt != null ? 'update' : 'insert'}" method="post" class="application-form" enctype="multipart/form-data">
      <input type="hidden" name="docid" value="${jpt != null ? jpt.docid : ''}" />
      <label for="doctype">Choose an item:</label>
      <select id="doctype" name="doctype">
        <option value="">-Pilih Borang-</option>
        <option value="borang1" ${jpt != null && jpt.doctype == 'borang1' ? 'selected' : ''}>Borang 1</option>
        <option value="borang2" ${jpt != null && jpt.doctype == 'borang2' ? 'selected' : ''}>Borang 2</option>
        <option value="borang3" ${jpt != null && jpt.doctype == 'borang3' ? 'selected' : ''}>Borang 3</option>
        <option value="borang4" ${jpt != null && jpt.doctype == 'borang4' ? 'selected' : ''}>Borang 4</option>
        <option value="borang5" ${jpt != null && jpt.doctype == 'borang5' ? 'selected' : ''}>Borang 5</option>
      </select>

      <label for="pic">Choose a picture:</label>
      <select id="pic" name="pic">
        <option value="" selected="selected">-Pilih PIC-</option>
        <option value="Team 1" ${jpt != null && jpt.pic == 'Team 1' ? 'selected' : ''}>Team 1</option>
        <option value="Team 2" ${jpt != null && jpt.pic == 'Team 2' ? 'selected' : ''}>Team 2</option>
        <option value="Team 3" ${jpt != null && jpt.pic == 'Team 3' ? 'selected' : ''}>Team 3</option>
        <option value="Team 4" ${jpt != null && jpt.pic == 'Team 4' ? 'selected' : ''}>Team 4</option>
        <option value="Team 5" ${jpt != null && jpt.pic == 'Team 5' ? 'selected' : ''}>Team 5</option>
      </select>

      <label for="file">Choose a file:</label>
      <input type="file" id="file" name="file" ${jpt != null ? '' : 'required'}>

      <label for="date">Choose a date:</label>
      <input type="date" id="date" name="date" value="${jpt != null ? jpt.date : ''}">

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

  <!-- Javascript sources -->
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/script.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/script2.js"></script>
  <script>
    function cancelEdit() {
      window.location.href = '${pageContext.request.contextPath}/jpt/list';
    }
  </script>
</body>
</html>
