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
        return "search";
    }

    // TODO #1 - Create handler to process search request and display results
    /*Add a results handler method, overloading existing method. Take in 2 parameters,
     specifying the type of search and the search term; name them appropriately, based on the corresponding
     form field names.
     To configure the correct mapping route, refer to the form action in search.html.
     After looking up the search results via the JobData class, pass them into the search.html view via the model.
     Pass ListController.columnChoices to the view, as the existing search handler does.
     */


    //Request path:  /search/results
    @RequestMapping (value = "results", method = RequestMethod.GET)
    public String displaySearchResults (Model model, @RequestParam String searchTerm, @RequestParam String searchType) {

        ArrayList<HashMap<String, String>> jobResults;

        if (searchType.equals("all")) {
            jobResults = JobData.findByValue(searchTerm);
        }
        else {
            jobResults = JobData.findByColumnAndValue(searchType, searchTerm);
        }

        /* For the fragment to work properly in a different file, the data passed into the view via model.addAttribute()
        must have the same key in both places.
         */

        model.addAttribute("jobResults", jobResults);
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }
}
