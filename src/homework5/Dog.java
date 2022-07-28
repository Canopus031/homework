package homework5;

import java.util.Random;

public class Dog extends Animal {

    Random limitGenerator = new Random();

    private static int id = 1;
    private int uid;

    public Dog(String name, String color) {
        super(name, color, "Собака");
        this.limitSwim = limitGenerator.nextInt(10) + 1;
        this.limitRun = limitGenerator.nextInt(500) + 1;
        this.limitJump = 0.5f;
        uid = id++; // Подсчет созданных Собак.
    }

    /**
     * @return - Количество созданных собак.
     */

    @Override
    public int getId() {
        return uid;
    }
}
