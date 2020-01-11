package com.rudy.biliotheque.mouvrage.model;

import com.rudy.biliotheque.mouvrage.model.common.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "_role")
public class Role extends AbstractEntity {

    @Column(length = 30, nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String description;


    @ManyToMany(mappedBy = "roles")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<User> users = new HashSet<>();

    public void addUser(User user) {
        if (!users.contains(user)) {
            users.add(user);
            user.addRole(this);
        }
    }
}
