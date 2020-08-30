package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.service.CompensationService;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.Compensation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompensationServiceImpl implements CompensationService {

    private static final Logger LOG = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private CompensationRepository compensationRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    /**
     * Note that this will only work if the employee exists in the employee repository, adding a compensation
     * for an employee that does not exist will store the compensation but it won't be possible to get it back with a
     * GET. It also reads by matching the exact employee object rather than just the employee ID, which is not ideal.
     */
    public Compensation read(String employeeId) {
        LOG.debug("Reading compensation for employee with id [{}]", employeeId);

        Employee employee = employeeRepository.findByEmployeeId(employeeId);

        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + employeeId);
        }

        Compensation compensation = compensationRepository.findByEmployee(employee);

        if (compensation == null) {
            throw new RuntimeException("Couldn't find compensation matching employee: " + employeeId);
        }

        return compensation;
    }

    @Override
    /**
     * Given the way the above read method works, it might be better to only allow compensations
     * to be saved for employees that already exist within the employee repository, rather than trusting
     * the user to do it correctly themselves.
     */
    public Compensation create(Compensation compensation) {
        LOG.debug("Creating compensation [{}]", compensation);

        compensationRepository.insert(compensation);

        return compensation;
    }
}
