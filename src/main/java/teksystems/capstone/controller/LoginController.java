package teksystems.capstone.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import teksystems.capstone.formbean.user.RegisterFormBean;

import javax.validation.Valid;

@Slf4j
@Controller
public class LoginController {

    // This method to show log-in form
    @RequestMapping(value = "/login/login", method = RequestMethod.GET)
    public ModelAndView login() throws Exception {
        ModelAndView response = new ModelAndView();

        response.setViewName("user/login");

        return response;
    }

//    @RequestMapping(value = "/login/loginSubmit", method = {RequestMethod.POST, RequestMethod.GET})
//    public ModelAndView loginSubmit(@Valid RegisterFormBean form, BindingResult bindingResult) throws Exception {
//        ModelAndView response = new ModelAndView();
//
//        if (bindingResult.hasErrors()) {
//            for (ObjectError error : bindingResult.getAllErrors()) {
//                log.info(((FieldError) error).getField() + " " + error.getDefaultMessage());
//            }
//            response.addObject("bindingResult", bindingResult);
//
//            // if there is one or more errors we don't want to continue with the creating process,
//            // and show register.jsp page
////            response.setViewName("user/register");
////            return response;
//        }
//        return response;
//    }
}
