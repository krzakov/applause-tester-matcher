package pl.mosek.applausematcher.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mosek.applausematcher.domain.Device;

import java.util.Set;

public interface DeviceRepository extends JpaRepository<Device, Long> {
    Set<Device> findAllByDescriptionIn(Set<String> deviceDescription);
}
