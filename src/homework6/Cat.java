package homework6;

public class Cat {
    private String name;
    private int appetite;

    private boolean satiety; // Сытость.

    public Cat(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;
        this.satiety = false;
    }

    public void eat(Plate plate) {
        if (plate.isEnoughFood(appetite)) {
            plate.decreaseFood(appetite);
            this.satiety = true;
        } else {
            System.out.println("В тарелке недостаточно еды");
        }
    }

    public String getName() {
        return name;
    }

    public boolean getSatiety() {
        return satiety;
    }

    public int getAppetite() {
        return appetite;
    }
}
