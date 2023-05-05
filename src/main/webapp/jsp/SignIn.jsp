<%@ page import="java.sql.*" %><%--
  Created by IntelliJ IDEA.
  User: 煖風遲來
  Date: 2023/5/5
  Time: 15:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%

    String Account = request.getParameter("account");
    String Password = request.getParameter("Password");

    Connection connection = null;
    Statement statement = null;
    ResultSet rs = null;

    try {
        // 导入驱动，加载具体的驱动类
        Class.forName("com.mysql.jdbc.Driver");

        //与数据库建立连接
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test01", "root", "root");
        statement = connection.createStatement();
        //发送sql语句，执行
        String sql =
                "select count(*) from user where uAccount = '"+Account+"' and uPassword = '"+Password+"'  ";
        rs = statement.executeQuery(sql);
        //处理结果
        int count = -1;
        if (rs.next()) {
            count = rs.getInt(1);
        }
        if (count > 0) {
            request.getRequestDispatcher("Main.jsp").forward(request, response);
        }else {
            request.getRequestDispatcher("Main.jsp").forward(request, response);
        }
    } catch (ClassNotFoundException | SQLException e) {
        e.printStackTrace();
    } finally {
        try {
            if(rs != null) rs.close();
            if(statement != null) statement.close();
            if(connection != null) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

%>
</html>
