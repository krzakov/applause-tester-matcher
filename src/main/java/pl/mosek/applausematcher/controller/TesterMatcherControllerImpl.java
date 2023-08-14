package pl.mosek.applausematcher.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.mosek.applausematcher.dto.MatchedTesterResponseDto;
import pl.mosek.applausematcher.mapper.TesterMapper;
import pl.mosek.applausematcher.service.DeviceService;
import pl.mosek.applausematcher.service.TesterMatcherFacade;

import java.util.List;
import java.util.Set;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
public class TesterMatcherControllerImpl implements TesterMatcherController {

    private final TesterMatcherFacade testerMatcherFacade;
    private final DeviceService deviceService;
    private final TesterMapper testerMapper;

    @GetMapping(value = "/devices", produces = APPLICATION_JSON_VALUE)
    public List<String> findAllDeviceDescriptions() {
        return deviceService.findDeviceDescriptions();
    }

    @GetMapping(value = "testers", produces = APPLICATION_JSON_VALUE)
    public List<MatchedTesterResponseDto> findMatchingTesters(@RequestParam(required = false, defaultValue = "") Set<String> countryCodes,
                                                              @RequestParam(required = false, defaultValue = "") Set<String> deviceDescriptions) {

        //validation of alpha-2 countryCodes omitted purposely for the sake of simplicity

        return testerMatcherFacade.findMatchingTesters(countryCodes, deviceDescriptions)
                .stream()
                .map(testerMapper::mapToReponseDto)
                .toList();
    }
}
