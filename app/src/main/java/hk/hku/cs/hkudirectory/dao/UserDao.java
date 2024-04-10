package hk.hku.cs.hkudirectory.dao;

import android.util.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

import hk.hku.cs.hkudirectory.entity.User;
import hk.hku.cs.hkudirectory.utils.JDBCUtils;

public class UserDao {
    private static final String TAG = "mysql-hkudirectory-UserDao";

    // function: login
    public int login(String userEmail, String userPassword) {

        // store queries content
        HashMap<String, Object> map = new HashMap<>();
        // build connection
        Connection connection = JDBCUtils.getConn();

        int msg = 0;
        try {
            String sql = "select * from user where userEmail = ?";
            if (connection != null) { // build connection if not null
                PreparedStatement ps = connection.prepareStatement(sql);
                if (ps != null) {
                    Log.e(TAG, "Email: " + userEmail);
                    // search email
                    ps.setString(1, userEmail);
                    // return result
                    ResultSet rs = ps.executeQuery();
                        int count = rs.getMetaData().getColumnCount();
                        // store queries into the map
                        while (rs.next()) {
                            // index start from 1
                            for (int i = 1; i <= count; i++) {
                                String field = rs.getMetaData().getColumnName(i);
                                map.put(field, rs.getString(field));
                            }
                        }
                        connection.close();
                        ps.close();

                    if (map.size() != 0) {
                        // check the password if it is correct
                        StringBuilder s = new StringBuilder();
                        for (String key:map.keySet()) {
                            if (key.equals("userPassword")) {
                                if(userPassword.equals(map.get(key))) {
                                    msg = 1;
                                }
                                else
                                    msg = 2;
                                break;
                            }
                        }
                    }else {
                        Log.e(TAG, "Empty result");
                        msg = 3;
                    }
                }else {
                    msg = 0;
                }
            }else {
                msg = 0;
            }
        }catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, "Login exception: " + e.getMessage());
            msg = 0;
        }
        return msg;
    }

    // function: Registration

    public boolean register(User user) {
        HashMap<String, Object> map = new HashMap<>();
        // connect to database
        Connection connection = JDBCUtils.getConn();
        try {
            String sql = "insert into user(userEmail,userPassword,userName,userType) values (?,?,?,?)";
            if (connection != null) {
                PreparedStatement ps = connection.prepareStatement(sql);
                if (ps != null) {
                    ps.setString(1, user.getUserEmail());
                    ps.setString(2, user.getUserPassword());
                    ps.setString(3, user.getUserName());
                    ps.setInt(4, user.getUserType());

                    int rs = ps.executeUpdate();
                    if (rs > 0)
                        return true;
                    else
                        return false;
                } else {
                    return false;
                }
            }else {
                return false;
            }
        }catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "Register exception: " + e.getMessage());
            return false;
        }
    }

    // function: check the user if exist from email

    public User findUser (String userEmail) {
        // connect database
        Connection connection = JDBCUtils.getConn();
        User user = null;
        try {
            String sql = "select * from user where userEmail = ?";
            if (connection != null) {
                PreparedStatement ps = connection.prepareStatement(sql);
                if (ps != null) {
                    ps.setString(1, userEmail);
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        int id = rs.getInt(1);
                        String userEmail1 = rs.getString(2);
                        String userPassword = rs.getString(3);
                        String userName = rs.getString(4);
                        int userType = rs.getInt(5);
                        user = new User(id, userEmail1, userPassword, userName, userType);
                    }
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, "Find user exception: " + e.getMessage());
            return null;
        }
        return user;
    }

}
