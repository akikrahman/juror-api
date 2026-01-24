package uk.gov.hmcts.juror.api.jurorer.service;

import uk.gov.hmcts.juror.api.jurorer.domain.*;


public interface ErUserService {

    ErJwtDto createJwt(String email, String laCode);

    ErUserDetailsDto getUser(String username);

    ErUser findUserByUsername(String username);

}
