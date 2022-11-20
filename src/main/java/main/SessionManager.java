package main;

import dao.SessionDao;
import java.sql.SQLException;
import models.Employee;
import models.Session;

/**
 * @author Tran Thanh The
 */
public class SessionManager {

    public static Session session;
    static SessionDao sessionDao = new SessionDao();

    public SessionManager() {
    }

    public static Session getSession() {
        return session;
    }

    public static void setSession(Session session) {
        SessionManager.session = session;
    }

    public static void create(Employee e) throws SQLException {
        if (e == null) {
            throw new SQLException("Nguoi dung khong hop le!");
        }
        Session ss = new Session();
        ss.setEmployee(e);
        ss.setMessage("login");
        ss.setIdEmployee(e.getId());
        sessionDao.save(ss);
        setSession(ss);
    }

    public static void update() throws SQLException {
        if (session == null) {
            throw new SQLException("Ban chua dang nhap!");
        }
        sessionDao.update(session);
        setSession(null);
    }

}
