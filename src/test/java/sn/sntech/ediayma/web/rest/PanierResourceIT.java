package sn.sntech.ediayma.web.rest;

import sn.sntech.ediayma.EDiaymaApp;
import sn.sntech.ediayma.domain.Panier;
import sn.sntech.ediayma.repository.PanierRepository;
import sn.sntech.ediayma.service.PanierService;
import sn.sntech.ediayma.service.dto.PanierDTO;
import sn.sntech.ediayma.service.mapper.PanierMapper;

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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link PanierResource} REST controller.
 */
@SpringBootTest(classes = EDiaymaApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class PanierResourceIT {

    private static final String DEFAULT_CODE_PANIER = "AAAAAAAAAA";
    private static final String UPDATED_CODE_PANIER = "BBBBBBBBBB";

    @Autowired
    private PanierRepository panierRepository;

    @Autowired
    private PanierMapper panierMapper;

    @Autowired
    private PanierService panierService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPanierMockMvc;

    private Panier panier;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Panier createEntity(EntityManager em) {
        Panier panier = new Panier()
            .codePanier(DEFAULT_CODE_PANIER);
        return panier;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Panier createUpdatedEntity(EntityManager em) {
        Panier panier = new Panier()
            .codePanier(UPDATED_CODE_PANIER);
        return panier;
    }

    @BeforeEach
    public void initTest() {
        panier = createEntity(em);
    }

    @Test
    @Transactional
    public void createPanier() throws Exception {
        int databaseSizeBeforeCreate = panierRepository.findAll().size();
        // Create the Panier
        PanierDTO panierDTO = panierMapper.toDto(panier);
        restPanierMockMvc.perform(post("/api/paniers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(panierDTO)))
            .andExpect(status().isCreated());

        // Validate the Panier in the database
        List<Panier> panierList = panierRepository.findAll();
        assertThat(panierList).hasSize(databaseSizeBeforeCreate + 1);
        Panier testPanier = panierList.get(panierList.size() - 1);
        assertThat(testPanier.getCodePanier()).isEqualTo(DEFAULT_CODE_PANIER);
    }

    @Test
    @Transactional
    public void createPanierWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = panierRepository.findAll().size();

        // Create the Panier with an existing ID
        panier.setId(1L);
        PanierDTO panierDTO = panierMapper.toDto(panier);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPanierMockMvc.perform(post("/api/paniers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(panierDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Panier in the database
        List<Panier> panierList = panierRepository.findAll();
        assertThat(panierList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllPaniers() throws Exception {
        // Initialize the database
        panierRepository.saveAndFlush(panier);

        // Get all the panierList
        restPanierMockMvc.perform(get("/api/paniers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(panier.getId().intValue())))
            .andExpect(jsonPath("$.[*].codePanier").value(hasItem(DEFAULT_CODE_PANIER)));
    }
    
    @Test
    @Transactional
    public void getPanier() throws Exception {
        // Initialize the database
        panierRepository.saveAndFlush(panier);

        // Get the panier
        restPanierMockMvc.perform(get("/api/paniers/{id}", panier.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(panier.getId().intValue()))
            .andExpect(jsonPath("$.codePanier").value(DEFAULT_CODE_PANIER));
    }
    @Test
    @Transactional
    public void getNonExistingPanier() throws Exception {
        // Get the panier
        restPanierMockMvc.perform(get("/api/paniers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePanier() throws Exception {
        // Initialize the database
        panierRepository.saveAndFlush(panier);

        int databaseSizeBeforeUpdate = panierRepository.findAll().size();

        // Update the panier
        Panier updatedPanier = panierRepository.findById(panier.getId()).get();
        // Disconnect from session so that the updates on updatedPanier are not directly saved in db
        em.detach(updatedPanier);
        updatedPanier
            .codePanier(UPDATED_CODE_PANIER);
        PanierDTO panierDTO = panierMapper.toDto(updatedPanier);

        restPanierMockMvc.perform(put("/api/paniers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(panierDTO)))
            .andExpect(status().isOk());

        // Validate the Panier in the database
        List<Panier> panierList = panierRepository.findAll();
        assertThat(panierList).hasSize(databaseSizeBeforeUpdate);
        Panier testPanier = panierList.get(panierList.size() - 1);
        assertThat(testPanier.getCodePanier()).isEqualTo(UPDATED_CODE_PANIER);
    }

    @Test
    @Transactional
    public void updateNonExistingPanier() throws Exception {
        int databaseSizeBeforeUpdate = panierRepository.findAll().size();

        // Create the Panier
        PanierDTO panierDTO = panierMapper.toDto(panier);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPanierMockMvc.perform(put("/api/paniers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(panierDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Panier in the database
        List<Panier> panierList = panierRepository.findAll();
        assertThat(panierList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePanier() throws Exception {
        // Initialize the database
        panierRepository.saveAndFlush(panier);

        int databaseSizeBeforeDelete = panierRepository.findAll().size();

        // Delete the panier
        restPanierMockMvc.perform(delete("/api/paniers/{id}", panier.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Panier> panierList = panierRepository.findAll();
        assertThat(panierList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
