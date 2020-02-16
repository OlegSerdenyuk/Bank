package entity;

import javax.persistence.*;
import javax.transaction.Transaction;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue
    private int idUser;
    private String nameSurname;
    private String phone;
    private String email;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", orphanRemoval = true)
    private List<Transaction> transaction = new ArrayList<>();

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idAccount")
    private Account account;

    public User(String nameSurname, String phone, String email, List<Transaction> transaction, Account account) {
        this.nameSurname = nameSurname;
        this.phone = phone;
        this.email = email;
        this.transaction = transaction;
        this.account = account;
    }

    public User() {
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getNameSurname() {
        return nameSurname;
    }

    public void setNameSurname(String nameSurname) {
        this.nameSurname = nameSurname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Transaction> getTransaction() {
        return transaction;
    }

    public void setTransaction(List<Transaction> transaction) {
        this.transaction = transaction;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "User{" +
                "idUser=" + idUser +
                ", nameSurname='" + nameSurname + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", transaction=" + transaction +
                ", account=" + account +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        User user = (User) o;
        return idUser == user.idUser && Objects.equals(nameSurname, user.nameSurname)
                && Objects.equals(phone, user.phone) && Objects.equals(email, user.email)
                && Objects.equals(transaction, user.transaction) && Objects.equals(account, user.account);
    }

    public int hashCode() {
        return Objects.hash(idUser, nameSurname, phone, email, transaction, account);
    }
}
