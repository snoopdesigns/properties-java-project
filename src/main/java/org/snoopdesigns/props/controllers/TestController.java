package org.snoopdesigns.props.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.snoopdesigns.props.persistence.repository.ComplexRepository;
import org.snoopdesigns.props.services.DataLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    private final static Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private DataLoader dataLoader;

    @Autowired
    private ComplexRepository complexRepository;

    @RequestMapping("/")
    public String index() throws Exception {
        logger.info("FUUUUUU");
        return dataLoader.loadData();
    }

    @RequestMapping("/c")
    public String complexes() throws Exception {
        StringBuilder sb = new StringBuilder();
        complexRepository.findAll().forEach(e -> sb.append(e.toString() + "<br>"));
        return sb.toString();
    }
}
