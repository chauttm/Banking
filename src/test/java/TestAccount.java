import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.internal.invocation.Invocation;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.List;

import static junit.framework.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created with IntelliJ IDEA.
 * User: dse
 * Date: 6/6/13
 * Time: 9:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestAccount{
    BankAccountDao mockAccountDao = mock(BankAccountDao.class);

    @Before
    public void setUp () {
        reset(mockAccountDao);
//        when(mockAccountDao.save(any(BankAccountDTO.class))).thenAnswer(new Answer() {
//            @Override
//            public BankAccountDTO answer(InvocationOnMock invocation) throws Throwable {
//                return (BankAccountDTO)invocation.getArguments()[0];
//            }
//        });
        BankAccount.setBankAccountDao(mockAccountDao);
    }

    @Test
    public void newAccountHasZeroBalanceAndIsPersistent() {
        BankAccount.openAccount("1234567890");

        ArgumentCaptor<BankAccountDTO> savedAccountRecords = ArgumentCaptor.forClass(BankAccountDTO.class);

        verify(mockAccountDao).save(savedAccountRecords.capture());
        assertEquals(savedAccountRecords.getValue().getBalance(), 0.0, 0.01);
        assertEquals(savedAccountRecords.getValue().getAccountNumber(), "1234567890");
    }

    @Test
    public void transactionChangesBalanceAndIsPersistent() {
        ArgumentCaptor<BankAccountDTO> argument = ArgumentCaptor.forClass(BankAccountDTO.class);

        BankAccountDTO account = BankAccount.openAccount("1234567890");

        BankAccount.doTransaction(100, account);
        verify(mockAccountDao,times(2)).save(argument.capture());
        List<BankAccountDTO> savedAccountRecords = argument.getAllValues();
        assertEquals(savedAccountRecords.get(1).getBalance(), 100.0, 0.01);

        BankAccount.doTransaction(-50, account);

        verify(mockAccountDao,times(3)).save(argument.capture());
        savedAccountRecords = argument.getAllValues();
        assertEquals(savedAccountRecords.get(2).getBalance(), 50.0, 0.01);
    }


    @Test
    public void canSaveAccountWithTimestamp() {
        BankAccountDTO account = BankAccount.openAccount("1234567890");
        BankAccount.doTransaction(50, account, 0L);

        ArgumentCaptor<BankAccountDTO> argument = ArgumentCaptor.forClass(BankAccountDTO.class);
        ArgumentCaptor<Long> timeArgument = ArgumentCaptor.forClass(Long.class);
        verify(mockAccountDao).save(argument.capture(), timeArgument.capture());
        assertEquals(timeArgument.getValue().longValue(), 0L);
    }


    @Test
    public void canGetAccountDTOFromDB() {
        BankAccountDTO accountDTO = BankAccount.findByAccountNumber("1234567890");
        verify(mockAccountDao).findByAccountNumber("1234567890");
    }
}
