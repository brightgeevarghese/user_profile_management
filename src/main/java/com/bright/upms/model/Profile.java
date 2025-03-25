package com.bright.upms.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "profiles")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long profileId;
    private String biodata;
    private String location;
    private LocalDate birthday;
    private String gender;
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "profile")
    private User user;

    public Profile(String biodata, String location, LocalDate birthday, String gender) {
        this.biodata = biodata;
        this.location = location;
        this.birthday = birthday;
        this.gender = gender;
    }
}
