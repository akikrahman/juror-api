package uk.gov.hmcts.juror.api.jurorer.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;
import uk.gov.hmcts.juror.api.moj.domain.Role;
import uk.gov.hmcts.juror.api.moj.domain.UserType;
import uk.gov.hmcts.juror.api.moj.domain.authentication.UserCourtDto;
import uk.gov.hmcts.juror.api.validation.ValidationConstants;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@SuperBuilder
@AllArgsConstructor
@ToString(callSuper = true)
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ErUserDetailsDto {
    @NotBlank
    private String username;
    @NotBlank
    private String email;
    @NotBlank
    private String name;

    @NotNull
    private Boolean isActive;
    @JsonFormat(pattern = ValidationConstants.DATETIME_FORMAT)
    private LocalDateTime lastSignIn;
    private UserType userType;

    private Set<Role> roles;
    private List<UserCourtDto> courts;

    private BigDecimal approvalLimit;

}
