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
import teksystems.capstone.database.dao.SnakeDAO;
import teksystems.capstone.database.entity.Snake;
import teksystems.capstone.formbean.snake.AddSnakeFormBean;

import javax.validation.Valid;
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

//    RequestMethod.POST, <-- this request post is taken from this request below
    @RequestMapping(value = "/snake/added", method = {RequestMethod.GET})
    public ModelAndView added(@Valid AddSnakeFormBean form, BindingResult bindingResult) throws Exception {
        ModelAndView response = new ModelAndView();
        if (bindingResult.hasErrors()) {
            for (ObjectError error : bindingResult.getAllErrors()) {
                log.info(((FieldError) error).getField() + " " + error.getDefaultMessage());
            }
            response.addObject("bindingResult", bindingResult);

            // if there is one or more errors we don't want to continue with the creating process,
            // and show register.jsp page
            response.setViewName("snake/added");
            return response;
        }

        // first, we assumed it's an edit, and thus we want to query the user from database using id
        Snake snake = snakeDAO.findById(form.getId());
        // if user is null, aka the id isn't there, aka new user
        if (snake == null) {
            // hence, create new user
            snake = new Snake();
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

        snake.setSpecies(form.getSpecies());
        snake.setSex(form.getSex());
        snake.setBirthDate(form.getBirthDate());
        snake.setNote(form.getNote());
        snake.setImgUrl(form.getImgUrl());

        snakeDAO.save(snake);

//        HashMap<Integer, Snake> map = new HashMap<>();
//        for (Snake x:snakes) {
//            map.put(x.getId(), x);
//        }
//        response.addObject("mapModel", map);

        // if have time, add adding successful message
        // use redirect to trigger the next method/function
        response.setViewName("redirect:/snake/show");
        return response;
    }

    @GetMapping(value = "/snake/show")
    public ModelAndView showSnakes(@RequestParam(name = "search", required = false) String search) throws Exception {
        ModelAndView response = new ModelAndView();
        List<Snake> snakes;
        // if the search is not blank
        if(!StringUtils.isBlank(search)) {
            // run these lines
            snakes = snakeDAO.findBySpeciesContainingIgnoreCase(search);
        } else {
            // else, run these
            snakes = snakeDAO.findAll();
            search = "...";
        }
        // this line puts the list of users we just queried into the model
        // usersModelKey - users: is a key-value pair in a model map
        response.addObject("snakesModel", snakes);

        response.addObject("searchTerm", search);
        return response;
    }


    @GetMapping(value = "/snake/edit/{snakeId}")
    public ModelAndView editSnake(@PathVariable("snakeId") Integer snakeId) throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("snake/add");

        log.info("snake id in Edit: "+snakeId);
        Snake snake = snakeDAO.findById(snakeId);
        AddSnakeFormBean form = new AddSnakeFormBean();

        form.setSpecies(snake.getSpecies());
        form.setSex(snake.getSex());
        form.setBirthDate(snake.getBirthDate());
        form.setNote(snake.getNote());
        form.setImgUrl(snake.getImgUrl());

        response.addObject("formBean", form);

        return response;
    }
}
