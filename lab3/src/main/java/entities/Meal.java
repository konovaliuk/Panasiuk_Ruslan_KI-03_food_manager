package entities;
import jakarta.persistence.*;

import java.util.Date;
@Entity
@Table(name = "meal")
public class Meal {

    @Id
    private Long id;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "food_id")
    private Long foodId;

    @Column(name = "calories")
    private Integer calories = 0;

    @Column(name = "proteins")
    private Integer proteins = 0;

    @Column(name = "fats")
    private Integer fats = 0;

    @Column(name = "carbohydrates")
    private Integer carbohydrates = 0;

    @Column(name = "fiber")
    private Integer fiber = 0;

    @Column(name = "topicality")
    private Boolean topicality = false;

    @Column(name = "date_created")
    private Date dateCreated;

    public Meal() {
        this.dateCreated = new Date(System.currentTimeMillis());
    }

    public Meal(Long id, Double amount, Long userId, Long foodId) {
        this.id = id;
        this.amount = amount;
        this.userId = userId;
        this.foodId = foodId;
        this.dateCreated = new Date(System.currentTimeMillis());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getCalories() {
        return calories;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    public Integer getProteins() {
        return proteins;
    }

    public void setProteins(Integer proteins) {
        this.proteins = proteins;
    }

    public Integer getFats() {
        return fats;
    }

    public void setFats(Integer fats) {
        this.fats = fats;
    }

    public Integer getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(Integer carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public Integer getFiber() {
        return fiber;
    }

    public void setFiber(Integer fiber) {
        this.fiber = fiber;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getFoodId() {
        return foodId;
    }

    public void setFoodId(Long foodId) {
        this.foodId = foodId;
    }

    public Boolean getTopicality() {
        return topicality;
    }

    public void setTopicality(Boolean topicality) {
        this.topicality = topicality;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
}
