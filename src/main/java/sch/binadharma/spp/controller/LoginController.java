package sch.binadharma.spp.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@Slf4j
public class LoginController {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(LoginController.class);
    @GetMapping("login")
    public String getLoginPage(){
        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return "/auth/login";
        //}

        //return "redirect:/auth/login";
    }


//    public String getLoginView(@RequestParam(value = "invalid-session", defaultValue = "false") boolean invalidSession,
//                               final Model model){
//
//        if(invalidSession){
//             model.addAttribute("invalidSession","Session expired please re-login");
//        }
//        return "login"; //name must be exact the same with the one in templates folder
//    }

//    @GetMapping("category")
//    public String getCategoryView(@RequestParam(value = "message") String messageInfo,
//                                  final Model model){
//        model.addAttribute("message",messageInfo);
//        return "category"; //name must be exact the same with the one in templates folder
//    }
}
