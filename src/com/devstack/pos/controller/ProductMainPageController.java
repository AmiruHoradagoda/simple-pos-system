package com.devstack.pos.controller;

import com.devstack.pos.dao.DatabaseAccessCode;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;

import java.sql.SQLException;

public class ProductMainPageController {
    public JFXButton btnSaveUpdate;
    public TextArea txtProductDescription;
    public JFXTextField txtProductCode;

    public void initialize(){
        //load new product id
        loadProductId();
    }

    private void loadProductId() {
        try {
            txtProductCode.setText(String.valueOf(DatabaseAccessCode.getLastProductId()));

        }catch (SQLException | ClassNotFoundException e){
            throw new RuntimeException(e);
        }
    }

    public void btnBackToHomeOnAction(ActionEvent actionEvent) {
    }

    public void btnNewCustomerOnAction(ActionEvent actionEvent) {
    }

    public void btnNewProductOnAction(ActionEvent actionEvent) {
    }
}
