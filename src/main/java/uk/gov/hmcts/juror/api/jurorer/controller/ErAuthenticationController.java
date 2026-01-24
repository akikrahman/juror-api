package uk.gov.hmcts.juror.api.jurorer.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import uk.gov.hmcts.juror.api.jurorer.domain.ErEmailDto;
import uk.gov.hmcts.juror.api.jurorer.domain.ErJwtDto;
import uk.gov.hmcts.juror.api.jurorer.service.ErUserService;

@RestController
@Validated
@RequestMapping(value = "/api/v1/auth/juror-er", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Authentication")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ErAuthenticationController {

    ErUserService userService;


    @PostMapping("/jwt/{la_code}")
    @Operation(summary = "Creates a jwt for a given user at local authority")
    public ResponseEntity<ErJwtDto> createJwt(
        @P("la_code") @PathVariable("la_code") @Valid @NotBlank
        @Pattern(regexp = "^\\d{3}$") String laCode,
        @RequestBody @Valid ErEmailDto emailDto
    ) {
        return ResponseEntity.ok(userService.createJwt(emailDto.toString(), laCode));
    }
}
