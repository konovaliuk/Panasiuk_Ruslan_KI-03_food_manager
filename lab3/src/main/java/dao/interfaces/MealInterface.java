package dao.interfaces;

import entities.Meal;
import java.util.List;

public interface MealInterface {
    List<Meal> findAllByUserId(Long id);
    List<Meal> findAllTopical(Long id);
    boolean deleteMeal(Long id);
    boolean addMeal(Meal v);
    boolean updateMeal(Long id, Double amount);
}
