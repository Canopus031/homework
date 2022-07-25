package homework4;

public class Employee {
    private static int count = 1; // счетчик
    private int id; // Уникальный порядковый номер.
    private String firstName;
    private String lastName;
    private String middleName;
    private String post;
    private String email;
    private String numberPhone;
    private int salary = 0;
    private int age = 0;

    /**
     * Конструктор класса Employee.
     * @param lastName - Фамилия.
     * @param firstName - Имя.
     * @param middleName - Отчество.
     * @param post - Должность.
     * @param email - email Почта.
     * @param numberPhone - Номер телефона.
     * @param salary - Заработная плата.
     * @param age - Возраст.
     */

    Employee(String lastName, String firstName, String middleName,
             String post, String email, String numberPhone, int salary, int age) {

        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.post = post;
        this.email = email;
        this.numberPhone = numberPhone;
        this.salary = salary;
        this.age = age;
        id = IdUnique(); // Присваивает уникальный порядковый номер.
    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getPost() {
        return post;
    }

    public String getEmail() {
        return email;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public int getSalary() {
        return salary;
    }

    public int getAge() {
        return age;
    }

    public int getId() {
        return id;
    }

    /**
     * Метод увеличивает заработную плату [salary] на параметр raising.
     * @param raising - Число, на которое увеличивается заработная плата.
     */

    public void salaryIncrease(int raising) {
        salary += raising;
    }

    /**
     * Метод увеличивает переменную count на 1
     * @return - увеличенное значение count на 1
     */

    public int IdUnique() {
        return count++;
    }

}
