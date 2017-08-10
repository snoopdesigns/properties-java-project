package org.snoopdesigns.props.ml;

import org.snoopdesigns.props.crawler.nextgen.entities.Apartment;

public abstract class SynteticFeatureGenerator<T> {
    public abstract T generate(Apartment apartmentExtended);
}
