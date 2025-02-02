package com.devstack.pos.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class DashboardFormController {
    @FXML
    private AnchorPane context;

    public void btnCustomerOnAction(ActionEvent actionEvent) throws IOException {
        setUI("CustomerForm");
    }

    public void btnProductOnAction(ActionEvent actionEvent) throws IOException {
        setUI("ProductMainPage");
    }

    public void btnPlaceOrderOnAction(ActionEvent actionEvent) throws IOException {
        setUI("");
    }

    public void btnOrderDetailsOnAction(ActionEvent actionEvent) throws IOException {
        setUI("");
    }

    public void btnIncomeReportOnAction(ActionEvent actionEvent) throws IOException {
        setUI("");
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
