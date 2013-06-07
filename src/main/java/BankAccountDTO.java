/**
 * Created with IntelliJ IDEA.
 * User: chauttm
 * Date: 6/6/13
 * Time: 10:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class BankAccountDTO {
    private double balance;
    private final String accountNumber;



    public BankAccountDTO(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    @Override
    public boolean equals(Object otherAccount) {
        BankAccountDTO other = (BankAccountDTO)otherAccount;
        return accountNumber.equals(other.accountNumber) && ((balance - other.balance) < 0.01);
    }
}
