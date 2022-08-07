import com.onurozdemir.javabootcamp.project.model.InsuranceAgency;
import com.onurozdemir.javabootcamp.project.repository.InsuranceAgencyRepository;
import com.onurozdemir.javabootcamp.project.util.DatabaseConnection;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class InsuranceAgencyTest {
    @Test
    public void getAllInsuranceAgencyRepositoryTest() {
        InsuranceAgencyRepository insuranceAgencyRepository = new InsuranceAgencyRepository(DatabaseConnection.setup());
        List<InsuranceAgency> insuranceAgencyList = insuranceAgencyRepository.getAll();
        assertEquals(insuranceAgencyList.size(), 5);
    }
}
