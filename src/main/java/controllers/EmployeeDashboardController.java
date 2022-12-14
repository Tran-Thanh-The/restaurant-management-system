package controllers;

import controllers.admin.OrderManagerController;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import main.SessionManager;
import models.Employee;
import utils.IconManager;
import views.EmployeeDashboardView;
import views.LoginView;
import models.admin.AboutView;
import models.admin.ManagerPaneView;
import models.admin.MenuItem;
import models.admin.OrderManagerView;

/**
 * Nguyễn Trọng Dũng
 */
public class EmployeeDashboardController {

    private EmployeeDashboardView view;
    ManagerController orderManagerController = new OrderManagerController();
    ManagerPaneView orderManagerView = new OrderManagerView();
    AboutView aboutView = new AboutView();

    SideBarController sideBarController = new SideBarController();
    JPanel[] cards = { orderManagerView, aboutView};

    public EmployeeDashboardController(EmployeeDashboardView view) {
        this.view = view;
        sideBarController.setPanelSideBar(view.getPanelSideBar());
        view.setVisible(true);
        initMenu();
        addEvent();
        Employee session = SessionManager.getSession().getEmployee();
        if (session != null) {
            view.getLbName().setText(session.getName());
        }
        view.setCards(cards);
        view.setPanel(orderManagerView);
        orderManagerController.setView(orderManagerView);
        orderManagerController.updateData();
    }

    public EmployeeDashboardView getView() {
        return view;
    }

    public void setView(EmployeeDashboardView view) {
        this.view = view;
        sideBarController.setPanelSideBar(view.getPanelSideBar());
    }

    private void initMenu() {
        IconManager im = new IconManager();
        MenuItem menuQLDDH = new MenuItem("QLDDH", im.getIcon("purchase_order_25px.png"), "Quản lý đơn đặt hàng");
        MenuItem menuTT = new MenuItem("TT", im.getIcon("help_25px.png"), "About us");
        sideBarController.addMenu(menuQLDDH, menuTT);
        sideBarController.addMenuEvent(this::onMenuChange);
    }

    private void addEvent() {
        view.getBtnLogout().addActionListener(evt -> {
            int confirm = JOptionPane.showConfirmDialog(view, "Bạn thực sự muốn đăng xuất?");
            if (confirm != JOptionPane.YES_OPTION) {
                return;
            }
            try {
                SessionManager.update();// Đẵng xuất
            } catch (SQLException ex) {
                view.showError(ex);
            }
            view.dispose();
            new LoginController(new LoginView());
        });
    }

    public void onMenuChange(MenuItem item) {
        switch (item.getId()) {
            case "TT":
                view.setPanel(aboutView);
                break;
            default:
                view.setPanel(orderManagerView);
                orderManagerController.setView(orderManagerView);
                orderManagerController.updateData();
        }
    }
}
