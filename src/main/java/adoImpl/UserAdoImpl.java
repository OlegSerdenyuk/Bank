package adoImpl;

import ado.UserAdo;
import entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class UserAdoImpl implements UserAdo {
    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("bank");
    private EntityManager entityManager = entityManagerFactory.createEntityManager();
    private EntityTransaction entityTransaction = entityManager.getTransaction();

    @Override
    public void addUser(User user) {
        try{
            entityTransaction.begin();
            entityManager.persist(user);
            entityTransaction.commit();
        }catch (Exception e){
            e.getStackTrace();
            entityTransaction.rollback();

        }finally {
            if (entityManagerFactory!=null)
                entityManagerFactory.close();
            if(entityManager!=null)
                entityManager.close();
        }
    }
}
