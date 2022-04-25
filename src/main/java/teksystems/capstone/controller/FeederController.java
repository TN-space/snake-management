package teksystems.capstone.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import teksystems.capstone.database.dao.FeederDAO;
import teksystems.capstone.database.dao.UserDAO;
import teksystems.capstone.database.entity.Feeder;
import teksystems.capstone.database.entity.User;
import teksystems.capstone.formbean.feeder.AddFeederFormBean;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
@PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
public class FeederController {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private FeederDAO feederDAO;

    // This method to show addFeeder form
    @RequestMapping(value = "/feeder/add", method = RequestMethod.GET)
    public ModelAndView addingFeeder() throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("feeder/addFeeder");

        return response;
    }

    // This method to catch the info passed from action = "/feeder/added" feederForm
    @RequestMapping(value = "/feeder/added", method = {RequestMethod.GET})
    public ModelAndView feederAdded(@Valid AddFeederFormBean form, BindingResult bindingResult) throws Exception {
        ModelAndView response = new ModelAndView();
        // if it has any errors
        if (bindingResult.hasErrors()) {
            // iterate through every field
            for (ObjectError error : bindingResult.getAllErrors()) {
                // log out error for each field
                log.info(((FieldError) error).getField() + " " + error.getDefaultMessage());
            }
            // send the errors object model - "bindingResult" back to the form to display on ui
            response.addObject("bindingResult", bindingResult);

            // re-render the addFeeder form
            response.setViewName("feeder/addFeeder");
            return response;
        }

        // first, we assumed it's an edit, and thus we want to query the feeder from database using id
        Feeder feeder = feederDAO.findById(form.getId());
        // if feeder is null, aka the id isn't there, aka new feeder
        if (feeder == null) {
            // hence, create new feeder
            feeder = new Feeder();
        }

        // get username (email, in this case) from log in
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) principal).getUsername();

        // get that specific user by email
        User user = userDAO.findByEmail(username);
        // set feeder associated to said user, using userId
        feeder.setUserId(user.getId());

        // set other information for feeder using information passed in from addFeeder form
        feeder.setName(form.getName());
        feeder.setSize(form.getSize());
        feeder.setStatus(form.getStatus());
        feeder.setQuantity(form.getQuantity());
        feeder.setImgUrl(form.getImgUrl());

        // save feeder into database
        feederDAO.save(feeder);

        // "redirect:/feeder/showFeeders" triggers method showFeeders
        response.setViewName("redirect:/feeder/showFeeders");
        return response;
    }

    // This method to show all feeders
    @GetMapping(value = "/feeder/showFeeders")
    public ModelAndView showFeeders(@RequestParam(name = "search", required = false) String search) throws Exception {
        ModelAndView response = new ModelAndView();
        List<Feeder> feeders;

        // if the search is not blank
        if(!StringUtils.isBlank(search)) {
            // run these lines
            feeders = feederDAO.findByNameContainingIgnoreCase(search);
        } else {
            // else, run these
            feeders = feederDAO.findAll();
            search = "search feeder...";
        }

        // this line puts the list of feeders we just queried into the model
        // feedersModel - feeders: is a key-value pair in a model map
        response.addObject("feedersModel", feeders);

        response.addObject("searchTerm", search);
        return response;
    }

    // This method is to run when edit button is clicked and the url changed
    @GetMapping(value = "/feeder/edit/{feederId}")
    public ModelAndView editFeeder(@PathVariable("feederId") Integer feederId) throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("feeder/addFeeder");

        // query feeder by feederId
        Feeder feeder = feederDAO.findById(feederId);
        // create a new form
        AddFeederFormBean form = new AddFeederFormBean();

        // set feeder info from the info queried into the new form
        form.setId(feeder.getId());
        form.setName(feeder.getName());
        form.setSize(feeder.getSize());
        form.setStatus(feeder.getStatus());
        form.setQuantity(feeder.getQuantity());
        form.setImgUrl(feeder.getImgUrl());

        // pass "feederFormBean" as a modelkey back to form to be displayed on ui
        response.addObject("feederFormBean", form);

        return response;
    }

    // This method to remove a feeder when url changed to include specific /feeder/remove/{feederId}
    @GetMapping(value = "/feeder/remove/{feederId}")
    public ModelAndView removeFeeder(@PathVariable("feederId") Integer feederId) throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("feeder/showFeeders");

        // query feeder by feederId
        Feeder feeder = feederDAO.findById(feederId);
        // if feeder is not null, aka found
        if (feeder != null) {
            // delete from database
            feederDAO.delete(feeder);
        }
        // redirect to showFeeders page
        response.setViewName("redirect:/feeder/showFeeders");
        return response;
    }

}
