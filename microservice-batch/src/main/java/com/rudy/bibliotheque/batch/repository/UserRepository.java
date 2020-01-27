package com.rudy.bibliotheque.batch.repository;

import com.rudy.bibliotheque.batch.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
