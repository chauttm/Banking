/**
 * Created with IntelliJ IDEA.
 * User: chauttm
 * Date: 6/7/13
 * Time: 12:42 AM
 * To change this template use File | Settings | File Templates.
 */
public class Transaction {
    private static TransactionDao dao;

    public static void setDao(TransactionDao dao) {
        Transaction.dao = dao;
    }

    public static TransactionDao getDao() {
        return dao;
    }

    public static TransactionDTO createTransaction(String accountNumber, double amount, String description) {
        return new TransactionDTO(accountNumber, amount, description);
    }
}
