package csu.web.mypetstore.persistence;

public interface LogDao {
    void insertLog(String username,String action,String request);
}
