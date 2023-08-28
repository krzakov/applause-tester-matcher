package pl.mosek.applausematcher.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.mosek.applausematcher.domain.Device;
import pl.mosek.applausematcher.dto.TesterBugCountDto;
import pl.mosek.applausematcher.exception.NotFoundException;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class TesterMatcherFacadeImpl implements TesterMatcherFacade {

    private final DeviceService deviceService;
    private final BugReportService bugReportService;

    @Transactional
    public List<TesterBugCountDto> findMatchingTesters(Set<String> countryCodes, Set<String> deviceDescriptions) {

        log.info("Finding testers for country codes: {} and devices: {}", countryCodes, deviceDescriptions);

        var descriptionToDevice = deviceService.findDevicesByDescription(deviceDescriptions)
                .stream()
                .collect(Collectors.toMap(Device::getDescription, device -> device));

        validateDeviceDescriptions(deviceDescriptions, descriptionToDevice);

        return bugReportService.findMatchingTesters(countryCodes, new HashSet<>(descriptionToDevice.values()));
    }

    private static void validateDeviceDescriptions(Set<String> deviceDescriptions, Map<String, Device> descriptionToDevice) {
        deviceDescriptions.forEach(deviceDescription ->
                Optional.ofNullable(descriptionToDevice.get(deviceDescription))
                        .orElseThrow(() -> new NotFoundException(deviceDescription)));
    }
}
