package org.example;

import dao.implementations.*;
import dao.interfaces.*;
import entities.*;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("food_manager");

        MealInterface mealInterface = new MealImpl(entityManagerFactory);
        FoodInterface foodInterface = new FoodImpl(entityManagerFactory);
        UserInterface userInterface = new UserImpl(entityManagerFactory);



        // test findAllByUserId
        List<Meal> mealsByUserId = mealInterface.findAllByUserId(1L);
        System.out.println("Meals for user with id 1:");
        mealsByUserId.forEach(System.out::println);

        // Test findById
        Long foodId = 1L;
        Optional<Food> food = foodInterface.findById(foodId);
        if (food.isPresent()) {
            System.out.println("Food found with ID " + foodId + ":");
            System.out.println(food.get());
        } else {
            System.out.println("Food not found with ID " + foodId);
        }

        // Test updateUser
        Long userId = 1L;
        Optional<User> user = userInterface.findUserById(userId);
        if (user.isPresent()) {
            User updatedUser = user.get();
            updatedUser.setName("John Doe");
            updatedUser.setEmail("john.doe@example.com");
            boolean isUpdated = userInterface.updateUser(updatedUser);
            if (isUpdated) {
                System.out.println("User updated successfully.");
            } else {
                System.out.println("Failed to update user.");
            }
        } else {
            System.out.println("User not found with ID " + userId);
        }
    }
}
