package csu.web.mypetstore.service;

import csu.web.mypetstore.persistence.LogDao;
import csu.web.mypetstore.persistence.impl.LogDaoImpl;

public class LogService {
    private LogDao logDao;

    public LogService(){
        this.logDao = new LogDaoImpl();
    }
    public void insertLog(String username, String action, String request){
        if(username == null || username.isEmpty()){
            return;
        }
        logDao.insertLog(username,action,request);
    }
}
