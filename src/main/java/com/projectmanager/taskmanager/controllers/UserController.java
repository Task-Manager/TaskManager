package com.projectmanager.taskmanager.controllers;

import com.projectmanager.taskmanager.dtos.UserDto;
import com.projectmanager.taskmanager.entities.Role;
import com.projectmanager.taskmanager.entities.User;
import com.projectmanager.taskmanager.services.impl.RoleServiceImpl;
import com.projectmanager.taskmanager.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class UserController {
    private RoleServiceImpl roleService;
    private UserServiceImpl userService;

    @Autowired
    public UserController(RoleServiceImpl roleService,
                          UserServiceImpl userService) {

        this.roleService = roleService;
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login(Model model) {

        model.addAttribute("view", "user/login");
        return "base-layout";
    }


    /*
        RequestMapping - combines Get and Post request / not only /.
        We specify that we are interested in GET request.

        This method checks if there is logged in user and if there is,
        it simply tells the authentication module to logout the user.
        Then it redirects to the login page again.
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null) {
            // logout of the currently logged user.
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }

        /*
            This url has been setup in WebSecurityConfig class in method configure.
         */
        return "redirect:/login?logout";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("view", "user/register");
        return "base-layout";
    }

    @PostMapping("/register")
    public String processRegister(Model model, UserDto userDto,
                                  BindingResult result) {

        if (!userDto.getPassword().equals(userDto.getConfirmPassword())) {
            return "redirect:/register";
        }

        /*
            Creating new user from the form data / userDto /
            with encoded password.
         */
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User user = new User(
                userDto.getEmail(),
                userDto.getFullName(),
                passwordEncoder.encode(userDto.getPassword()));

        /*
            Setting new role for our user.
            We can change permissions here.
         */
        Role userRole = this.roleService.findByName("ROLE_USER");
        user.addRole(userRole);
        this.userService.addNewUser(user);


        return "redirect:/login";
    }

    @GetMapping("/profile")
//    @PreAuthorize("isAuthenticated()") uncomment this to require the user to be login to access this page.
    public String profilePage(Model model) {

        /*
            Remove this if check only if @PreAuthorize is active / uncommented /.
            Otherwise this method throws runtime exception.
         */
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) {
            return "redirect:/";
        }


        /*
            This will get our currently logged in user.
            It will have all basic information -> principal.getUsername() returns our USER EMAIL !!
            because we've setted up that our loggin will be with email not by username.
            Principal will have also this user roles + password.
         */
        UserDetails principal =
                (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = this.userService.findByEmail(principal.getUsername());

        model.addAttribute("user", user);
        model.addAttribute("view", "user/profile");
        return "base-layout";
    }
}
