package entities;

import jakarta.persistence.*;


@Entity
@Table(name = "food")
public class Food {

    @Id
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "measurement")
    private String measurement;

    @Column(name = "calories_multiplier")
    private Double caloriesM;

    @Column(name = "proteins_multiplier")
    private Double proteinsM;

    @Column(name = "fats_multiplier")
    private Double fatsM;

    @Column(name = "carbohydrates_multiplier")
    private Double carbohydratesM;

    @Column(name = "fiber_multiplier")
    private Double fiberM;

    public Food() {
    }

    public Food(Long id, String name, String measurement, Double caloriesM, Double proteinsM, Double fatsM, Double carbohydratesM, Double fiberM) {
        this.id = id;
        this.name = name;
        this.measurement = measurement;
        this.caloriesM = caloriesM;
        this.proteinsM = proteinsM;
        this.fatsM = fatsM;
        this.carbohydratesM = carbohydratesM;
        this.fiberM = fiberM;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMeasurement() {
        return measurement;
    }

    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }

    public Double getCaloriesM() {
        return caloriesM;
    }

    public void setCaloriesM(Double caloriesM) {
        this.caloriesM = caloriesM;
    }

    public Double getProteinsM() {
        return proteinsM;
    }

    public void setProteinsM(Double proteinsM) {
        this.proteinsM = proteinsM;
    }

    public Double getFatsM() {
        return fatsM;
    }

    public void setFatsM(Double fatsM) {
        this.fatsM = fatsM;
    }

    public Double getCarbohydratesM() {
        return carbohydratesM;
    }

    public void setCarbohydratesM(Double carbohydratesM) {
        this.carbohydratesM = carbohydratesM;
    }

    public Double getFiberM() {
        return fiberM;
    }

    public void setFiberM(Double fiberM) {
        this.fiberM = fiberM;
    }
}
