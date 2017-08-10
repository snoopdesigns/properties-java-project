package org.snoopdesigns.props.persistence.converters;

import org.snoopdesigns.props.crawler.nextgen.entities.Complex;
import org.snoopdesigns.props.persistence.entities.ComplexEntity;
import org.springframework.core.convert.converter.Converter;

@TypeConverter
public class Complex2ComplexEntityConverter implements Converter<Complex, ComplexEntity> {

    @Override
    public ComplexEntity convert(Complex complex) {
        ComplexEntity complexEntity = new ComplexEntity();
        complexEntity.setCianId(complex.getCianId());
        complexEntity.setName(complex.getName());
        complexEntity.setAddress(complex.getAddress());
        complexEntity.setLat(complex.getLat());
        complexEntity.setLng(complex.getLng());
        return complexEntity;
    }
}
