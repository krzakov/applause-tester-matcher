package pl.mosek.applausematcher.dto;


import java.time.LocalDateTime;

public record TesterBugCountDto(long id,
                                String firstName,
                                String lastName,
                                String country,
                                LocalDateTime lastLogin,
                                long bugCount) {
}
