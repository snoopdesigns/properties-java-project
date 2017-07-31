package org.snoopdesigns.props.persistence.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.snoopdesigns.props.controllers.ApartmentsFilter;
import org.snoopdesigns.props.persistence.entities.Apartment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ApartmentsFilterRepository {

    @Autowired
    private ApartmentsRepository apartmentsRepository;

    public List<Apartment> findWithFilter(ApartmentsFilter af) {
        List<Apartment> apartments = new ArrayList<>();
        apartments.addAll(apartmentsRepository.findAll());
        return apartments.stream()
                .filter(a -> af.getId() == null || af.getId().isEmpty() || (a.getId() != null && a.getId().toString().startsWith(af.getId())))
                .filter(a -> af.getCianId() == null || af.getCianId().isEmpty() || (a.getCianId() != null && a.getCianId().startsWith(af.getCianId())))
                .filter(a -> af.getUrl() == null || af.getUrl().isEmpty() || (a.getUrl() != null && a.getUrl().startsWith(af.getUrl())))
                .filter(a -> af.getHouseType() == null || af.getHouseType().isEmpty() || (a.getHouseType() != null && a.getHouseType().startsWith(af.getHouseType())))
                .filter(a -> af.getSellType() == null || af.getSellType().isEmpty() || (a.getSellType() != null && a.getSellType().startsWith(af.getSellType())))
                .filter(a -> af.getTotalArea() == null || af.getTotalArea().isEmpty() || (a.getTotalArea() != null && a.getTotalArea().toString().startsWith(af.getTotalArea())))
                .filter(a -> af.getRoomsArea() == null || af.getRoomsArea().isEmpty() || (a.getRoomsArea() != null && a.getRoomsArea().toString().startsWith(af.getRoomsArea())))
                .filter(a -> af.getLivingArea() == null || af.getLivingArea().isEmpty() || (a.getLivingArea() != null && a.getLivingArea().toString().startsWith(af.getLivingArea())))
                .filter(a -> af.getPrice() == null || af.getPrice().isEmpty() || (a.getPrice() != null && a.getPrice().toString().startsWith(af.getPrice())))
                .filter(a -> af.getAddress() == null || af.getAddress().isEmpty() || (a.getAddress() != null && a.getAddress().startsWith(af.getAddress())))
                .collect(Collectors.toList());
    }
}
