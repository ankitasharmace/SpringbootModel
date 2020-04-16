package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
class Controller {
    @Autowired
    private Handler handler;

    @RequestMapping(value = "/hotel/",method = RequestMethod.POST,consumes={MediaType.APPLICATION_JSON_VALUE},produces={MediaType.APPLICATION_JSON_VALUE})

    @ResponseBody
    public ResponseEntity<RequestFormat> print(@RequestBody RequestFormat hotelid) throws IOException{
        RequestFormat value = new RequestFormat();
        value= (RequestFormat) handler.processInput(hotelid);
        return new ResponseEntity<>(value,HttpStatus.OK);
    }
}






















