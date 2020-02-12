package com.rudy.bibliotheque.mbook.model;

import com.rudy.bibliotheque.mbook.model.common.AbstractEntityComposedId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class UserInfo extends AbstractEntityComposedId {

    @Id
    private String id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String email;

    private String firstname;

    private String lastname;

    private String phone;

    private String adress;
}
