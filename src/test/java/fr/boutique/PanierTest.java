import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PanierTest {

    @Test
    void ajouterArticleDoitAugmenterLeNombreDeArticles() {
        // Arranger
        Panier panier = new Panier();
        Article article = new Article("REF-001", "Stylo bleu", 1.50);
        // Agir
        panier.ajouterArticle(article, 2);
        // Affirmer
        assertEquals(1, panier.nombreArticles());
    }

    @Test
    void calculerTotalDoitRetournerLaSommDesSousTotaux() {
        // Arranger
        Panier panier = new Panier();
        Article article1 = new Article("REF-001", "Stylo bleu", 1.50);
        Article article2 = new Article("REF-002", "Stylo rouge", 1.50);
        // Agir
        panier.ajouterArticle(article1, 3);
        panier.ajouterArticle(article2, 3);
        // Affirmer
        assertEquals(9.00, panier.calculerTotal(), 0.001);
    }

    @Test
    void panierVideDoitRetournerEstVideEgalTrue() {
        // Arranger
        Panier panier = new Panier();
        // Affirmer
        assertTrue(panier.estVide());
    }

    @Test
    void panierAvecArticlesNeDoitPasEtreVide() {
        // Arranger
        Panier panier = new Panier();
        Article article = new Article("REF-001", "Stylo bleu", 1.50);
        // Agir
        panier.ajouterArticle(article, 1);
        // Affirmer
        assertFalse(panier.estVide());
    }

    @Test
    void articleNulDoitLeverException() {
        // Arranger
        Panier panier = new Panier();
        // Affirmer
        assertThrows(IllegalArgumentException.class,
                () -> panier.ajouterArticle(null, 1));
    }

    @Test
    void quantiteNulleDoitLeverException() {
        // Arranger
        Panier panier = new Panier();
        Article article = new Article("REF-001", "Stylo", 1.50);
        // Affirmer
        assertThrows(IllegalArgumentException.class,
                () -> panier.ajouterArticle(article, 0));
    }

    @Test
    void quantiteNegativeDoitLeverException() {
        // Arranger
        Panier panier = new Panier();
        Article article = new Article("REF-001", "Stylo", 1.50);
        // Affirmer
        assertThrows(IllegalArgumentException.class,
                () -> panier.ajouterArticle(article, -3));
    }

    @Test
    void codeReductionVideDoitLeverException() {
        // Arranger
        Panier panier = new Panier();
        // Affirmer
        assertThrows(IllegalArgumentException.class,
                () -> panier.appliquerCodeReduction(""));
    }

    @Test
    void codeReductionNulDoitLeverException() {
        // Arranger
        Panier panier = new Panier();
        // Affirmer
        assertThrows(IllegalArgumentException.class,
                () -> panier.appliquerCodeReduction(null));
    }

    @Test
    void quantiteUneDoitEtreAcceptee() {
        // Arranger
        Panier panier = new Panier();
        Article article = new Article("REF-001", "Stylo", 9.99);
        // Agir
        panier.ajouterArticle(article, 1);
        // Affirmer
        assertEquals(9.99, panier.calculerTotal(), 0.001);
    }

    @Test
    void articleGratuitDoitEtreAccepte() {
        // Arranger
        Panier panier = new Panier();
        Article article = new Article("OFFERT-01", "Stylo offert", 0.0);
        // Agir
        panier.ajouterArticle(article, 1);
        // Affirmer
        assertEquals(0.0, panier.calculerTotal(), 0.001);
    }

    @Test
    void prixEleveDoitFonctionner() {
        // Arranger
        Panier panier = new Panier();
        Article article = new Article("REF-001", "Ordinateur", 999.99);
        // Agir
        panier.ajouterArticle(article, 1);
        // Affirmer
        assertEquals(999.99, panier.calculerTotal(), 0.001);
    }

    @Test
    void panierAvecUnSeulArticleDoitFonctionner() {
        // Arranger
        Panier panier = new Panier();
        Article article = new Article("REF-001", "Stylo", 1.50);
        // Agir
        panier.ajouterArticle(article, 1);
        // Affirmer
        assertEquals(1, panier.nombreArticles());
    }

    @Test
    void plusieursArticlesDifferentsDansPanier() {
        // Arranger
        Panier panier = new Panier();
        Article article1 = new Article("REF-001", "Stylo", 1.50);
        Article article2 = new Article("REF-002", "Cahier", 3.00);
        Article article3 = new Article("REF-003", "Règle", 2.50);
        // Agir
        panier.ajouterArticle(article1, 1);
        panier.ajouterArticle(article2, 1);
        panier.ajouterArticle(article3, 1);
        // Affirmer
        assertEquals(7.00, panier.calculerTotal(), 0.001);
    }
}