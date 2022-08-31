package io.davidabejirin.dailyplanner.repository;

import io.davidabejirin.dailyplanner.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository  extends JpaRepository<Task, Integer> {
    Page<Task> findAllByDoneTrue(Pageable pageable);
    Page<Task> findAllByDoneFalse(Pageable pageable);
}
