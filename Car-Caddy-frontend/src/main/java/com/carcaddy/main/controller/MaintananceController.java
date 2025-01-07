package com.carcaddy.main.controller;

import com.carcaddy.main.model.Maintainance;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
public class MaintananceController {

    private final RestTemplate restTemplate;

    @Autowired
    public MaintananceController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/")
    public String index() {
//        String url = "http://localhost:8080/maintenance/data"; // Replace with the actual API URL
//        String response = restTemplate.getForObject(url, String.class);
//        model.addAttribute("data", response);
//        System.out.println(response);
        return "index";
    }

    @GetMapping("/maintenance")
    public String showMaintenanceForm(Model model) {
        model.addAttribute("record", new Maintainance());
        System.out.println("Code is running , REQUEST IS CAME HERE.............");
        return "create-maintenance";
    }

    @GetMapping("/maintenance/list")
    public String viewMaintenanceRecords(Model model) {
        String url = "http://localhost:8080/maintenance/data";
        String response = restTemplate.getForObject(url, String.class);
        // Convert the JSON response to a List of MaintenanceRecord objects
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<Maintainance> maintenanceList = objectMapper.readValue(response, new TypeReference<List<Maintainance>>() {
            });
            System.out.println(maintenanceList);
            model.addAttribute("records", maintenanceList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "view-maintenance"; // No redirect needed, render the template directly
    }

    @GetMapping("/maintenance/delete/{id}")
    public String deleteMaintenanceRecord(@PathVariable("id") Integer id, Model model) {
        String url = "localhost:8080/maintenance/delete/" + id;
        String response = restTemplate.getForObject(url, String.class);
        return "redirect:/maintenance/list";
    }

    @GetMapping("/maintenance/edit/{id}")
    public String showEditMaintenanceForm(@PathVariable("id") Integer id, Model model) {
//        System.out.println(maintenanceList);
//        model.addAttribute("record", maintenanceList);
        return "edit-maintenance"; // Return the edit form view
    }
}
