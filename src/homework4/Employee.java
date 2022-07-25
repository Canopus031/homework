package homework4;

public class Employee {
    private static int count = 1; // �������
    private int id; // ���������� ���������� �����.
    private String firstName;
    private String lastName;
    private String middleName;
    private String post;
    private String email;
    private String numberPhone;
    private int salary = 0;
    private int age = 0;

    /**
     * ����������� ������ Employee.
     * @param lastName - �������.
     * @param firstName - ���.
     * @param middleName - ��������.
     * @param post - ���������.
     * @param email - email �����.
     * @param numberPhone - ����� ��������.
     * @param salary - ���������� �����.
     * @param age - �������.
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
        id = IdUnique(); // ����������� ���������� ���������� �����.
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
     * ����� ����������� ���������� ����� [salary] �� �������� raising.
     * @param raising - �����, �� ������� ������������� ���������� �����.
     */

    public void salaryIncrease(int raising) {
        salary += raising;
    }

    /**
     * ����� ����������� ���������� count �� 1
     * @return - ����������� �������� count �� 1
     */

    public int IdUnique() {
        return count++;
    }

}
