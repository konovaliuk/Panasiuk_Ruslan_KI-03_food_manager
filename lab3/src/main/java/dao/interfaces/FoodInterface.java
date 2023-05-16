package dao.interfaces;

import entities.Food;
import java.util.List;
import java.util.Optional;

public interface FoodInterface {
    List<Food> findFoodByName(String name);
    Optional<Food> findById(Long id);
    boolean addFood(Food food);
}
