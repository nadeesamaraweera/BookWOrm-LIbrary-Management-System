package org.bookmanagement.Controller.Table_Row.Save;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import org.bookmanagement.Bo.ServiceFactor;
import org.bookmanagement.Bo.UserMangeService;
import org.bookmanagement.Dto.BranchDto;

public class SaveUserFormController {
    @FXML
    private TextField email;

    @FXML
    private TextField name;

    @FXML
    private TextField password;

    @FXML
    private TextField username;

    UserMangeService userMangeBo = (UserMangeService) ServiceFactor.getBoFactory().getBo(ServiceFactor.BoType.UserMange);
    @FXML
    void saveBtnOnActhion(ActionEvent event) {
//      int id = userMangeBo.save(new AdminDto(0, name.getText(), username.getText(), password.getText(), email.getText()));
//
//      if(id > 0){
//          new Alert(Alert.AlertType.CONFIRMATION, "User Save").show();
//        }else {
//          new Alert(Alert.AlertType.ERROR, "User Save Fail").show();
//      }


    }

}
