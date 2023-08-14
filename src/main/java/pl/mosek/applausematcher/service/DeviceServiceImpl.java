package pl.mosek.applausematcher.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.mosek.applausematcher.repository.DeviceRepository;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class DeviceServiceImpl implements DeviceService {

    private final DeviceRepository deviceRepository;
    public List<String> findDeviceDescriptions() {
        log.info("fetching device descriptions");
        return deviceRepository.findAllDescriptionsSortedAlphabetically();
    }
}
