package controllers.admin;

import dao.EmployeeDao;
import dao.OrderDao;
import java.beans.PropertyChangeListener;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import utils.IntervalIncrease;
import models.admin.StatisticalView;
import models.Employee;
import models.Order;
import java.util.ArrayList;
import java.text.SimpleDateFormat;

/**
 * Nguyễn Trọng Dũng
 */
public class StatisticalController {

    StatisticalView view;
    EmployeeDao employeeDao = new EmployeeDao();
    OrderDao orderDao = new OrderDao();
    DecimalFormat formatter = new DecimalFormat("###,###,###");

    public StatisticalController() {
    }

    public StatisticalView getView() {
        return view;
    }

    public void setView(StatisticalView view) {
        if (view != this.view) {
            this.view = view;
        }
    }

    public void initData() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date(System.currentTimeMillis()));
        view.getDateChooserEnd().setCalendar(c);
        c.add(Calendar.DATE, -30);
        view.getDateChooserStart().setCalendar(c);
        try {
            ArrayList<Employee> employees = employeeDao.getAll();
            view.setTableData1(employees);
        } catch (Exception e) {
            view.showError(e);
        }

        try {
            // Date start = view.getDateChooserStart().getDate();
            // Date end = view.getDateChooserEnd().getDate();
            // Timestamp start_at = new Timestamp(start.getTime());  
            // Timestamp end_at = new Timestamp(end.getTime());  
            ArrayList<Order> orders = orderDao.getAll();
            view.setTableData2(orders);
        } catch (Exception e) {
            view.showError(e);
        }
    }
}
