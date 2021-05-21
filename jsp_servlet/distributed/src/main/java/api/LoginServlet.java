package api;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = "/authentication")
public class LoginServlet extends HttpServlet {
    private boolean makeQueryToDB(String username, String password) {
        String databaseUrl = "jdbc:mysql://localhost:3306/distributed?user=" + "guest" + "&password=" + "password";
        Connection conn = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(databaseUrl);
            System.out.println("Connected to database");
            PreparedStatement st = conn.prepareStatement("SELECT * FROM login WHERE username= ? AND password = ?");
            st.setString(1, username);
            st.setString(2, password);
            ResultSet res = st.executeQuery();
            if (res.next()) 
                return true;
            else 
                return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("DO post run");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        
        if (makeQueryToDB(username, password)) {
            resp.addCookie(new Cookie("isLogin", "true"));
            resp.sendRedirect(req.getContextPath() + "/calculator.html");
        } else {
            // resp.sendRedirect(req.getContextPath() + "/index.html");
            req.setAttribute("message", "Wrong username and password");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }
}