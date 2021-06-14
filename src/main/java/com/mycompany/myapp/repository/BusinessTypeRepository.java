package com.mycompany.myapp.repository;

import com.cosium.spring.data.jpa.entity.graph.domain.EntityGraph;
import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphJpaRepository;
import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphJpaSpecificationExecutor;
import com.mycompany.myapp.domain.BusinessType;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

// jhipster-needle-add-import - JHipster will add getters and setters here, do not remove

/**
 * Spring Data SQL repository for the BusinessType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BusinessTypeRepository extends JpaRepository<BusinessType, Long>, JpaSpecificationExecutor<BusinessType> {
    // jhipster-needle-repository-add-method - JHipster will add getters and setters here, do not remove
}
