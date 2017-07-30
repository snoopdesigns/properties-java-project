package org.snoopdesigns.props.persistence.repository;

import javax.transaction.Transactional;

import org.snoopdesigns.props.persistence.entities.Complex;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Transactional
public interface ComplexRepository extends JpaRepository<Complex, Long> {

    @Query("select c from Complex c where c.cianId = ?1")
    public Complex findByCianId(Integer cianId);
}
