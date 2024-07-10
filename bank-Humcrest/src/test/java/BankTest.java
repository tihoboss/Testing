import com.tiler.Bank;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
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
        MatcherAssert.assertThat(bank.authenticate("user1", "password1"), Matchers.is(true));
    }

    @Test
    public void testAuthenticate_InvalidCredentials() {
        MatcherAssert.assertThat(bank.authenticate("user1", "wrongpassword"), Matchers.is(false));
    }

    @Test
    public void testDeposit() {
        double initialBalance = bank.getBalance();
        bank.deposit(100);
        MatcherAssert.assertThat(bank.getBalance(), Matchers.equalTo(initialBalance + 100));
    }

    @Test
    public void testWithdraw_SuccessfulWithdrawal() {
        double initialBalance = bank.getBalance();
        MatcherAssert.assertThat(bank.withdraw(100), Matchers.is(true));
        MatcherAssert.assertThat(bank.getBalance(), Matchers.equalTo(initialBalance - 100));
    }

    @Test
    public void testWithdraw_InsufficientBalance() {
        bank.deposit(50);
        double initialBalance = bank.getBalance();
        MatcherAssert.assertThat(bank.withdraw(5000), Matchers.is(false));
        MatcherAssert.assertThat(bank.getBalance(), Matchers.equalTo(initialBalance));
    }

    @Test
    public void testTransfer() {
        bank.deposit(1000);
        Bank anotherBank = new Bank();
        anotherBank.deposit(500);

        double initialBalanceInBank = bank.getBalance();
        double initialBalanceInAnotherBank = anotherBank.getBalance();

        MatcherAssert.assertThat(bank.transfer(anotherBank, 300), Matchers.is(true));

        MatcherAssert.assertThat(bank.getBalance(), Matchers.equalTo(initialBalanceInBank - 300));
        MatcherAssert.assertThat(anotherBank.getBalance(), Matchers.equalTo(initialBalanceInAnotherBank + 300));
    }
}