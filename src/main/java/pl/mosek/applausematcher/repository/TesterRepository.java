package pl.mosek.applausematcher.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.mosek.applausematcher.domain.Tester;

import java.util.List;

@Repository
public interface TesterRepository extends JpaRepository<Tester, Long> {

        @Query("SELECT DISTINCT t.country FROM Tester t ORDER BY t.country")
        List<String> findAllUniqueCountriesOrdered();
}
