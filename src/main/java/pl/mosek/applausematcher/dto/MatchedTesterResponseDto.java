package pl.mosek.applausematcher.dto;


import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record MatchedTesterResponseDto(long id,
                                       String firstName,
                                       String lastName,
                                       String country,
                                       LocalDateTime lastLogin,
                                       Long bugCount) {
}
