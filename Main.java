import java.sql.*;

public class Main {

    public static void main(String[] args) throws SQLException {
        String user = "root";
        String password = "12345678";
        String URL = "jdbc:mysql://localhost:3306/Test";

        Connection connection = getConnection(user, password, URL); //Подключение к БД

        ResultSet rs = createStatement(connection).executeQuery("select distinct DEPARTMENT from EMPLOYERS"); //Получение списка отделов
        while (rs.next()){
            String dep = rs.getString("DEPARTMENT"); //Название отдела
            ResultSet sumSalary = createStatement(connection)
                            .executeQuery("select sum(SALARY) from EMPLOYERS where DEPARTMENT = \"" + dep + "\"" ); //Сумма зарплат для указанного отдела

            sumSalary.next();
            System.out.println(dep + ": " + sumSalary.getInt(1)); //Вывод отдела и суммы зарплат
        }
    }

    public static Connection getConnection(String user, String password, String URL) throws SQLException {
        return DriverManager.getConnection(URL, user, password); //Возврат подключения к бд
    }

    public static Statement createStatement(Connection connection) throws SQLException {
        return connection.createStatement();
    }
}
