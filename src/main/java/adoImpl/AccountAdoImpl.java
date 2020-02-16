package adoImpl;

import ado.AccountAdo;
import ado.ExchangeRateAdo;
import entity.Account;
import entity.ExchangeRate;
import entity.Transaction;
import entity.User;
import org.decimal4j.util.DoubleRounder;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class AccountAdoImpl implements AccountAdo {
    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("bank");
    private EntityManager entityManager = entityManagerFactory.createEntityManager();
    private EntityTransaction entityTransaction = entityManager.getTransaction();

    @Override
    public void addAccount(Account account) {
        entityTransaction.begin();
        entityManager.persist(account);
        entityTransaction.commit();
    }

    @Override
    public List<AccountAdoImpl> getAllAccount() {
        List<AccountAdoImpl> accountAdoLmplList = new ArrayList<>();
        entityTransaction.begin();
        accountAdoLmplList = entityManager.createQuery("select a from Account a").getResultList();
        entityTransaction.commit();
        return accountAdoLmplList;
    }

    @Override
    public Account getAccountByCard(String card) {
        Account account = null;
        entityTransaction.begin();
        account = (Account) entityManager.createQuery("select a from Account a where a.card " +
                "= '" + card + "'").getSingleResult();
        entityTransaction.commit();
        return account;
    }

    @Override
    public boolean addMoneyToTheCard(String card, String currency, double amount) {
        Account account = getAccountByCard(card);
        switch (currency.toLowerCase()) {
            case ("usd"): {
                entityTransaction.begin();
                account.setWalletUsd(amount + account.getWalletUsd());
                entityTransaction.commit();
                break;
            }
            case ("eur"): {
                entityTransaction.begin();
                account.setWalletEur(amount + account.getWalletEur());
                entityTransaction.commit();
                break;
            }
            case ("uan"): {
                entityTransaction.begin();
                account.setWalletUan(amount + account.getWalletUan());
                entityTransaction.commit();
                break;
            }
            default:
                System.out.println("You entered incorrect information. Try again please.");
        }
        return true;
    }

    @Override
    public boolean sendMoneyToAnotherCard(String yourCard, String anotherCard, String currency, double amount) {
        Account senderCard = getAccountByCard(yourCard);
        User user = getUserByCard(yourCard);
        Account recipientCard = getAccountByCard(anotherCard);
        Transaction transaction = null;
        if (isEnoughMoneyToSend(yourCard, amount, currency)) {
            entityTransaction.begin();
        switch (currency.toLowerCase()) {
            case ("usd"): {
                senderCard.setWalletUsd(senderCard.getWalletUsd() - amount);
                recipientCard.setWalletUsd(amount + recipientCard.getWalletUsd());
                transaction = new Transaction(anotherCard, yourCard, currency, amount, LocalDate.now(), LocalTime.now(), user);
                entityManager.persist(transaction);
                entityTransaction.commit();
                break;
            }
            case ("eur"): {
                senderCard.setWalletEur(senderCard.getWalletEur() - amount);
                recipientCard.setWalletEur(amount + recipientCard.getWalletEur());
                transaction = new Transaction(anotherCard, yourCard, currency, amount, LocalDate.now(), LocalTime.now(), user);
                entityManager.persist(transaction);
                entityTransaction.commit();
                break;
            }
            case ("uan"): {
                senderCard.setWalletUan(senderCard.getWalletUan() - amount);
                recipientCard.setWalletUan(amount + recipientCard.getWalletUan());
                transaction = new Transaction(anotherCard, yourCard, currency, amount, LocalDate.now(), LocalTime.now(), user);
                entityManager.persist(transaction);
                entityTransaction.commit();
                break;
            }
            default:
                System.out.println("You entered incorrect information. Try again please.");
        }
        } else {
                System.out.println("Your card does not have the required amount.");
            }
        return true;
    }

    @Override
    public boolean isEnoughMoneyToSend(String card, double amount, String currency) {
        Account account = getAccountByCard(card);
        switch (currency.toLowerCase()) {
            case ("usd"): {
                if (account.getWalletUsd() >= amount) {
                    return true;
                } else
                    return false;
            }
            case ("eur"): {
                if (account.getWalletEur() >= amount) {
                    return true;
                } else
                    return false;
            }
            case ("uan"): {
                if (account.getWalletUan() >= amount) {
                    return true;
                } else
                    return false;
            }
        }
        return true;
    }

    @Override
    public User getUserByCard(String card) {
        User user = null;
        entityTransaction.begin();
        user = (User) entityManager.createQuery("select u from User u where u.account.card = '" + card + "'").getSingleResult();
        entityTransaction.commit();
        return user;
    }

    @Override
    public boolean convertCurrency(String fromWhichCurrency, String toWhichCurrency, double amount, String card) {
        ExchangeRateAdo ado = new ExchangeRateAdoImpl();
        ado.updateData();
        ExchangeRate exchangeRate= (ExchangeRate) ado.getAllDate();
        Account account = getAccountByCard(card);

        if (fromWhichCurrency.toLowerCase().equals("uan") && (toWhichCurrency.toLowerCase().equals("usd"))) {
            entityTransaction.begin();
            account.setWalletUan(account.getWalletUan() - amount);
            account.setWalletUsd((account.getWalletUsd() + amount / Double.parseDouble(exchangeRate.getUsd())));
            entityTransaction.commit();
        } else if (fromWhichCurrency.toLowerCase().equals("usd") && (toWhichCurrency.toLowerCase().equals("uan"))) {
            entityTransaction.begin();
            account.setWalletUsd(account.getWalletUsd() - amount);
            account.setWalletUan((account.getWalletUan() + amount * Double.parseDouble(exchangeRate.getUsd())));
            entityTransaction.commit();
        } else if (fromWhichCurrency.toLowerCase().equals("eur") && (toWhichCurrency.toLowerCase().equals("uan"))) {
            entityTransaction.begin();
            account.setWalletEur(account.getWalletEur() - amount);
            account.setWalletUan((account.getWalletUan() + amount * Double.parseDouble(exchangeRate.getEur())));
            entityTransaction.commit();
        } else if (fromWhichCurrency.toLowerCase().equals("uan") && (toWhichCurrency.toLowerCase().equals("eur"))) {
            entityTransaction.begin();
            account.setWalletUan(account.getWalletUan() - amount);
            account.setWalletEur((account.getWalletEur() + amount / Double.parseDouble(exchangeRate.getEur())));
            entityTransaction.commit();
        } else if (fromWhichCurrency.toLowerCase().equals("eur") && (toWhichCurrency.toLowerCase().equals("usd"))) {
            entityTransaction.begin();
            account.setWalletEur(account.getWalletEur() - amount);
            account.setWalletUsd(account.getWalletUsd() + ((amount / (Double.parseDouble(exchangeRate.getUsd()))
                    * Double.parseDouble(exchangeRate.getEur()))));
            entityTransaction.commit();
        } else if (fromWhichCurrency.toLowerCase().equals("usd") && (toWhichCurrency.toLowerCase().equals("eur"))) {
            entityTransaction.begin();
            account.setWalletUsd(account.getWalletUsd() - amount);
            account.setWalletEur(account.getWalletEur() + ((amount / (Double.parseDouble(exchangeRate.getEur()))
                    * Double.parseDouble(exchangeRate.getUsd()))));
            entityTransaction.commit();
        } else return false;
        return true;
    }

    @Override
    public double getAllMoneyInUan(String card) {
        Account account = getAccountByCard(card);
        ExchangeRateAdo ado = new ExchangeRateAdoImpl();
        ado.updateData();
        ExchangeRate ex=ado.getAllDate();
        double sum=0.0;
        sum = account.getWalletUan()+account.getWalletEur()*Double.parseDouble(ex.getEur()) +account.getWalletUsd()*Double.parseDouble(ex.getUsd());
        return DoubleRounder.round(sum,2);
    }
}
