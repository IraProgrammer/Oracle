import java.sql.*;
import java.util.Locale;

public class OracleHelper {

    public ResultSet OracleConnector(String sql) throws SQLException {
        Locale.setDefault(Locale.ENGLISH);
        String dbUrl = "jdbc:oracle:thin:@localhost:1521:XE";
        String user = "SYSTEM";
        String password = "Bhbirf1997";

        Connection c = DriverManager.getConnection(dbUrl, user, password);
        Statement s = c.createStatement();
        ResultSet r = s.executeQuery(sql);

        return r;
    }

}
