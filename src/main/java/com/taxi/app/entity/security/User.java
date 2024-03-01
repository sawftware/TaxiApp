package com.taxi.app.entity.security;

import java.util.Set;
import lombok.Setter;
import lombok.Getter;
import lombok.Builder;
import java.util.HashSet;
import lombok.AccessLevel;
import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.NoArgsConstructor;
import com.taxi.app.entity.Taxi;
import javax.persistence.Column;
import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import javax.persistence.OneToOne;
import javax.persistence.FetchType;
import javax.persistence.Transient;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;
import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
@Table(name = "user")
public class User implements Serializable {

    @Id
    @Column(name="user_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userId;

    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;

    @Transient
    @Column(name="password_confirm")
    private String passwordConfirm;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "taxi_id", referencedColumnName = "taxi_id")
    private Taxi taxi;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @Override
    public String toString() { return username; }

    @Override
    public int hashCode() {
        return (int) userId * username.hashCode() * password.hashCode();
    }

}
