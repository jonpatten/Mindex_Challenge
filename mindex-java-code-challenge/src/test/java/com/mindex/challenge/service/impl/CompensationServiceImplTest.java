package com.mindex.challenge;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.impl.CompensationServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CompensationServiceImplTest {

    @Autowired
    private CompensationServiceImpl cs;
    @Autowired
    private EmployeeRepository employeeRepository;


    @Test
    /**
     * I'm not sure how to mock it up, but in this test I would've created a new
     * compensation for an existing employee using create, then checked to make
     * sure the compensation had been saved with the correct information using read.
     */
    public void test() {
    }
}