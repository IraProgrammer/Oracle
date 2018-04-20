import java.sql.ResultSet;
import java.sql.SQLException;

public class Data {

    public String[][] getRows(String sql){
        ResultSet resSet;
        OracleHelper oracleHelper = new OracleHelper();
        String[][] data = {};

        try {

            resSet = oracleHelper.OracleConnector(sql);
            data = new String[resSet.getFetchSize()][resSet.getMetaData().getColumnCount()];
            int i = 0;

            while (resSet.next()) {

                for (int j = 0; j < resSet.getMetaData().getColumnCount(); j++) {
                    data[i][j] = resSet.getString(j+1);
                }
                i++;
            }
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return data;
    }
}
