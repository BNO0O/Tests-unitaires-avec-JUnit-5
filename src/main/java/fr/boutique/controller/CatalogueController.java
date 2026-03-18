package fr.boutique.controller;

import fr.boutique.model.Article;
import fr.boutique.service.CatalogueService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Endpoints REST de la Boutique.
 *
 * GET  /api/articles              → liste tous les articles
 * GET  /api/articles/{reference}  → détail d'un article
 * GET  /api/health                → statut simplifié (pour tests manuels)
 */
@RestController
@RequestMapping("/api")
public class CatalogueController {

    private final CatalogueService catalogueService;

    public CatalogueController(CatalogueService catalogueService) {
        this.catalogueService = catalogueService;
    }

    // ── GET /api/articles ─────────────────────────────────────
    @GetMapping("/articles")
    public List<Article> listerArticles() {
        return catalogueService.findAll();
    }

    // ── GET /api/articles/{reference} ─────────────────────────
    @GetMapping("/articles/{reference}")
    public ResponseEntity<Article> getArticle(@PathVariable String reference) {
        Article article = catalogueService.findByReference(reference);
        if (article == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(article);
    }

    // ── GET /api/health ────────────────────────────────────────
    // Endpoint simplifié pour les tests manuels et Selenium.
    // Prometheus utilise /actuator/prometheus (configuré automatiquement).
    @GetMapping("/health")
    public Map<String, String> health() {
        return Map.of(
            "status",  "UP",
            "service", "boutique",
            "version", "1.0.0"
        );
    }
}
