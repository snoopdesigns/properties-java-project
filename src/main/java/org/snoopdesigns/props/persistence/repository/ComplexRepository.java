package org.snoopdesigns.props.persistence.repository;

import org.snoopdesigns.props.persistence.entities.Complex;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface ComplexRepository extends CrudRepository<Complex, Long> {
}
