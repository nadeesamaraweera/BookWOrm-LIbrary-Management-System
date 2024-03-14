package org.bookmanagement.Controller.AdminController;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import org.bookmanagement.Bo.ServiceFactor;
import org.bookmanagement.Bo.UserMangeService;
import org.bookmanagement.Controller.Table_Row.UserRowFromController;

import java.io.IOException;

public class UserManageFromController {
    public VBox TableBox;

    UserMangeService userMangeBo = (UserMangeService) ServiceFactor.getBoFactory().getBo(ServiceFactor.BoType.UserMange);
    public void initialize() {
        TableBox.getChildren().clear();
        userMangeBo.getAll().forEach(MemberDto -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Forms/Table_Row/View/UserRow.fxml"));
                Parent root = fxmlLoader.load();
                UserRowFromController controller = fxmlLoader.getController();
                controller.setData(MemberDto);
                TableBox.getChildren().add(root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void AddUserOnActhion(ActionEvent actionEvent) {

    }

    public void UpdateuserOnActhion(ActionEvent actionEvent) {

    }
}
