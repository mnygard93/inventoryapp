package ax.ha.laboration2.inventory.controller;

import ax.ha.laboration2.inventory.controller.dto.UserRegistrationDto;
import ax.ha.laboration2.inventory.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/registration")
@CrossOrigin(origins = "http://localhost:5000")
public class UserRegistrationController {

    private final UserService userService;

    @Autowired
    public UserRegistrationController(final UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

    @GetMapping
    public String showRegistrationForm() {
        return "registration";
    }

    @PostMapping
    public String registerUser(@ModelAttribute("user") final UserRegistrationDto userRegistrationDto) {
        userService.saveUser(userRegistrationDto);
        return "redirect:/registration?success";
    }
}
