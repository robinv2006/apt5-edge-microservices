package fact.it.edgeservice.model;

public class Booking {
    private String roomCode;
    private String hotelCode;
    private String customerCode;
    private String employeeCode;

    public Booking(Hotel hotel, Room room, Customer customer, Employee employee) {
        this.roomCode = room.getRoomCode();
        this.hotelCode = hotel.getHotelCode();
        this.customerCode = customer.getCustomerCode();
        this.employeeCode = employee.getEmployeeCode();
    }
}
