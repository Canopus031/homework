package homework5;

public class Bird extends Animal {

    private static int id = 1;
    private int uid;

    public Bird(String name, String color, float limitSwim, float limitRun, float limitJump) {
        super(name, color, limitSwim, limitRun, limitJump, "Птица");
        uid = id++; // Подсчет созданных птиц.
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
