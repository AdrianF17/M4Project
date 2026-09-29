package edu.fscj.cen3024c.taskmanager.services;

import edu.fscj.cen3024c.taskmanager.dto.SubtaskDTO;
import edu.fscj.cen3024c.taskmanager.entities.Subtask;
import edu.fscj.cen3024c.taskmanager.entities.Task;
import edu.fscj.cen3024c.taskmanager.enums.SubtaskStatus;
import edu.fscj.cen3024c.taskmanager.exceptions.SubtaskNotFoundException;
import edu.fscj.cen3024c.taskmanager.repositories.SubtaskRepository;
import edu.fscj.cen3024c.taskmanager.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubtaskService {

    private final SubtaskRepository subtaskRepository;
    private final TaskRepository taskRepository;

    public SubtaskService(SubtaskRepository subtaskRepository,
                          TaskRepository taskRepository) {
        this.subtaskRepository = subtaskRepository;
        this.taskRepository = taskRepository;
    }

    public List<SubtaskDTO> findAll() {
        return subtaskRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public SubtaskDTO findById(Integer id) {
        Subtask subtask = subtaskRepository.findById(id)
                .orElseThrow(() -> new SubtaskNotFoundException(id));
        return convertToDTO(subtask);
    }

    public Subtask findByIdEntity(Integer id) {
        return subtaskRepository.findById(id)
                .orElseThrow(() -> new SubtaskNotFoundException(id));
    }

    public SubtaskDTO save(Subtask subtask) {
        if (subtask.getStatus() == null) {
            subtask.setStatus(SubtaskStatus.PENDING);
        }

        Subtask savedSubtask = subtaskRepository.save(subtask);
        return convertToDTO(savedSubtask);
    }

    public SubtaskDTO updateSubtask(Integer id, Subtask subtaskDetails) {
        Subtask existingSubtask = findByIdEntity(id);

        existingSubtask.setTitle(subtaskDetails.getTitle());
        existingSubtask.setStatus(subtaskDetails.getStatus());

        Subtask updatedSubtask = subtaskRepository.save(existingSubtask);
        return convertToDTO(updatedSubtask);
    }

    public void deleteById(Integer id) {
        if (!subtaskRepository.existsById(id)) {
            throw new SubtaskNotFoundException(id);
        }

        subtaskRepository.deleteById(id);
    }

    public SubtaskDTO convertToDTO(Subtask subtask) {
        Integer taskId = (subtask.getTask() != null)
                ? subtask.getTask().getId()
                : null;

        String status = (subtask.getStatus() != null)
                ? subtask.getStatus().name()
                : null;

        return new SubtaskDTO(
                subtask.getId(),
                subtask.getTitle(),
                status,
                taskId
        );
    }
}