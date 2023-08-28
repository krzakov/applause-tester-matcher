package pl.mosek.applausematcher.service;

import pl.mosek.applausematcher.domain.Device;

import java.util.List;
import java.util.Set;

public interface DeviceService {
    List<String> findDeviceDescriptions();
    Set<Device> findDevicesByDescription(Set<String> deviceDescriptions);
}
