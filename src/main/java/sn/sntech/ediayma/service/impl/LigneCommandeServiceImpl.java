package sn.sntech.ediayma.service.impl;

import sn.sntech.ediayma.service.LigneCommandeService;
import sn.sntech.ediayma.domain.LigneCommande;
import sn.sntech.ediayma.repository.LigneCommandeRepository;
import sn.sntech.ediayma.service.dto.LigneCommandeDTO;
import sn.sntech.ediayma.service.mapper.LigneCommandeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link LigneCommande}.
 */
@Service
@Transactional
public class LigneCommandeServiceImpl implements LigneCommandeService {

    private final Logger log = LoggerFactory.getLogger(LigneCommandeServiceImpl.class);

    private final LigneCommandeRepository ligneCommandeRepository;

    private final LigneCommandeMapper ligneCommandeMapper;

    public LigneCommandeServiceImpl(LigneCommandeRepository ligneCommandeRepository, LigneCommandeMapper ligneCommandeMapper) {
        this.ligneCommandeRepository = ligneCommandeRepository;
        this.ligneCommandeMapper = ligneCommandeMapper;
    }

    @Override
    public LigneCommandeDTO save(LigneCommandeDTO ligneCommandeDTO) {
        log.debug("Request to save LigneCommande : {}", ligneCommandeDTO);
        LigneCommande ligneCommande = ligneCommandeMapper.toEntity(ligneCommandeDTO);
        ligneCommande = ligneCommandeRepository.save(ligneCommande);
        return ligneCommandeMapper.toDto(ligneCommande);
    }

    @Override
    @Transactional(readOnly = true)
    public List<LigneCommandeDTO> findAll() {
        log.debug("Request to get all LigneCommandes");
        return ligneCommandeRepository.findAll().stream()
            .map(ligneCommandeMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<LigneCommandeDTO> findOne(Long id) {
        log.debug("Request to get LigneCommande : {}", id);
        return ligneCommandeRepository.findById(id)
            .map(ligneCommandeMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete LigneCommande : {}", id);
        ligneCommandeRepository.deleteById(id);
    }
}
