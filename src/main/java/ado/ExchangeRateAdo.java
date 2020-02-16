package ado;

import entity.ExchangeRate;

public interface ExchangeRateAdo {
    public ExchangeRate getRateByDate(String data);
    public boolean updateData();
    public ExchangeRate getAllDate();
}
