package org.bookmanagement.Controller.Table_Row;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import org.bookmanagement.Bo.BorrowBookService;
import org.bookmanagement.Bo.ServiceFactor;
import org.bookmanagement.Controller.BorrowBookPageFormController;
import org.bookmanagement.Dto.BookDto;

public class BorrowBookRowController {
    public Label Id;
    public Label Title;

    private int index = 0;

    public void setData(String title , int index) {
        Title.setText(title);
        SetId(title);
        this.index = index;
    }

    private final BorrowBookService borrowBookService = (BorrowBookService) ServiceFactor.getBoFactory().getBo(ServiceFactor.BoType.Borrow_Book);

    void SetId(String title){
        BookDto data = borrowBookService.getData(title);
        Id.setText("B0"+data.getId());
    }

    public void DeleteBtnOnActhion(ActionEvent actionEvent) {
        BorrowBookPageFormController.borrowBookPageFormController.borrowRows.remove(Title.getText());
        BorrowBookPageFormController.borrowBookPageFormController.LoadTable();
    }


}
