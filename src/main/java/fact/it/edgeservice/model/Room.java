package fact.it.edgeservice.model;

public class Room {
    //mongo db: id's are strings
    private String id;
    private String roomCode ;
    private String hotelCode;



    private Integer number;
    private Double price;
    private String description;

    public Room() {
    }

    public Room(String roomCode, String hotelCode, Integer number, Double price, String description) {
        this.roomCode = roomCode;
        this.hotelCode = hotelCode;
        this.number = number;
        this.price = price;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoomCode() {
        return roomCode;
    }

    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
    }

    public String getHotelCode() {
        return hotelCode;
    }

    public void setHotelCode(String hotelCode) {
        this.hotelCode = hotelCode;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
