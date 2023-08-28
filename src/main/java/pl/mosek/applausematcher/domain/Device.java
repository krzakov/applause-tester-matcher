package pl.mosek.applausematcher.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "devices")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Device {

    @Id
    private long id;

    private String description;
}
