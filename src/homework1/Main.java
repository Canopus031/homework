package homework1;

public class Main {

    public static void main(String[] args) {

        int integerValue = 10;
        long longValue = 124000000;
        byte byteValue = 127;
        short shortValue = 32000;
        float floatValue = 1.0f;
        double doubleValue = 3.5d;
        char charValue = 'A';
        boolean booleanValue = true;

        // Вызов метода напечатает результат вычисления аргументов
        System.out.println(expressionResult(1.2f, 0.7f, 5f, 2f));

        // Вызов метода напечатает True если сумма аргументов лежит в диапозоне от 10 до 20, иначе False
        System.out.println("Сумма чисел находится в пределах от 10 до 20 (включительно): " + checkSum(2, 10));

        // Вызов метода напечатает является число положительным или нет
        positiveCheckNumber(-1);

        // Вызов метода печатает в консоли True если число отрицательное, иначе False для положительного
        System.out.println("Если число отрицательное, то true, если положительное, то false: " + negetiveCheckNumber(-5));

        // Вызов метода напечатает в консоли «Привет, name», где name аргумент переданный методу
        sayHelloName("Алексей");

        // Вызов метода вычисляет является ли год високосным если да, то печатает "year год является високосным",
        // где year аргумент переданный методу. Иначе "year год не является високосным"
        checkingLeapYear(2024);

    }

    /*
       (3) Написать метод вычисляющий выражение a * (b + (c / d)) и возвращающий результат,
        где a, b, c, d – аргументы этого метода, имеющие тип float.
    */

    public static float expressionResult(float a, float b, float c, float d) {
        return a * (b + (c / d));
    }

    /*
       (4) Написать метод, принимающий на вход два целых числа и проверяющий, что их сумма лежит
        в пределах от 10 до 20 (включительно), если да – вернуть true, в противном случае – false.
    */

    public static boolean checkSum(int firstInteger, int secondInteger) {

        int sum = firstInteger + secondInteger;
        if (sum >= 10 && sum <= 20) {
            return true;
        } else {
            return false;
        }

    }

     /*
       (5) Написать метод, которому в качестве параметра передается целое число, метод должен
        напечатать в консоль, положительное ли число передали или отрицательное. Замечание:
        ноль считаем положительным числом
    */

    public static void positiveCheckNumber(int number) {

        if (number >= 0) {
            System.out.println("Число " + number + " положительное");
        } else {
            System.out.println("Число " + number + " отрицательное");
        }

    }

    /*
      (6)  Написать метод, которому в качестве параметра передается целое число. Метод должен
        вернуть true, если число отрицательное, и вернуть false если положительное.
    */

    public static boolean negetiveCheckNumber(int number) {

        if (number < 0) {
            return true;
        } else {
            return false;
        }

    }

    /*
      (7) Написать метод, которому в качестве параметра передается строка, обозначающая имя.
        Метод должен вывести в консоль сообщение «Привет, указанное_имя!»
    */

    public static void sayHelloName(String name) {
        System.out.println("Привет, " + name + "!");
    }

    /*
      (8) Написать метод, который определяет, является ли год високосным, и выводит сообщение в
        консоль. Каждый 4-й год является високосным, кроме каждого 100-го, при этом каждый 400-й –
        високосный
    */

    public static void checkingLeapYear(int year) {

        if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
            System.out.println(year + " год является високосным");
        } else {
            System.out.printf(year + " год не является високосным");
        }

    }
}
