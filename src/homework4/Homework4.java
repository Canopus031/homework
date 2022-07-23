package homework4;

public class Homework4 {

    public static void main(String[] args) {
        Employee employee = new Employee("Ivanov", "Ivan", "Yovanovitch", "Engineer", "ivan@gmail.com", "89537772356", 30000, 23);

        System.out.printf("Сотрудник: %s %s %s. Должность сотрудника: %s\n", employee.getLastName(), employee.getFirstName(), employee.getMiddleName(), employee.getPost());

        Employee[] employees = {
                new Employee(
                        "Hvorostovsky", "Dmitriy", "Yuryevich", "General manager",
                        "dmitriyhvorostovsky@gmail.com", "89508647443", 150000, 45),
                new Employee(
                        "Melnikova", "Ksenia", "Sergeevna", "Consultant",
                        "melnikova@gmail.com", "8953255490", 40000, 35),
                new Employee(
                        "Petrov", "Daniel", "Aleksandrovich", "Deputy General Director",
                        "petrovDaniel@gmail.com", "89053074230", 90000, 43),
                new Employee(
                        "Smirnova", "Lisa", "Vyacheslavovna", "Intern",
                        "smirnovaLisa@gmail.com", "89532356071", 27000, 25),
                new Employee(
                        "Kuznetsov", "Denis", "Vyacheslavovich", "Consultant",
                        "ivan@gmail.com", "89053346129", 40000, 43)
        };

        /*
         * Внутри цикла проводится проверка,
         * если сотрудник старше 40 лет, то в консоль выводится полная информация о нем.
         */

        for (int i = 0; i < employees.length; i++) {
            if (employees[i].getAge() > 40) {
                System.out.printf("ID: %s. Сотрудник: %s %s %s. Должность: %s. Email: %s. Number: %s. Salary: %d. Age: %d\n", employees[i].getId(),
                        employees[i].getLastName(), employees[i].getFirstName(), employees[i].getMiddleName(), employees[i].getPost(),
                        employees[i].getEmail(), employees[i].getNumberPhone(), employees[i].getSalary(), employees[i].getAge());
            }
        }

        System.out.println();

        /*
         * Внутри цикла проводится проверка, если сотрудник старше 35 лет,
         * то вызывается метод salaryIncrease.
         */

        for (int i = 0; i < employees.length; i++) {
            if (employees[i].getAge() > 35) {
                employees[i].salaryIncrease(10000);
            }
        }

    }

}
