package pl.mosek.applausematcher.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import org.springframework.http.HttpStatus;

import java.time.Instant;
import java.util.List;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ApiError(
        @NotBlank
        Instant timestamp,
        @NotBlank
        String correlationId,
        @NotBlank
        HttpStatus httpStatus,
        @NotBlank
        Integer httpStatusCode,
        @NotEmpty
        List<String> messages,
        List<ApiFieldError> apiFieldErrors
) {
}
