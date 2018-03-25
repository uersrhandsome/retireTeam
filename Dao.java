package Com.Dao.Dao;


import Com.Dao.User.User;

import java.sql.*;

public class Dao {

    static Connection connection;
    static PreparedStatement stmt;
    static ResultSet resultSet;

    public Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception ex) {

            System.out.println(ex);
        }
        try {
            String url = "jdbc:mysql://127.0.0.1:3306/user?useSSL=false";
            String user = "root";
            String password = "mysql123456";
            connection = DriverManager.getConnection(url, user, password);

        } catch (Exception ss) {
            System.out.println(ss);

        }


        return connection;


    }


    public User getUser(String userName, String userPassword) {

        User user = new User();

        try {
            String sql = "SELECT * FROM userinformation WHERE user_name LIKE ? AND user_password LIKE ?;";
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, userName);
            stmt.setString(2, userPassword);


            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                String uname = resultSet.getString("user_name");
                String upass = resultSet.getString("user_pass");
                user.setName(uname);
                user.setPassword(upass);

            }
            System.out.println(userName);


        } catch (Exception DD) {
            System.out.println(DD);
        }

        return user;
    }


    public void closeAll() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (connection != null) {
                        connection.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
