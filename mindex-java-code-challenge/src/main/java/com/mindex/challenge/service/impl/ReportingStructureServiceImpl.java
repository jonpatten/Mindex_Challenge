package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportingStructureServiceImpl implements ReportingStructureService {

    private static final Logger LOG = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public ReportingStructure read(String id) {
        LOG.debug("Reading reporting structure for employee with id [{}]", id);

        Employee employee = employeeRepository.findByEmployeeId(id);

        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }

        return new ReportingStructure(employee, calcNumberOfReports(employee));
    }

    /**
     * Recursively calculates the number of direct and indirect reports for the given employee
     * @param e: The employee to find the reporting structure of
     * @return: the number of direct and indirect reports for employee e
     */
    private int calcNumberOfReports(Employee e){

        if(e.getDirectReports() == null){
            return 0;
        }

        int reports = 0;

        for(Employee e1 : e.getDirectReports()){
            reports++;
            e1 = employeeRepository.findByEmployeeId(e1.getEmployeeId());
            reports += calcNumberOfReports(e1);
        }

        return reports;
    }
}
