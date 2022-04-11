package teksystems.capstone.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import teksystems.capstone.database.dao.FeederSnakeDAO;

@Slf4j
@Controller
public class FeederSnakeController {

    @Autowired
    private FeederSnakeDAO feederSnakeDAO;

    @RequestMapping(value = "/feederSnake/add", method = RequestMethod.GET)
    public ModelAndView creatingFeeding() throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("feederSnake/addFeederSnake");

        return response;
    }
}
