package org.snoopdesigns.props.controllers;

import org.snoopdesigns.props.services.DataLoaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/dataload")
public class DataLoadingController {

    @Autowired
    private DataLoaderService dataLoader;

    @RequestMapping(value = "/load")
    public String loadDataNew(@RequestParam(name = "pages", required = false, defaultValue = "10") Integer numPages) throws Exception {
        dataLoader.loadData(numPages);
        return "OK";
    }
}
