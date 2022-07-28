package homework5;

import java.util.Random;

public class Bird extends Animal {

    Random limitGenerator = new Random();

    private static int id = 1;
    private int uid;

    public Bird(String name, String color) {
        super(name, color, "Птица");
        this.limitRun = limitGenerator.nextInt(3) + 2;
        this.limitJump = 0.2f;
        uid = id++; // Подсчет созданных Птиц.
    }

    @Override
    public void swim(float obstacleLength) {
        System.out.printf("[%s] - %s: Не умеет плавать.\n", getTypeAnimal(), name);
    }

    /**
     * @return - Количество созданных птиц.
     */

    @Override
    public int getId() {
        return uid;
    }
}
