package org.snoopdesigns.props.ml;

import org.snoopdesigns.props.ml.entity.ApartmentExtended;

public abstract class SynteticFeatureGenerator<T> {
    public abstract T generate(ApartmentExtended apartmentExtended);
}
