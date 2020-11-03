package fact.it.edgeservice.model;

public class Employee {
    private String id;
    private String employeeCode;
    private String hotelCode;
    private String firstName;
    private String lastName;
    private String function;

    public Employee() {
    }

    public Employee(String employeeCode, String hotelCode, String firstName, String lastName, String function) {
        this.employeeCode = employeeCode;
        this.hotelCode = hotelCode;
        this.firstName = firstName;
        this.lastName = lastName;
        this.function = function;
    }

    /*public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }*/

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getHotelCode() {
        return hotelCode;
    }

    public void setHotelCode(String hotelCode) {
        this.hotelCode = hotelCode;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }
}
