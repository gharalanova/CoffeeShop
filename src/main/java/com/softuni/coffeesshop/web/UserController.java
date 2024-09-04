package com.softuni.coffeesshop.web;

import com.softuni.coffeesshop.model.binding.LoginBindingModel;
import com.softuni.coffeesshop.model.binding.UserRegisterBindingModel;
import com.softuni.coffeesshop.model.service.UserServiceModel;
import com.softuni.coffeesshop.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String registerConfirm(@Valid UserRegisterBindingModel userRegisterBindingModel,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors() || !userRegisterBindingModel.getPassword()
                .equals(userRegisterBindingModel.getConfirmPassword())) {

            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult);

            return "redirect:register";
        }

        //TODO db
        userService.registerUser(modelMapper
                .map(userRegisterBindingModel, UserServiceModel.class));

        return "redirect:login";
    }

    @GetMapping("/login")
    public String login(Model model) {

        if (!model.containsAttribute("isFound")) {
            model.addAttribute("isFound", true);
        }


        return "login";
    }

    @PostMapping("/login")
    public String loginConfirm(@Valid LoginBindingModel loginBindingModel,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("loginBindingModel", loginBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.loginBindingModel", bindingResult);

            return "redirect:login";
        }
//da proverim dali tazi kombinaciq syshtestvuva
        UserServiceModel userServiceModel = userService
                .findByUsernameAndPassword(loginBindingModel.getUsername(), loginBindingModel.getPassword());

        //TODO user not found

        if (userServiceModel == null) {
            redirectAttributes.addFlashAttribute("loginBindingModel", loginBindingModel);
            redirectAttributes.addFlashAttribute("isFound", false);

            return "redirect:login";
        }

        //TODO login user

        userService.loginUser(userServiceModel.getId(), userRegisterBindingModel().getUsername());

        return "redirect:/"; // home
    }

    @GetMapping("/logout")
    public  String logout(HttpSession httpSession) {
        httpSession.invalidate();

        return "redirect:/";
    }

    @ModelAttribute
    public UserRegisterBindingModel userRegisterBindingModel() {
        return new UserRegisterBindingModel();
    }

    @ModelAttribute
    public LoginBindingModel loginBindingModel() {
        return new LoginBindingModel();
    }
}
