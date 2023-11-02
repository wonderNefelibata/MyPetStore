package csu.web.mypetstore.persistence;

import java.sql.*;

public class DBUtil{

    //数据库驱动
    private static final String DRIVER ="com.mysql.cj.jdbc.Driver";

    //数据库url
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/mypetstore";

    //用户名
    private static final String USERNAME= "root";

    //密码
    private static final String PASSWORD ="1234";

    //获取connection连接
    public static Connection getConnection(){
        Connection connection=null;
        try {
            Class.forName(DRIVER);
            connection=DriverManager.getConnection(URL,USERNAME,PASSWORD);
        }catch (Exception e){
            e.printStackTrace();
        }
        return connection;
    }

    //关闭连接
    public static void closeConnection (Connection connection){
        if(connection!=null){
            try {
                connection.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }


    public static void closeStatement(Statement statement){
        if(statement!= null){
            try{
                statement.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }


    public static void closePreparedStatement(PreparedStatement preparedStatement){
        if(preparedStatement!= null){
            try{
                preparedStatement.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }

    public static void closeResultSet(ResultSet resultSet){
        if(resultSet!= null){
            try{
                resultSet.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }

//    public static void main(String[] args) {
//        System.out.println(getConnection());
//    }
}