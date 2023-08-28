package pl.mosek.applausematcher.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

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
}
