package edu.fscj.cen3024c.taskmanager.services;

import edu.fscj.cen3024c.taskmanager.entities.Priority;
import edu.fscj.cen3024c.taskmanager.exceptions.PriorityNotFoundException;
import edu.fscj.cen3024c.taskmanager.repositories.PriorityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriorityService {

    private final PriorityRepository priorityRepository;

    public PriorityService(PriorityRepository priorityRepository) {
        this.priorityRepository = priorityRepository;
    }

    public List<Priority> findAll() {
        return priorityRepository.findAll();
    }

    public Priority findById(Integer id) {
        return priorityRepository.findById(id)
                .orElseThrow(() -> new PriorityNotFoundException(id));
    }
}