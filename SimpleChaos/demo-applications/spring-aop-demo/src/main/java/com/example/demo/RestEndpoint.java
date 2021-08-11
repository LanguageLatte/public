package com.example.demo;

import com.example.demo.annotated_classes.DemoComponent;
import com.example.demo.annotated_classes.DemoController;
import com.example.demo.annotated_classes.DemoRepository;
import com.example.demo.annotated_classes.DemoService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/simplechaos/aop")
public class RestEndpoint {

    private DemoService demoService;
    private DemoRepository demoRepository;
    private DemoComponent demoComponent;
    private DemoController demoController;
    public RestEndpoint(DemoService serviceA, DemoRepository demoRepository, DemoComponent demoComponent,DemoController demoController){
        this.demoService = serviceA;
        this.demoRepository = demoRepository;
        this.demoComponent = demoComponent;
        this.demoController = demoController;
    }
    
    
    @RequestMapping(value = "/service", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<String> service() {  
        demoService.testMethod();
        String result = "Service";
        return new ResponseEntity<String>(result , HttpStatus.OK);
    }

    @RequestMapping(value = "/repository", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<String> repository() {  
        demoRepository.testMethod();
        String result = "repository";
        return new ResponseEntity<String>(result , HttpStatus.OK);
    }

    @RequestMapping(value = "/component", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<String> component() {  
        demoComponent.testMethod();
        String result = "component";
        return new ResponseEntity<String>(result , HttpStatus.OK);
    }

    @RequestMapping(value = "/controller", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<String> controller() {  
        demoController.testMethod();
        String result = "controller";
        return new ResponseEntity<String>(result , HttpStatus.OK);
    }
    
}
