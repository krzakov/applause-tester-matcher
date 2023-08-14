package pl.mosek.applausematcher.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Table(name = "testers")
@Getter
public class Tester {

    @Id
    private long id;

    private String firstName;
    private String lastName;
    private String country;
    private LocalDateTime lastLogin;

    @ManyToMany(fetch = LAZY)
    @JoinTable(name = "tester_devices",
            joinColumns = @JoinColumn(name = "tester_id"),
            inverseJoinColumns = @JoinColumn(name = "device_id"))
    private List<Device> devices;

    @OneToMany(mappedBy = "tester", fetch = LAZY)
    private List<BugReport> bugReports;
}
