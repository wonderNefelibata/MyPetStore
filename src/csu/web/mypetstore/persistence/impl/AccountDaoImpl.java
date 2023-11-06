package csu.web.mypetstore.persistence.impl;

import csu.web.mypetstore.domain.Account;
import csu.web.mypetstore.persistence.AccountDao;
import csu.web.mypetstore.persistence.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class AccountDaoImpl implements AccountDao {
    private static final String GET_ACCOUNT_BY_USERNAME_AND_PASSWORD = "SELECT " +
            "SIGNON.USERNAME," +
            "ACCOUNT.EMAIL,ACCOUNT.FIRSTNAME,ACCOUNT.LASTNAME,ACCOUNT.STATUS," +
            "ACCOUNT.ADDR1 AS address1,ACCOUNT.ADDR2 AS address2," +
            "ACCOUNT.CITY,ACCOUNT.STATE,ACCOUNT.ZIP,ACCOUNT.COUNTRY,ACCOUNT.PHONE," +
            "PROFILE.LANGPREF AS languagePreference,PROFILE.FAVCATEGORY AS favouriteCategoryId," +
            "PROFILE.MYLISTOPT AS listOption,PROFILE.BANNEROPT AS bannerOption," +
            "BANNERDATA.BANNERNAME " +
            "FROM ACCOUNT, PROFILE, SIGNON, BANNERDATA " +
            "WHERE ACCOUNT.USERID = ? AND SIGNON.PASSWORD = ? " +
            "AND SIGNON.USERNAME = ACCOUNT.USERID " +
            "AND PROFILE.USERID = ACCOUNT.USERID " +
            "AND PROFILE.FAVCATEGORY = BANNERDATA.FAVCATEGORY";

    private static final String INSERT_ACCOUNT = """
                INSERT INTO ACCOUNT
      (EMAIL, FIRSTNAME, LASTNAME, STATUS, ADDR1, ADDR2, CITY, STATE, ZIP, COUNTRY, PHONE, USERID)
    VALUES
      ('a@qq.com', 'a', 'a', 'OK', 'a','a','a','a','a','a', '110',
            """;

    @Override
    public Account getAccountByUsername(String username) {
        return null;
    }

    @Override
    public Account getAccountByUsernameAndPassword(Account account) {
        Account accountResult = null;
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ACCOUNT_BY_USERNAME_AND_PASSWORD);
            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getPassword());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                accountResult = this.resultSetToAccount(resultSet);
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
        return accountResult;
    }

    private Account resultSetToAccount(ResultSet resultSet) throws Exception{
        Account account = new Account();
        account.setUsername(resultSet.getString("username"));
//        account.setPassword(resultSet.getString("password"));
        account.setEmail(resultSet.getString("email"));
        account.setFirstName(resultSet.getString("firstName"));
        account.setLastName(resultSet.getString("lastName"));
        account.setStatus(resultSet.getString("status"));
        account.setAddress1(resultSet.getString("address1"));
        account.setAddress2(resultSet.getString("address2"));
        account.setCity(resultSet.getString("city"));
        account.setState(resultSet.getString("state"));
        account.setZip(resultSet.getString("zip"));
        account.setCountry(resultSet.getString("country"));
        account.setPhone(resultSet.getString("phone"));
        account.setFavouriteCategoryId(resultSet.getString("favouriteCategoryId"));
        account.setLanguagePreference(resultSet.getString("languagePreference"));
        account.setListOption(resultSet.getInt("listOption") == 1);
        account.setBannerOption(resultSet.getInt("bannerOption") == 1);
        account.setBannerName(resultSet.getString("bannerName"));
        return account;
    }

    public void insertAccount(String username){
        try{
            Connection connection = DBUtil.getConnection();
            Statement statement = connection.createStatement();
            String query = INSERT_ACCOUNT + "'" + username + "')";
            statement.executeUpdate(query);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void insertProfile(String username,String preference) {
        try {
            Connection connection = DBUtil.getConnection();
            Statement statement = connection.createStatement();
            String query = """
            INSERT INTO PROFILE (LANGPREF, FAVCATEGORY, MYLISTOPT, BANNEROPT, USERID)
             VALUES ('english',
        """ + "'" + preference + " ','1','1','"  +
                    username + "')";
            statement.executeUpdate(query);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void insertSignon(String username,String password) {
        try {
            Connection connection = DBUtil.getConnection();
            Statement statement = connection.createStatement();
            String query = """
            INSERT INTO SIGNON (PASSWORD,USERNAME)
            VALUES (
        """ + "'" + password + "','"  + username + "')";
            statement.executeUpdate(query);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void updateAccount(Account account) {

    }

    @Override
    public void updateProfile(Account account) {

    }

    @Override
    public void updateSignon(Account account) {

    }

//    public static void main(String[] args) {
//        AccountDao accountDao = new AccountDaoImpl();
//        Account account = new Account();
//        account.setUsername("j2ee");
//        account.setPassword("j2ee");
//        Account result = accountDao.getAccountByUsernameAndPassword(account);
//        System.out.println("success");
//    }
}
