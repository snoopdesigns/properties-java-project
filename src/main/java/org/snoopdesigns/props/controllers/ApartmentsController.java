package org.snoopdesigns.props.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

import org.snoopdesigns.props.crawler.nextgen.entities.Apartment;
import org.snoopdesigns.props.persistence.PersistenceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/apartments")
public class ApartmentsController {

    @Autowired
    private PersistenceServiceImpl persistenceService;

    @Autowired
    private FilteringService filteringService;

    @RequestMapping(method = RequestMethod.POST, value = "/load")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Apartment> loadWithFilter(@RequestBody ApartmentsFilter apartmentsFilter,
                                          HttpServletRequest request) throws Exception {
        return filteringService.findWithFilter(apartmentsFilter);
    }

    @RequestMapping(value = "/load")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Apartment> load() throws Exception {
        return persistenceService.getAllApartments();
    }
}
