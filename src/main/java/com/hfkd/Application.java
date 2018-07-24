package com.hfkd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

/**
 * SpringBoot启动入口
 * @author hexq
 * @date 2017/11/6 10:27
 */
@SpringBootApplication
@PropertySource("classpath:config.properties")
public class Application {

    //private final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /**
     * 解决tomcat关闭时可能导致memory leak的问提，详情见：
     * <a>https://github.com/spring-projects/spring-boot/issues/2612</a>
     */
    /*@Bean
    protected ServletContextListener listener() {
        return new ServletContextListener() {

            @Override
            public void contextInitialized(ServletContextEvent sce) {
                log.info("Initialising Context...");
            }

            @Override
            public final void contextDestroyed(ServletContextEvent sce) {
                log.info("Destroying Context...");
                try {
                    log.info("Calling MySQL AbandonedConnectionCleanupThread shutdown");
                    com.mysql.jdbc.AbandonedConnectionCleanupThread.checkedShutdown();
                } catch (Exception e) {
                    log.error("Error calling MySQL AbandonedConnectionCleanupThread shutdown {}", e);
                }
                ClassLoader cl = Thread.currentThread().getContextClassLoader();
                Enumeration<Driver> drivers = DriverManager.getDrivers();
                while (drivers.hasMoreElements()) {
                    Driver driver = drivers.nextElement();
                    if (driver.getClass().getClassLoader() == cl) {
                        try {
                            log.info("Deregistering JDBC driver {}", driver);
                            DriverManager.deregisterDriver(driver);
                        } catch (SQLException ex) {
                            log.error("Error deregistering JDBC driver {}", driver, ex);
                        }
                    } else {
                        log.trace("Not deregistering JDBC driver {} as it does not belong to this webapp's ClassLoader", driver);
                    }
                }
            }
        };
    }*/

    //    @Bean
    //    ServletWebServerFactory servletWebServerFactory(){
    //        return new TomcatServletWebServerFactory();
    //    }

}
