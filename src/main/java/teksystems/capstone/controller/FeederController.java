package teksystems.capstone.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import teksystems.capstone.database.dao.FeederDAO;
import teksystems.capstone.database.entity.Feeder;
import teksystems.capstone.database.entity.Snake;
import teksystems.capstone.formbean.feeder.AddFeederFormBean;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
public class FeederController {

    @Autowired
    private FeederDAO feederDAO;

    @RequestMapping(value = "/feeder/add", method = RequestMethod.GET)
    public ModelAndView create() throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("feeder/addFeeder");

        return response;
    }

    @RequestMapping(value = "/feeder/added", method = {RequestMethod.GET})
    public ModelAndView added(@Valid AddFeederFormBean form, BindingResult bindingResult) throws Exception {
        ModelAndView response = new ModelAndView();

        if (bindingResult.hasErrors()) {
            for (ObjectError error : bindingResult.getAllErrors()) {
                log.info(((FieldError) error).getField() + " " + error.getDefaultMessage());
            }
            response.addObject("bindingResult", bindingResult);

            // if there is one or more errors we don't want to continue with the creating process,
            // and show register.jsp page
            response.setViewName("feeder/added");
            return response;
        }



        // first, we assumed it's an edit, and thus we want to query the user from database using id
        Feeder feeder = feederDAO.findById(form.getId());
        // if user is null, aka the id isn't there, aka new user
        if (feeder == null) {
            // hence, create new user
            feeder = new Feeder();
        }

        // to calculate age - not using right now
//        Long ageDay = DAYS.between(form.getBirthDate(), LocalDate.now());
//        log.info(ageDay.toString());
//        String age;
//        if (ageDay/365 > 0) {
//            age = ageDay/365 + " years old";
//        } else if (ageDay/30 > 0) {
//            age = ageDay/30 + " months old";
//        } else {
//            age = ageDay + " days old";
//        }
//        List<String> ages = new ArrayList<>();
//        ages.add(age);
//        response.addObject("ages", ages);
        log.info("info from From: "+form);
        feeder.setName(form.getName());
        feeder.setSize(form.getSize());
        feeder.setStatus(form.getStatus());
        feeder.setQuantity(form.getQuantity());

        feederDAO.save(feeder);

//        HashMap<Integer, Snake> map = new HashMap<>();
//        for (Snake x:snakes) {
//            map.put(x.getId(), x);
//        }
//        response.addObject("mapModel", map);

        // if have time, add adding successful message
        // use redirect to trigger the next method/function
        response.setViewName("redirect:/feeder/showFeeders");
        return response;
    }

    @GetMapping(value = "/feeder/showFeeders")
    public ModelAndView showSnakes(@RequestParam(name = "search", required = false) String search) throws Exception {
        ModelAndView response = new ModelAndView();
        List<Feeder> feeders;
        // if the search is not blank
        if(!StringUtils.isBlank(search)) {
            // run these lines
            feeders = feederDAO.findByNameContainingIgnoreCase(search);
        } else {
            // else, run these
            feeders = feederDAO.findAll();
            search = "search animal...";
        }
        // this line puts the list of users we just queried into the model
        // usersModelKey - users: is a key-value pair in a model map
        response.addObject("feedersModel", feeders);

        response.addObject("searchTerm", search);
        return response;
    }

}
