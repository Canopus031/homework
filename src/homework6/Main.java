package homework6;

import java.util.Scanner;

public class Main {

    /**
     * Задание из методички:
     * 1. Расширить задачу про котов и тарелки с едой.
     * 2. Сделать так, чтобы в тарелке с едой не могло получиться отрицательного количества еды
     * (например, в миске 10 еды, а кот пытается покушать 15-20).
     * 3. Каждому коту нужно добавить поле сытость (когда создаем котов, они голодны). Если коту
     * удалось покушать (хватило еды), сытость = true.
     * 4. Считаем, что если коту мало еды в тарелке, то он её просто не трогает, то есть не может быть
     * наполовину сыт (это сделано для упрощения логики программы).
     * 5. Создать массив котов и тарелку с едой, попросить всех котов покушать из этой тарелки и
     * потом вывести информацию о сытости котов в консоль.
     * 6. Добавить в тарелку метод, с помощью которого можно было бы добавлять еду в тарелку.
     */

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Cat[] cats = {
                new Cat("Барсик", 20),
                new Cat("Мурка", 20),
                new Cat("Том", 20),
                new Cat("Рокки", 20),
                new Cat("Нарцисс", 20),
        };
        Plate plate = new Plate(100);

        System.out.println("****** Информация о котах ******");

        for (int i = 0; i < cats.length; i++) {
            System.out.printf("%s, аппетит: %s ед. еды.\n", cats[i].getName(), cats[i].getAppetite());
        }

        System.out.println("***** Кол-во еды в тарелке *****");
        System.out.println("Тарелка: " + plate.getFood() + " ед. еды");
        System.out.println("********************************");

        for (int i = 0; i < cats.length; i++) {
            cats[i].eat(plate);
            System.out.printf("Кот %s - %s\n", cats[i].getName(), ((cats[i].getSatiety())) ? "сытый" : "голодный");
        }

        System.out.println("Вы можете добавить еду в тарелку или отказаться. 0 = отказ.");
        int answer = scanner.nextInt();
        if (answer > 0) {
            plate.setFood(answer);
        } else {
            System.out.println("К сожалению, котики останутся без пищи :(");
        }
        System.out.println("Тарелка: " + plate.getFood() + " ед. еды");
    }


}
