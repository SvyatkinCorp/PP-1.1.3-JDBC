package jm.task.core.jdbc.util;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

//Класс Util должен содержать логику настройки соединения с базой данных
public class Util {

    private static final String URL = "jdbc:mysql://localhost:3306/myBase";
    private static final String USER = "admin";
    private static final String PASSWORD = "admin";
    private static final String DIALECT = "org.hibernate.dialect.MySQLDialect";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static SessionFactory sessionFactory;
    private static Connection connection;

    public Util() {
    }

    public static Connection getConnection() {

        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            if (!connection.isClosed()) {
                System.out.println("Мы законектились");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return connection;
    }

    public static SessionFactory createSessionFactory() {
        if (sessionFactory == null) {
            try {
                // Настройка свойств Hibernate
                Properties properties = new Properties();
                properties.put(Environment.DIALECT, DIALECT);
                properties.put(Environment.DRIVER, DRIVER);
                properties.put(Environment.URL, URL);
                properties.put(Environment.USER, USER);
                properties.put(Environment.PASS, PASSWORD);
                // отображение sql запросов
                properties.setProperty("hibernate.show_sql", "true");
                properties.setProperty("hibernate.format_sql", "true");
                // Создание объекта Configuration
                Configuration configuration = new Configuration();
                // Применение свойств к конфигурации
                configuration.setProperties(properties);
                // Добавление классов сущностей
                configuration.addAnnotatedClass(jm.task.core.jdbc.model.User.class);
                sessionFactory = configuration.buildSessionFactory();
            } catch (HibernateException e) {
                e.printStackTrace();
                return null;
            }
        }
        return sessionFactory;
    }

}