package login;

import java.sql.*;


import static control.Password.*;

public class LoginDb {

    private String driver = "com.mysql.jdbc.Driver";
    private String dbName = "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/sandern?user=sandern&password=TUyEYWPb&useSSL=false&autoReconnect=true";


    public boolean authenticateUser(LoginBean loginBean) {
        Connection connection;
        PreparedStatement preparedStatement;
        ResultSet resultSet;


        String email = loginBean.getEmail();
        String password = loginBean.getPassword();
        boolean privileged = loginBean.getPrivileged();

        //String hash = hashPassword(password);

        String selectQuery = "SELECT hash, email FROM admin WHERE email = ?";

        String emailDB = "";
        String hashDB = "";

        try {
            connection = DriverManager.getConnection(dbName);
            Class.forName(driver);

            preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                emailDB = resultSet.getString("username");
                hashDB = resultSet.getString("hash");
            }
            if (email.equals(emailDB) && check(password, hashDB)) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}