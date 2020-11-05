package fact.it.edgeservice.controller;

import fact.it.edgeservice.model.*;
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

    @GetMapping("/bookings/rooms/{hotelCode}")
    public List<Room> getRoomsByHotelCode(@PathVariable String hotelCode){
        ResponseEntity<List<Room>> responseEntityRooms =
                restTemplate.exchange("http://" + roomserviceBaseUrl + "/hotels/" + hotelCode,
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Room>>() {
                        }, hotelCode);

        List<Room> rooms = responseEntityRooms.getBody();

        return rooms;
    }

    @GetMapping("/bookings/hotels")
    public List<Hotel> getHotels(){
        ResponseEntity<List<Hotel>> responseEntityHotels =
                restTemplate.exchange("http://" + hotelserviceBaseUrl + "/hotels",
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Hotel>>() {
                        });

        List<Hotel> hotels = responseEntityHotels.getBody();

        return hotels;
    }

    @GetMapping("/bookings/employees/{hotelCode}")
    public List<Employee> getEmployeesByHotelCode(@PathVariable String hotelCode){
        ResponseEntity<List<Employee>> responseEntityEmployees =
                restTemplate.exchange("http://" + employeeserviceBaseUrl + "/employees/" + hotelCode,
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Employee>>() {
                        }, hotelCode);

        List<Employee> employees = responseEntityEmployees.getBody();

        return employees;
    }

    @GetMapping("/bookings/customers/{hotelCode}")
    public List<Customer> getCustomersByHotelCode(@PathVariable String hotelCode){

        //List<Customer> returnList= new ArrayList();

        ResponseEntity<List<Room>> responseEntityRooms =
                restTemplate.exchange("http://" + roomserviceBaseUrl + "/hotels/" + hotelCode,
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Room>>() {
                        }, hotelCode);

        List<Room> rooms = responseEntityRooms.getBody();

        //lijst customers aanmaken
        List<Customer> customers = new ArrayList();

        for (Room room: rooms) {
            ResponseEntity<List<Customer>> responseEntityCustomers =
                    restTemplate.exchange("http://" + customerserviceBaseUrl + "/customers/rooms/{roomCode}",
                            HttpMethod.GET, null, new ParameterizedTypeReference<List<Customer>>() {
                            }, room.getRoomCode());
            //returnList.add(new Customer(responseEntityCustomers.getBody());

            //eerste element van lijst dat je krijgt van getBody() toevoegen
            customers.add(responseEntityCustomers.getBody().get(0));
        }
        return customers;
    }
}
