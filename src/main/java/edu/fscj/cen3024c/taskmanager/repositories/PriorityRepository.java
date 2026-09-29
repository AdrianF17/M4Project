package edu.fscj.cen3024c.taskmanager.repositories;

import edu.fscj.cen3024c.taskmanager.entities.Priority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriorityRepository extends JpaRepository<Priority, Integer> {
}