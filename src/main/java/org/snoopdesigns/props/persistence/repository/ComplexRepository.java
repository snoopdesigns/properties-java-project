package org.snoopdesigns.props.persistence.repository;

import javax.transaction.Transactional;

import org.snoopdesigns.props.crawler.nextgen.entities.Complex;
import org.snoopdesigns.props.persistence.entities.ComplexEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Transactional
public interface ComplexRepository extends JpaRepository<ComplexEntity, Long> {

    @Query("select c from ComplexEntity c where c.cianId = ?1")
    public Complex findByCianId(Integer cianId);
}
