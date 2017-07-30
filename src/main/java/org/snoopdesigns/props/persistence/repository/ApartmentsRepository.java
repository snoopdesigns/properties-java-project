package org.snoopdesigns.props.persistence.repository;

import javax.transaction.Transactional;

import org.snoopdesigns.props.persistence.entities.Apartment;
import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
public interface ApartmentsRepository extends JpaRepository<Apartment, Long> {
}
