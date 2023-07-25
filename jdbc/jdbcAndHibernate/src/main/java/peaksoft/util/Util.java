package peaksoft.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import peaksoft.model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    // реализуйте настройку соеденения с БД

    public static Connection getConnection() {
        Connection connection;

        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                    "postgres",
                    "postgres");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return connection;
    }

        public static SessionFactory getSession () {
            Properties properties = new Properties();
            properties.put(Environment.DRIVER, "org.postgresql.Driver");
            properties.put(Environment.URL, "jdbc:postgresql://localhost:5432/postgres");
            properties.put(Environment.USER, "postgres");
            properties.put(Environment.PASS, "postgres");
            properties.put(Environment.HBM2DDL_AUTO, "update");
            properties.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
            properties.put(Environment.SHOW_SQL, "true");

            Configuration configuration = new Configuration();
            configuration.addProperties(properties);
            configuration.addAnnotatedClass(User.class);
            return configuration.buildSessionFactory();
        }
    }