package homework5;

public class Dog extends Animal {

    private static int id = 1;
    private int uid;

    public Dog(String name, String color, float limitSwim, float limitRun, float limitJump) {
        super(name, color, limitSwim, limitRun, limitJump, "Собака");
        uid = id++; // Подсчет созданных собак.
    }

    /**
     * @return - Количество созданных собак.
     */

    @Override
    public int getId() {
        return uid;
    }
}
