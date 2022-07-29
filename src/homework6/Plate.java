package homework6;

public class Plate {
    private int food;

    public Plate(int food) {
        this.food = food;
    }

    public void decreaseFood(int n) {
        food -= n;
    }

    public boolean isEnoughFood(int n) {
        return food >= n;
    }

    public int getFood() {
        return food;
    }

    public int setFood(int quantity) {
        if (quantity > 0) {
            food += quantity;
        }
        return food;
    }

}