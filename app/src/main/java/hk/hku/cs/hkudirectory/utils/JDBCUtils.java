package hk.hku.cs.hkudirectory.utils;

import java.sql.Connection;
import java.sql.DriverManager;

// Refactor database connection
public class JDBCUtils {
    private static final String TAG = "mysql-hkudirectory-JDBCUtils";
    private static String driver = "com.mysql.jdbc.Driver";
    // private static String url = "jdbc:mysql://nuc.hkumars.potatoma.com:3306/comp7506?useSSL=false&allowPublicKeyRetrieval=true";
    private static String user = "potatoma";
    private static String password = "potatoma123";

    public static Connection getConn() {
        Connection connection = null;
        try {
            Class.forName(driver);
            String url = "jdbc:mysql://nuc.hkumars.potatoma.com:3306/comp7506?useSSL=false&allowPublicKeyRetrieval=true";
            connection = DriverManager.getConnection(url, user, password);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

}
