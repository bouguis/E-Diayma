package sn.sntech.ediayma.web.rest;

import sn.sntech.ediayma.EDiaymaApp;
import sn.sntech.ediayma.domain.LigneCommande;
import sn.sntech.ediayma.repository.LigneCommandeRepository;
import sn.sntech.ediayma.service.LigneCommandeService;
import sn.sntech.ediayma.service.dto.LigneCommandeDTO;
import sn.sntech.ediayma.service.mapper.LigneCommandeMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link LigneCommandeResource} REST controller.
 */
@SpringBootTest(classes = EDiaymaApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class LigneCommandeResourceIT {

    private static final Integer DEFAULT_QUANTITE_COMMANDE = 1;
    private static final Integer UPDATED_QUANTITE_COMMANDE = 2;

    private static final BigDecimal DEFAULT_PRIX_TOTAL = new BigDecimal(1);
    private static final BigDecimal UPDATED_PRIX_TOTAL = new BigDecimal(2);

    @Autowired
    private LigneCommandeRepository ligneCommandeRepository;

    @Autowired
    private LigneCommandeMapper ligneCommandeMapper;

    @Autowired
    private LigneCommandeService ligneCommandeService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restLigneCommandeMockMvc;

    private LigneCommande ligneCommande;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LigneCommande createEntity(EntityManager em) {
        LigneCommande ligneCommande = new LigneCommande()
            .quantiteCommande(DEFAULT_QUANTITE_COMMANDE)
            .prixTotal(DEFAULT_PRIX_TOTAL);
        return ligneCommande;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LigneCommande createUpdatedEntity(EntityManager em) {
        LigneCommande ligneCommande = new LigneCommande()
            .quantiteCommande(UPDATED_QUANTITE_COMMANDE)
            .prixTotal(UPDATED_PRIX_TOTAL);
        return ligneCommande;
    }

    @BeforeEach
    public void initTest() {
        ligneCommande = createEntity(em);
    }

    @Test
    @Transactional
    public void createLigneCommande() throws Exception {
        int databaseSizeBeforeCreate = ligneCommandeRepository.findAll().size();
        // Create the LigneCommande
        LigneCommandeDTO ligneCommandeDTO = ligneCommandeMapper.toDto(ligneCommande);
        restLigneCommandeMockMvc.perform(post("/api/ligne-commandes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ligneCommandeDTO)))
            .andExpect(status().isCreated());

        // Validate the LigneCommande in the database
        List<LigneCommande> ligneCommandeList = ligneCommandeRepository.findAll();
        assertThat(ligneCommandeList).hasSize(databaseSizeBeforeCreate + 1);
        LigneCommande testLigneCommande = ligneCommandeList.get(ligneCommandeList.size() - 1);
        assertThat(testLigneCommande.getQuantiteCommande()).isEqualTo(DEFAULT_QUANTITE_COMMANDE);
        assertThat(testLigneCommande.getPrixTotal()).isEqualTo(DEFAULT_PRIX_TOTAL);
    }

    @Test
    @Transactional
    public void createLigneCommandeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = ligneCommandeRepository.findAll().size();

        // Create the LigneCommande with an existing ID
        ligneCommande.setId(1L);
        LigneCommandeDTO ligneCommandeDTO = ligneCommandeMapper.toDto(ligneCommande);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLigneCommandeMockMvc.perform(post("/api/ligne-commandes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ligneCommandeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the LigneCommande in the database
        List<LigneCommande> ligneCommandeList = ligneCommandeRepository.findAll();
        assertThat(ligneCommandeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllLigneCommandes() throws Exception {
        // Initialize the database
        ligneCommandeRepository.saveAndFlush(ligneCommande);

        // Get all the ligneCommandeList
        restLigneCommandeMockMvc.perform(get("/api/ligne-commandes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ligneCommande.getId().intValue())))
            .andExpect(jsonPath("$.[*].quantiteCommande").value(hasItem(DEFAULT_QUANTITE_COMMANDE)))
            .andExpect(jsonPath("$.[*].prixTotal").value(hasItem(DEFAULT_PRIX_TOTAL.intValue())));
    }
    
    @Test
    @Transactional
    public void getLigneCommande() throws Exception {
        // Initialize the database
        ligneCommandeRepository.saveAndFlush(ligneCommande);

        // Get the ligneCommande
        restLigneCommandeMockMvc.perform(get("/api/ligne-commandes/{id}", ligneCommande.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(ligneCommande.getId().intValue()))
            .andExpect(jsonPath("$.quantiteCommande").value(DEFAULT_QUANTITE_COMMANDE))
            .andExpect(jsonPath("$.prixTotal").value(DEFAULT_PRIX_TOTAL.intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingLigneCommande() throws Exception {
        // Get the ligneCommande
        restLigneCommandeMockMvc.perform(get("/api/ligne-commandes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLigneCommande() throws Exception {
        // Initialize the database
        ligneCommandeRepository.saveAndFlush(ligneCommande);

        int databaseSizeBeforeUpdate = ligneCommandeRepository.findAll().size();

        // Update the ligneCommande
        LigneCommande updatedLigneCommande = ligneCommandeRepository.findById(ligneCommande.getId()).get();
        // Disconnect from session so that the updates on updatedLigneCommande are not directly saved in db
        em.detach(updatedLigneCommande);
        updatedLigneCommande
            .quantiteCommande(UPDATED_QUANTITE_COMMANDE)
            .prixTotal(UPDATED_PRIX_TOTAL);
        LigneCommandeDTO ligneCommandeDTO = ligneCommandeMapper.toDto(updatedLigneCommande);

        restLigneCommandeMockMvc.perform(put("/api/ligne-commandes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ligneCommandeDTO)))
            .andExpect(status().isOk());

        // Validate the LigneCommande in the database
        List<LigneCommande> ligneCommandeList = ligneCommandeRepository.findAll();
        assertThat(ligneCommandeList).hasSize(databaseSizeBeforeUpdate);
        LigneCommande testLigneCommande = ligneCommandeList.get(ligneCommandeList.size() - 1);
        assertThat(testLigneCommande.getQuantiteCommande()).isEqualTo(UPDATED_QUANTITE_COMMANDE);
        assertThat(testLigneCommande.getPrixTotal()).isEqualTo(UPDATED_PRIX_TOTAL);
    }

    @Test
    @Transactional
    public void updateNonExistingLigneCommande() throws Exception {
        int databaseSizeBeforeUpdate = ligneCommandeRepository.findAll().size();

        // Create the LigneCommande
        LigneCommandeDTO ligneCommandeDTO = ligneCommandeMapper.toDto(ligneCommande);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLigneCommandeMockMvc.perform(put("/api/ligne-commandes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ligneCommandeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the LigneCommande in the database
        List<LigneCommande> ligneCommandeList = ligneCommandeRepository.findAll();
        assertThat(ligneCommandeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteLigneCommande() throws Exception {
        // Initialize the database
        ligneCommandeRepository.saveAndFlush(ligneCommande);

        int databaseSizeBeforeDelete = ligneCommandeRepository.findAll().size();

        // Delete the ligneCommande
        restLigneCommandeMockMvc.perform(delete("/api/ligne-commandes/{id}", ligneCommande.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<LigneCommande> ligneCommandeList = ligneCommandeRepository.findAll();
        assertThat(ligneCommandeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
