package pl.mosek.applausematcher.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.mosek.applausematcher.dto.MatchedTesterResponseDto;
import pl.mosek.applausematcher.mapper.TesterMapper;
import pl.mosek.applausematcher.service.TesterMatcherService;

import java.util.List;
import java.util.Set;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("testers")
@RequiredArgsConstructor
public class TesterMatcherControllerImpl implements TesterMatcherController {

    private final TesterMatcherService testerMatcherService;
    private final TesterMapper testerMapper;

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<MatchedTesterResponseDto> findMatchingTesters(@RequestParam Set<String> countryCodes,
                                                              @RequestParam Set<String> deviceDescriptions) {

        //validation of alpha-2 countryCodes omitted purposely for the sake of simplicity

        return testerMatcherService.findMatchingTesters(countryCodes, deviceDescriptions)
                .stream()
                .map(testerMapper::mapToReponseDto)
                .toList();
    }
}
