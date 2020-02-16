package adoImpl;

import ado.ExchangeRateAdo;
import com.google.gson.stream.JsonReader;
import entity.ExchangeRate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExchangeRateAdoImpl implements ExchangeRateAdo {
    private JsonReader js = new JsonReader();

    public ExchangeRateAdoImpl() {
    }

    @Override
    public ExchangeRate getRateByDate(String data) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("bank");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        ExchangeRate exchangeRate = new ExchangeRate();
        try {
            exchangeRate = (ExchangeRate) entityManager.createQuery
                    ("Select e From ExchangeRate ex where e.date = '" + data + "'").getSingleResult();
        }finally {
            if(entityManager!=null)
                entityManager.close();
            if(entityManager!=null)
                entityManager.close();
        }
        return exchangeRate;
    }

    @Override
    public ExchangeRate getAllDate() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("bank");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        ExchangeRate exchangeRate;
        try {
            exchangeRate = (ExchangeRate) entityManager.createQuery
                    ("Select e From ExchangeRate e order by e.idRate asc ").getSingleResult();
        }finally {
            if(entityManager!=null)
                entityManagerFactory.close();
            if(entityManager!=null)
                entityManagerFactory.close();
        }
        return exchangeRate;
    }

    @Override
    public boolean updateData() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("bank");
        EntityManager entity = entityManagerFactory.createEntityManager();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.YYYY");
        Date date = new Date();
        String dateNow = sdf.format(date);
        ExchangeRate exchangeRate = null;
        try {
            exchangeRate = js.parseJson(dateNow);
        } catch (IOException e) {
            e.printStackTrace();
        }
        EntityTransaction entityTransaction = entity.getTransaction();
        try {
            entityTransaction.begin();
            entity.createQuery("Delete from ExchangeRate e ").executeUpdate();
            entityTransaction.commit();
            entityTransaction.begin();
            entity.merge(exchangeRate);
            entityTransaction.commit();
        } catch (Exception ex) {
            entityTransaction.rollback();
            System.out.println(ex.getCause());
            return false;
        }
        return true;
    }
}
