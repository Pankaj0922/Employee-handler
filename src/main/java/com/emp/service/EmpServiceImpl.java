package com.emp.service;

import com.emp.entity.Employee;
import com.emp.repository.EmpRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;
import java.util.Optional;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpRepository repository;

    @Override
    public Employee saveEmp(Employee emp) {
        Employee newEmp = repository.save(emp);
        return newEmp;
    }

    @Override
    public List<Employee> getAllEmp() {

        return repository.findAll();
    }

    @Override
    public Employee getEmpById(int id) {
        Optional<Employee> optionalEmployee = repository.findById(id);
        return optionalEmployee.orElse(null);
    }


    @Override
    public boolean deleteEmp(int id) {
        Optional<Employee> optionalEmployee = repository.findById(id);
        if (optionalEmployee.isPresent()) {
            repository.delete(optionalEmployee.get());
            return true;
        }
        return false;
    }

    public void removeSessionMessage() {
        HttpSession session = ((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes())).getRequest().getSession();
        session.removeAttribute("msg");
    }
}
