package teksystems.capstone.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import teksystems.capstone.database.dao.FeederDAO;
import teksystems.capstone.database.dao.FeederSnakeDAO;
import teksystems.capstone.database.dao.SnakeDAO;
import teksystems.capstone.database.entity.Feeder;
import teksystems.capstone.database.entity.FeederSnake;
import teksystems.capstone.database.entity.Snake;
import teksystems.capstone.formbean.feederSnake.AddFeederSnakeFormBean;

import javax.validation.Valid;

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
            response.setViewName("feederSnake/added");
            return response;
        }

        FeederSnake feederSnake = feederSnakeDAO.findById(form.getId());
        if (feederSnake == null) {
            feederSnake = new FeederSnake();
        }

        Snake snake = snakeDAO.findSnakeBySpeciesIgnoreCase(form.getSpecies());
        Feeder feeder = feederDAO.findFeederByNameIgnoreCase(form.getName());

        feederSnake.setId(form.getId());
        feederSnake.setSnake(snake);
        if (feeder.getQuantity() >= form.getQuantity()) {
            feederSnake.setFeeder(feeder);
            feederSnake.setQuantity(form.getQuantity());
            feeder.setQuantity(feeder.getQuantity() - form.getQuantity());
            feederDAO.save(feeder);
        } else {
            response.setViewName("redirect:/feeder/showFeeders");
        }
        snakeDAO.save(snake);
        feederSnakeDAO.save(feederSnake);
        log.info("snake info*** "+snake);
        log.info("feederSnake info*** "+feederSnake);

        // if have time, add adding successful message
        // use redirect to trigger the next method/function
        response.setViewName("redirect:/feeder/showFeeders");
        return response;

    }
}
