package entity;

import javax.persistence.*;

@Entity
@Table(name = "exchangerate")
public class ExchangeRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdExchangeRate")
    Integer IdRate;

    @Transient
    private String date;
    @Transient
    private String baseCurrency;
    @Transient
    private Double saleRateNB;
    @Transient
    private Double purchaseRateNB;
    @Transient
    private String currency;
    @Transient
    private Double saleRate;
    @Transient
    private Double purchaseRate;

    @Column(name = "usd")
    private String usd;
    @Column(name = "eur")
    private String eur;

    public ExchangeRate() {
    }

    public ExchangeRate(String date, String baseCurrency, Double saleRateNB, Double purchaseRateNB,
                        String currency, Double saleRate, Double purchaseRate, String usd, String eur) {
        this.date = date;
        this.baseCurrency = baseCurrency;
        this.saleRateNB = saleRateNB;
        this.purchaseRateNB = purchaseRateNB;
        this.currency = currency;
        this.saleRate = saleRate;
        this.purchaseRate = purchaseRate;
        this.usd = usd;
        this.eur = eur;
    }

    public Integer getIdRate() {
        return IdRate;
    }

    public void setIdRate(Integer idRate) {
        IdRate = idRate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public ExchangeRate withBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
        return this;
    }

    public Double getSaleRateNB() {
        return saleRateNB;
    }

    public void setSaleRateNB(Double saleRateNB) {
        this.saleRateNB = saleRateNB;
    }

    public ExchangeRate withSaleRateNB(Double saleRateNB) {
        this.saleRateNB = saleRateNB;
        return this;
    }

    public Double getPurchaseRateNB() {
        return purchaseRateNB;
    }

    public void setPurchaseRateNB(Double purchaseRateNB) {
        this.purchaseRateNB = purchaseRateNB;
    }

    public ExchangeRate withPurchaseRateNB(Double purchaseRateNB) {
        this.purchaseRateNB = purchaseRateNB;
        return this;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public ExchangeRate withCurrency(String currency) {
        this.currency = currency;
        return this;
    }

    public Double getSaleRate() {
        return saleRate;
    }

    public void setSaleRate(Double saleRate) {
        this.saleRate = saleRate;
    }

    public ExchangeRate withSaleRate(Double saleRate) {
        this.saleRate = saleRate;
        return this;
    }

    public Double getPurchaseRate() {
        return purchaseRate;
    }

    public void setPurchaseRate(Double purchaseRate) {
        this.purchaseRate = purchaseRate;
    }

    public ExchangeRate withPurchaseRate(Double purchaseRate) {
        this.purchaseRate = purchaseRate;
        return this;
    }

    public String getUsd() {
        return usd;
    }

    public void setUsd(String usd) {
        this.usd = usd;
    }

    public String getEur() {
        return eur;
    }

    public void setEur(String eur) {
        this.eur = eur;
    }

    @Override
    public String toString() {
        return "ExchangeRate{" +
                "IdRate=" + IdRate +
                ", date='" + date + '\'' +
                ", baseCurrency='" + baseCurrency + '\'' +
                ", saleRateNB=" + saleRateNB +
                ", purchaseRateNB=" + purchaseRateNB +
                ", currency='" + currency + '\'' +
                ", saleRate=" + saleRate +
                ", purchaseRate=" + purchaseRate +
                ", usd='" + usd + '\'' +
                ", eur='" + eur + '\'' +
                '}';
    }
}
