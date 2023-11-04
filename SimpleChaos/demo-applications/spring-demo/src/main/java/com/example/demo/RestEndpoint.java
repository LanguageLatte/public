package com.example.demo;

import com.languagelatte.simplechaos.spring.ChaosService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
@RequestMapping("/simplechaos")
public class RestEndpoint {

    private final ChaosService chaosService;

    public RestEndpoint(ChaosService chaosService){
        this.chaosService = chaosService;
    }
    
    @RequestMapping(value = "/exception", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<String> exception() {  

        String response;
        try {   
            chaosService.exception();
            response = "No Exception";
        }catch (Exception e){
               response = e.getMessage();
        }
        return new ResponseEntity<String>(response , HttpStatus.OK);
    }

    @RequestMapping(value = "/error", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<String> error() {  

        String response;
        try {   
            chaosService.error();
            response = "No Error";
        }catch (Error e){
               response = e.getMessage();
        }
        return new ResponseEntity<String>(response , HttpStatus.OK);
    }

    @RequestMapping(value = "/latency", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<String> latency() {  

        String response;
        
        long start = System.currentTimeMillis();
        chaosService.latency();
        response = "Time Taken = " + (System.currentTimeMillis() - start);
        
        return new ResponseEntity<String>(response , HttpStatus.OK);
    }

    @RequestMapping(value = "/jvmcrash", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<String> jvmcrash() {  

        String response = "No Jvm Shutdown";
        
        chaosService.jvmCrash();
         
        return new ResponseEntity<String>(response , HttpStatus.OK);
    }
    
}
