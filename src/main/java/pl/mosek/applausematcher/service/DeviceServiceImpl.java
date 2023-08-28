package pl.mosek.applausematcher.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.mosek.applausematcher.domain.Device;
import pl.mosek.applausematcher.repository.DeviceRepository;

import java.util.List;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class DeviceServiceImpl implements DeviceService {

    private final DeviceRepository deviceRepository;
    public List<String> findDeviceDescriptions() {
        log.info("fetching device descriptions");
        return deviceRepository.findAllDescriptionsSortedAlphabetically();
    }

    public Set<Device> findDevicesByDescription(Set<String> deviceDescriptions) {
        log.info("fetching devices matching descriptions: {}", deviceDescriptions);
        return deviceRepository.findAllByDescriptionIn(deviceDescriptions);
    }
}
