package com.rudy.bibliotheque.mbook.repository;

import com.rudy.bibliotheque.mbook.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, String> {

}
