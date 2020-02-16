package entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Entity
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue
    private int idTransaction;
    @Column(name = "cardOfTheSender")
    private String to;
    @Column(name = "cardOfTheRecipient")
    private String from;
    private String currency;
    private double amount;
    private LocalDate date;
    private LocalTime time;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    public Transaction() {
    }

    public Transaction(String to, String from, String currency, double amount, LocalDate date, LocalTime time, User user) {
        this.to = to;
        this.from = from;
        this.currency = currency;
        this.amount = amount;
        this.date = date;
        this.time = time;
        this.user = user;
    }

    public int getIdTransaction() {
        return idTransaction;
    }

    public void setIdTransaction(int idTransaction) {
        this.idTransaction = idTransaction;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "idTransaction=" + idTransaction +
                ", to='" + to + '\'' +
                ", from='" + from + '\'' +
                ", currency='" + currency + '\'' +
                ", amount=" + amount +
                ", date=" + date +
                ", time=" + time +
                '}';
    }

    @Override
    public boolean equals(Object o) {
      if (this == o)
          return true;
      if (o == null || getClass() != o.getClass())
          return false;
      Transaction transaction = (Transaction) o;
        return idTransaction == transaction.idTransaction && amount == transaction.amount
                && Objects.equals(to, transaction.to) && Objects.equals(from, transaction.from)
                && Objects.equals(currency, transaction.currency) && Objects.equals(user, transaction.user);

    }

    public int hashCode() {
        return Objects.hash(idTransaction, to, from, currency, amount, user);
    }
}
