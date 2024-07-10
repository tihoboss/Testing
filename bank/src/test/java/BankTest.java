import com.tiler.Bank;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BankTest {
    private Bank bank;

    @BeforeEach
    public void setUp() {
       bank = new Bank();
    }
    @AfterEach
    public void tearDown() {
        // Reset the banking system to its initial state
        bank = new Bank();
    }
    @Test
    public void testAuthenticate_ValidCredentials() {
        Assertions.assertTrue(bank.authenticate("user1", "password1"));
    }

    @Test
    public void testAuthenticate_InvalidCredentials() {
        Assertions.assertFalse(bank.authenticate("user1", "wrongpassword"));
    }

    @Test
    public void testDeposit() {
        double initialBalance = bank.getBalance();
        bank.deposit(100);
        Assertions.assertEquals(initialBalance + 100, bank.getBalance());
    }

    @Test
    public void testWithdraw_SuccessfulWithdrawal() {
        double initialBalance = bank.getBalance();
        Assertions.assertTrue(bank.withdraw(100));
        Assertions.assertEquals(initialBalance - 100, bank.getBalance());
    }

}
