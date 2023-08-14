package pl.mosek.applausematcher.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "bug_reports")
@Getter
public class BugReport {

    @Id
    private long id;

    @ManyToOne
    @JoinColumn(name = "device_id")
    private Device device;

    @ManyToOne
    @JoinColumn(name = "tester_id")
    private Tester tester;
}
