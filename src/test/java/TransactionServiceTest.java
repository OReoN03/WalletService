import org.example.domain.Player;
import org.example.domain.Transaction;
import org.example.exceptions.InsufficientFundsException;
import org.example.exceptions.NotUniqueTransactionIdException;
import org.example.repository.TransactionRepository;
import org.example.service.TransactionService;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * This class contains unit tests for the TransactionService class.
 */
@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {
    private TransactionService transactionService;
    private TransactionRepository transactionRepository;

    /**
     * Set up the necessary dependencies for the tests.
     */
    @Before
    public void setUp() {
        transactionRepository = Mockito.mock(TransactionRepository.class);
        transactionService = new TransactionService(transactionRepository);
    }

    /**
     * Test the credit method of the TransactionService.
     * @throws NotUniqueTransactionIdException if the transaction ID is not unique
     */
    @Test
    public void testCredit() throws NotUniqueTransactionIdException {
        Player player = new Player("John", "Doe", 100);
        long transactionId = 1L;

        transactionService.credit(player, 50, transactionId);

        assertThat(player.getBalance()).isEqualTo(150);
        assertThat(player.getTransactions()).hasSize(1);
        assertThat(player.getTransactions().get(transactionId)).isNotNull();
        verify(transactionRepository, times(1)).add(any(Transaction.class));
    }

    /**
     * Test the credit method of the TransactionService when the transaction ID is not unique.
     * @throws NotUniqueTransactionIdException if the transaction ID is not unique
     */
    @Test
    public void testCreditWithNotUniqueTransactionId() throws NotUniqueTransactionIdException {
        Player player = new Player("John", "Doe", 100);
        long transactionId = 1L;

        assertThatExceptionOfType(NotUniqueTransactionIdException.class)
                .isThrownBy(() -> player.getTransactions().put(transactionId,
                        new Transaction(transactionId, 50, Transaction.Type.CREDIT, player, LocalDateTime.now())));

        transactionService.credit(player, 50, transactionId);
    }

    /**
     * Test the debit method of the TransactionService.
     * @throws NotUniqueTransactionIdException if the transaction ID is not unique
     * @throws InsufficientFundsException     if there are insufficient funds for the debit
     */
    @Test
    public void testDebit() throws NotUniqueTransactionIdException, InsufficientFundsException {
        Player player = new Player("John", "Doe", 100);
        long transactionId = 1L;

        transactionService.debit(player, 50, transactionId);

        assertThat(player.getBalance()).isEqualTo(50);
        assertThat(player.getTransactions()).hasSize(1);
        assertThat(player.getTransactions().get(transactionId)).isNotNull();
        verify(transactionRepository, times(1)).add(any(Transaction.class));
    }

    /**
     * Test the debit method of the TransactionService when the transaction ID is not unique.
     * @throws NotUniqueTransactionIdException if the transaction ID is not unique
     * @throws InsufficientFundsException     if there are insufficient funds for the debit
     */
    @Test
    public void testDebitWithNotUniqueTransactionId() throws NotUniqueTransactionIdException, InsufficientFundsException {
        Player player = new Player("John", "Doe", 100);
        long transactionId = 1L;

        assertThatExceptionOfType(NotUniqueTransactionIdException.class)
                .isThrownBy(() -> player.getTransactions().put(transactionId,
                        new Transaction(transactionId, 50, Transaction.Type.DEBIT, player, LocalDateTime.now())));

        transactionService.debit(player, 50, transactionId);
    }

    @Test
    public void testDebitWithInsufficientFunds() {
        Player player = new Player("John", "Doe", 50);
        long transactionId = 1L;

        assertThatExceptionOfType(InsufficientFundsException.class).isThrownBy(() ->
                transactionService.debit(player, 100, transactionId));
    }

    @Test
    public void testMakeTransactionWithCredit() throws NotUniqueTransactionIdException, InsufficientFundsException {
        Player player = new Player("John", "Doe", 100);
        long transactionId = 1L;

        transactionService.makeTransaction(player, 50, transactionId, Transaction.Type.CREDIT);

        assertThat(player.getBalance()).isEqualTo(150);
        assertThat(player.getTransactions()).hasSize(1);
        assertThat(player.getTransactions().get(transactionId)).isNotNull();
        verify(transactionRepository, times(1)).add(any(Transaction.class));
    }

    @Test
    public void testMakeTransactionWithDebit() throws NotUniqueTransactionIdException, InsufficientFundsException {
        Player player = new Player("John", "Doe", 100);
        long transactionId = 1L;

        transactionService.makeTransaction(player, 50, transactionId, Transaction.Type.DEBIT);

        assertThat(player.getBalance()).isEqualTo(50);
        assertThat(player.getTransactions()).hasSize(1);
        assertThat(player.getTransactions().get(transactionId)).isNotNull();
        verify(transactionRepository, times(1)).add(any(Transaction.class));
    }

    @Test
    public void testTransactionsForPlayer() {
        Player player = new Player("John", "Doe", 100);
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(1L, 50, Transaction.Type.CREDIT, player, LocalDateTime.now()));
        transactions.add(new Transaction(2L, 25, Transaction.Type.DEBIT, player, LocalDateTime.now()));
        when(transactionRepository.findForPlayer(player)).thenReturn(transactions);

        List<Transaction> result = transactionService.transactionsForPlayer(player);

        assertThat(result).hasSize(2);
        verify(transactionRepository, times(1)).findForPlayer(player);
    }
}
