package csu.web.mypetstore.persistence;

import csu.web.mypetstore.domain.Account;

public interface AccountDao {
    Account getAccountByUsername(String username);

    Account getAccountByUsernameAndPassword(Account account);

    void insertAccount(String username);
//    void insertAccount(Account account);???

    void insertProfile(String username,String preference);

    void insertSignon(String username,String password);

    void updateAccount(Account account);

    void updateProfile(Account account);

    void updateSignon(Account account);
}
