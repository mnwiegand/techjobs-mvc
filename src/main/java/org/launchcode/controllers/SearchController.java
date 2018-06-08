package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        // what does ListController.columnChoices do?
        // hashmap ListController.columnchoices goes into Thymeleaf "columns"
        // in template: search.html
        return "search";
    }

    // TODO #1 - Create handler to process search request and display results
        //find objects that contain the searchTerm
        //list those objects via Thymeleaf in template
        //redirects to template search.html
    // are searchType & searchTerm the right names of Params to request?
    // NOT supposed to be post method.... GET!!!
    @RequestMapping(value = "results", method = RequestMethod.GET)
    public String search(Model model, @RequestParam String searchType, @RequestParam String searchTerm) {
        model.addAttribute("columns", ListController.columnChoices);
        //add an if statement to cover search for ALL

        ArrayList<HashMap<String, String>> jobs;

        if (searchType.equals("all")){
            jobs = JobData.findByValue(searchTerm);

        }

        else{
            jobs = JobData.findByColumnAndValue(searchType, searchTerm);
        }

        String info = "Jobs by " + searchType + ", Containing: " + searchTerm;
        model.addAttribute("title", info);
        model.addAttribute("jobs", jobs);

        //template: search.html or redirect
        return "search";

    }
}
