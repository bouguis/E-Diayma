package sn.sntech.ediayma.service.impl;

import sn.sntech.ediayma.service.PanierService;
import sn.sntech.ediayma.domain.Panier;
import sn.sntech.ediayma.repository.PanierRepository;
import sn.sntech.ediayma.service.dto.PanierDTO;
import sn.sntech.ediayma.service.mapper.PanierMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Panier}.
 */
@Service
@Transactional
public class PanierServiceImpl implements PanierService {

    private final Logger log = LoggerFactory.getLogger(PanierServiceImpl.class);

    private final PanierRepository panierRepository;

    private final PanierMapper panierMapper;

    public PanierServiceImpl(PanierRepository panierRepository, PanierMapper panierMapper) {
        this.panierRepository = panierRepository;
        this.panierMapper = panierMapper;
    }

    @Override
    public PanierDTO save(PanierDTO panierDTO) {
        log.debug("Request to save Panier : {}", panierDTO);
        Panier panier = panierMapper.toEntity(panierDTO);
        panier = panierRepository.save(panier);
        return panierMapper.toDto(panier);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PanierDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Paniers");
        return panierRepository.findAll(pageable)
            .map(panierMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<PanierDTO> findOne(Long id) {
        log.debug("Request to get Panier : {}", id);
        return panierRepository.findById(id)
            .map(panierMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Panier : {}", id);
        panierRepository.deleteById(id);
    }
}
