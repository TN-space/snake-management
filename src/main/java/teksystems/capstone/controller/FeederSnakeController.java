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

    @RequestMapping(value = "/feederSnake/add", method = RequestMethod.GET)
    public ModelAndView creatingFeeding() throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("feederSnake/addFeederSnake");
        List<Snake> snakes = snakeDAO.findAll();
        List<Feeder> feeders = feederDAO.findAll();
        response.addObject("snakesModelKey", snakes);
        response.addObject("feedersModelKey", feeders);
        // query to get all snakes, add to model
        // query to get all feeders, add to model
        // on jsp page, loop over both
        // if not using formBean, can use request param for both id
        return response;
    }

    @RequestMapping(value = "/feederSnake/added", method = {RequestMethod.GET})
    public ModelAndView feederSnakeAdded(@Valid AddFeederSnakeFormBean form, BindingResult bindingResult) throws Exception {
        ModelAndView response = new ModelAndView();
        if (bindingResult.hasErrors()) {
            for (ObjectError error : bindingResult.getAllErrors()) {
                log.info(((FieldError) error).getField() + " " + error.getDefaultMessage());
            }
            response.addObject("bindingResult", bindingResult);

            // if there is one or more errors we don't want to continue with the creating process,
            // and show register.jsp page
            response.setViewName("feederSnake/addFeederSnake");
            return response;
        }

        FeederSnake feederSnake = feederSnakeDAO.findById(form.getId());
        if (feederSnake == null) {
            feederSnake = new FeederSnake();
        }

        Snake snake = snakeDAO.findSnakeById(form.getSnakeId());
        Feeder feeder = feederDAO.findFeederById(form.getFeederId());
        log.info("form: "+ form);
        log.info("snake: " + snake);
        log.info("feeder: " + feeder);

        Integer defaultQuantity = form.getQuantity();
        if (defaultQuantity == null) defaultQuantity = 1;

        if (feeder.getQuantity() >= defaultQuantity) {
            feederSnake.setFeeder(feeder);
            feederSnake.setQuantity(defaultQuantity);
            feeder.setQuantity(feeder.getQuantity() - defaultQuantity);
            feederDAO.save(feeder);
            feederSnake.setId(form.getId());
            feederSnake.setSnake(snake);
            snakeDAO.save(snake);
            feederSnakeDAO.save(feederSnake);
        } else {
//            response.setViewName("redirect:/feeder/showFeeders");
        }

        // if have time, add adding successful message
        // use redirect to trigger the next method/function
        response.setViewName("redirect:/feederSnake/showFeedings");
        return response;
    }

    @GetMapping(value = "/feederSnake/showFeedings")
    public ModelAndView showFeedings(@RequestParam(name = "search", required = false) String search) throws Exception {
        ModelAndView response = new ModelAndView();

        //write query in sql to select everything I need
        List<Map<String, Object>> feederSnakes;
        // if the search is not blank
        if(!StringUtils.isBlank(search)) {
            // run these lines
//            feederSnakes = feederSnakeDAO.findBySpeciesContainingIgnoreCase(search);
        } else {
            // else, run these
//            feederSnakes = feederSnakeDAO.findAll();
            search = "search feeding by species...";
        }
        // this line puts the list of users we just queried into the model
        // usersModelKey - users: is a key-value pair in a model map
        feederSnakes = feederSnakeDAO.findAllFeedings();

        for (Map<String,Object> x:feederSnakes) {
            log.info("log every feederSnake: "+x);
        }
        response.addObject("feedingsModel", feederSnakes);

        response.addObject("searchTerm", search);
        return response;
    }

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

    @GetMapping(value = "/snake/remove/{feedingId}")
    public ModelAndView removeSnake(@PathVariable("feedingId") Integer feedingId) throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("feederSnake/showFeedings");

        FeederSnake feeding = feederSnakeDAO.findById(feedingId);
        if (feeding != null) {
            feederSnakeDAO.delete(feeding);
        }
        response.setViewName("redirect:/feederSnake/showFeedings");
        return response;
    }
}
