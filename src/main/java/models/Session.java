package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 *
 * @author Nguyen Trung Kien
 */
public class Session {

    private int id, idEmployee;
    private Employee employee;
    private String message;

    public Session() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
        if (employee != null) {
            this.idEmployee = employee.getId();
        }
    }

    public int getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(int idEmployee) {
        this.idEmployee = idEmployee;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static Session getFromResultSet(ResultSet rs) throws SQLException {
        Session s = new Session();
        s.setId(rs.getInt("id"));
        s.setIdEmployee(rs.getInt("idEmployee"));
        s.setMessage(rs.getNString("message"));
        return s;
    }

    @Override
    public String toString() {
        return "Session{" + "id=" + id + ", idEmployee=" + idEmployee + ", message=" + message + '}';
    }
}
