package org.snoopdesigns.props.controllers;

import org.snoopdesigns.props.crawler.DataLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/dataload")
public class DataLoadingController {

    @Autowired
    private DataLoader dataLoader;

    @RequestMapping(value = "/load")
    public String loadData() throws Exception {
        dataLoader.loadData(2);
        return "OK";
    }
}
