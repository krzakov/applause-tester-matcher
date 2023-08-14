package pl.mosek.applausematcher.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Table(name = "devices")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Device {

    @Id
    private long id;

    private String description;

    @ManyToMany(mappedBy = "devices", fetch = LAZY)
    private List<Tester> testers;

    @OneToMany(mappedBy = "device", fetch = LAZY)
    private List<BugReport> bugReports;
}
