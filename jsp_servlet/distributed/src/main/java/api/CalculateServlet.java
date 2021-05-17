package api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = "/calculate")
public class CalculateServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String math = req.getParameter("input");
        System.out.println(math);
        String[] mathArr = math.split(" ");

        resp.addHeader("Access-Control-Allow-Origin", "*"); // remove CORS policy
        try {
            int result = Integer.parseInt(mathArr[0]);
            for (int i = 1; i < mathArr.length; i += 2) {
                if (mathArr[i].equals("+")) {
                    result += Integer.parseInt(mathArr[i+1]);
                } else if (mathArr[i].equals("-")) {
                    result -= Integer.parseInt(mathArr[i+1]);
                } else if ( mathArr[i].equals("*")) {
                    result *= Integer.parseInt(mathArr[i+1]);
                } else if (mathArr[i].equals("/")) {
                    result /= Integer.parseInt(mathArr[i+1]);
                }
            }
            System.out.println(result);
            resp.getWriter().println(result);
        } catch (Exception e) {
            resp.getWriter().println("Wrong input");
            e.printStackTrace();
        } 
    }
}