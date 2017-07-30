package org.snoopdesigns.props.controllers;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

import org.snoopdesigns.props.persistence.entities.Apartment;
import org.snoopdesigns.props.persistence.repository.ApartmentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/apartments")
public class ApartmentsController {

    @Autowired
    private ApartmentsRepository apartmentsRepository;

    @RequestMapping(value = "/load")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Apartment> load() throws Exception {
        List<Apartment> apartments = new ArrayList<>();
        apartments.addAll(apartmentsRepository.findAll());
        return apartments;
    }
}
