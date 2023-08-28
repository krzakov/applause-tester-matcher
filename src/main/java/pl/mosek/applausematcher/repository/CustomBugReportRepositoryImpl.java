package pl.mosek.applausematcher.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mosek.applausematcher.domain.BugReport;
import pl.mosek.applausematcher.domain.Device;
import pl.mosek.applausematcher.domain.Tester;
import pl.mosek.applausematcher.dto.TesterBugCountDto;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class CustomBugReportRepositoryImpl implements BugReportRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public List<TesterBugCountDto> findMatchedTestersByCountryAndDevicesOrderedByBugsDesc(Set<String> countryCodes, Set<Device> devices) {
        var criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<TesterBugCountDto> cq = criteriaBuilder.createQuery(TesterBugCountDto.class);
        Root<BugReport> rootBugReport = cq.from(BugReport.class);

        Join<BugReport, Device> joinDevice = rootBugReport.join("device", JoinType.INNER);
        Join<BugReport, Tester> joinTester = rootBugReport.join("tester", JoinType.INNER);

        Predicate whereClause = criteriaBuilder.conjunction();

        if (!countryCodes.isEmpty()) {
            Predicate countryPredicate = joinTester.get("country").in(countryCodes);
            whereClause = criteriaBuilder.and(whereClause, countryPredicate);
        }

        if (!devices.isEmpty()) {
            Predicate devicePredicate = joinDevice.in(devices);
            whereClause = criteriaBuilder.and(whereClause, devicePredicate);
        }

        cq.select(criteriaBuilder.construct(
                        TesterBugCountDto.class,
                        joinTester.get("id"),
                        joinTester.get("firstName"),
                        joinTester.get("lastName"),
                        joinTester.get("country"),
                        joinTester.get("lastLogin"),
                        criteriaBuilder.count(joinTester)
                )
        );
        cq.groupBy(joinTester);

        cq.orderBy(criteriaBuilder.desc(criteriaBuilder.count(rootBugReport)));
        cq.where(whereClause);

        TypedQuery<TesterBugCountDto> query = entityManager.createQuery(cq);
        return query.getResultList();
    }
}
