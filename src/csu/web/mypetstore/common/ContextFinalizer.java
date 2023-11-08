package csu.web.mypetstore.common;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.concurrent.ExecutorService;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;
import csu.web.mypetstore.persistence.DBUtil;

@WebListener
public class ContextFinalizer implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        Driver d = null;
        while (drivers.hasMoreElements()) {
            try {
                d = drivers.nextElement();
                DriverManager.deregisterDriver(d);
            }
            catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        AbandonedConnectionCleanupThread.checkedShutdown();
    }
}
