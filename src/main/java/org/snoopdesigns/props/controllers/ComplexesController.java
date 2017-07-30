package org.snoopdesigns.props.controllers;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

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
    @Produces(MediaType.APPLICATION_JSON)
    public List<Complex> load() throws Exception {
        List<Complex> complexes = new ArrayList<>();
        complexes.addAll(complexRepository.findAll());
        return complexes;
    }
}
