package exam;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class BalanceCalculatorJDBC {
	private static final String url = "jdbc:mysql://localhost:3306/teca41?user=root&password=12345";
    

    public static void main(String[] args) {
        Connection connection = null;
        try {
            // Connect to the database
            connection = DriverManager.getConnection(url);

            // Calculate balances
            Map<String, Double> balances = calculateBalances(connection);

            // Print balances
            for (Map.Entry<String, Double> entry : balances.entrySet()) {
                System.out.println(entry.getKey() + ", " + entry.getValue());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the connection
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static Map<String, Double> calculateBalances(Connection connection) throws SQLException {
        Map<String, Double> balances = new HashMap<>();

        String sql = "SELECT customer, SUM(debit) - SUM(credit) AS balance FROM transactions GROUP BY customer ORDER BY customer ASC";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                String customer = resultSet.getString("customer");
                double balance = resultSet.getDouble("balance");
                balances.put(customer, balance);
            }
        }

        return balances;
    }
}
