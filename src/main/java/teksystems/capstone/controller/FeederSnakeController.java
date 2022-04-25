package teksystems.capstone.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import teksystems.capstone.database.dao.FeederDAO;
import teksystems.capstone.database.dao.FeederSnakeDAO;
import teksystems.capstone.database.dao.SnakeDAO;
import teksystems.capstone.database.entity.Feeder;
import teksystems.capstone.database.entity.FeederSnake;
import teksystems.capstone.database.entity.Snake;
import teksystems.capstone.formbean.feederSnake.AddFeederSnakeFormBean;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
public class FeederSnakeController {

    @Autowired
    private FeederSnakeDAO feederSnakeDAO;

    @Autowired
    private FeederDAO feederDAO;

    @Autowired
    private SnakeDAO snakeDAO;

    // This method to show add form for feedersnake, aka add feeding form
    @RequestMapping(value = "/feederSnake/add", method = RequestMethod.GET)
    public ModelAndView creatingFeeding() throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("feederSnake/addFeederSnake");
        // query all snakes in database
        List<Snake> snakes = snakeDAO.findAll();
        // query all feeder in database
        List<Feeder> feeders = feederDAO.findAll();

        // save all snakes and feeders into their respective modelKey to be used in jsp
        response.addObject("snakesModelKey", snakes);
        response.addObject("feedersModelKey", feeders);
        return response;
    }

    // This method to run when the submit button in addFeederSnake form is clicked
    @RequestMapping(value = "/feederSnake/added", method = {RequestMethod.GET})
    public ModelAndView feederSnakeAdded(@Valid AddFeederSnakeFormBean form, BindingResult bindingResult) throws Exception {
        ModelAndView response = new ModelAndView();
        // if there is any errors
        if (bindingResult.hasErrors()) {
            // iterate through every field
            for (ObjectError error : bindingResult.getAllErrors()) {
                // log out error for every field
                log.info(((FieldError) error).getField() + " " + error.getDefaultMessage());
            }
            response.addObject("bindingResult", bindingResult);

            // if there is one or more errors we don't want to continue with the creating process,
            // and re-render addFeederSnake again
            response.setViewName("feederSnake/addFeederSnake");
            return response;
        }

        // query a  feederSnake using the form id
        FeederSnake feederSnake = feederSnakeDAO.findById(form.getId());
        // if feederSnake is null, aka form doesn't have an id yet -> new form, new feederSnake
        if (feederSnake == null) {
            // create a new feederSnake
            feederSnake = new FeederSnake();
        }

        // query a snake using snake id
        Snake snake = snakeDAO.findSnakeById(form.getSnakeId());
        // query a feeder using feeder id
        Feeder feeder = feederDAO.findFeederById(form.getFeederId());

        // set the default feeding quantity, defaultQuantity, to be equal to quantity entered in the submitted form
        Integer defaultQuantity = form.getQuantity();
        // if defaultQuantity is null, aka no quantity entered in form, set defaultQuantity to 1
        if (defaultQuantity == null) defaultQuantity = 1;

        // if the quantity in-stock of a feeder is greater than or equal to defaultQuantity
        if (feeder.getQuantity() >= defaultQuantity) {
            // set feederSnake information
            feederSnake.setFeeder(feeder);
            feederSnake.setQuantity(defaultQuantity);
            feeder.setQuantity(feeder.getQuantity() - defaultQuantity);
            feederDAO.save(feeder);
            feederSnake.setId(form.getId());
            feederSnake.setSnake(snake);
            snakeDAO.save(snake);
            feederSnakeDAO.save(feederSnake);
            response.setViewName("redirect:/feederSnake/showFeedings");
        } else {
            // if feeder quantity in-stock is lower than defaultQuantity, re-render addFeederSnake form
            response.setViewName("redirect:/feederSnake/add");
        }
        return response;
    }

    // This method to show all feedings (past feedings)
    @GetMapping(value = "/feederSnake/showFeedings")
    public ModelAndView showFeedings(@RequestParam(name = "search", required = false) String search) throws Exception {
        ModelAndView response = new ModelAndView();

        // create a feederSnakes List<Map<String, Object>>
        List<Map<String, Object>> feederSnakes;

        // search for feeding - not implemented yet
//        if(!StringUtils.isBlank(search)) {
//            // run these lines
//            feederSnakes = feederSnakeDAO.findBySpeciesContainingIgnoreCase(search);
//        } else {
//            // else, run these
//            feederSnakes = feederSnakeDAO.findAll();
//            search = "search feeding by species...";
//        }

        // query for all feedings in database
        feederSnakes = feederSnakeDAO.findAllFeedings();

        response.addObject("feedingsModel", feederSnakes);

        response.addObject("searchTerm", search);
        return response;
    }

    // not implemented yet
//    @GetMapping(value = "/feederSnake/edit/{feedingId}")
//    public ModelAndView editFeeding(@PathVariable("feedingId") Integer feedingId) throws Exception {
//        ModelAndView response = new ModelAndView();
//        response.setViewName("feederSnake/addFeederSnake");
//
//        FeederSnake feeding = feederSnakeDAO.findById(feedingId);
//        AddFeederSnakeFormBean form = new AddFeederSnakeFormBean();
//
//        form.setId(feeding.getId());
//        form.setFeederId(feeding.getFeederId());
//        form.setSnakeId(feeding.getSnakeId());
//        form.setQuantity(feeding.getQuantity());
//
//        response.addObject("feedingFormBean", form);
//
//        return response;
//    }

        // not implemented yet
//    @GetMapping(value = "/snake/remove/{feedingId}")
//    public ModelAndView removeSnake(@PathVariable("feedingId") Integer feedingId) throws Exception {
//        ModelAndView response = new ModelAndView();
//        response.setViewName("feederSnake/showFeedings");
//
//        FeederSnake feeding = feederSnakeDAO.findById(feedingId);
//        log.info("feeding: "+feeding);
//        if (feeding != null) {
//            feederSnakeDAO.delete(feeding);
//        }
//        response.setViewName("redirect:/feederSnake/showFeedings");
//        return response;
//    }
}
