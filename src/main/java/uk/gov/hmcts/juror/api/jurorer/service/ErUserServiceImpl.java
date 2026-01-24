package uk.gov.hmcts.juror.api.jurorer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uk.gov.hmcts.juror.api.jurorer.repository.ErUserRepository;
import uk.gov.hmcts.juror.api.jurorer.domain.*;
import uk.gov.hmcts.juror.api.moj.exception.MojException;


import uk.gov.hmcts.juror.api.moj.service.JwtService;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ErUserServiceImpl implements ErUserService {


    private final ErUserRepository userRepository;
    private final JwtService jwtService;


    @Override
    @Transactional
    public ErJwtDto createJwt(String email, String locCode) {
        ErUser user = findUserByUsername(email);

        throw new MojException.Forbidden("User not part of court", null);
    }


    @Override
    @Transactional(readOnly = true)
    public ErUserDetailsDto getUser(String username) {
        ErUser user = findUserByUsername(username);
        return new ErUserDetailsDto();
    }


    @Override
    @Transactional(readOnly = true)
    public ErUser findUserByUsername(String username) {
        return userRepository.findById(username).orElseThrow(
            () -> new MojException.NotFound("User not found", null)
        );
    }

    String createUsername(String email) {
        String username = email.split("@")[0];
        username = username.substring(0, Math.min(username.length(), 30));
        // limit to 28 characters (DB constraint + 2 digits for numerics)
        int i = 1;
        String usernameTemp = username;
        while (userRepository.existsById(usernameTemp)) {
            usernameTemp = username + i;
        }
        return usernameTemp;
    }

}
