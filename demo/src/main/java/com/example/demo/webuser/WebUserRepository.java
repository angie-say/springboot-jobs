package com.example.demo.webuser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Repository
@Transactional(readOnly = true)
public interface WebUserRepository extends JpaRepository<WebUser, Long> {

    Optional<WebUser> findByEmail(String email);

    @Transactional
    @Query("SELECT u FROM WebUser u  JOIN FETCH u.favList WHERE u.username = ?1")
    Optional<WebUser> findByUsername(String username);

    @Transactional
    @Modifying
    @Query("UPDATE WebUser a " +
            "SET a.enabled = TRUE WHERE a.email = ?1")
    int enableWebUser(String email);

}
