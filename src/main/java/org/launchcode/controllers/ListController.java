package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping(value = "list")
public class ListController {

    static HashMap<String, String> columnChoices = new HashMap<>();

    public ListController () {
        columnChoices.put("core competency", "Skill");
        columnChoices.put("employer", "Employer");
        columnChoices.put("location", "Location");
        columnChoices.put("position type", "Position Type");
        columnChoices.put("all", "All");
    }

    @RequestMapping(value = "")
    // Model model is for Thymeleaf templates
    public String list(Model model) {
        // below corresponds to assigning values to template variables
        // columnChoices goes into "columns"
        //in Thymeleaf template "list"
        model.addAttribute("columns", columnChoices);

        // template: list.html
        return "list";
    }

    @RequestMapping(value = "values")
    // Model model is for Thymeleaf templates
    public String listColumnValues(Model model, @RequestParam String column) {

        if (column.equals("all")) {
            ArrayList<HashMap<String, String>> jobs = JobData.findAll();
            // below corresponds to assigning values to template variables
            model.addAttribute("title", "All Jobs");
            model.addAttribute("jobs", jobs);
            //template: list-jobs.html
            return "list-jobs";
        } else {
            ArrayList<String> items = JobData.findAll(column);
            // below corresponds to assigning values to template variables
            model.addAttribute("title", "All " + columnChoices.get(column) + " Values");
            model.addAttribute("column", column);
            model.addAttribute("items", items);
            //template: list-column.html
            return "list-column";
        }

    }

    @RequestMapping(value = "jobs")
    // Model model is for Thymeleaf templates
    public String listJobsByColumnAndValue(Model model,
            @RequestParam String column, @RequestParam String value) {

        ArrayList<HashMap<String, String>> jobs = JobData.findByColumnAndValue(column, value);
        model.addAttribute("title", "Jobs with " + columnChoices.get(column) + ": " + value);
        model.addAttribute("jobs", jobs);

        //template: list-jobs.html
        return "list-jobs";
    }
}
