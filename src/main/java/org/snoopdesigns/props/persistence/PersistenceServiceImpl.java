package org.snoopdesigns.props.persistence;

import java.util.ArrayList;
import java.util.List;

import org.snoopdesigns.props.crawler.nextgen.entities.Apartment;
import org.snoopdesigns.props.crawler.nextgen.entities.Complex;
import org.snoopdesigns.props.persistence.entities.ApartmentEntity;
import org.snoopdesigns.props.persistence.entities.ComplexEntity;
import org.snoopdesigns.props.persistence.repository.ApartmentsRepository;
import org.snoopdesigns.props.persistence.repository.ComplexRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

@Component
public class PersistenceServiceImpl {

    @Autowired
    private ApartmentsRepository apartmentsRepository;

    @Autowired
    private ComplexRepository complexRepository;

    @Autowired
    private ConversionService conversionService;

    public List<Apartment> getAllApartments() {
        List<Apartment> result = new ArrayList<>();
        List<ApartmentEntity> apartmentEntities = apartmentsRepository.findAll();
        for (ApartmentEntity apartmentEntity : apartmentEntities) {
            Apartment ap = conversionService.convert(apartmentEntity, Apartment.class);
            Complex complex = conversionService.convert(apartmentEntity.getComplex(), Complex.class);
            ap.setComplex(complex);
            result.add(ap);
        }
        return result;
    }

    public List<Complex> getAllComplexes() {
        List<ComplexEntity> complexEntities = complexRepository.findAll();
        List<Complex> result = new ArrayList<>();
        for (ComplexEntity complexEntity : complexEntities) {
            Complex complex = conversionService.convert(complexEntity, Complex.class);
            List<Apartment> apartments = new ArrayList<>();
            complexEntity.getApartments().forEach(a -> apartments.add(conversionService.convert(a, Apartment.class)));
            complex.getApartments().addAll(apartments);
            result.add(complex);
        }
        return result;
    }

    public void saveComplex(Complex complex) {
        List<ApartmentEntity> apartmentEntities = new ArrayList<>();
        ComplexEntity complexEntity = conversionService.convert(complex, ComplexEntity.class);
        complexRepository.save(complexEntity);
        for (Apartment apartment : complex.getApartments()) {
            ApartmentEntity apartmentEntity = conversionService.convert(apartment, ApartmentEntity.class);
            apartmentEntity.setComplex(complexEntity);
            apartmentsRepository.save(apartmentEntity);
        }
    }
}
