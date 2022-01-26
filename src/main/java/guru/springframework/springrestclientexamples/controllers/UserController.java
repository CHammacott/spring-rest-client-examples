package guru.springframework.springrestclientexamples.controllers;

import guru.springframework.api.domain.User;
import guru.springframework.springrestclientexamples.services.ApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@Controller
public class UserController {

    private ApiService apiService;

    public UserController(ApiService apiService) {
        this.apiService = apiService;
    }

    @GetMapping({"", "/", "/index"})
    public String index(){
        return "index";
    }

    @PostMapping("/users")
    public String formPost(Model model, ServerWebExchange serverWebExchange){
        serverWebExchange.getFormData()
                .map(map -> map.get("limit").get(0))
                .map(Integer::parseInt)
                .map(apiService::getUsers)
                .subscribe(data -> model.addAttribute("users", data));
        return "userList";

        //todo Blows up due to url issue and not parsing json
//        model.addAttribute("users",
//                apiService.getUsers(serverWebExchange
//                        .getFormData()
//                        .map(data -> new Integer(data.getFirst("limit")))));
//        return "userlist";
    }
}