/**
 * Created with IntelliJ IDEA.
 * User: chauttm
 * Date: 6/6/13
 * Time: 9:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class BankAccount {
    private static BankAccountDao bankAccountDao;

    public static BankAccountDTO openAccount(String accountNumber) {
        BankAccountDTO account = new BankAccountDTO(accountNumber);
        bankAccountDao.save(account);
        return account;
    }

    public static void setBankAccountDao(BankAccountDao bankAccountDao) {
        BankAccount.bankAccountDao = bankAccountDao;
    }

    public static boolean doTransaction(int amount, BankAccountDTO account) {
        account.setBalance( account.getBalance() + amount);
        bankAccountDao.save(account);
        return true;
    }

    public static BankAccountDTO findByAccountNumber(String accountNumber) {
        return bankAccountDao.findByAccountNumber(accountNumber);
    }

    public static void doTransaction(int i, BankAccountDTO account, long l) {
        bankAccountDao.save(account,l);
    }
}
