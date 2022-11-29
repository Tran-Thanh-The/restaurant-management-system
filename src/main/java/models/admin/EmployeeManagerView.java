package models.admin;

import javax.swing.DefaultComboBoxModel;
import models.Employee;

/**
 * @author Nguyen Trung Kien
 */
public class EmployeeManagerView extends ManagerPaneView<Employee> {

    String[] list = {"ID", "Name", "phoneNumber"};

    public EmployeeManagerView() {
        super();
        setTableModel();
        renderTable();
    }

    @Override
    public void setTableModel() {
        tableModel.addColumn("ID");
        tableModel.addColumn("Họ và tên");
        tableModel.addColumn("Tên tài khoản");
        tableModel.addColumn("Mật khẩu");
        tableModel.addColumn("Số điện thoại");
        tableModel.addColumn("Chức vụ");
        this.getCboSearchField().setModel(new DefaultComboBoxModel(list));
    }
}
