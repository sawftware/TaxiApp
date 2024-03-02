package com.taxi.app.entity.security;

import lombok.Setter;
import lombok.Getter;
import java.util.Set;
import lombok.Builder;
import java.util.HashSet;
import lombok.AccessLevel;
import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * Role Model/Entity
 *
 * @author alankavanagh
 *
 * Defines the Role entity used for Authorization to webpages/components
 */
@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
@Table(name = "role")
public class Role implements Serializable {

    @Id
    @Column(name="role_id")
    @GeneratedValue(generator = "role-sg")
    @GenericGenerator(
            name = "role-sg",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "role_sequence"),
                    @Parameter(name = "initial_value", value = "3"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    private long roleId;

    @Column(name="name")
    private String name;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER)
    private Set<User> users = new HashSet<>();

    @Override
    public String toString() { return name; }

    @Override
    public int hashCode() {
        return (int) roleId * name.hashCode();
    }

}