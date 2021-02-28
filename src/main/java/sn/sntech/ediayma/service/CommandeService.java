package sn.sntech.ediayma.service;

import sn.sntech.ediayma.service.dto.CommandeDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link sn.sntech.ediayma.domain.Commande}.
 */
public interface CommandeService {

    /**
     * Save a commande.
     *
     * @param commandeDTO the entity to save.
     * @return the persisted entity.
     */
    CommandeDTO save(CommandeDTO commandeDTO);

    /**
     * Get all the commandes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<CommandeDTO> findAll(Pageable pageable);

    /**
     * Get all the commandes with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    Page<CommandeDTO> findAllWithEagerRelationships(Pageable pageable);


    /**
     * Get the "id" commande.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CommandeDTO> findOne(Long id);

    /**
     * Delete the "id" commande.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
