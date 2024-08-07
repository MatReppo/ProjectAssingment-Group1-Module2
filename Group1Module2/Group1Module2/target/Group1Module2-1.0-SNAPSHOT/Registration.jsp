<%-- 
    Document   : Registration
    Created on : 26 Jun 2024, 6:22:20 pm
    Author     : USER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registration Page</title>
    <style>
        body {
    font-family: Arial, sans-serif;
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
    background-color: #f0f0f0;
}

.form-container {
    background: #fff;
    padding: 20px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    border-radius: 8px;
    width: 300px;
}

.form-container h1 {
    margin-bottom: 20px;
}

.form-container input {
    width: calc(100% - 20px);
    padding: 10px;
    margin: 10px 0;
    border: 1px solid #ccc;
    border-radius: 4px;
}

.show-password {
    display: flex;
    align-items: center;
    cursor: pointer;
    font-family: Arial, sans-serif;
    font-size: 16px;
    color: #333;
}

.show-password input[type="checkbox"] {
    display: none;
}

.checkbox-custom {
    width: 20px;
    height: 20px;
    border: 2px solid #333;
    border-radius: 3px;
    display: inline-block;
    position: relative;
    margin-right: 10px;
    transition: background-color 0.3s ease;
}

.checkbox-custom::after {
    content: '';
    position: absolute;
    top: 50%;
    left: 50%;
    width: 12px;
    height: 12px;
    background-color: #333;
    transform: translate(-50%, -50%) scale(0);
    transition: transform 0.2s ease;
}

.show-password input[type="checkbox"]:checked + .checkbox-custom::after {
    transform: translate(-50%, -50%) scale(1);
}

.submit-btn {
    display: flex;
    justify-content: center;
    gap: 10px;
    padding: 10px 20px;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    background: #28a745;
    color: white;
}

.submit-btn:hover {
    background: #218838;
}
    </style>
</head>
<body>
    <div class="form-container">
        <h1>User Registration</h1>
        <form action="register" method="post">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required>
            
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required>
            
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>  
            
            <label class="show-password">
            <input type="checkbox" id="show-password-checkbox" onclick="togglePasswordVisibility()">
                <span class="checkbox-custom"></span> Show Password
            </label>
            
            <input class="submit-btn" type="submit" value="Register">
        </form>
    </div>
    <script>
        function submitForm() {
    const username = document.getElementById('username').value;
    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;

    if (!username || !email || !password1) {
        alert('Please fill out all fields.');
        return;
    }

    // Simulate registration process
    alert('Registration successful!');
}

function togglePasswordVisibility() {
    const passwordField = document.getElementById('password');
    if (passwordField.type === 'password') {
        passwordField.type = 'text';
    } else {
        passwordField.type = 'password';
    }
}
    </script>
</body>
</html>
