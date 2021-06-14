package com.mycompany.myapp.repository;

import com.cosium.spring.data.jpa.entity.graph.domain.EntityGraph;
import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphJpaRepository;
import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphJpaSpecificationExecutor;
import com.mycompany.myapp.domain.CommonLong;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

// jhipster-needle-add-import - JHipster will add getters and setters here, do not remove

/**
 * Spring Data SQL repository for the CommonLong entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CommonLongRepository extends JpaRepository<CommonLong, Long>, JpaSpecificationExecutor<CommonLong> {
    // jhipster-needle-repository-add-method - JHipster will add getters and setters here, do not remove
}
