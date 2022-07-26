package homework5;

public class Horse extends Animal {

    protected static int id = 1;
    protected int uid;

    public Horse(String name, String color, float limitSwim, float limitRun, float limitJump) {
        super(name, color, limitSwim, limitRun, limitJump, "Лошадь");
        uid = id++; // Подсчет созданных лошадей.
    }

    /**
     * @return - Количество созданных лошадей.
     */

    @Override
    public int getId() {
        return uid;
    }
}