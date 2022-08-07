import com.onurozdemir.javabootcamp.project.model.Customer;
import com.onurozdemir.javabootcamp.project.repository.CustomerRepository;
import com.onurozdemir.javabootcamp.project.util.DatabaseConnection;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.assertEquals;

public class CustomerRepositoryTest {
    @Test
    public void getAllCustomerRepositoryTest() {
        CustomerRepository customerRepository = new CustomerRepository(DatabaseConnection.setup());
        List<Customer> customers = customerRepository.getAll();
        assertEquals(customers.size(), 100);
    }
}
