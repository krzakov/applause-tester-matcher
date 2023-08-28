package pl.mosek.applausematcher.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.mosek.applausematcher.repository.TesterRepository;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TesterServiceImpl implements TesterService {

    private final TesterRepository testerRepository;

    public List<String> findCountryCodes() {
        log.info("fetching tester countries");
        return testerRepository.findAllUniqueCountriesOrdered();
    }
}
