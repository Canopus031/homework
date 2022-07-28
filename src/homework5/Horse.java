package homework5;

import java.util.Random;

public class Horse extends Animal {

    Random limitGenerator = new Random();

    private static int id = 1;
    private int uid;

    public Horse(String name, String color) {
        super(name, color, "Лошадь");
        this.limitSwim = limitGenerator.nextInt(100) + 1;
        this.limitRun = limitGenerator.nextInt(1500) + 1;
        this.limitJump = limitGenerator.nextInt(3) + 1;
        uid = id++; // Подсчет созданных Лошадей.
    }

    /**
     * @return - Количество созданных лошадей.
     */

    @Override
    public int getId() {
        return uid;
    }
}