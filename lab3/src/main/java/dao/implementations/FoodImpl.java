package dao.implementations;

import dao.interfaces.FoodInterface;
import entities.Food;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Optional;

public class FoodImpl implements FoodInterface {
    private final EntityManagerFactory emf;

    public FoodImpl(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public List<Food> findFoodByName(String name) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Food> query = em.createQuery("SELECT f FROM Food f WHERE f.name = :name", Food.class);
            query.setParameter("name", name);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    @Override
    public Optional<Food> findById(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return Optional.ofNullable(em.find(Food.class, id));
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        } finally {
            em.close();
        }
    }

    @Override
    public boolean addFood(Food food) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = em.getTransaction();
            transaction.begin();
            em.persist(food);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }
}
