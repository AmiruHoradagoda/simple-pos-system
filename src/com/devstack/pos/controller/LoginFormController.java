package com.devstack.pos.controller;

import com.devstack.pos.bo.BoFactory;
import com.devstack.pos.bo.custom.UserBo;
import com.devstack.pos.dto.UserDto;
import com.devstack.pos.enums.BoType;
import com.devstack.pos.util.PasswordManager;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class LoginFormController {
    @FXML
    private JFXTextField txtEmail;
    @FXML
    private JFXTextField txtPassword;

    @FXML
    private AnchorPane context;

    UserBo userBo = BoFactory.getInstance().getDao(BoType.USER);

    public void btnSignOnAction(ActionEvent actionEvent) {
        try {
            UserDto userDto = userBo.findUser(txtEmail.getText());

            if (userDto != null) {
                if (PasswordManager.cheakPassword(txtPassword.getText(), userDto.getPassword())) {
                    setUI("DashboardForm");
                } else {
                    new Alert(Alert.AlertType.WARNING, "check your password and try again !").show();
                }
            } else {
                new Alert(Alert.AlertType.WARNING, "User Email not found !").show();
            }

        } catch (ClassNotFoundException | SQLException | IOException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnCreateAnAccountOnAction(ActionEvent actionEvent) throws IOException {
        setUI("SignUpForm");
    }

    private void setUI(String url) throws IOException {
        if (context != null) {
            Stage stage = (Stage) context.getScene().getWindow();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/" + url + ".fxml"))));
            stage.centerOnScreen();
        } else {
            System.err.println("Context is not initialized.");
        }
    }
}
