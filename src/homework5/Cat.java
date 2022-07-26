package homework5;

public class Cat extends Animal {

    private static int id = 1;
    private int uid;

    public Cat(String name, String color, float limitSwim, float limitRun, float limitJump) {
        super(name, color, limitSwim, limitRun, limitJump, "Кот");
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
