package fact.it.edgeservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import fact.it.edgeservice.model.Customer;
import fact.it.edgeservice.model.Employee;
import fact.it.edgeservice.model.Hotel;
import fact.it.edgeservice.model.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class BookingControllerUnitTests {

    @Value("${roomservice.baseurl}")
    private String roomServiceBaseUrl;

    @Value("${hotelservice.baseurl}")
    private String hotelServiceBaseUrl;

    @Value("${customerservice.baseurl}")
    private String customerServiceBaseUrl;

    @Value("${employeeservice.baseurl}")
    private String employeeServiceBaseUrl;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private MockMvc mockMvc;

    private MockRestServiceServer mockServer;
    private ObjectMapper mapper = new ObjectMapper();

    private Room room1Hotel1 = new Room("R01", "H01", 1, 15.00, "goedkoop kamerke");

    private Hotel hotel1 = new Hotel("H01", "Sprinton Palace", "Geel", "Geelsebaan", 52);
    private Hotel hotel2 = new Hotel("H02", "Hotelleke aan zee", "Oostende", "Oostendsebaan", 12);

    private Customer customer1 = new Customer("C01", "R01", "Jeff", "Jeffen", "Antwerpen", "Straat1", 1);
    private Customer customer2 = new Customer("C02", "R02", "Jos", "Jossen", "Gent", "Straat2", 10);

    private Employee employee1 = new Employee("E01", "H01", "Emp", "Loyee", "Barman");
    private Employee employee2 = new Employee("E02", "H02", "Emp", "Loyee", "Kuisvrouw");

    private List<Room> allRoomsFromHotel1 = Arrays.asList(room1Hotel1);
    private List<Hotel> allHotels = Arrays.asList(hotel1, hotel2);
    private List<Employee> allEmployeesHotel1 = Arrays.asList(employee1);
    private List<Customer> allCustomersHotel1 = Arrays.asList(customer1);
    private List<Customer> allCustomersRoom1 = Arrays.asList(customer1);

    @BeforeEach
    public void initializeMockserver() {
        mockServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    public void whenGetRoomsByHotelCode_thenReturnRoomsJson() throws Exception {
        // GET all rooms from Hotel 1
        mockServer.expect(ExpectedCount.once(),
                requestTo(new URI("http://" + roomServiceBaseUrl + "/hotels/H01")))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString((allRoomsFromHotel1))));


        mockMvc.perform(get("/bookings/rooms/{hotelCode}", "H01"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].roomCode", is("R01")))
                .andExpect(jsonPath("$[0].hotelCode", is("H01")))
                .andExpect(jsonPath("$[0].number", is(1)))
                .andExpect(jsonPath("$[0].price", is(15.0)))
                .andExpect(jsonPath("$[0].description", is("goedkoop kamerke")));
    }

    @Test
    public void whenGetHotels_thenReturnHotelsJson() throws Exception{
        mockServer.expect(ExpectedCount.once(),
                requestTo(new URI("http://" + hotelServiceBaseUrl + "/hotels")))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString((allHotels))));

        mockMvc.perform(get("/bookings/hotels"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].hotelCode", is("H01")))
                .andExpect(jsonPath("$[0].name", is("Sprinton Palace")))
                .andExpect(jsonPath("$[0].city", is("Geel")))
                .andExpect(jsonPath("$[0].street", is("Geelsebaan")))
                .andExpect(jsonPath("$[0].number", is(52)))
                .andExpect(jsonPath("$[1].hotelCode", is("H02")))
                .andExpect(jsonPath("$[1].name", is("Hotelleke aan zee")))
                .andExpect(jsonPath("$[1].city", is("Oostende")))
                .andExpect(jsonPath("$[1].street", is("Oostendsebaan")))
                .andExpect(jsonPath("$[1].number", is(12)));
    }

    @Test
    public void whenGetEmployeesByHotelCode_thenReturnEmployeesJson() throws Exception{
        mockServer.expect(ExpectedCount.once(),
                requestTo(new URI("http://" + employeeServiceBaseUrl + "/employees/H01")))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString((allEmployeesHotel1))));

        mockMvc.perform(get("/bookings/employees/{hotelCode}", "H01"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].employeeCode", is("E01")))
                .andExpect(jsonPath("$[0].hotelCode", is("H01")))
                .andExpect(jsonPath("$[0].firstName", is("Emp")))
                .andExpect(jsonPath("$[0].lastName", is("Loyee")))
                .andExpect(jsonPath("$[0].function", is("Barman")));
    }

    @Test
    public void whenGetCustomersByHotelCode_thenReturnCustomersJson() throws Exception{
        mockServer.expect(ExpectedCount.once(),
                requestTo(new URI("http://" + roomServiceBaseUrl + "/hotels/H01")))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString((allRoomsFromHotel1))));

        mockServer.expect(ExpectedCount.once(),
                requestTo(new URI("http://" + customerServiceBaseUrl + "/customers/rooms/R01")))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString((allCustomersRoom1))));

        mockMvc.perform(get("/bookings/customers/{hotelCode}", "H01"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].customerCode", is("C01")))
                .andExpect(jsonPath("$[0].roomCode", is("R01")))
                .andExpect(jsonPath("$[0].firstName", is("Jeff")))
                .andExpect(jsonPath("$[0].lastName", is("Jeffen")))
                .andExpect(jsonPath("$[0].city", is("Antwerpen")))
                .andExpect(jsonPath("$[0].street", is("Straat1")))
                .andExpect(jsonPath("$[0].number", is(1)));
    }
}
