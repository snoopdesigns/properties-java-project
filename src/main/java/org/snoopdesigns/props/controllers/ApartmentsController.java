package org.snoopdesigns.props.controllers;

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
    public String load() throws Exception {
        StringBuilder sb = new StringBuilder();
        apartmentsRepository.findAll().forEach(e -> sb.append(e.toString() + "<br>"));
        return sb.toString();
    }
}
