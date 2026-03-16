import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ServiceCommandeTest {

    private DepotStock stockDisponible = reference -> 100;
    private ServiceCommande service;
    private Panier panier;
    private Article articleTest;

    @BeforeEach
    void initialiser() {
        service = new ServiceCommande(stockDisponible);
        panier = new Panier();
        articleTest = new Article("REF-001", "Cahier", 3.50);
    }

    @Test
    void commandeValideDoitRetournerUneCommande() {
        // Agir
        panier.ajouterArticle(articleTest, 2);
        Commande commande = service.passerCommande(panier, "CLIENT-42");
        // Affirmer
        assertNotNull(commande);
        assertEquals(7.0, commande.total(), 0.001);
    }

    @Test
    void panierVideDoitLeverIllegalStateException() {
        assertThrows(IllegalStateException.class,
                () -> service.passerCommande(panier, "CLIENT-42"));
    }

    @Test
    void identifiantClientNulDoitLeverException() {
        // Arranger
        panier.ajouterArticle(articleTest, 1);
        // Affirmer
        assertThrows(IllegalArgumentException.class,
                () -> service.passerCommande(panier, null));
    }

    @Test
    void identifiantClientVideDoitLeverException() {
        // Arranger
        panier.ajouterArticle(articleTest, 1);
        // Affirmer
        assertThrows(IllegalArgumentException.class,
                () -> service.passerCommande(panier, ""));
    }

    @Test
    void stockInsuffisantDoitLeverStockInsuffisantException() {
        // Arranger - stock limité à 1
        DepotStock stockInsuffisant = reference -> 1;
        ServiceCommande serviceStockLimite = new ServiceCommande(stockInsuffisant);
        panier.ajouterArticle(articleTest, 5); // on demande 5 mais stock = 1
        // Affirmer
        assertThrows(StockInsuffisantException.class,
                () -> serviceStockLimite.passerCommande(panier, "CLIENT-42"));
    }

    @Test
    void totalCommandeDoitCorrespondreAuTotalDuPanier() {
        // Arranger
        panier.ajouterArticle(articleTest, 2);
        // Agir
        Commande commande = service.passerCommande(panier, "CLIENT-42");
        // Affirmer
        assertEquals(panier.calculerTotal(), commande.total(), 0.001);
    }
}