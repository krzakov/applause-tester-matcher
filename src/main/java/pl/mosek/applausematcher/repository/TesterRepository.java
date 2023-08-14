package pl.mosek.applausematcher.repository;

import pl.mosek.applausematcher.domain.Device;
import pl.mosek.applausematcher.dto.TesterBugCountDto;

import java.util.List;
import java.util.Set;

public interface TesterRepository {
        List<TesterBugCountDto> findTestersByCountryAndDevicesOrderedByBugsDesc(
                Set<String> countryCodes, Set<Device> devices);
}
