package sn.sntech.ediayma.service.impl;

import sn.sntech.ediayma.service.CommandeService;
import sn.sntech.ediayma.domain.Commande;
import sn.sntech.ediayma.repository.CommandeRepository;
import sn.sntech.ediayma.service.dto.CommandeDTO;
import sn.sntech.ediayma.service.mapper.CommandeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Commande}.
 */
@Service
@Transactional
public class CommandeServiceImpl implements CommandeService {

    private final Logger log = LoggerFactory.getLogger(CommandeServiceImpl.class);

    private final CommandeRepository commandeRepository;

    private final CommandeMapper commandeMapper;

    public CommandeServiceImpl(CommandeRepository commandeRepository, CommandeMapper commandeMapper) {
        this.commandeRepository = commandeRepository;
        this.commandeMapper = commandeMapper;
    }

    @Override
    public CommandeDTO save(CommandeDTO commandeDTO) {
        log.debug("Request to save Commande : {}", commandeDTO);
        Commande commande = commandeMapper.toEntity(commandeDTO);
        commande = commandeRepository.save(commande);
        return commandeMapper.toDto(commande);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CommandeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Commandes");
        return commandeRepository.findAll(pageable)
            .map(commandeMapper::toDto);
    }


    public Page<CommandeDTO> findAllWithEagerRelationships(Pageable pageable) {
        return commandeRepository.findAllWithEagerRelationships(pageable).map(commandeMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CommandeDTO> findOne(Long id) {
        log.debug("Request to get Commande : {}", id);
        return commandeRepository.findOneWithEagerRelationships(id)
            .map(commandeMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Commande : {}", id);
        commandeRepository.deleteById(id);
    }
}
