package edu.fscj.cen3024c.taskmanager.controllers;

import edu.fscj.cen3024c.taskmanager.dto.SubtaskDTO;
import edu.fscj.cen3024c.taskmanager.entities.Subtask;
import edu.fscj.cen3024c.taskmanager.services.SubtaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subtasks")
public class SubtaskController {

    private final SubtaskService subtaskService;

    public SubtaskController(SubtaskService subtaskService) {
        this.subtaskService = subtaskService;
    }

    @GetMapping
    public List<SubtaskDTO> getAllSubtasks() {
        return subtaskService.findAll();
    }

    @GetMapping("/{id}")
    public SubtaskDTO getSubtaskById(@PathVariable Integer id) {
        return subtaskService.findById(id);
    }

    @PostMapping
    public SubtaskDTO createSubtask(@RequestBody Subtask subtask) {
        return subtaskService.save(subtask);
    }

    @PutMapping("/{id}")
    public SubtaskDTO updateSubtask(
            @PathVariable Integer id,
            @RequestBody Subtask subtask) {
        return subtaskService.updateSubtask(id, subtask);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubtask(@PathVariable Integer id) {
        subtaskService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}