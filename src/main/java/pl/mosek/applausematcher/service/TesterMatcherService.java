package pl.mosek.applausematcher.service;

import pl.mosek.applausematcher.dto.TesterBugCountDto;

import java.util.List;
import java.util.Set;

public interface TesterMatcherService {
    List<TesterBugCountDto> findMatchingTesters(Set<String> countryCodes , Set<String> devices);
}
