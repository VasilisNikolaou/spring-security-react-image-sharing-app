package com.example.imagesharing.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class UserProfile {

    @Id
    private Long id;

    private String userProfileImageLink;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private User user;

    public UserProfile(String userProfileImageLink) {
        this.userProfileImageLink = userProfileImageLink;
    }

}
