package csu.web.mypetstore.persistence.impl;

import csu.web.mypetstore.persistence.DBUtil;
import csu.web.mypetstore.persistence.LogDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogDaoImpl implements LogDao {
    private static final String insertLogString = "insert into log (username,logtime,action,request) VALUES (?,?,?,?)";

    @Override
    public void insertLog(String username, String action,String request) {
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(insertLogString);
            String logtime = getNowTime();
            preparedStatement.setString(1, username);
            preparedStatement.setString(2,logtime);
            preparedStatement.setString(3, action);
            preparedStatement.setString(4, request);

            preparedStatement.executeUpdate();
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private String getNowTime() {
        Date now = new Date();
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(now);
    }

//    public static void main(String[] args) {
//        LogDaoImpl logDao = new LogDaoImpl();
//        logDao.insertLog("j2ee",
//                "1",
//                "/wyk/mainForm?null");
//    }
}
