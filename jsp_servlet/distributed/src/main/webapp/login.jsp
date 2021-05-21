<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false" %>

<!DOCTYPE html>
<html>
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"
    />
    <link rel="stylesheet" href="login.css" />
  </head>
  <body>
    <div class="container">
      <form action="/distributed/authentication" method="POST">
        <div class="row">
          <h2 style="text-align: center">Login</h2>

          <div class="col">
            <div class="hide-md-lg">
              <p>Or sign in manually:</p>
            </div>
              <input
                type="text"
                name="username"
                placeholder="Username"
                required
              />
              <input
                type="password"
                name="password"
                placeholder="Password"
                required
              />
              <input type="submit" value="Login" />
              <% String error = (String) request.getAttribute("message");
                if (error != null) {
                %>
                  <p style="color: red; margin: 0px auto 0px auto"><%=error%></p>
                <%
                }
              %>
          </div>
        </div>
      </form>
    </div>
  </body>
</html>

