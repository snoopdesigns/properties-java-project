package org.snoopdesigns.props.persistence.converters;

import org.snoopdesigns.props.crawler.nextgen.entities.Complex;
import org.snoopdesigns.props.persistence.entities.ComplexEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;

@TypeConverter
public class ComplexEntity2ComplexConverter implements Converter<ComplexEntity, Complex> {

    @Autowired
    private ConversionService conversionService;

    @Override
    public Complex convert(ComplexEntity complexEntity) {
        Complex complex = new Complex();
        complex.setCianId(complexEntity.getCianId());
        complex.setName(complexEntity.getName());
        complex.setAddress(complexEntity.getAddress());
        complex.setLat(complexEntity.getLat());
        complex.setLng(complexEntity.getLng());
        return complex;
    }
}
