package org.snoopdesigns.props.controllers;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

import org.snoopdesigns.props.crawler.nextgen.entities.Complex;
import org.snoopdesigns.props.persistence.PersistenceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/datainfo")
public class DataInfoController {

    @Autowired
    private PersistenceServiceImpl persistenceService;

    @RequestMapping(value = "/load")
    @Produces(MediaType.APPLICATION_JSON)
    public DataInfo getDataInfo() {
        List<Complex> complexList = persistenceService.getAllComplexes();
        Integer numApartments = complexList.stream().mapToInt(c -> c.getApartments().size()).sum();
        return new DataInfo(complexList.size(), numApartments);
    }
}
