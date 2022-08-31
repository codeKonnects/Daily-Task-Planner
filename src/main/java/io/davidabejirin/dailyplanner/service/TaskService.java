package io.davidabejirin.dailyplanner.service;

import io.davidabejirin.dailyplanner.entity.Task;
import org.springframework.data.domain.Page;
import java.util.List;

public interface TaskService {
    Task getTaskById(Integer id);

    void saveTask(Task task);

    void updateTask(Integer id, String message, String description, boolean done);

    void deleteTask(Integer id);

    Page<Task> findAll(int pageNumber, int size);

    String getSortMethod();

    void setSortMethod(String sortMethod);
    String getFilterMethod();
    List<Integer> getPageItems(Page<Task> page);
    void setFilterMethod(String filterMethod);
}