package lifantou.com.service;

import lifantou.com.entity.*;
import lifantou.com.utils.UserForm;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public interface AdminService {
	public Produit SaveProduit(Produit p, MultipartFile file) throws IOException;

	public Produit editProduit(Produit p, MultipartFile file) throws IOException;

	public List<Produit> getProduits();

	public List<Produit> getProduitMarche(Integer stock);

	public List<Produit> getProduitMarcheBySearch(String param1, Integer stock);

	public Produit getProduit(Long id);

	public Ecole getEcole(Long id);

	public Producteur getProducteur(Long id);

	public Partenaire getPartenaire(Long id);

	public List<Ecole> getEcoles();

	public List<Producteur> getProducteurs();

	public List<Partenaire> getPartenaires();

	public PartenaireEcoles addEcoleForPartenaire(Long idPartenaire, Long idEcole);

	public List<Ecole> getAllEcoleByPartenaire(Long idPartenaire);

	public List<PartenaireEcoles> getAllEcoleByPartenaires(Long idPartenaire);

	public List<Partenaire> getAllPartenaireByEcole(Long idEcole);

	public ProduitProducteur getProduitProducteurById(Long id);

	public Cart saveCart(Long idEcole, Long idProduit, int qteCmd);

	public List<Donation> getAllDonation();

	public List<FaireUnDon> getAllFaireUnDon();

	public UserForm getUserForm(User user, String type);

	public User editProfilUser(UserForm userForm);

	public List<AccesApp> getAllAccessApp();

	public AccesApp getAccesAppActived(Long id, int actived);

	public void deleteAccesApp(Long id);

	public void deleteCommentaire(Long id);

	public AccesApp getAccesApp(Long id);

	public List<Cart> getAllCartByEcole(Long id);

	public void removeProdCart(Long id);

	public FaireUnDon saveFaireUnDon(FaireUnDon faireUnDon);

	public void deleteDonationByToken(String token);

	public void deleteEcoleToPart(Long idPartEcole);

	public boolean deleteProduitBase(Long idProd);

	// ================================================================================
	public ProduitProducteur AddProduitToProducteur(ProduitProducteur prodProducteur, Integer quantite, Long produit_id,
			Long producteur_id, String dateProd);

	public ProduitProducteur verificationProdProductExist(Long producteur_id, Long produit_id);

	public List<ProduitProducteur> getAllProduitProdByProducteur(Long idProducteur);

	public List<ProduitProducteur> getAllProduitProducteurByProduit(Long idProduit);

	// public List<ProduitProducteur> ProduitsPArProducteur(String username);
	public List<ProduitProducteur> getAllProduitProducteurByStock();

	// ==============================ADMIN==================================================
	public List<LigneCommande> getAllLigneCommandes();

	public List<LigneCommande> getAllLigneCommandesEncours();

	public LigneCommande saveLigneCommande(Long produit_id, Long ecole_id, Integer quantite, Integer montantTotal,
			Date dateLiv);

	public void valideLigneCommande(Long idLigneCmd, Long idProdProducteur, int qte);

	public LigneCommande changeEtatLigneCommande(Long idLigneCmd, Integer etatCmd);

	public LigneCommande changeEtatPaiementLigneCmd(Long idLigneCmd, Integer etatPaiement);

	public LigneCommande getLigneCommandeById(Long id);

	public List<ChoixProducteur> getAllChoixProducteurByLigneCmd(Long idLigneCmd);

	public List<ChoixProducteur> getAllChoixProducteur();

	public ChoixProducteur changeEtatPaiementChoixProducteur(Long idChoixProd, Integer etatPaiement);

	// ==============================ECOLE==================================================
	public List<LigneCommande> getAllLigneCommandesByEcole(Long idEcole);

	public List<LigneCommande> getAllLigneCommandesByEcoleN(Long idEcole);

	public List<LigneCommande> getAllLigneCommandesEcoleEncours(Long idEcole);

	// ==============================PRODUCTEUR==================================================
	public List<ChoixProducteur> getAllOtherCommandesProducteur(Long idProducteur);

	public List<ChoixProducteur> getAllCommandesProducteurEncours(Long idProducteur);

	public List<ChoixProducteur> getAllCommandesRecuByProducteur(Long idProducteur);

	// ==============================PAIEMENTS==================================================
	public List<LigneCommande> getAllPaiements();

	public List<LigneCommande> getAllPaiementsEffectueByEcole(Long idEcole);

	public List<LigneCommande> getAllPaiementsRestantByEcole(Long idEcole);

	public List<ChoixProducteur> getAllPaiementsRecuByProducteur(Long idProducteur);

	public List<ChoixProducteur> getAllPaiementsRestantByProducteur(Long idProducteur);

	public List<ChoixProducteur> getAllPaiementsByEtatPaiement(boolean etatPaiement);

	// ===============================================================================
	public void misajourStockRollback(String refCmd);

	public Zone saveZone(Zone zone);

	public List<Zone> getAllZone();

	public Region saveRegion(String nomRegion, String sigle, Long idZone);

	public List<Region> getAllRegion();

	public void misajourStockGlobale();

	public String generateReferenceCmd();

	public List<Produit> getAllProduitByZone(Long idZone);

	// ==================================STATISTIQUES==============================================
	// public void getNbStat();
	public List<Ecole> getAllEcoleNonAffecterToPart(List<Ecole> listEcoleExist);

	public List<NousContactez> getAllCommentaires();
}
