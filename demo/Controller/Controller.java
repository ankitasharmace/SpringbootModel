package com.example.demo.Controller;

import com.example.demo.Entities.RequestFormat;
import com.example.demo.Model.Ranking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;

@RestController
class Controller {
    @Autowired
    private Ranking ranking;

    @RequestMapping(value = "/hotel/",method = RequestMethod.POST,consumes={MediaType.APPLICATION_JSON_VALUE},produces={MediaType.APPLICATION_JSON_VALUE})

    @ResponseBody
    public List<String> print(@RequestBody RequestFormat hotelid) throws IOException{
        List<String> value= ranking.processInput(hotelid);
        return value;
    }
}






















