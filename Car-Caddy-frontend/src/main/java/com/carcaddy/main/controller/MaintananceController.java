package com.carcaddy.main.controller;

import com.carcaddy.main.model.Maintainance;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RequestMapping
@Controller
public class MaintananceController {

    private final RestTemplate restTemplate;

    @Autowired
    public MaintananceController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/maintenance")
    public String showMaintenanceForm(Model model) {
        model.addAttribute("maintainance", new Maintainance());
        return "create-maintenance";
    }

    @PostMapping("/delete/{id}")
    public String deleteRequest(@PathVariable Integer id){
        String url = "http://localhost:8080/maintenance/delete/"+id;
//        System.out.println(url);
        String response = restTemplate.getForObject(url, String.class);
        System.out.println(response.toString());
        return "redirect:/maintenance/list";
    }

    @PostMapping("/maintenance/edit/{id}")
    public String updateRecord(@PathVariable("id") Integer id,@ModelAttribute("maintenance") Maintainance maintainance, Model model){
        System.out.println("updated maintenance data : "+maintainance.toString());
        boolean flag = false;
        if(maintainance.getCarId()<0){
            flag = true;
            model.addAttribute("carIdError","Car Id must be positive number");
        }
        if(maintainance.getDescription().length()>=255){
            flag = true;
            model.addAttribute("DescriptionError","Description cannot exceed 255 characte.");
        }
        if(maintainance.getMaintenanceStatus().length()==0){
            flag = true;
            model.addAttribute("statusError","Maintenance type is required.");
        }
        if(maintainance.getMaintenanceType().length()==0){
            flag = true;
            model.addAttribute("typeError","Maintenance type is required.");
        }
        if(flag) {
            return "/maintenance/edit/{id}(id=${id})";
        }
        String url = "http://localhost:8080/maintenance/edit/"+id;
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<Maintainance> request = new HttpEntity<>(maintainance, headers);
        ResponseEntity<?> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
        return "redirect:/maintenance/list";
    }

    @PostMapping("/register")
    public String submitMaintenanceForm(@ModelAttribute("maintainance") Maintainance maintainance, Model model){
        System.out.println(maintainance.toString());
        boolean flag = false;
        if(maintainance.getCarId()<0){
            flag = true;
            model.addAttribute("carIdError","Car Id must be positive number");
        }
        if(maintainance.getDescription().length()>=255){
            flag = true;
            model.addAttribute("DescriptionError","Description cannot exceed 255 characters.");
        }
        if(maintainance.getMaintenanceStatus().length()==0){
            flag = true;
            model.addAttribute("statusError","Maintenance type is required.");
        }
        if(maintainance.getMaintenanceType().length()==0){
            flag = true;
            model.addAttribute("typeError","Maintenance type is required.");
        }
        if(flag) {
            return "create-maintenance";
        }
        String url = "http://localhost:8080/maintenance/create";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<Maintainance> request = new HttpEntity<>(maintainance, headers);
        ResponseEntity<?> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
        System.out.println(response.toString());
        return "redirect:/maintenance/list";
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
        String url = "http://localhost:8080/maintenance/data/"+id;
        String response = restTemplate.getForObject(url, String.class);
       // Convert the JSON response to a List of MaintenanceRecord objects
       ObjectMapper objectMapper = new ObjectMapper();
       try {
           Maintainance maintenance = objectMapper.readValue(response,new TypeReference<Maintainance>(){});
           model.addAttribute("maintenance", maintenance);
       } catch (Exception e) {
           e.printStackTrace();
       }
        return "edit-maintenance"; // Return the edit form view
    }
}
