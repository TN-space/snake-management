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

    // This method to show addSnake form
    @RequestMapping(value = "/snake/add", method = RequestMethod.GET)
    public ModelAndView addingSnake() throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("snake/addSnake");
        return response;
    }

//    RequestMethod.POST, <-- this request post is taken from this request below
    // Once the submit button in the above form is clicked, this method is triggered (because action in above form points to this path "/snake/added"
    @RequestMapping(value = "/snake/added", method = {RequestMethod.GET})
    public ModelAndView snakeAdded(@Valid AddSnakeFormBean form, BindingResult bindingResult) throws Exception {
        ModelAndView response = new ModelAndView();

        // if there is any errors
        if (bindingResult.hasErrors()) {
            // iterate over all field
            for (ObjectError error : bindingResult.getAllErrors()) {
                // log out error for each field
                log.info(((FieldError) error).getField() + " " + error.getDefaultMessage());
            }
            response.addObject("bindingResult", bindingResult);

            // if there is one or more errors we don't want to continue with the creating process,
            // and re-render addSnake form again
            response.setViewName("snake/addSnake");
            return response;
        }

        // first, we assumed it's an edit, and thus we want to query the snake from database using id
        Snake snake = snakeDAO.findById(form.getId());
        // if snake is null, aka the id isn't there, aka new snake
        if (snake == null) {
            // hence, create new snake
            snake = new Snake();
        }

        // to calculate age dynamically - not working yet
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

        // get username (user email, in this case)
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) principal).getUsername();

        // find user using said username
        User user = userDAO.findByEmail(username);
        // set association between the new snake and the user, using user id
        snake.setUserId(user.getId());

        // set snake information using info passed in from the form
        snake.setSpecies(form.getSpecies());
        snake.setSex(form.getSex());
        snake.setBirthDate(form.getBirthDate());
        snake.setNote(form.getNote());
        snake.setImgUrl(form.getImgUrl());

        // save the new snake into database
        snakeDAO.save(snake);

        // this redirect triggers showSnakes method below
        response.setViewName("redirect:/snake/showSnakes");
        return response;
    }

    // This method to show all snakes
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

        // this line puts the list of snakes we just queried into the model
        // snakesModel - snakes: is a key-value pair in a model map
        response.addObject("snakesModel", snakes);

        response.addObject("searchTerm", search);
        return response;
    }


    // This method to edit a snake's information when edit button is clicked and url changed to /snake/edit/{snakeId}
    @GetMapping(value = "/snake/edit/{snakeId}")
    public ModelAndView editSnake(@PathVariable("snakeId") Integer snakeId) throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("snake/addSnake");

        // query the snake using snakeId
        Snake snake = snakeDAO.findById(snakeId);
        // creat a new form
        AddSnakeFormBean form = new AddSnakeFormBean();

        // set queried snake info into the new form
        form.setId(snake.getId());
        form.setSpecies(snake.getSpecies());
        form.setSex(snake.getSex());
        form.setBirthDate(snake.getBirthDate());
        form.setNote(snake.getNote());
        form.setImgUrl(snake.getImgUrl());

        // save all the info into snakeFormBean to be used in jsp and show on ui
        response.addObject("snakeFormBean", form);

        return response;
    }

    // This method remove a snake using snakeId
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
