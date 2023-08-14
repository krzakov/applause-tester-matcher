package pl.mosek.applausematcher.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.mosek.applausematcher.domain.Device;
import pl.mosek.applausematcher.dto.TesterBugCountDto;
import pl.mosek.applausematcher.exception.NotFoundException;
import pl.mosek.applausematcher.repository.DeviceRepository;
import pl.mosek.applausematcher.repository.TesterRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class TesterMatcherServiceImpl implements TesterMatcherService {

    private final DeviceRepository deviceRepository;
    private final TesterRepository testerRepository;

    public List<TesterBugCountDto> findMatchingTesters(Set<String> countryCodes, Set<String> deviceDescriptions) {

        log.info("Finding testers for country codes: {} and devices: {}", countryCodes, deviceDescriptions);

        var descriptionToDevice = deviceRepository.findAllByDescriptionIn(deviceDescriptions)
                .stream()
                .collect(Collectors.toMap(Device::getDescription, device -> device));

        deviceDescriptions.forEach(deviceDescription ->
                Optional.ofNullable(descriptionToDevice.get(deviceDescription))
                        .orElseThrow(() -> new NotFoundException(deviceDescription)));

        return testerRepository.findTestersByCountryAndDevicesOrderedByBugsDesc(countryCodes, new HashSet<>(descriptionToDevice.values()));
    }
}
