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
import teksystems.capstone.database.dao.SnakeDAO;
import teksystems.capstone.database.dao.UserDAO;
import teksystems.capstone.database.entity.Snake;
import teksystems.capstone.database.entity.User;
import teksystems.capstone.formbean.snake.AddSnakeFormBean;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
@PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
public class SnakeController {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private SnakeDAO snakeDAO;

    @RequestMapping(value = "/snake/add", method = RequestMethod.GET)
    public ModelAndView addingSnake() throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("snake/addSnake");
        return response;
    }

//    RequestMethod.POST, <-- this request post is taken from this request below
    @RequestMapping(value = "/snake/added", method = {RequestMethod.GET})
    public ModelAndView snakeAdded(@Valid AddSnakeFormBean form, BindingResult bindingResult) throws Exception {
        ModelAndView response = new ModelAndView();

        if (bindingResult.hasErrors()) {
            for (ObjectError error : bindingResult.getAllErrors()) {
                log.info(((FieldError) error).getField() + " " + error.getDefaultMessage());
            }
            response.addObject("bindingResult", bindingResult);

            // if there is one or more errors we don't want to continue with the creating process,
            // and show register.jsp page
            response.setViewName("snake/addSnake");
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
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) principal).getUsername();

        User user = userDAO.findByEmail(username);
        log.info("user in add: "+user);
        snake.setUserId(user.getId());

        snake.setSpecies(form.getSpecies());
        snake.setSex(form.getSex());
        snake.setBirthDate(form.getBirthDate());
        snake.setNote(form.getNote());
        snake.setImgUrl(form.getImgUrl());

        log.info("snake before save: "+ snake);
        snakeDAO.save(snake);

//        HashMap<Integer, Snake> map = new HashMap<>();
//        for (Snake x:snakes) {
//            map.put(x.getId(), x);
//        }
//        response.addObject("mapModel", map);

        // if have time, add adding successful message
        // use redirect to trigger the next method/function
        response.setViewName("redirect:/snake/showSnakes");
        return response;
    }

    @GetMapping(value = "/snake/showSnakes")
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
            search = "search animal...";
        }
//        snakes.forEach((x)->log.info(""+x));
        // this line puts the list of users we just queried into the model
        // usersModelKey - users: is a key-value pair in a model map
        response.addObject("snakesModel", snakes);

        response.addObject("searchTerm", search);
        return response;
    }


    @GetMapping(value = "/snake/edit/{snakeId}")
    public ModelAndView editSnake(@PathVariable("snakeId") Integer snakeId) throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("snake/addSnake");

        Snake snake = snakeDAO.findById(snakeId);
        AddSnakeFormBean form = new AddSnakeFormBean();

        form.setId(snake.getId());
        form.setSpecies(snake.getSpecies());
        form.setSex(snake.getSex());
        form.setBirthDate(snake.getBirthDate());
        form.setNote(snake.getNote());
        form.setImgUrl(snake.getImgUrl());

        response.addObject("snakeFormBean", form);

        return response;
    }

    @GetMapping(value = "/snake/remove/{snakeId}")
    public ModelAndView removeSnake(@PathVariable("snakeId") Integer snakeId) throws Exception {
        ModelAndView response = new ModelAndView();
        log.info("in remove");
        response.setViewName("snake/showSnakes");

        Snake snake = snakeDAO.findById(snakeId);
        log.info("found snake: " + snake);
        if (snake != null) {
            snakeDAO.delete(snake);
        }
        response.setViewName("redirect:/snake/showSnakes");
        return response;
    }
}
