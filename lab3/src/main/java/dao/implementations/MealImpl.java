package dao.implementations;

import dao.interfaces.MealInterface;
import entities.Meal;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import java.util.Collections;
import java.util.List;

public class MealImpl implements MealInterface {

    private final EntityManagerFactory emf;

    public MealImpl(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public List<Meal> findAllByUserId(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Meal> query = em.createQuery("SELECT m FROM Meal m WHERE m.userId = :userId", Meal.class);
            query.setParameter("userId", id);
            return query.getResultList();
        } catch(Exception e){
            e.printStackTrace();
            return Collections.emptyList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Meal> findAllTopical(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Meal> query = em.createQuery("SELECT m FROM Meal m WHERE m.userId = :userId AND m.topicality = false", Meal.class);
            query.setParameter("userId", id);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public boolean deleteMeal(Long id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Meal meal = em.find(Meal.class, id);
            if (meal != null) {
                em.remove(meal);
                tx.commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }

    @Override
    public boolean addMeal(Meal meal) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(meal);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }

    @Override
    public boolean updateMeal(Long id, Double amount) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Meal meal = em.find(Meal.class, id);
            if (meal != null) {
                meal.setAmount(amount);
                em.merge(meal);
                tx.commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }
}