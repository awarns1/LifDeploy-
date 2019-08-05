package lifantou.com.service;

import lifantou.com.dao.*;
import lifantou.com.entity.*;
import lifantou.com.utils.UserForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Transactional
@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	private LigneCommandeRepository ligneCommandeRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AccesAppRepository accesAppRepository;
	@Autowired
	private DonationRepository donationRepository;
	@Autowired
	private FaireUnDonRepository faireUnDonRepository;
	@Autowired
	private CartRepository cartRepository;
	@Autowired
	private ZoneRepository zoneRepository;
	@Autowired
	NousContactezRepository nousContactezRepository;
	@Autowired
	private RegionRepository regionRepository;
	@Autowired
	private PartenaireEcoleRepository partEcoleRepository;
	@Autowired
	private AdminService adminService;
	@Autowired
	private ProduitProducteurRepository prodProducteurRepository;
	@Autowired
	private ProduitRepository produitRepository;
	@Autowired
	private ChoixProducteurRepository choixProducteurRepository;
	@Autowired
	private AccountService accountService;
	// private final String DIRECTOR = "src/main/resources/static/images/";
	private Integer seuilStock = 0;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	// SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy");

	@Override
	public Produit SaveProduit(Produit p, MultipartFile file) throws IOException {
		p.setOriginalName(file.getOriginalFilename());
		p.setPhotoBytes(file.getBytes());
		p.setStockGlobal(0);
		return produitRepository.save(p);
	}

	@Override
	public Produit editProduit(Produit p, MultipartFile file) throws IOException {
		Produit pr = getProduit(p.getId());
		if (file.isEmpty()) {
			p.setOriginalName(pr.getOriginalName());
			p.setPhotoBytes(pr.getPhotoBytes());
		} else {
			p.setOriginalName(file.getOriginalFilename());
			p.setPhotoBytes(file.getBytes());
		}
		return produitRepository.save(p);
	}

	@Override
	public List<Produit> getProduits() {
		return produitRepository.findAll();
	}

	@Override
	public Produit getProduit(Long id) {
		return produitRepository.getOne(id);
	}

	@Override
	public List<Ecole> getEcoles() {
		List<Ecole> ecoles = new ArrayList<>();
		userRepository.findAll().forEach(e -> {
			if (e instanceof Ecole) {
				ecoles.add((Ecole) e);
			}
		});
		return ecoles;
	}

	@Override
	public List<Producteur> getProducteurs() {
		List<Producteur> producteurs = new ArrayList<>();
		userRepository.findAll().forEach(e -> {
			if (e instanceof Producteur) {
				producteurs.add((Producteur) e);
			}
		});
		return producteurs;
	}

	@Override
	public Producteur getProducteur(Long id) {
		Producteur pr = new Producteur();
		User u = userRepository.findOne(id);
		if (u instanceof Producteur) {
			pr = (Producteur) u;
		}
		return pr;
	}

	@Override
	public Ecole getEcole(Long id) {
		Ecole ec = new Ecole();
		User u = userRepository.findOne(id);
		if (u instanceof Ecole) {
			ec = (Ecole) u;
		}
		return ec;
	}

	@Override
	public Partenaire getPartenaire(Long id) {
		Partenaire pa = new Partenaire();
		User u = userRepository.findOne(id);
		if (u instanceof Partenaire) {
			pa = (Partenaire) u;
		}
		return pa;
	}

	@Override
	public List<Partenaire> getPartenaires() {
		List<Partenaire> partenaires = new ArrayList<>();
		userRepository.findAll().forEach(p -> {
			if (p instanceof Partenaire) {
				partenaires.add((Partenaire) p);
			}
		});
		return partenaires;
	}

	// ================================================================================
	@Override
	public ProduitProducteur AddProduitToProducteur(ProduitProducteur prodProducteur, Integer quantite, Long produit_id,
			Long producteur_id, String dateProd) {
		Date dateP;
		try {
			dateP = sdf.parse(dateProd);
			prodProducteur.setProducteur(userRepository.getOne(producteur_id));
			prodProducteur.setProduit(produitRepository.getOne(produit_id));
			prodProducteur.setDateProduction(dateP);
			prodProducteur.setQuantiteStock(quantite);
			return prodProducteurRepository.save(prodProducteur);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ProduitProducteur verificationProdProductExist(Long producteur_id, Long produit_id) {
		return prodProducteurRepository.findProduitProducteurByProducteurIdAndProduitId(producteur_id, produit_id);
	}

	@Override
	public List<ProduitProducteur> getAllProduitProdByProducteur(Long idProducteur) {
		Producteur producteur = null;
		if (userRepository.findOne(idProducteur) instanceof Producteur) {
			producteur = (Producteur) userRepository.getOne(idProducteur);
		}
		return prodProducteurRepository.findProduitProducteurByProducteurId(idProducteur);
	}

	@Override
	public List<ProduitProducteur> getAllProduitProducteurByProduit(Long idProduit) {
		return this.prodProducteurRepository.findProduitProducteurByProduitId(idProduit);
	}

	/*
	 * @Override public List<ProduitProducteur> ProduitsPArProducteur(String
	 * username) { return
	 * prodProducteurRepository.findProduitProducteurByProducteurUsernameEquals(
	 * username); }
	 */

	@Override
	public List<ProduitProducteur> getAllProduitProducteurByStock() {
		return prodProducteurRepository.findProduitProducteurByQuantiteStockGreaterThan(seuilStock);
	}

	@Override
	public ProduitProducteur getProduitProducteurById(Long id) {
		return prodProducteurRepository.findProduitProducteurById(id);
	}

	// ================================================================================
	@Override
	public List<LigneCommande> getAllLigneCommandes() {
		return ligneCommandeRepository.findLigneCommandeByEtatCmdIsNotIn(0);
	}

	@Override
	public List<LigneCommande> getAllLigneCommandesEncours() {
		return ligneCommandeRepository.findLigneCommandeByEtatCmd(0);
	}

	@Override
	public LigneCommande saveLigneCommande(Long produit_id, Long ecole_id, Integer quantite, Integer montantTotal,
			Date dateLiv) {
		try {
			// Date dateL = sdf.parse(dateLiv);
			LigneCommande ligneCommande = new LigneCommande();
			ligneCommande.setQuantite(quantite);
			ligneCommande.setProduit(adminService.getProduit(produit_id));
			User ecole = userRepository.getOne(ecole_id);
			ligneCommande.setEcole(ecole);
			ligneCommande.setDateCmd(new Date());
			ligneCommande.setDateLivraison(dateLiv);
			ligneCommande.setEtatCmd(0);
			ligneCommande.setEtatPaiement(false);
			ligneCommande.setMontantTotal(montantTotal);
			ligneCommande.setRef(adminService.generateReferenceCmd());
			return ligneCommandeRepository.save(ligneCommande);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void valideLigneCommande(Long idLigneCmd, Long idProdProducteur, int qte) {
		LigneCommande ligneCmd = ligneCommandeRepository.getOne(idLigneCmd);
		ProduitProducteur pp = prodProducteurRepository.getOne(idProdProducteur);
		ChoixProducteur choixProducteur = new ChoixProducteur();
		choixProducteur.setLigneCommande(ligneCmd);
		choixProducteur.setProduitProducteur(pp);
		choixProducteur.setQuantite(qte);
		choixProducteur.setEtatPaiement(false);
		choixProducteurRepository.save(choixProducteur);
		ligneCmd.setEtatCmd(1);
		ligneCommandeRepository.saveAndFlush(ligneCmd);
		pp.setQuantiteStock(pp.getQuantiteStock() - qte);
		prodProducteurRepository.saveAndFlush(pp);
	}

	@Override
	public LigneCommande changeEtatLigneCommande(Long idLigneCmd, Integer etatCmd) {
		LigneCommande ligneCommande = ligneCommandeRepository.getOne(idLigneCmd);
		ligneCommande.setEtatCmd(etatCmd);
		return ligneCommandeRepository.saveAndFlush(ligneCommande);
	}

	@Override
	public LigneCommande changeEtatPaiementLigneCmd(Long idLigneCmd, Integer etatPaiement) {
		LigneCommande ligneCommande = ligneCommandeRepository.getOne(idLigneCmd);
		if (etatPaiement == 0) {
			ligneCommande.setEtatPaiement(false);
		} else {
			ligneCommande.setEtatPaiement(true);
		}
		return ligneCommandeRepository.saveAndFlush(ligneCommande);
	}

	@Override
	public ChoixProducteur changeEtatPaiementChoixProducteur(Long idChoixProd, Integer etatPaiement) {
		ChoixProducteur choixProducteur = choixProducteurRepository.getOne(idChoixProd);
		if (etatPaiement == 0) {
			choixProducteur.setEtatPaiement(false);
		} else {
			choixProducteur.setEtatPaiement(true);
		}
		return choixProducteurRepository.saveAndFlush(choixProducteur);
	}

	// =================================ECOLE===============================================
	@Override
	public List<LigneCommande> getAllLigneCommandesByEcole(Long idEcole) {
		return ligneCommandeRepository.findLigneCommandeByEcoleId(idEcole);
	}

	@Override
	public List<LigneCommande> getAllLigneCommandesByEcoleN(Long idEcole) {
		return ligneCommandeRepository.findLigneCommandeByEcoleIdAndEtatCmdIsNotIn(idEcole, 0);
	}

	@Override
	public List<LigneCommande> getAllLigneCommandesEcoleEncours(Long idEcole) {
		return ligneCommandeRepository.findLigneCommandeByEcoleIdAndEtatCmd(idEcole, 0);
	}

	// =================================PRODUCTEUR===============================================
	@Override
	public List<ChoixProducteur> getAllOtherCommandesProducteur(Long idProducteur) {
		return choixProducteurRepository
				.findChoixProducteursByProduitProducteurProducteurIdAndLigneCommandeEtatCmdNotIn(idProducteur, 1);
	}

	@Override
	public List<ChoixProducteur> getAllCommandesProducteurEncours(Long idProducteur) {
		return choixProducteurRepository
				.findChoixProducteursByProduitProducteurProducteurIdAndLigneCommandeEtatCmd(idProducteur, 1);
	}

	@Override
	public List<ChoixProducteur> getAllCommandesRecuByProducteur(Long idProducteur) {
		return choixProducteurRepository.findChoixProducteursByProduitProducteurProducteurId(idProducteur);
	}

	// ==============================PAIEMENTS==================================================
	@Override
	public List<LigneCommande> getAllPaiements() {
		return ligneCommandeRepository.findLigneCommandeByEtatPaiement(true);
	}

	@Override
	public List<LigneCommande> getAllPaiementsEffectueByEcole(Long idEcole) {
		return ligneCommandeRepository.findLigneCommandeByEcoleIdAndEtatPaiement(idEcole, true);
	}

	@Override
	public List<LigneCommande> getAllPaiementsRestantByEcole(Long idEcole) {
		return ligneCommandeRepository.findLigneCommandeByEcoleIdAndEtatPaiement(idEcole, false);
	}

	@Override
	public List<ChoixProducteur> getAllPaiementsRecuByProducteur(Long idProducteur) {
		return choixProducteurRepository
				.findChoixProducteursByProduitProducteurProducteurIdAndEtatPaiement(idProducteur, true);
	}

	@Override
	public List<ChoixProducteur> getAllPaiementsRestantByProducteur(Long idProducteur) {
		return choixProducteurRepository
				.findChoixProducteursByProduitProducteurProducteurIdAndEtatPaiement(idProducteur, false);
	}

	@Override
	public List<ChoixProducteur> getAllPaiementsByEtatPaiement(boolean etatPaiement) {
		return choixProducteurRepository.findChoixProducteursByLigneCommandeEtatPaiement(etatPaiement);
	}

	// ================================================================================
	@Override
	public String generateReferenceCmd() {
		int valeurMin = 100;
		int valeurMax = 10000;
		Random r = new Random();
		int valeur = valeurMin + r.nextInt(valeurMax - valeurMin);
		String ref = "CMD-RS-REF-" + valeur;
		return ref;
	}

	@Override
	public PartenaireEcoles addEcoleForPartenaire(Long idPartenaire, Long idEcole) {
		PartenaireEcoles peco = partEcoleRepository.findPartenaireEcolesByEcoleIdAndPartenaireId(idEcole, idPartenaire);
		if (peco == null) {
			Ecole ec = (Ecole) userRepository.findOne(idEcole);
			Partenaire part = (Partenaire) userRepository.findOne(idPartenaire);
			PartenaireEcoles partEc = new PartenaireEcoles();
			partEc.setEcole(ec);
			partEc.setPartenaire(part);
			partEc.setDatePartenariat(new Date());
			return partEcoleRepository.save(partEc);
		} else {
			return peco;
		}
	}

	@Override
	public List<Ecole> getAllEcoleByPartenaire(Long idPartenaire) {
		List<PartenaireEcoles> listPartEcoles = partEcoleRepository.findPartenaireEcolesByPartenaireId(idPartenaire);
		List<Ecole> listEcole = new ArrayList<>();
		for (PartenaireEcoles pe : listPartEcoles) {
			Ecole ec = (Ecole) userRepository.findOne(pe.getEcole().getId());
			listEcole.add(ec);
		}
		return listEcole;
	}

	@Override
	public List<PartenaireEcoles> getAllEcoleByPartenaires(Long idPartenaire) {
		return partEcoleRepository.findPartenaireEcolesByPartenaireId(idPartenaire);
	}

	@Override
	public List<Partenaire> getAllPartenaireByEcole(Long idEcole) {
		List<PartenaireEcoles> listPartEcoles = partEcoleRepository.findPartenaireEcolesByEcoleId(idEcole);
		List<Partenaire> listPartenaire = new ArrayList<>();
		for (PartenaireEcoles pec : listPartEcoles) {
			Partenaire part = (Partenaire) userRepository.findOne(pec.getPartenaire().getId());
			listPartenaire.add(part);
		}
		return listPartenaire;
	}

	@Override
	public void misajourStockRollback(String refCmd) {
		LigneCommande lCmd = ligneCommandeRepository.findLigneCommandeByRef(refCmd);
		List<ChoixProducteur> listChProd = choixProducteurRepository.findChoixProducteursByLigneCommande(lCmd);
		for (ChoixProducteur cp : listChProd) {
			ProduitProducteur pp = prodProducteurRepository.findOne(cp.getProduitProducteur().getId());
			pp.setQuantiteStock(pp.getQuantiteStock() + cp.getQuantite());

			prodProducteurRepository.save(pp);
		}
	}

	@Override
	public Zone saveZone(Zone zone) {
		return zoneRepository.save(zone);
	}

	@Override
	public List<Zone> getAllZone() {
		return zoneRepository.findAll();
	}

	@Override
	public Region saveRegion(String nomRegion, String sigle, Long idZone) {
		Region region = new Region();
		region.setNomRegion(nomRegion);
		region.setSigle(sigle);
		Zone z = new Zone();
		z.setId(idZone);
		region.setZone(z);
		return regionRepository.save(region);
	}

	@Override
	public List<Region> getAllRegion() {
		return regionRepository.findAll();
	}

	@Override
	public void misajourStockGlobale() {
		List<Produit> listeProd = produitRepository.findAll();
		int stockGl = 0;
		for (Produit produit : listeProd) {
			List<ProduitProducteur> listProdProctc = prodProducteurRepository
					.findProduitProducteurByProduitId(produit.getId());
			for (ProduitProducteur pp : listProdProctc) {
				stockGl = stockGl + pp.getQuantiteStock();
			}
			produit.setStockGlobal(stockGl);
			produitRepository.save(produit);
			stockGl = 0;
		}
	}

	@Override
	public List<Produit> getAllProduitByZone(Long idZone) {
		List<ProduitProducteur> listProdProdc = prodProducteurRepository
				.findProduitProducteursByProducteurRegionZoneId(idZone);
		List<Produit> listeProd = new ArrayList<>();
		List<Produit> listeAllProd = produitRepository.findAll();
		int qte = 0;
		boolean exist = false;
		for (Produit produit : listeAllProd) {
			Produit prod = new Produit();
			for (ProduitProducteur pp : listProdProdc) {
				if (pp.getProduit().getId() == produit.getId()) {
					qte = qte + pp.getQuantiteStock();
					prod = pp.getProduit();
					exist = true;
				}
			}
			if (exist == true && prod.getId() != null) {
				prod.setStockGlobal(qte);
				listeProd.add(prod);
				qte = 0;
			}
		}
		return listeProd;
	}

	@Override
	public Cart saveCart(Long idEcole, Long idProduit, int qteCmd) {
		Cart c = new Cart();
		c.setEcole(userRepository.getOne(idEcole));
		c.setProduit(produitRepository.getOne(idProduit));
		c.setQuantite(qteCmd);
		return cartRepository.save(c);
	}

	@Override
	public List<Donation> getAllDonation() {
		return donationRepository.findAll();
	}

	@Override
	public List<FaireUnDon> getAllFaireUnDon() {
		return faireUnDonRepository.findAll();
	}

	@Override
	public List<NousContactez> getAllCommentaires() {
		return nousContactezRepository.findAll();
	}

	@Override
	public UserForm getUserForm(User user, String type) {
		UserForm usf = new UserForm();
		if (type.equals("ECOLE")) {
			Ecole eco = (Ecole) user;
			usf.setId(eco.getId());
			usf.setIdentifiant(eco.getIdentifiant());
			usf.setEmail(eco.getEmail());
			usf.setTel(eco.getTel());
			usf.setAdresse(eco.getAdresse());
			usf.setType("ECOL");
			usf.setIdRegion(eco.getRegion().getId());
			usf.setNomEcole(eco.getNomEcole());
			usf.setDepartement(eco.getDepartement());
			usf.setLongitudeEcole(eco.getLongitudeEcole());
			usf.setLatitudeEcole(eco.getLatitudeEcole());
			usf.setNbEleve(eco.getNbEleve());
			usf.setNomGest(eco.getNomGest());
			usf.setPrenomGest(eco.getPrenomGest());
			usf.setActiviteGest(eco.getActiviteGest());
			usf.setBudgetAnnuel(eco.getBudgetAnnuel());
			return usf;
		} else if (type.equals("PRODUCTEUR")) {
			Producteur pr = (Producteur) user;
			usf.setId(pr.getId());
			usf.setIdentifiant(pr.getIdentifiant());
			usf.setEmail(pr.getEmail());
			usf.setTel(pr.getTel());
			usf.setAdresse(pr.getAdresse());
			usf.setType("PROD");
			usf.setIdRegion(pr.getRegion().getId());
			usf.setNomProduct(pr.getNomProduct());
			usf.setPrenomProduct(pr.getPrenomProduct());
			usf.setLongitudeProduct(pr.getLongitudeProduct());
			usf.setLatitudeProduct(pr.getLatitudeProduct());
			return usf;
		} else if (type.equals("PARTENAIRE")) {
			Partenaire pa = (Partenaire) user;
			usf.setId(pa.getId());
			usf.setIdentifiant(pa.getIdentifiant());
			usf.setEmail(pa.getEmail());
			usf.setTel(pa.getTel());
			usf.setAdresse(pa.getAdresse());
			usf.setType("PART");
			usf.setIdRegion(pa.getRegion().getId());
			usf.setNomStructure(pa.getNomStructure());
			usf.setDomaineActivite(pa.getDomaineActivite());
			return usf;
		} else {
			// simple user
			return usf;
		}
	}

	@Override
	public User editProfilUser(UserForm usfr) {
		Region reg = regionRepository.findOne(usfr.getIdRegion());
		String iden[] = usfr.getIdentifiant().split("-");
		String identEdit = reg.getSigle() + "-" + iden[1] + "-" + iden[2];
		if (usfr.getType().equals("ECOL")) {
			Ecole ec = new Ecole(identEdit, usfr.getEmail(), usfr.getTel(), usfr.getAdresse(), reg);
			ec.setId(usfr.getId());
			ec.setNomEcole(usfr.getNomEcole());
			ec.setDepartement(usfr.getDepartement());
			ec.setNomGest(usfr.getNomGest());
			ec.setPrenomGest(usfr.getPrenomGest());
			ec.setActiviteGest(usfr.getActiviteGest());
			ec.setNbEleve(usfr.getNbEleve());
			ec.setBudgetAnnuel(usfr.getBudgetAnnuel());
			ec.setLongitudeEcole(usfr.getLongitudeEcole());
			ec.setLatitudeEcole(usfr.getLatitudeEcole());
			return userRepository.save(ec);
		} else if (usfr.getType().equals("PROD")) {
			Producteur pr = new Producteur(identEdit, usfr.getEmail(), usfr.getTel(), usfr.getAdresse(), reg);
			pr.setId(usfr.getId());
			pr.setNomProduct(usfr.getNomProduct());
			pr.setPrenomProduct(usfr.getPrenomProduct());
			pr.setLongitudeProduct(usfr.getLongitudeProduct());
			pr.setLatitudeProduct(usfr.getLatitudeProduct());
			return userRepository.save(pr);

		} else if (usfr.getType().equals("PART")) {
			Partenaire pa = new Partenaire(identEdit, usfr.getEmail(), usfr.getTel(), usfr.getAdresse(), reg);
			pa.setId(usfr.getId());
			pa.setNomStructure(usfr.getNomStructure());
			pa.setDomaineActivite(usfr.getDomaineActivite());
			return userRepository.save(pa);
		}
		return null;
	}

	@Override
	public LigneCommande getLigneCommandeById(Long id) {
		return ligneCommandeRepository.findOne(id);
	}

	@Override
	public List<ChoixProducteur> getAllChoixProducteurByLigneCmd(Long idLigneCmd) {
		return choixProducteurRepository.findChoixProducteursByLigneCommandeId(idLigneCmd);
	}

	@Override
	public List<ChoixProducteur> getAllChoixProducteur() {
		return choixProducteurRepository.findAll();
	}

	@Override
	public List<Produit> getProduitMarche(Integer stock) {
		return produitRepository.findProduitsByStockGlobalGreaterThan(stock);
	}

	@Override
	public List<Produit> getProduitMarcheBySearch(String param1, Integer stock) {
		return produitRepository.findProduitsByNomProduitContainingAndStockGlobalGreaterThan(param1, stock);
	}

	@Override
	public List<AccesApp> getAllAccessApp() {
		return accesAppRepository.findAll();
	}

	@Override
	public AccesApp getAccesAppActived(Long id, int actived) {
		AccesApp ac = accesAppRepository.findOne(id);
		ac.setActived(actived);
		return ac;
	}

	@Override
	public void deleteAccesApp(Long id) {
		AccesApp ac = accesAppRepository.findOne(id);
		User user = ac.getIdUser();

		accesAppRepository.delete(ac);
		userRepository.delete(user);
	}

	@Override
	public void deleteCommentaire(Long id) {
		NousContactez cmnt = nousContactezRepository.findOne(id);
		nousContactezRepository.delete(cmnt);
	}

	@Override
	public AccesApp getAccesApp(Long id) {
		return accesAppRepository.findOne(id);
	}

	@Override
	public List<Cart> getAllCartByEcole(Long id) {
		return cartRepository.findCartByEcoleId(id);
	}

	@Override
	public void removeProdCart(Long id) {
		cartRepository.delete(id);
	}

	@Override
	public FaireUnDon saveFaireUnDon(FaireUnDon faireUnDon) {
		User user = accountService.verificationCompteUser(faireUnDon.getEmail());
		if (user == null) {
			user = new User();
			user.setEmail(faireUnDon.getEmail());
			user.setAdresse(faireUnDon.getAdresse());
			user.setTel(faireUnDon.getTel());
			user.setEmail(faireUnDon.getEmail());
			userRepository.save(user);
		}
		faireUnDon.setUser(user.getId());
		faireUnDon.setDate(new Date());
		return faireUnDonRepository.save(faireUnDon);
	}

	@Override
	public void deleteDonationByToken(String token) {
		Donation donation = donationRepository.findDonationByToken(token);
		donationRepository.delete(donation);
	}

	@Override
	public void deleteEcoleToPart(Long idPartEcole) {
		partEcoleRepository.delete(idPartEcole);
	}

	@Override
	public boolean deleteProduitBase(Long idProd) {
		boolean resp = false;
		List<ProduitProducteur> listProdProduct = adminService.getAllProduitProducteurByProduit(idProd);
		if (listProdProduct.isEmpty()) {
			produitRepository.delete(idProd);
			resp = true;
		}
		return resp;
	}

	@Override
	public List<Ecole> getAllEcoleNonAffecterToPart(List<Ecole> listEcoleExist) {
		List<Ecole> listAllEcoles = getEcoles();
		List<Ecole> listAllNonExist = new ArrayList<Ecole>(listAllEcoles);
		listAllNonExist.removeAll(listEcoleExist);
		return listAllNonExist;
	}

}
