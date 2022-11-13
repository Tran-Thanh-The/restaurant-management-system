package controllers.admin;

import dao.StatisticalDao;
import java.beans.PropertyChangeListener;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import utils.Debouncer;
import utils.IntervalIncrease;
import models.admin.StatisticalView;

/**
 * Nguyễn Trọng Dũng
 */
public class StatisticalController {

    StatisticalView view;
    StatisticalDao statisticalDao = new StatisticalDao();
    DecimalFormat formatter = new DecimalFormat("###,###,###");
    Debouncer debouncer = new Debouncer();

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
    }
}
