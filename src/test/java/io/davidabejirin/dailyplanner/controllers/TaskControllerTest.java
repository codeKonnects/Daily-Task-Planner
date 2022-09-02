package io.davidabejirin.dailyplanner.controllers;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import io.davidabejirin.dailyplanner.entity.Task;
import io.davidabejirin.dailyplanner.entity.User;
import io.davidabejirin.dailyplanner.service.TaskService;
import io.davidabejirin.dailyplanner.service.serviceImpl.TaskServiceImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

@ContextConfiguration(classes = {TaskController.class})
@ExtendWith(SpringExtension.class)
class TaskControllerTest {
    @Autowired
    private TaskController taskController;

    @MockBean
    private TaskService taskService;

    /**
     * Method under test: {@link TaskController#deleteTask(Integer)}
     */
    @Test
    void testDeleteTask() throws Exception {
        doNothing().when(taskService).deleteTask((Integer) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/delete/{id}", 1);
        MockMvcBuilders.standaloneSetup(taskController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/home"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/home"));
    }

    /**
     * Method under test: {@link TaskController#deleteTask(Integer)}
     */
    @Test
    void testDeleteTask2() throws Exception {
        doNothing().when(taskService).deleteTask((Integer) any());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/delete/{id}", 1);
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(taskController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/home"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/home"));
    }


    /**
     * Method under test: {@link TaskController#editSort(String)}
     */
    @Test
    void testEditSort() throws Exception {
        doNothing().when(taskService).setSortMethod((String) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/sort/{sortMethod}", "Sort Method");
        MockMvcBuilders.standaloneSetup(taskController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/home"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/home"));
    }

    /**
     * Method under test: {@link TaskController#editSort(String)}
     */
    @Test
    void testEditSort2() throws Exception {
        doNothing().when(taskService).setSortMethod((String) any());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/sort/{sortMethod}", "Sort Method");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(taskController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/home"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/home"));
    }

    /**
     * Method under test: {@link TaskController#editTask(Integer, Model)}
     */
    @Test
    void testEditTask() throws Exception {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setId(123L);
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setTasks(new ArrayList<>());

        Task task = new Task();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        task.setDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        task.setDescription("The characteristics of someone or something");
        task.setDone(true);
        task.setId(1);
        task.setMessage("Not all who wander are lost");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        task.setUpdatedDate(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        task.setUserId(user);
        when(taskService.getTaskById((Integer) any())).thenReturn(task);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/edit/{id}", 1);
        MockMvcBuilders.standaloneSetup(taskController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("task"))
                .andExpect(MockMvcResultMatchers.view().name("edit"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("edit"));
    }

    /**
     * Method under test: {@link TaskController#editTaskDone(Integer)}
     */
    @Test
    void testEditTaskDone() throws Exception {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setId(123L);
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setTasks(new ArrayList<>());

        Task task = new Task();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        task.setDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        task.setDescription("The characteristics of someone or something");
        task.setDone(true);
        task.setId(1);
        task.setMessage("Not all who wander are lost");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        task.setUpdatedDate(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        task.setUserId(user);
        doNothing().when(taskService).saveTask((Task) any());
        when(taskService.getTaskById((Integer) any())).thenReturn(task);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/editTaskDone/{id}", 1);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(taskController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated());
    }

    /**
     * Method under test: {@link TaskController#editTaskDone(Integer)}
     */
    @Test
    void testEditTaskDone2() throws Exception {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setId(123L);
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setTasks(new ArrayList<>());

        Task task = new Task();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        task.setDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        task.setDescription("The characteristics of someone or something");
        task.setDone(false);
        task.setId(1);
        task.setMessage("Not all who wander are lost");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        task.setUpdatedDate(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        task.setUserId(user);
        doNothing().when(taskService).saveTask((Task) any());
        when(taskService.getTaskById((Integer) any())).thenReturn(task);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/editTaskDone/{id}", 1);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(taskController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated());
    }

    /**
     * Method under test: {@link TaskController#filterChoose(String)}
     */
    @Test
    void testFilterChoose() throws Exception {
        doNothing().when(taskService).setFilterMethod((String) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/filter/{filter}", "Filter");
        MockMvcBuilders.standaloneSetup(taskController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/home"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/home"));
    }

    /**
     * Method under test: {@link TaskController#filterChoose(String)}
     */
    @Test
    void testFilterChoose2() throws Exception {
        doNothing().when(taskService).setFilterMethod((String) any());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/filter/{filter}", "Filter");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(taskController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/home"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/home"));
    }

    /**
     * Method under test: {@link TaskController#mainDailyPlanner(Model, int, int)}
     */
    @Test
    void testMainDailyPlanner() throws Exception {
        when(taskService.getPageItems((Page<Task>) any())).thenReturn(new ArrayList<>());
        when(taskService.findAll(anyInt(), anyInt())).thenReturn(new PageImpl<>(new ArrayList<>()));
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/home");
        MockHttpServletRequestBuilder paramResult = getResult.param("pageNumber", String.valueOf(1));
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("size", String.valueOf(1));
        MockMvcBuilders.standaloneSetup(taskController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(3))
                .andExpect(MockMvcResultMatchers.model().attributeExists("page", "pageItems", "tasks"))
                .andExpect(MockMvcResultMatchers.view().name("index"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("index"));
    }
}

