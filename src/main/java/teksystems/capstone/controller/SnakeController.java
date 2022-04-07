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
import teksystems.capstone.database.dao.SnakeDAO;
import teksystems.capstone.database.entity.Snake;
import teksystems.capstone.database.entity.User;
import teksystems.capstone.formbean.AddSnakeFormBean;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Slf4j
@Controller
public class SnakeController {

    @Autowired
    private SnakeDAO snakeDAO;

    @RequestMapping(value = "/snake/add", method = RequestMethod.GET)
    public ModelAndView create() throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("snake/add");

        return response;
    }

    @RequestMapping(value = "/snake/show", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView added(@Valid AddSnakeFormBean form, BindingResult bindingResult) throws Exception {
        ModelAndView response = new ModelAndView();
        if (bindingResult.hasErrors()) {
            for (ObjectError error : bindingResult.getAllErrors()) {
                log.info(((FieldError) error).getField() + " " + error.getDefaultMessage());
            }
            response.addObject("bindingResult", bindingResult);

            // if there is one or more errors we don't want to continue with the creating process,
            // and show register.jsp page
            response.setViewName("snake/add");
            return response;
        }

        log.info(form.toString());

        // first, we assumed it's an edit, and thus we want to query the user from database using id
        Snake snake = snakeDAO.findById(form.getId());

        // if user is null, aka the id isn't there, aka new user
        if (snake == null) {
            // hence, create new user
            snake = new Snake();
        }

        snake.setSpecies(form.getSpecies());
        snake.setAge(form.getAge());
        snake.setSex(form.getSex());
        snake.setBirthDate(form.getBirthDate());
        snake.setNote(form.getNote());

        snakeDAO.save(snake);

        List<Snake> snakes = snakeDAO.findAll();
        // this line puts the list of users we just queried into the model
        // usersModelKey - users: is a key-value pair in a model map
        response.addObject("snakeModels", snakes);

//        response.setViewName("redirect:/snake/edit/" + user.getId());
        return response;
    }

}
