package com.rudy.biliotheque.mouvrage.model;

import com.rudy.biliotheque.mouvrage.model.common.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Borrow extends AbstractEntity {
}
