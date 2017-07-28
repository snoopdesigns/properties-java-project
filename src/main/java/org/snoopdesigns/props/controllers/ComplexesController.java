package org.snoopdesigns.props.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.snoopdesigns.props.persistence.entities.Complex;
import org.snoopdesigns.props.persistence.repository.ComplexRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/complexes")
public class ComplexesController {

    private final static Logger logger = LoggerFactory.getLogger(ComplexesController.class);

    @Autowired
    private ComplexRepository complexRepository;

    @RequestMapping(value = "/load")
    public String load() throws Exception {
        StringBuilder sb = new StringBuilder();
        complexRepository.findAll().forEach(e -> sb.append(e.toString() + "<br>"));
        return sb.toString();
    }

    @RequestMapping(value = "/create")
    public String create() throws Exception {
        complexRepository.save(new Complex("cian1111"));
        return "SUCCESS";
    }
}
