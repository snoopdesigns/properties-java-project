package org.snoopdesigns.props.controllers;

import java.util.List;

import org.snoopdesigns.props.ml.DataPreprocessor;
import org.snoopdesigns.props.ml.entity.ApartmentExtended;
import org.snoopdesigns.props.persistence.repository.ApartmentsRepository;
import org.snoopdesigns.props.services.DataLoaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/dataload")
public class DataLoadingController {

    @Autowired
    private DataLoaderService dataLoader;

    @Autowired
    private DataPreprocessor dataPreprocessor;

    @Autowired
    private ApartmentsRepository apartmentsRepository;

    @RequestMapping(value = "/load")
    public String loadData() throws Exception {
        dataLoader.loadData();
        return "OK";
    }

    @RequestMapping(value = "/loadnew")
    public String loadDataNew() throws Exception {
        dataLoader.loadDataNew();
        return "OK";
    }

    @RequestMapping(value = "/process")
    public String preprocessData() throws Exception {
        List<ApartmentExtended> result = dataPreprocessor.preprocessData(apartmentsRepository.findAll());
        return "OK";
    }
}
