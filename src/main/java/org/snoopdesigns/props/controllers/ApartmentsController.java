package org.snoopdesigns.props.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

import org.snoopdesigns.props.persistence.entities.Apartment;
import org.snoopdesigns.props.persistence.repository.ApartmentsFilterRepository;
import org.snoopdesigns.props.persistence.repository.ApartmentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/apartments")
public class ApartmentsController {

    @Autowired
    private ApartmentsRepository apartmentsRepository;

    @Autowired
    private ApartmentsFilterRepository apartmentsFilterRepository;

    @RequestMapping(method = RequestMethod.POST, value = "/load")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Apartment> loadWithFilter(@RequestBody ApartmentsFilter apartmentsFilter,
                                          HttpServletRequest request) throws Exception {
        return apartmentsFilterRepository.findWithFilter(apartmentsFilter);
    }

    @RequestMapping(value = "/load")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Apartment> load() throws Exception {
        List<Apartment> apartments = new ArrayList<>();
        apartments.addAll(apartmentsRepository.findAll());
        return apartments;
    }
}
