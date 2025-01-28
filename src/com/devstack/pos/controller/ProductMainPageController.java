package com.devstack.pos.controller;

import com.devstack.pos.bo.BoFactory;
import com.devstack.pos.bo.custom.ProductBo;
import com.devstack.pos.dto.ProductDto;
import com.devstack.pos.enums.BoType;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class ProductMainPageController {
    public JFXButton btnSaveUpdate;
    public TextArea txtProductDescription;
    public JFXTextField txtProductCode;
    public AnchorPane context;
    public String searchText = "";

    ProductBo productBo = BoFactory.getInstance().getDao(BoType.PRODUCT);

    public void initialize() {
        //load new product id
        loadProductId();
    }

    private void loadProductId() {
        try {
            txtProductCode.setText(String.valueOf(productBo.getLastProductId()));

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnSaveProductOnAction(ActionEvent actionEvent) {
        try {
            if (btnSaveUpdate.getText().equals("Save Product")) {
                if (
                        productBo.saveProduct(
                                new ProductDto(
                                        Integer.parseInt(txtProductCode.getText()),
                                        txtProductDescription.getText()
                                )
                        )
                ) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Products Saved!").show();
                    clearFields();
                    loadAllProducts(searchText);
                } else {
                    new Alert(Alert.AlertType.WARNING, "Tri Again!").show();
                }
            } else {
                if (
                        productBo.saveProduct(
                                new ProductDto(
                                        Integer.parseInt(txtProductCode.getText()),
                                        txtProductDescription.getText()
                                )
                        )
                ) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Product Updated!").show();
                    clearFields();
                    loadAllProducts(searchText);
                    btnSaveUpdate.setText("Save Product");
                } else {
                    new Alert(Alert.AlertType.WARNING, "Tri Again!").show();
                }
            }


        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.WARNING, "Tri Again!").show();

        }
    }
    public void btnNewProductOnAction(ActionEvent actionEvent) {
    }
    private void loadAllProducts(String searchText) {
    }



    public void btnBackToHomeOnAction(ActionEvent actionEvent) throws IOException {
        setUI("DashboardForm");
    }
    private void setUI(String url) throws IOException {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(
                new Scene(FXMLLoader.load(getClass().getResource("../view/" + url + ".fxml")))
        );
        stage.centerOnScreen();
    }
    private void clearFields() {
        txtProductDescription.clear();
        txtProductCode.clear();
        loadProductId();
    }


    public void btnNewBatchOnAction(ActionEvent actionEvent) {
    }
}
