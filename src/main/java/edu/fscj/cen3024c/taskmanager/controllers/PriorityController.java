package edu.fscj.cen3024c.taskmanager.controllers;

import edu.fscj.cen3024c.taskmanager.entities.Priority;
import edu.fscj.cen3024c.taskmanager.services.PriorityService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/priorities")
public class PriorityController {

    private final PriorityService priorityService;

    public PriorityController(PriorityService priorityService) {
        this.priorityService = priorityService;
    }

    @GetMapping
    public List<Priority> getAllPriorities() {
        return priorityService.findAll();
    }

    @GetMapping("/{id}")
    public Priority getPriorityById(@PathVariable Integer id) {
        return priorityService.findById(id);
    }
}