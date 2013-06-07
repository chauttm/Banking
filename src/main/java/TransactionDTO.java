import java.security.Timestamp;

/**
 * Created with IntelliJ IDEA.
 * User: chauttm
 * Date: 6/7/13
 * Time: 12:45 AM
 * To change this template use File | Settings | File Templates.
 */
public class TransactionDTO {
    private String accountNumber;
    private double amount;
    private final String description;
    private boolean executed = false;
    private long timestamp;

    public TransactionDTO(String accountNumber, double amount, String description) {
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.description = description;
        this.timestamp = System.currentTimeMillis();
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getAmount() {
        return amount;
    }

    public boolean isExecuted() {
        return executed;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
