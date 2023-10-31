package com.example.VodReco.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

//    @Id
//    @Column(name = "user_id")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long userId;
    @Id
    @Column(name = "email",unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "nickname")
    private String nickname;

//    @ElementCollection
//    @CollectionTable(name = "selected_vods" )
//    //, joinColumns = @JoinColumn(name = "email"))
//    @Column(name="selected_vods")
//    @Convert(converter = StringArrayConverter.class)
//    private List<String> selectedVods;

    @Column(name = "activated")
    private boolean activated;

    @ManyToMany
    @JoinTable( // 여기서 FK 지정되는듯
            //DB에 미리 저장해둔 user_authority에서 꺼내오는 것
            name = "user_authority",
            joinColumns = {@JoinColumn(name = "email", referencedColumnName = "email")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")})
    private Set<Authority> authorities;
}