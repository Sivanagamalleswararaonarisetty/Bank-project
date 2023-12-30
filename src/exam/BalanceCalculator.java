package exam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

class Transaction {
    String customer;
    double debit;
    double credit;
    Date date;

    public Transaction(String customer, double debit, double credit, Date date) {
        this.customer = customer;
        this.debit = debit;
        this.credit = credit;
        this.date = date;
    }
}

public class BalanceCalculator {
    public static void main(String[] args) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<Transaction> transactions = new ArrayList<>();

        try {
            transactions.add(new Transaction("Arney Cuff", 6.43, 16.12, dateFormat.parse("2021-11-30 12:48:22")));
            transactions.add(new Transaction("Amey Cuff", 97.78, 1253, dateFormat.parse("2021-12-25 19:00:46")));
            transactions.add(new Transaction("Donaugh Furneaux", 89.71, 85.04, dateFormat.parse("2021-11-27 21:34:24")));
            transactions.add(new Transaction("Ferrell Brunn", 63.58, 28.58, dateFormat.parse("2021-12-15 07:31:37")));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Map<String, Double> balances = new HashMap<>();

        for (Transaction transaction : transactions) {
            balances.put(transaction.customer, balances.getOrDefault(transaction.customer, 0.0) + transaction.debit - transaction.credit);
        }

        // Sort the balances in ascending order by customer
        List<Map.Entry<String, Double>> sortedBalances = new ArrayList<>(balances.entrySet());
        sortedBalances.sort(Comparator.comparing(Map.Entry::getKey));

        for (Map.Entry<String, Double> entry : sortedBalances) {
            System.out.println(entry.getKey() + ", " + entry.getValue());
        }
    }
}
