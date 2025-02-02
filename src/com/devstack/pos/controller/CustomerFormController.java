package com.devstack.pos.controller;

import com.devstack.pos.dao.DatabaseAccessCode;
import com.devstack.pos.dto.CustomerDto;
import com.devstack.pos.view.tm.CustomerTm;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class CustomerFormController {
    public JFXTextField txtEmail;
    public JFXTextField txtName;
    public JFXTextField txtContact;
    public JFXTextField txtSalary;
    public JFXButton btnSaveUpdate;

    public TableView<CustomerTm> tbl;

    public TableColumn colId;
    public TableColumn colEmail;
    public TableColumn colName;
    public TableColumn colContact;
    public TableColumn colSalary;
    public TableColumn colOperate;
    public TextField txtSearch;
    public AnchorPane context;

    private String searchText = "";

    public void initialize() throws SQLException, ClassNotFoundException {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colOperate.setCellValueFactory(new PropertyValueFactory<>("deleteButton"));

        loadAllCustomers(searchText);

        tbl.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                setData(newValue);
            }
        });
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            searchText = newValue;

            try {
                loadAllCustomers(searchText);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException();
            }


        });
    }

    private void setData(CustomerTm newValue) {
        txtEmail.setEditable(false);
        btnSaveUpdate.setText("Update Customer");
        txtEmail.setText(newValue.getEmail()); // Email is not allow updating (future porous added)
        txtName.setText(newValue.getName());
        txtSalary.setText(String.valueOf(newValue.getSalary())); //String.valueOf is convert String and return
        txtContact.setText(newValue.getContact());
    }

    private void loadAllCustomers(String searchText) throws SQLException, ClassNotFoundException {
        ObservableList<CustomerTm> observableList = FXCollections.observableArrayList();
        int counter = 1;

        for (CustomerDto dto : searchText.length() > 0 ? DatabaseAccessCode.searchCustomers(searchText) :
                DatabaseAccessCode.findAllCustomers()) {
            Button btn = new Button("Delete");
            CustomerTm tm = new CustomerTm(
                    counter, dto.getEmail(), dto.getName(), dto.getContact(), dto.getSalary(), btn
            );
            observableList.add(tm);
            counter++;

            btn.setOnAction((e) -> {
                try {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure ?", ButtonType.YES, ButtonType.NO);
                    Optional<ButtonType> selectedButtonType = alert.showAndWait();
                    if (selectedButtonType.get().equals(ButtonType.YES)) {
                        DatabaseAccessCode.deleteCustomer(
                                dto.getEmail()

                        );
                        new Alert(Alert.AlertType.CONFIRMATION, "Customer Deleted!").show();
                        loadAllCustomers(searchText);
                    } else {
                        new Alert(Alert.AlertType.WARNING, "Tri Again!").show();
                    }
                } catch (ClassNotFoundException | SQLException exception) {
                    exception.printStackTrace();
                    new Alert(Alert.AlertType.ERROR,exception.getMessage()).show();
                }

            });
        }
        tbl.setItems(observableList);
    }

    public void btnSaveUpdateOnAction(ActionEvent actionEvent) {
        try {
            if (btnSaveUpdate.getText().equals("Save Customer")) {
                if (
                        DatabaseAccessCode.createCustomer(
                                txtEmail.getText(),
                                txtName.getText(),
                                txtContact.getText(),
                                Double.parseDouble(txtSalary.getText())
                        )
                ) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Customer Saved!").show();
                    clearFields();
                    loadAllCustomers(searchText);
                } else {
                    new Alert(Alert.AlertType.WARNING, "Tri Again!").show();
                }
            } else {
                if (
                        DatabaseAccessCode.updateCustomer(
                                txtEmail.getText(),
                                txtName.getText(),
                                txtContact.getText(),
                                Double.parseDouble(txtSalary.getText())
                        )
                ) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Customer Updated!").show();
                    clearFields();
                    loadAllCustomers(searchText);
                    txtEmail.setEditable(true);
                    btnSaveUpdate.setText("Save Customer");
                } else {
                    new Alert(Alert.AlertType.WARNING, "Tri Again!").show();
                }
            }


        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.WARNING, "Tri Again!").show();

        }

    }

    private void clearFields() {
        txtEmail.clear();
        txtName.clear();
        txtContact.clear();
        txtSalary.clear();
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

    public void btnNewCustomerOnAction(ActionEvent actionEvent) {
        txtEmail.setEditable(true);
        btnSaveUpdate.setText("Save Customer");
        tbl.getSelectionModel().clearSelection();
        clearFields();
    }
}
