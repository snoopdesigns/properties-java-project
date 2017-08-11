package org.snoopdesigns.props.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.snoopdesigns.props.crawler.nextgen.entities.Complex;
import org.snoopdesigns.props.persistence.PersistenceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/complexes")
public class ComplexesController {

    private final static Logger logger = LoggerFactory.getLogger(ComplexesController.class);

    @Autowired
    private PersistenceServiceImpl persistenceService;

    @Autowired
    private FilteringService filteringService;

    @RequestMapping(method = RequestMethod.POST, value = "/load")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Complex> loadWithFilter(@RequestBody ComplexesFilter complexesFilter,
                                        HttpServletRequest request) throws Exception {
        return filteringService.findWithFilter(complexesFilter);
    }

    @RequestMapping(value = "/load")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Complex> load(String req, HttpServletRequest request) throws Exception {
        return persistenceService.getAllComplexes();
    }
}
