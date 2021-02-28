package sn.sntech.ediayma.web.rest;

import sn.sntech.ediayma.EDiaymaApp;
import sn.sntech.ediayma.domain.Article;
import sn.sntech.ediayma.repository.ArticleRepository;
import sn.sntech.ediayma.service.ArticleService;
import sn.sntech.ediayma.service.dto.ArticleDTO;
import sn.sntech.ediayma.service.mapper.ArticleMapper;

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
 * Integration tests for the {@link ArticleResource} REST controller.
 */
@SpringBootTest(classes = EDiaymaApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ArticleResourceIT {

    private static final String DEFAULT_REF_ARTICLE = "AAAAAAAAAA";
    private static final String UPDATED_REF_ARTICLE = "BBBBBBBBBB";

    private static final String DEFAULT_DESIGNATION = "AAAAAAAAAA";
    private static final String UPDATED_DESIGNATION = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_PRIX = new BigDecimal(1);
    private static final BigDecimal UPDATED_PRIX = new BigDecimal(2);

    private static final Long DEFAULT_QUANTITE_STOCKE = 1L;
    private static final Long UPDATED_QUANTITE_STOCKE = 2L;

    private static final String DEFAULT_IMAGE = "AAAAAAAAAA";
    private static final String UPDATED_IMAGE = "BBBBBBBBBB";

    private static final String DEFAULT_ORIGINE = "AAAAAAAAAA";
    private static final String UPDATED_ORIGINE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_DISPONIBLITE = false;
    private static final Boolean UPDATED_DISPONIBLITE = true;

    private static final Boolean DEFAULT_SELECTIONNE = false;
    private static final Boolean UPDATED_SELECTIONNE = true;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restArticleMockMvc;

    private Article article;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Article createEntity(EntityManager em) {
        Article article = new Article()
            .refArticle(DEFAULT_REF_ARTICLE)
            .designation(DEFAULT_DESIGNATION)
            .description(DEFAULT_DESCRIPTION)
            .prix(DEFAULT_PRIX)
            .quantiteStocke(DEFAULT_QUANTITE_STOCKE)
            .image(DEFAULT_IMAGE)
            .origine(DEFAULT_ORIGINE)
            .disponiblite(DEFAULT_DISPONIBLITE)
            .selectionne(DEFAULT_SELECTIONNE);
        return article;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Article createUpdatedEntity(EntityManager em) {
        Article article = new Article()
            .refArticle(UPDATED_REF_ARTICLE)
            .designation(UPDATED_DESIGNATION)
            .description(UPDATED_DESCRIPTION)
            .prix(UPDATED_PRIX)
            .quantiteStocke(UPDATED_QUANTITE_STOCKE)
            .image(UPDATED_IMAGE)
            .origine(UPDATED_ORIGINE)
            .disponiblite(UPDATED_DISPONIBLITE)
            .selectionne(UPDATED_SELECTIONNE);
        return article;
    }

    @BeforeEach
    public void initTest() {
        article = createEntity(em);
    }

    @Test
    @Transactional
    public void createArticle() throws Exception {
        int databaseSizeBeforeCreate = articleRepository.findAll().size();
        // Create the Article
        ArticleDTO articleDTO = articleMapper.toDto(article);
        restArticleMockMvc.perform(post("/api/articles")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(articleDTO)))
            .andExpect(status().isCreated());

        // Validate the Article in the database
        List<Article> articleList = articleRepository.findAll();
        assertThat(articleList).hasSize(databaseSizeBeforeCreate + 1);
        Article testArticle = articleList.get(articleList.size() - 1);
        assertThat(testArticle.getRefArticle()).isEqualTo(DEFAULT_REF_ARTICLE);
        assertThat(testArticle.getDesignation()).isEqualTo(DEFAULT_DESIGNATION);
        assertThat(testArticle.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testArticle.getPrix()).isEqualTo(DEFAULT_PRIX);
        assertThat(testArticle.getQuantiteStocke()).isEqualTo(DEFAULT_QUANTITE_STOCKE);
        assertThat(testArticle.getImage()).isEqualTo(DEFAULT_IMAGE);
        assertThat(testArticle.getOrigine()).isEqualTo(DEFAULT_ORIGINE);
        assertThat(testArticle.isDisponiblite()).isEqualTo(DEFAULT_DISPONIBLITE);
        assertThat(testArticle.isSelectionne()).isEqualTo(DEFAULT_SELECTIONNE);
    }

    @Test
    @Transactional
    public void createArticleWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = articleRepository.findAll().size();

        // Create the Article with an existing ID
        article.setId(1L);
        ArticleDTO articleDTO = articleMapper.toDto(article);

        // An entity with an existing ID cannot be created, so this API call must fail
        restArticleMockMvc.perform(post("/api/articles")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(articleDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Article in the database
        List<Article> articleList = articleRepository.findAll();
        assertThat(articleList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllArticles() throws Exception {
        // Initialize the database
        articleRepository.saveAndFlush(article);

        // Get all the articleList
        restArticleMockMvc.perform(get("/api/articles?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(article.getId().intValue())))
            .andExpect(jsonPath("$.[*].refArticle").value(hasItem(DEFAULT_REF_ARTICLE)))
            .andExpect(jsonPath("$.[*].designation").value(hasItem(DEFAULT_DESIGNATION)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].prix").value(hasItem(DEFAULT_PRIX.intValue())))
            .andExpect(jsonPath("$.[*].quantiteStocke").value(hasItem(DEFAULT_QUANTITE_STOCKE.intValue())))
            .andExpect(jsonPath("$.[*].image").value(hasItem(DEFAULT_IMAGE)))
            .andExpect(jsonPath("$.[*].origine").value(hasItem(DEFAULT_ORIGINE)))
            .andExpect(jsonPath("$.[*].disponiblite").value(hasItem(DEFAULT_DISPONIBLITE.booleanValue())))
            .andExpect(jsonPath("$.[*].selectionne").value(hasItem(DEFAULT_SELECTIONNE.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getArticle() throws Exception {
        // Initialize the database
        articleRepository.saveAndFlush(article);

        // Get the article
        restArticleMockMvc.perform(get("/api/articles/{id}", article.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(article.getId().intValue()))
            .andExpect(jsonPath("$.refArticle").value(DEFAULT_REF_ARTICLE))
            .andExpect(jsonPath("$.designation").value(DEFAULT_DESIGNATION))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.prix").value(DEFAULT_PRIX.intValue()))
            .andExpect(jsonPath("$.quantiteStocke").value(DEFAULT_QUANTITE_STOCKE.intValue()))
            .andExpect(jsonPath("$.image").value(DEFAULT_IMAGE))
            .andExpect(jsonPath("$.origine").value(DEFAULT_ORIGINE))
            .andExpect(jsonPath("$.disponiblite").value(DEFAULT_DISPONIBLITE.booleanValue()))
            .andExpect(jsonPath("$.selectionne").value(DEFAULT_SELECTIONNE.booleanValue()));
    }
    @Test
    @Transactional
    public void getNonExistingArticle() throws Exception {
        // Get the article
        restArticleMockMvc.perform(get("/api/articles/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateArticle() throws Exception {
        // Initialize the database
        articleRepository.saveAndFlush(article);

        int databaseSizeBeforeUpdate = articleRepository.findAll().size();

        // Update the article
        Article updatedArticle = articleRepository.findById(article.getId()).get();
        // Disconnect from session so that the updates on updatedArticle are not directly saved in db
        em.detach(updatedArticle);
        updatedArticle
            .refArticle(UPDATED_REF_ARTICLE)
            .designation(UPDATED_DESIGNATION)
            .description(UPDATED_DESCRIPTION)
            .prix(UPDATED_PRIX)
            .quantiteStocke(UPDATED_QUANTITE_STOCKE)
            .image(UPDATED_IMAGE)
            .origine(UPDATED_ORIGINE)
            .disponiblite(UPDATED_DISPONIBLITE)
            .selectionne(UPDATED_SELECTIONNE);
        ArticleDTO articleDTO = articleMapper.toDto(updatedArticle);

        restArticleMockMvc.perform(put("/api/articles")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(articleDTO)))
            .andExpect(status().isOk());

        // Validate the Article in the database
        List<Article> articleList = articleRepository.findAll();
        assertThat(articleList).hasSize(databaseSizeBeforeUpdate);
        Article testArticle = articleList.get(articleList.size() - 1);
        assertThat(testArticle.getRefArticle()).isEqualTo(UPDATED_REF_ARTICLE);
        assertThat(testArticle.getDesignation()).isEqualTo(UPDATED_DESIGNATION);
        assertThat(testArticle.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testArticle.getPrix()).isEqualTo(UPDATED_PRIX);
        assertThat(testArticle.getQuantiteStocke()).isEqualTo(UPDATED_QUANTITE_STOCKE);
        assertThat(testArticle.getImage()).isEqualTo(UPDATED_IMAGE);
        assertThat(testArticle.getOrigine()).isEqualTo(UPDATED_ORIGINE);
        assertThat(testArticle.isDisponiblite()).isEqualTo(UPDATED_DISPONIBLITE);
        assertThat(testArticle.isSelectionne()).isEqualTo(UPDATED_SELECTIONNE);
    }

    @Test
    @Transactional
    public void updateNonExistingArticle() throws Exception {
        int databaseSizeBeforeUpdate = articleRepository.findAll().size();

        // Create the Article
        ArticleDTO articleDTO = articleMapper.toDto(article);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restArticleMockMvc.perform(put("/api/articles")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(articleDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Article in the database
        List<Article> articleList = articleRepository.findAll();
        assertThat(articleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteArticle() throws Exception {
        // Initialize the database
        articleRepository.saveAndFlush(article);

        int databaseSizeBeforeDelete = articleRepository.findAll().size();

        // Delete the article
        restArticleMockMvc.perform(delete("/api/articles/{id}", article.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Article> articleList = articleRepository.findAll();
        assertThat(articleList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
