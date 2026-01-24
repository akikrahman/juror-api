package uk.gov.hmcts.juror.api.jurorer.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "user", schema = "juror_er")
@Data
@Builder
@AllArgsConstructor
@Audited
@EqualsAndHashCode(exclude = {"courts"})
public class ErUser implements Serializable {

    @Id
    @Column(name = "username", unique = true, length = 100)
    @NotEmpty
    @Size(min = 1, max = 100)
    private String username;

    @NotNull
    @Column(name = "active", nullable = false)
    @Builder.Default
    private boolean active = true;

    @NotAudited
    @JsonProperty("last_logged_in")
    private LocalDateTime lastLoggedIn;

    @Column(name = "updated_by")
    @LastModifiedBy
    @NotEmpty
    private String updatedBy;

    public ErUser() {

    }

}
