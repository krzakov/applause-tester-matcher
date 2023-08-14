package pl.mosek.applausematcher.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.mosek.applausematcher.dto.MatchedTesterResponseDto;
import pl.mosek.applausematcher.exception.ApiError;

import java.util.List;
import java.util.Set;

public interface TesterMatcherController {

    @Operation(
            summary = "Find matching Testers for criteria",
            description = "Find matching Testers for criteria",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Matched testers found successfully",
                            content = @Content(
                                    array = @ArraySchema(
                                            schema = @Schema(implementation = MatchedTesterResponseDto.class))
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad request, invalid device description",
                            content = @Content(schema = @Schema(implementation = ApiError.class))
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal Server Error",
                            content = @Content(
                                    schema = @Schema(implementation = ApiError.class)
                            )
                    )
            }
    )
    @ResponseStatus(HttpStatus.CREATED)
    List<MatchedTesterResponseDto> findMatchingTesters(Set<String> countryCodes, Set<String> deviceDescriptions);
}
