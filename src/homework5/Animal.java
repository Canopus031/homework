package homework5;

public class Animal {
    protected String name;
    protected String color;
    protected String typeAnimal;
    protected float limitSwim;
    protected float limitRun;
    protected float limitJump;
    protected int uid;

    /**
     * @param name       - Имя животного.
     * @param color      - Цвет животного.
     * @param typeAnimal - Тип животного ( Dog, Cat ...etc).
     */

    public Animal(String name, String color, String typeAnimal) {
        this.name = name;
        this.color = color;
        this.typeAnimal = typeAnimal;
        this.limitSwim = 0;
        this.limitRun = 0;
        this.limitJump = 0;
    }

    /**
     * @param obstacleLength - Длина бега.
     */

    public void run(float obstacleLength) {
        if (limitRun >= obstacleLength) {
            System.out.printf("[%s] - %s: Пробежал %s м.\n", getTypeAnimal(), name, obstacleLength);
        } else {
            System.out.printf("[%s] - %s: Не смог пробежать %s м.\n", getTypeAnimal(), name, obstacleLength);
        }
    }

    /**
     * @param obstacleLength - Длина плавания.
     */

    public void swim(float obstacleLength) {
        if (limitSwim >= obstacleLength) {
            System.out.printf("[%s] - %s: Проплыл %s м.\n", getTypeAnimal(), name, obstacleLength);
        } else {
            System.out.printf("[%s] - %s: Не смог проплыть %s м.\n", getTypeAnimal(), name, obstacleLength);
        }
    }

    /**
     * @param obstacleHeight - Высота прыжков.
     */

    public void jump(float obstacleHeight) {
        if (limitJump >= obstacleHeight) {
            System.out.printf("[%s] - %s: Прыжок удался. Длина прыжка: %s м.\n", getTypeAnimal(), name, obstacleHeight);
        } else {
            System.out.printf("[%s] - %s: Прыжок не удался.\n", getTypeAnimal(), name);
        }
    }

    /**
     * @return - Тип животного.
     */

    public String getTypeAnimal() {
        return typeAnimal;
    }

    public int getId() {
        return uid;
    }

    public String getInfoAnimal() {
        return "Кол-во: [" + this.getId() + "] " + "Тип: [" + this.typeAnimal + "] - " + this.name + ": "
                + this.color + ". Предел: Бега: " + this.limitRun
                + " м., плавания: " + this.limitSwim + " м., прыжка: " + this.limitJump + " м.";
    }

}

