package com.mindex.challenge;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.impl.ReportingStructureServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReportingStructureImplTest {

    @Autowired
    private ReportingStructureServiceImpl rssi;


    @Test
    /**
     * I can't seem to figure out how to run these but I think they are correct.
     */
    public void test() {
        //John's reports, should be 4
        ReportingStructure rs1 = rssi.read("16a596ae-edd3-4847-99fe-c4518e82c86f");
        assertEquals(4,rs1.getNumberOfReports());

        //Pete's reports, should be 0
        ReportingStructure rs2 = rssi.read("62c1084e-6e34-4630-93fd-9153afb65309");
        assertEquals(0,rs2.getNumberOfReports());
    }
}