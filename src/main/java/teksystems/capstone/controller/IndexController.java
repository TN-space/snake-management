package teksystems.capstone.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import teksystems.capstone.database.dao.UserDAO;


@Slf4j
@Controller
public class IndexController {

    @Autowired
    private UserDAO userDAO;

    // This method to show index page
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView index() throws Exception {
        ModelAndView response = new ModelAndView();

        response.setViewName("home");

        return response;
    }
}

