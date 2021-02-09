package com.example.demo

import com.languagelatte.simplechaos.spring.ChaosService
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.http.ResponseEntity
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import java.lang.Error
import java.lang.Exception

@Controller
@RestController
@RequestMapping("/simplechaos")
class RestEndpoint(private val chaosService: ChaosService) {
    @RequestMapping(value = ["/exception"], method = [RequestMethod.GET])
    @ResponseBody
    fun exception(): ResponseEntity<String?> {
        var response: String
        try {
            chaosService.exception()
            response = "No Exception"
        } catch (e: Exception) {
            response = if (e.message != null) {
                e.message!!
            } else {
                "exception"
            }

        }
        return ResponseEntity(response, HttpStatus.OK)
    }

    @RequestMapping(value = ["/error"], method = [RequestMethod.GET])
    @ResponseBody
    fun error(): ResponseEntity<String?> {
        var response: String
        try {
            chaosService.error()
            response = "No Error"
        } catch (e: Error) {
            response = if (e.message != null) {
                e.message!!
            } else {
                "error"
            }
        }
        return ResponseEntity(response, HttpStatus.OK)
    }

    @RequestMapping(value = ["/latency"], method = [RequestMethod.GET])
    @ResponseBody
    fun latency(): ResponseEntity<String> {
        val response: String
        val start = System.currentTimeMillis()
        chaosService.latency()
        response = "Time Taken = " + (System.currentTimeMillis() - start)
        return ResponseEntity(response, HttpStatus.OK)
    }

    @RequestMapping(value = ["/jvmcrash"], method = [RequestMethod.GET])
    @ResponseBody
    fun jvmcrash(): ResponseEntity<String> {
        val response = "No Jvm Shutdown"
        chaosService.jvmCrash()
        return ResponseEntity(response, HttpStatus.OK)
    }
}