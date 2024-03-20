package org.venom.SpringTaskMVC.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.venom.SpringTaskMVC.domain.Task;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
public class TaskController {
    private List<Task> taskList=new ArrayList<>();
    private List<Task> completedList=new ArrayList<>();
    {
        taskList.add(new Task(101,"ABC"));
        taskList.add(new Task(102,"PQR"));
        taskList.add(new Task(103,"XYZ"));
    }

    @GetMapping("/")
    public String getTaskPage(Model model){
        model.addAttribute("tasks",taskList);
        return "taskhomepage";
    }

    @GetMapping("/gettaskform")
    public String getTaskForm(Model model){
        Task t1=new Task();
        model.addAttribute("task",t1);
        return "taskform";
    }

    @PostMapping("/savetask")
    public String addTaskDetails(Task task){
        taskList.add(task);
        return "redirect:/";
    }
    @GetMapping("/getcompletedtask")
    public String getCompletedTask(Model model){
        model.addAttribute("cTasks",completedList);
        return "completedtask";
    }

    @GetMapping("delete/{id}")
    public String completedTask(@PathVariable(name = "id") int id){
        //fetch the specific task from existing task list based on id
        Task t1=null;
        for(Task t:taskList){
            if (t.getTaskId()==id){
                t1=t;
            }
        }
        //add it to the completed task list
        completedList.add(t1);
        //remove it from original task list
        taskList.remove(t1);
        return "redirect:/";
    }


}

