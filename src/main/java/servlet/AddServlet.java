package servlet;

import model.User;
import service.UserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/add", name = "AddServlet")
public class AddServlet extends HttpServlet {
    private UserService userService = UserService.getInstance();

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        String name = request.getParameter("nameToAdd");
        String password = request.getParameter("passwordToAdd");
        userService.addUser(new User(name, password));

        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
        response.sendRedirect("users.jsp");
    }
}
