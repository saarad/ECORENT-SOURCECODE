
package loginAdm;

import model.DBCleanup;

import java.sql.*;


import static control.Password.*;
/**
 * LoginDb.java
 * @author Team 007
 * @version 1.0
 * Class which is used to authenticate an user trying to login.
 */
public class LoginDb {

    /**
     * Checks if the login credentials are identical to the ones stored in the database. Checks if an users email and password correspond.
     *
     * @param loginBean login credentials
     * @return     if the user credentials are correct
     */
    public static boolean authenticateUser(LoginBean loginBean) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String email = loginBean.getEmail();
        String password = loginBean.getPassword();

        String selectQuery = "SELECT hash, email FROM admin WHERE email = ?";

        String emailDB = "";
        String hashDB = "";

        try {
            connection = DBCleanup.getConnection();

            preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                emailDB = resultSet.getString("email");
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
        finally {
            DBCleanup.closeStatement(preparedStatement);
            DBCleanup.closeResultSet(resultSet);
            DBCleanup.closeConnection(connection);
        }
        return false;
    }
}
