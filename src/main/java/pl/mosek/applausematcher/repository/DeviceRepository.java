package pl.mosek.applausematcher.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.mosek.applausematcher.domain.Device;

import java.util.List;
import java.util.Set;

public interface DeviceRepository extends JpaRepository<Device, Long> {
    Set<Device> findAllByDescriptionIn(Set<String> deviceDescription);

    @Query("SELECT DISTINCT d.description FROM Device d ORDER BY d.description ASC")
    List<String> findAllDescriptionsSortedAlphabetically();
}
