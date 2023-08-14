package pl.mosek.applausematcher.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ApiFieldError(
        @NotBlank
        String field,
        @NotBlank
        String message
) {
}
