package fact.it.edgeservice.controller;

import fact.it.edgeservice.model.Booking;
import fact.it.edgeservice.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@RestController
public class BookingController {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${roomservice.baseurl}")
    private String roomserviceBaseUrl;

    @Value("${hotelservice.baseurl}")
    private String hotelserviceBaseUrl;

    @Value("${customerservice.baseurl}")
    private String customerserviceBaseUrl;

    @Value("${employeeservice.baseurl}")
    private String employeeserviceBaseUrl;

    @GetMapping("/bookings/hotel/{hotelCode}")
    public List<Room> getRoomsByHotelCode(@PathVariable String hotelCode){
        ResponseEntity<List<Room>> responseEntityRooms =
                restTemplate.exchange("http://" + roomserviceBaseUrl + "/hotels/" + hotelCode,
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Room>>() {
                        }, hotelCode);

        List<Room> rooms = responseEntityRooms.getBody();

        return rooms;
    }


}
