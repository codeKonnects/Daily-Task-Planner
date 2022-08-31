package io.davidabejirin.dailyplanner.controllers;

import io.davidabejirin.dailyplanner.entity.Task;
import io.davidabejirin.dailyplanner.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
public class TaskController {
    private  TaskService service;

    @Autowired
    public void setTaskService(TaskService service) {
        this.service = service;
    }

    @GetMapping("/home")
    public String mainDailyPlanner(Model model,
    @RequestParam(defaultValue = "0") int pageNumber,
    @RequestParam(defaultValue = "10") int size){
        Page<Task> page = service.findAll(pageNumber, size);
        model.addAttribute("pageItems",service.getPageItems(page));
        model.addAttribute("tasks",page.getContent());
        model.addAttribute("page", page);
        return "index";
    }

    @GetMapping("/sort/{sortMethod}")
    public String editSort(@PathVariable String sortMethod) {
       service.setSortMethod(sortMethod);

        return "redirect:/home";
    }
    @GetMapping("/edit/{id}")
    public String editTask(@PathVariable Integer id, Model model) {
        Task task = service.getTaskById(id);
        model.addAttribute("task", task);
        return "edit";
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/editTaskDone/{id}")
    public void editTaskDone(@PathVariable Integer id) {
        Task task = service.getTaskById(id);
        if (task != null) {
            task.setDone(!task.getDone());
            service.saveTask(task);
        }
    }

    @PostMapping("/update")
    public String updateTask(@RequestParam Integer id, @RequestParam String message, @RequestParam String description,
                           @RequestParam(value = "done", required = false) boolean done) {
        service.updateTask(id, message,description, done);
        return "redirect:/home";
    }

    @GetMapping("/new")
    public String newTask() {
        return "new";
    }

    @PostMapping("/save")
    public String saveTask(@RequestParam String message, @RequestParam String description) {
        service.saveTask(new Task(message, description, false));
        return "redirect:/home";
    }

    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable Integer id) {
        service.deleteTask(id);
        return "redirect:/home";
    }

    @GetMapping("/filter/{filter}")
    public String filterChoose(@PathVariable String filter) {
        service.setFilterMethod(filter);
        return "redirect:/home";
    }
}
