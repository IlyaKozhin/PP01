package servlet;

import model.User;
import service.UserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/update", name = "UpdateServlet")
public class UpdateServlet extends HttpServlet {
    private UserService userService = UserService.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String string = request.getParameter("update");

        Long id = Long.parseLong(string.substring(3));
        String name = request.getParameter("name" + id);
        String password = request.getParameter("password" + id);
        userService.updateUser(new User(id, name, password));

        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
        response.sendRedirect("users.jsp");
    }
}
