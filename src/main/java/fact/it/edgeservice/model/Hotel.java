package fact.it.edgeservice.model;

public class Hotel {
    private Long id;
    private String hotelCode;
    private String name;
    private String city;
    private String street;
    private Integer number;

    public Hotel() {
    }

    public Hotel(String hotelCode, String name, String city, String street, Integer number) {
        this.hotelCode = hotelCode;
        this.name = name;
        this.city = city;
        this.street = street;
        this.number = number;
    }

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }

    public String getHotelCode() {
        return hotelCode;
    }

    public void setHotelCode(String hotelCode) {
        this.hotelCode = hotelCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
