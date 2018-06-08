package org.launchcode.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

/**
 * Created by LaunchCode
 */
@Controller
public class HomeController {

    @RequestMapping(value = "")
    // "Model model" class is used pass data to the templates, or the view (with Thymeleaf templates)
    public String index(Model model) {

        HashMap<String, String> actionChoices = new HashMap<>();
        actionChoices.put("search", "Search");
        actionChoices.put("list", "List");

        // below corresponds to assigning values to template variables
        model.addAttribute("actions", actionChoices);

        //in template: index.html
        return "index";
    }

}
