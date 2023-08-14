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
public class TesterCriteriaRepositoryImpl implements TesterRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public List<TesterBugCountDto> findTestersByCountryAndDevicesOrderedByBugsDesc(Set<String> countryCodes, Set<Device> devices) {

        var criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<TesterBugCountDto> cq = criteriaBuilder.createQuery(TesterBugCountDto.class);
        Root<Tester> rootTester = cq.from(Tester.class);

        Join<Tester, Device> joinDevice = rootTester.join("devices", JoinType.INNER);
        Join<Tester, BugReport> joinBugReport = rootTester.join("bugReports", JoinType.INNER);

        Predicate whereClause = criteriaBuilder.conjunction();

        if (!countryCodes.isEmpty()) {
            Predicate countryPredicate = rootTester.get("country").in(countryCodes);
            whereClause = criteriaBuilder.and(whereClause, countryPredicate);
        }

        if (!devices.isEmpty()) {
            Predicate devicePredicate = joinDevice.in(devices);
            whereClause = criteriaBuilder.and(whereClause, devicePredicate);
        }

        cq.select(criteriaBuilder.construct(
                        TesterBugCountDto.class,
                        rootTester.get("id"),
                        rootTester.get("country"),
                        rootTester.get("firstName"),
                        rootTester.get("lastName"),
                        rootTester.get("lastLogin"),
                        criteriaBuilder.count(joinBugReport)
                )
        );
        cq.groupBy(rootTester.get("id"),
                rootTester.get("country"),
                rootTester.get("firstName"),
                rootTester.get("lastName"),
                rootTester.get("lastLogin")
        );

        cq.orderBy(criteriaBuilder.desc(criteriaBuilder.count(joinBugReport)));
        cq.where(whereClause);

        TypedQuery<TesterBugCountDto> query = entityManager.createQuery(cq);
        return query.getResultList();
    }
}
