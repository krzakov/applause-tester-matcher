package pl.mosek.applausematcher.service;

import pl.mosek.applausematcher.domain.Device;
import pl.mosek.applausematcher.dto.TesterBugCountDto;

import java.util.List;
import java.util.Set;

public interface BugReportService {
    List<TesterBugCountDto> findMatchingTesters(Set<String> countryCodes , Set<Device> devices);
}
