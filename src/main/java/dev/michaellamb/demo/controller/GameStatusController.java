package dev.michaellamb.demo.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.michaellamb.demo.agent.GameStatusAgent;
import dev.michaellamb.demo.model.GameStatusResponse;

@RestController
@RequestMapping("/steam")
public class GameStatusController {

    @Resource
    GameStatusAgent gameStatusAgent;
    
    @GetMapping("/valheim")
    public GameStatusResponse getValheimStatus(@RequestParam(value = "address") String address, @RequestParam(value = "key") String key){
        return gameStatusAgent.getValheimInstanceStatus(address, key);
    }
}
