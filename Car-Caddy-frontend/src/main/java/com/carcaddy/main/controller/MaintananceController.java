package com.carcaddy.main.controller;

import com.carcaddy.main.model.Maintainance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

@Controller
public class MaintananceController {

    private RestTemplate restTemplate;

    @Autowired
    public MaintananceController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/")
    public String index() {
        String url = "http://localhost:8080/maintenance/data"; // Replace with the actual API URL
        String response = restTemplate.getForObject(url, String.class);
//        model.addAttribute("data", response);
        System.out.println(response);
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
        String url = "http://localhost:8080/maintenance/data"; // Replace with the actual API URL
        String response = restTemplate.getForObject(url, String.class);
        model.addAttribute("data", response);
        return "view-maintenance"; // No redirect needed, render the template directly
    }

    @GetMapping("/maintenance/delete/{id}")
    public String deleteMaintenanceRecord(@PathVariable("id") Integer id, Model model) {
//      maintananceService.deleteMaintenance(id); // Call the service to delete the record
        return "redirect:/maintenance/list"; // Redirect to the maintenance list page
    }

    @GetMapping("/maintenance/edit/{id}")
    public String showEditMaintenanceForm(@PathVariable("id") Integer id, Model model) {
//      Maintainance record = maintananceService.getMaintenanceById(id); // Get the record by ID
//      model.addAttribute("record", record);
        return "edit-maintenance"; // Return the edit form view
    }
}
