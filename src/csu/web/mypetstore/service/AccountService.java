package csu.web.mypetstore.service;

import csu.web.mypetstore.domain.Account;
import csu.web.mypetstore.persistence.impl.AccountDaoImpl;
import csu.web.mypetstore.persistence.AccountDao;

public class AccountService {
    private AccountDao accountDao;

    public AccountService() {
        accountDao = new AccountDaoImpl();
    }

    public Account getAccount(String username) {
        return accountDao.getAccountByUsername(username);
    }

    public Account getAccount(String username, String password) {
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(password);
        return accountDao.getAccountByUsernameAndPassword(account);
    }

    public void insertAccount(Account account) {
        accountDao.insertAccount(account);
        accountDao.insertProfile(account);
        accountDao.insertSignon(account);
    }

    public void updateAccount(Account account) {
        accountDao.updateAccount(account);
        accountDao.updateProfile(account);

        if (account.getPassword() != null && !account.getPassword().isEmpty()) {
            accountDao.updateSignon(account);
        }
    }
}