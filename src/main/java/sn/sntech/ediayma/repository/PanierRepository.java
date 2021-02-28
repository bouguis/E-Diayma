package sn.sntech.ediayma.repository;

import sn.sntech.ediayma.domain.Panier;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Panier entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PanierRepository extends JpaRepository<Panier, Long> {
}
