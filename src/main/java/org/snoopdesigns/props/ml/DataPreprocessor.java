package org.snoopdesigns.props.ml;

import java.util.Collections;
import java.util.List;

import org.snoopdesigns.props.ml.entity.ApartmentExtended;
import org.snoopdesigns.props.persistence.entities.Apartment;
import org.springframework.stereotype.Component;

@Component
public class DataPreprocessor {

    public List<ApartmentExtended> preprocessData(List<Apartment> apartments) {
        return Collections.emptyList();
    }

}
