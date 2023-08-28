package pl.mosek.applausematcher.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.mosek.applausematcher.domain.Device;
import pl.mosek.applausematcher.dto.TesterBugCountDto;
import pl.mosek.applausematcher.repository.BugReportRepository;

import java.util.List;
import java.util.Set;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class BugReportServiceImpl implements BugReportService {

    private final BugReportRepository bugReportRepository;

    public List<TesterBugCountDto> findMatchingTesters(Set<String> countryCodes, Set<Device> devices){
        log.info("fetching testers matching country codes: {} and devices: {}", countryCodes, devices);
        return bugReportRepository.findMatchedTestersByCountryAndDevicesOrderedByBugsDesc(countryCodes, devices);
    }
}
