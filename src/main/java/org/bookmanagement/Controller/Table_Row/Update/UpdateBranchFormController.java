package org.bookmanagement.Controller.Table_Row.Update;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import org.bookmanagement.Bo.ManageBookService;
import org.bookmanagement.Bo.ServiceFactor;
import org.bookmanagement.Bo.BranchService;
import org.bookmanagement.Dto.BookDto;
import org.bookmanagement.Dto.BranchDto;
import org.bookmanagement.util.Validation;

public class UpdateBranchFormController {
    @FXML
    private TextField email;

    @FXML
    private TextField locationtext;

    @FXML
    private TextField name;

    BranchDto branchDto;

    public void setData(BranchDto branchDto){
        name.setText(branchDto.getName());
        locationtext.setText(branchDto.getLocation());
        email.setText(branchDto.getEmail());
        this.branchDto = branchDto;
    }

    BranchService branchService = (BranchService) ServiceFactor.getBoFactory().getBo(ServiceFactor.BoType.Branch);

    @FXML
    void updateBtnOnActhion(ActionEvent event) {
        if (Validation.isValidName(name.getText()) && Validation.isValidAddress(locationtext.getText()) && Validation.isValidEmail(email.getText())) {
            branchService.update(new BranchDto(branchDto.getId(), name.getText(), locationtext.getText(), email.getText()));
            new Alert(Alert.AlertType.CONFIRMATION, "Updated").show();

        } else {
            new Alert(Alert.AlertType.ERROR, "Invalid Input").show();
        }

    }}
