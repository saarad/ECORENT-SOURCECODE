package login;

import control.Admin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import changescene.ChangeScene;
import javafx.event.ActionEvent;
import loginAdm.CurrentAdmin;
import loginAdm.LoginBean;
import loginAdm.LoginDb;
import java.security.GeneralSecurityException;
import model.AdminModel;

import java.awt.*;

public class loginController {

    @FXML
    private Button signInBtn;

    @FXML
    private Label incorrectLbl;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    void signIn(ActionEvent event) throws Exception {
        LoginBean loginBean = new LoginBean(usernameField.getText(),passwordField.getText());
        if(LoginDb.authenticateUser(loginBean)){

            AdminModel adminModel = new AdminModel();
            Admin admin = adminModel.getAdmin(loginBean.getEmail());
            CurrentAdmin currentAdmin = CurrentAdmin.getInstance();
            currentAdmin.setAdmin(admin);

            System.out.println("Nice!");
            ChangeScene cs = new ChangeScene();
            cs.setScene(event, "/main/mainView.fxml");

        }else{
            incorrectLbl.setTextFill(Color.web("#ff0000"));
            incorrectLbl.setText("Your password is incorrect!");
            throw new GeneralSecurityException("Feil passord eller email");
        }
    }
}
