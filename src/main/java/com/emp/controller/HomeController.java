package com.emp.controller;

import com.emp.entity.Employee;
import com.emp.service.EmpService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private EmpService empService;

    @GetMapping("/")
    public String index(Model m) {
        List<Employee> list = empService.getAllEmp();
        m.addAttribute("empList", list);

        return "index";
    }

    @GetMapping("/save")
    public String loadSaveEmp() {
        return "save";
    }

    @GetMapping("/edit/{id}")
    public String empEdit(@PathVariable int id, Model m) {
        Employee emp = empService.getEmpById(id);
        m.addAttribute("emp", emp);
        return "edit";
    }

    @PostMapping("/save")
    public String saveEmp(@ModelAttribute Employee emp, HttpSession session) {
//        System.out.println(emp);
        Employee newEmp = empService.saveEmp(emp);
        if (newEmp != null) {
//            System.out.println("save success");
            session.setAttribute("msg", "Registered Successfully");
        } else {
//            System.out.println("save no success");
            session.setAttribute("msg", "Something wrong on server");
        }
        return "redirect:/save";
    }

    @PostMapping("/updateEmp")
    public String updateEmp(@ModelAttribute Employee emp, HttpSession session) {
//        System.out.println(emp);
        Employee updateEmp = empService.saveEmp(emp);
        if (updateEmp != null) {
//            System.out.println("save success");
            session.setAttribute("msg", "Updated Successfully");
        } else {
//            System.out.println("save no success");
            session.setAttribute("msg", "Updated Successfully");
        }
        return "redirect:/";
    }

    @GetMapping("/deleteEmp/{id}")
    public String deleteEmp(@PathVariable int id, HttpSession session) {
        boolean f = empService.deleteEmp(id);
        if (f) {
            session.setAttribute("msg", "Deleted Successfully");
        } else {
            session.setAttribute("msg", "Something went wrong");
        }
        return "redirect:/";
    }

}
