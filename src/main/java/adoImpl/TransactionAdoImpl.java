package adoImpl;

import ado.TransactionAdo;
import entity.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class TransactionAdoImpl implements TransactionAdo {
    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("bank");
    private EntityManager entityManager = entityManagerFactory.createEntityManager();
    private EntityTransaction entityTransaction = entityManager.getTransaction();

    @Override
    public List<Transaction> getTransactionsByCard(String card) {
        List<Transaction> list ;
        try {
            entityTransaction.begin();
            list=entityManager.createQuery
                    ("Select t From Transaction t where t.from='"+card+"'"+" OR t.to= '"+card+"'").getResultList();
            entityTransaction.commit();
        }finally {
            if (entityManagerFactory!=null)
                entityManagerFactory.close();
            if (entityManager!=null)
                entityManager.close();
        }
        return list;
    }
}
