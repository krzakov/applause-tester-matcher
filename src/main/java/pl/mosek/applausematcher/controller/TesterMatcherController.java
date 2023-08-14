package pl.mosek.applausematcher.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
            summary = "Find all Device Descriptions",
            description = "Find all Device Descriptions used by Testers",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Found device descriptions",
                            content = @Content(
                                    array = @ArraySchema(
                                            schema = @Schema(implementation = String.class))
                            )
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
    @ResponseStatus(HttpStatus.OK)
    List<String> findAllDeviceDescriptions();

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
    List<MatchedTesterResponseDto> findMatchingTesters(
            @Parameter(description = "Set of country codes compliant with ISO 3166-1 alpha-2",
                    example = "US,JP",
                    schema = @Schema(defaultValue = "US,JP"))
            Set<String> countryCodes,
            @Parameter(description = "Set of device descriptions",
                    example = "iPhone 4",
                    schema = @Schema(defaultValue = "iPhone 4")) Set<String> deviceDescriptions);
}
