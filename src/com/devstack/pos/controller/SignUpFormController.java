package com.devstack.pos.controller;

import com.devstack.pos.dao.DatabaseAccessCode;
import com.devstack.pos.util.PasswordManager;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.mysql.cj.jdbc.Driver;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SignUpFormController {
    @FXML
    private JFXTextField txtEmail;
    @FXML
    private JFXTextField txtPassword;
    @FXML
    private AnchorPane context;

    public void btnRegisterNowOnAction(ActionEvent actionEvent) {
        try {

            if (DatabaseAccessCode.createUser(
                    txtEmail.getText(),
                    txtPassword.getText())
            ) {
                new Alert(Alert.AlertType.CONFIRMATION, "User Saved!").show();
                clearFields();
            } else {
                new Alert(Alert.AlertType.WARNING, "Tri Again!").show();
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    private void clearFields() {
        txtEmail.clear();
        txtPassword.clear();
    }

    public void btnAlreadyHaveAnAccountOnAction(ActionEvent actionEvent) throws IOException {
        setUI("LoginForm");
    }

    private void setUI(String url) throws IOException {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(
                new Scene(FXMLLoader.load(getClass().getResource("../view/" + url + ".fxml")))
        );
        stage.centerOnScreen();
    }
}
