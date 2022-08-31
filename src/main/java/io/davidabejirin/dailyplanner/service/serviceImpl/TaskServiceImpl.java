package io.davidabejirin.dailyplanner.service.serviceImpl;

import io.davidabejirin.dailyplanner.entity.Task;
import io.davidabejirin.dailyplanner.repository.TaskRepository;
import io.davidabejirin.dailyplanner.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TaskServiceImpl implements TaskService {

    private String sortMethod = "ASC";
    private String filterMethod = "ALL";
    private TaskRepository repository;

    @Autowired
    public void setTaskRepository(TaskRepository repository) {
        this.repository = repository;
    }
    @Override
    public Task getTaskById(Integer id) {
        return repository.findById(id).get();
    }
    @Override
    public void saveTask(Task task) {
        repository.save(task);
    }
    @Override
    public void updateTask(Integer id, String message, String description, boolean done) {
        Optional<Task> updated = repository.findById(id);
        if (updated.isEmpty()) return;
        Task task = updated.get();
        task.setDone(done);
        task.setDescription(description);
        task.setMessage(message);
        task.setUpdatedDate(new Date());
        repository.save(task);
    }
    @Override
    public void deleteTask(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public Page<Task> findAll(int pageNumber, int size) {
        switch (filterMethod){
            case "DoneTrue":
                return repository.findAllByDoneTrue(this.getPageable(pageNumber,size));
            case "DoneFalse":
                return repository.findAllByDoneFalse(this.getPageable(pageNumber,size));
            default:
                return repository.findAll(this.getPageable(pageNumber,size));
        }
    }
    @Override
    public String getSortMethod() {
        return sortMethod;
    }
    @Override
    public void setSortMethod(String sortMethod) {
        this.sortMethod = sortMethod;
    }

    @Override
    public String getFilterMethod() {
        return filterMethod;
    }
    @Override
    public void setFilterMethod(String filterMethod) {
        this.filterMethod = filterMethod;
    }
    private Pageable getPageable(int pageNumber, int size) {
        if (sortMethod.equals("DESC")){
            return PageRequest.of(pageNumber,size, Sort.by("done","message").descending());
        }
        return PageRequest.of(pageNumber,size, Sort.by("done","message"));
    }
    @Override
    public List<Integer> getPageItems(Page<Task> page){
        Integer first,last;
        first = page.getNumber();
        last = page.getNumber() + 3;
        if (page.isFirst()) first = 1;
        if(page.isLast()) last = page.getNumber() + 1;
        if(last > page.getTotalPages())last = page.getTotalPages();
        return Stream.iterate(first, n -> n + 1).limit(last - first + 1).collect(Collectors.toList());
    }
}
