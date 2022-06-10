package serv.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import serv.models.*;
import serv.sevices.*;

import java.security.Principal;
import java.util.Comparator;
import java.util.List;

@Controller
public class MyController {

    @Autowired
    private UserService userService;

    @Autowired
    private OwnerService ownerService;

    @Autowired
    private AutoService autoService;

    @Autowired
    private OrderService orderService;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String main(Model model, Principal principal) {
        model.addAttribute("owners", ownerService.getOwnersByUserId(userService.getIdbyName(principal.getName())));
        model.addAttribute("owner", new Owner());
        model.addAttribute("user", principal.getName());
        model.addAttribute("autos", autoService.getAutos());
        model.addAttribute("auto", new Auto());
        model.addAttribute("order", new Order());

        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String admin() {
        return "admin";
    }

    @RequestMapping(value = "/admin/registration", method = RequestMethod.GET)
    public String registration(User user) {
        return "registration";
    }

    @RequestMapping(value = "/admin/registration", method = RequestMethod.POST)
    String signUp(@ModelAttribute User user) {
        userService.signUpUser(user);
        return "redirect:/admin";
    }

    @RequestMapping(value = "/admin/isusername", method = RequestMethod.GET)
    public ResponseEntity<Boolean> isUsernameExist(@RequestParam String username) {
        try {
            userService.loadUserByUsername(username);
            return new ResponseEntity<Boolean>(true, HttpStatus.OK);
        }catch (UsernameNotFoundException e) {
            return new ResponseEntity<Boolean>(false, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/admin/createauto", method = RequestMethod.GET)
    public String getWindowCreateAuto(Auto auto) {
        return "newauto";
    }

    @RequestMapping(value = "/admin/createauto", method = RequestMethod.POST)
    public String createAuto(@ModelAttribute Auto auto) {
        autoService.createAuto(auto);
        return "redirect:/admin";
    }

    @RequestMapping(value = "/admin/deleteuser", method = RequestMethod.GET)
    public String windowDeleteUser(Model model) {
        model.addAttribute("users", userService.getUsers().listIterator(1));
        return "deleteuser";
    }

    @RequestMapping(value = "/admin/deleteuser", method = RequestMethod.POST)
    public Object deleteUser(@RequestParam(value = "user", required = true) int[] id) {
        for (int i : id) {
            userService.deleteUser(i);

        }
        return "redirect:/admin";
    }

    @RequestMapping(value = "/admin/deleteauto", method = RequestMethod.GET)
    public String windowDeleteAuto(Model model) {
        model.addAttribute("autos", autoService.getAutos());
        return "deleteauto";
    }

    @RequestMapping(value = "/admin/deleteauto", method = RequestMethod.POST)
    public Object deleteAuto(@RequestParam(value = "auto", required = true) int[] id) {
        for (int i : id) {
            autoService.delete(i);
        }
        return "redirect:/admin";
    }

    @RequestMapping(value = "/getowners", method = RequestMethod.GET)
    public ResponseEntity<List<Owner>> getOwners() {
        return new ResponseEntity<List<Owner>>(ownerService.getOwners(), HttpStatus.OK);
    }

    @RequestMapping(value = "/admin/getorders", method = RequestMethod.GET)
    public String getOrders(Model model) {
        List<Order> a = orderService.getOrders();
        a.sort(Comparator.comparing(Order::getDate).reversed());
        model.addAttribute("orders", a);
        return "order";
    }

    @RequestMapping(value = "/getuserorders", method = RequestMethod.GET)
    public String getUserOrders(Model model, Authentication authentication) {
        model.addAttribute("orders", orderService.getOrderByUserId(userService.getIdbyName(authentication.getName())).listIterator(0));
        return "order";
    }

    @RequestMapping(value = "/admin/usedautos", method = RequestMethod.GET)
    public String getUsedAutos(Model model) {
        List<Auto> a = autoService.getAutos();
        model.addAttribute("autos", a);
        return "autoTemplate";
    }

    @RequestMapping(value = "/createowner", method = RequestMethod.POST)
    public String createOwner(@ModelAttribute Owner owner, Authentication authentication) {
        owner.setUserId(userService.getIdbyName(authentication.getName()));
        ownerService.createOwner(owner);
        return "redirect:/";
    }

    @RequestMapping(value = "/createorder", method = RequestMethod.POST)
    public String createOrder(@ModelAttribute("order") Order order,
                              @RequestParam(value = "days", required = true) int days,
                              @RequestParam(value = "owner", required = true) int ownerId,
                              @RequestParam(value = "auto", required = true) int autoId,
                              Authentication authentication
                              )

    {
        Order a = new Order();
        a.setDays(days);
        a.setOwner(ownerService.getOwnerById(ownerId));
        a.setAuto(autoService.getAutoById(autoId));
        a.setUserId(userService.getIdbyName(authentication.getName()));
        orderService.createOrder(a);
        return "redirect:/";
    }

    @RequestMapping(value = "/changepassword", method = RequestMethod.GET)
    public String windowChangePassword() {
        return "password";
    }

    @RequestMapping(value = "/changepassword", method = RequestMethod.POST)
    public String changePassword(@RequestParam(value = "oldPassword", required = true) String oldPassword,
                                 @RequestParam(value = "newPassword", required = true) String newPassword,
                                 Principal principal,
                                 Model model) throws Exception {
        try {
            userService.changePassword(principal.getName(), oldPassword, newPassword);
            return "redirect:/";
        } catch (Exception e) {

            model.addAttribute("error", e.getMessage());

            return "password";

        }

    }

}