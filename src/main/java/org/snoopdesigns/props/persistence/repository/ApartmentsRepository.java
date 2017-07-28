package org.snoopdesigns.props.persistence.repository;

import javax.transaction.Transactional;

import org.snoopdesigns.props.persistence.entities.Apartment;
import org.springframework.data.repository.CrudRepository;

@Transactional
public interface ApartmentsRepository extends CrudRepository<Apartment, Long> {
}
