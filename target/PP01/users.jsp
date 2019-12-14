<%@ page import="service.UserService" %>
<%@ page import="model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="org.hibernate.exception.SQLGrammarException" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User list</title>
</head>
<body>
<form>
    <fieldset>
        <legend>User list</legend>
        <% UserService userService = UserService.getInstance();
            StringBuilder builder = new StringBuilder();
            List<User> list=userService.getAllUsers();
            for (User user : list) {
                Long id = user.getId();
                builder
                        .append("<p><label>ID: <em>*</em></label><input type=\"text\" id=\"id")
                        .append(id)
                        .append("\"  value=\"")
                        .append(id)
                        .append("\" readonly>\n")
                        .append("<label>Name</label><input type=\"text\" name=\"name")
                        .append(id)
                        .append("\"  value=\"")
                        .append(user.getName())
                        .append("\">\n")
                        .append("<label>Password</label><input type=\"password\" name=\"password")
                        .append(id)
                        .append("\"  value=\"")
                        .append(user.getPassword())
                        .append("\"><button formaction=\"/delete \" formmethod=\"post\" name=\"update\" value=\"del")
                        .append(id)
                        .append("\">Delete</button>\n<button formaction=\"/update \" formmethod=\"post\" name=\"update\" value=\"upd")
                        .append(id)
                        .append("\">Update</button>")
                        .append("</p>");
            }
        %>
        <%= builder%>
       </fieldset>
    <p>
        <label>Name</label><input type="text" name="nameToAdd">
        <label>password</label><input type="password" name="passwordToAdd">
        <button formaction="/add" formmethod="post" name="update" value="addUser">Add User</button>
    </p>
</form>
<%= new java.util.Date() %>

</body>
</html>
