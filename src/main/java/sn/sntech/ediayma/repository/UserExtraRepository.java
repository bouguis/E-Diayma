package sn.sntech.ediayma.repository;

import sn.sntech.ediayma.domain.UserExtra;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the UserExtra entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UserExtraRepository extends JpaRepository<UserExtra, Long> {
}
