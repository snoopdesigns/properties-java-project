package org.snoopdesigns.props.persistence.repository;

import org.snoopdesigns.props.persistence.entities.Complex;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface ComplexRepository extends JpaRepository<Complex, Long> {

    @Query("select c from Complex c where c.cianId = ?1")
    public Complex findByCiannId(Integer cianId);
}
