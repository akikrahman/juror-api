package uk.gov.hmcts.juror.api.jurorer.repository;


import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uk.gov.hmcts.juror.api.jurorer.domain.ErUser;

import java.util.Optional;

@Repository
public interface ErUserRepository extends CrudRepository<ErUser, String>, QuerydslPredicateExecutor<ErUser> {

    ErUser findByUsername(String username);

    boolean existsByUsername(String username);
}
