package com.nubank.capitalgains.controller;

import com.nubank.capitalgains.domain.Input;
import com.nubank.capitalgains.domain.Output;
import com.nubank.capitalgains.service.ICalculateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/calculate")
public class CalculateController {

    @Autowired
    private ICalculateService calculateService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<List<Output>> calculate(@RequestBody List<Input> request) {
        if(request.isEmpty())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<List<Output>>(calculateService.calculateTax(request), HttpStatus.OK);
    }
}
