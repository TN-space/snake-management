package teksystems.capstone.controller;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import teksystems.capstone.database.dao.UserDAO;
import teksystems.capstone.database.entity.User;
import teksystems.capstone.formbean.RegisterFormBean;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Slf4j
@Controller
public class UserController {

    @Autowired
    private UserDAO userDAO;

    /*
     * This is the controller method for the entry point of the user registration page.
     * It does not do anything other than provide a route to the register.jsp.jsp page
     * */
    @RequestMapping(value = "/user/register", method = RequestMethod.GET)
    public ModelAndView create() throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("user/register");

        return response;
    }

    /*
     * When user submits the form, it will call into this method
     * 1) the action on the form itself must match the value here in the requestMethod
     * 2) method on the form must match the method here,
     * otherwise spring MVC will not be able to respond to the request
     * */
    /*This code now can do a create or update depending on if the id is already populated or null*/
    @RequestMapping(value = "/user/registerSubmit", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView registerSubmit(@Valid RegisterFormBean form, BindingResult bindingResult) throws Exception {
        ModelAndView response = new ModelAndView();

//        int i = 10/0;
        if (bindingResult.hasErrors()) {
            for (ObjectError error : bindingResult.getAllErrors()) {
                log.info(((FieldError) error).getField() + " " + error.getDefaultMessage());
            }
            response.addObject("bindingResult", bindingResult);

            // if there is one or more errors we don't want to continue with the creating process,
            // and show register.jsp page
            response.setViewName("user/register");
            return response;
        }

        // first, we assumed it's an edit, and thus we want to query the user from database using id
        User user = userDAO.findById(form.getId());

        // if user is null, aka the id isn't there, aka new user
        if (user == null) {
            // hence, create new user
            user = new User();
        }

        user.setEmail(form.getEmail());
        user.setFirstName(form.getFirstName());
        user.setLastName(form.getLastName());
        user.setPassword(form.getPassword());
        user.setCreateDate(new Date());

        userDAO.save(user);

        log.info(form.toString());

        // here want to redirect to the edit page
        // the edit page will then be responsible for loading the user from database
        // redirect use an actual url rather than a view name - .setViewName uses a file name in the structure
        response.setViewName("redirect:/user/edit/" + user.getId());
        return response;
    }

}

