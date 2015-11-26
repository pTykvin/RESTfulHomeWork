package ru.tykvin.data.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import ru.tykvin.data.dao.DaoFactory;
import ru.tykvin.data.dao.GenericDao;
import ru.tykvin.data.dao.PersistException;
import ru.tykvin.data.domain.Group;
import ru.tykvin.data.domain.Student;

public class MySqlDaoFactory implements DaoFactory<Connection> {

    private String user = "root";//Логин пользователя
    private String password = "";//Пароль пользователя
    private String url = "jdbc:mysql://localhost:3306/daotalk";//URL адрес
    private String driver = "com.mysql.jdbc.Driver";//Имя драйвера
    private Map<Class, DaoCreator> creators;

    public Connection getContext() throws PersistException {
        Connection connection;
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new PersistException(e);
        }
        return  connection;
    }

    @Override
    public GenericDao getDao(Connection connection, Class dtoClass) throws PersistException {
        DaoCreator creator = creators.get(dtoClass);
        if (creator == null) {
            throw new PersistException("Dao object for " + dtoClass + " not found.");
        }
        return creator.create(connection);
    }

    public MySqlDaoFactory() {
        try {
            Class.forName(driver);//Регистрируем драйвер
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        creators = new HashMap<Class, DaoCreator>();
        creators.put(Group.class, new DaoCreator<Connection>() {
            @Override
            public GenericDao create(Connection connection) {
                return new MySqlGroupDao(connection);
            }
        });
        creators.put(Student.class, new DaoCreator<Connection>() {
            @Override
            public GenericDao create(Connection connection) {
                return new MySqlStudentDao(connection);
            }
        });
    }
}
