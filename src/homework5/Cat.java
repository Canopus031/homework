package homework5;

import java.util.Random;

public class Cat extends Animal {

    Random limitGenerator = new Random();

    private static int id = 1;
    private int uid;

    public Cat(String name, String color) {
        super(name, color, "Кот");
        this.limitRun = limitGenerator.nextInt(200) + 1;
        this.limitJump = limitGenerator.nextInt(2) + 1;
        uid = id++; // Подсчет созданных котов.
    }

    @Override
    public void swim(float obstacleLength) {
        System.out.printf("[%s] - %s: Не умеет плавать.\n", getTypeAnimal(), name);
    }

    /**
     * @return - Количество созданных котов.
     */

    @Override
    public int getId() {
        return uid;
    }
}
