package servlet;

import service.UserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/delete", name = "DelServlet")
public class DelServlet extends HttpServlet {

    private UserService userService = UserService.getInstance();

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String string = request.getParameter("update");

        Long id = Long.parseLong(string.substring(3));
        userService.deleteUser(id);

        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
        response.sendRedirect("users.jsp");
    }
}
