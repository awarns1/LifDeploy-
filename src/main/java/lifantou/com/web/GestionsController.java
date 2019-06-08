package lifantou.com.web;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lifantou.com.dao.CartRepository;
import lifantou.com.entity.Cart;
import lifantou.com.entity.Ecole;
import lifantou.com.entity.Partenaire;
import lifantou.com.entity.Producteur;
import lifantou.com.entity.Produit;
import lifantou.com.entity.ProduitProducteur;
import lifantou.com.service.AccountService;
import lifantou.com.service.AdminService;
import lifantou.com.utils.UserForm;

@Controller
public class GestionsController {
	@Autowired
	private AdminService adminService;
	@Autowired
	private AccountService accountService;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	Integer seuilStockGl = 10;
	@Autowired
	private CartRepository _cartRepository;

	@GetMapping("/Gestions/{name}")
	public String link2(@PathVariable String name, ModelMap map, HttpSession session) {
		try {
			Long idUser = (Long) session.getAttribute("idSessionUser");
			String role = (String) session.getAttribute("RoleName");
			map.put("role", role);
			map.put("idUser", idUser);
			switch (name) {
			case "Profil":
				map.put("AccesApp", accountService.getAccessAppByUser(idUser));
				map.put("getProfilUser", "actived");
				if (role.equals("ECOLE")) {
					Ecole eco = adminService.getEcole(idUser);
					map.put("datas", adminService.getUserForm(eco, role));
				} else if (role.equals("PRODUCTEUR")) {
					Producteur prod = adminService.getProducteur(idUser);
					map.put("datas", adminService.getUserForm(prod, role));
				} else if (role.equals("PARTENAIRE")) {
					Partenaire part = adminService.getPartenaire(idUser);
					map.put("datas", adminService.getUserForm(part, role));
				}
				map.put("regionList", adminService.getAllRegion());
				break;

			case "Marche":
				map.put("getMarcheEcole", "Place du Marché");
				map.put("getMarche", "actived");
				map.put("getProdList", "not");
				map.put("ListCatProduit", adminService.getProduitMarche(seuilStockGl));
				map.put("NbCart", adminService.getAllCartByEcole(idUser).size());
				map.put("desactiver", 0);
				break;
			case "Panier":
				map.put("getMarcheEcole", "Place du Marché");
				map.put("getMarche", "actived");
				map.put("ListProdCart", adminService.getAllCartByEcole(idUser));
				map.put("NbCart", adminService.getAllCartByEcole(idUser).size());
				break;
			case "Commandes":
				map.put("getCmdEtPaiementUser", "Commandes");
				map.put("getCmdUser", "actived");
				if (role.equals("ECOLE")) {
					map.put("cmdEcole", "actived");
					map.put("ListCommandeEcole", adminService.getAllLigneCommandesByEcole(idUser));
				} else if (role.equals("PRODUCTEUR")) {
					map.put("cmdProd", "actived");
					map.put("ListChoixCmdProd", adminService.getAllCommandesRecuByProducteur(idUser));
				}
				break;
			case "Paiements":
				map.put("getCmdEtPaiementUser", "Mes Paiements");
				map.put("getPaiementUser", "actived");
				if (role.equals("ECOLE")) {
					map.put("paieEcole", "active");
					map.put("ListPaiementEcole", adminService.getAllPaiementsEffectueByEcole(idUser));
					map.put("ListPaieEcoleRestant", adminService.getAllPaiementsRestantByEcole(idUser));
				} else if (role.equals("PRODUCTEUR")) {
					map.put("paieProd", "active");
					map.put("ListPaiementProdRecu", adminService.getAllPaiementsRecuByProducteur(idUser));
					map.put("ListPaiementProdRest", adminService.getAllPaiementsRestantByProducteur(idUser));
				}
				map.put("getBlocPaiements", true);
				return "views/dashboard_h";
			case "Stock":
				map.put("getCmdEtPaiementUser", "Mes Produits");
				map.put("getStock", "actived");
				map.put("getCtlStock", "not");
				map.put("ListProdProducte", adminService.getAllProduitProdByProducteur(idUser));
				break;
			case "Partenaires":
				map.put("getCmdEtPaiementUser", "Mes Partenaires");
				map.put("parteEcole", "actived");
				map.put("ListParteEcole", adminService.getAllPartenaireByEcole(idUser));
				break;
			case "Ecoles":
				map.put("getCmdEtPaiementUser", "Ecoles");
				map.put("ecolePart", "actived");
				map.put("ListEcoleParte", adminService.getAllEcoleByPartenaire(idUser));
				break;
			}
			map.put("getOtherList", true);
			return "views/dashboard_h";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// recherche produit marche ecole
	@PostMapping("/Gestions/Marche/Search/produit")
	public String searchProdEcol(@RequestParam("valSearch") String libProd, ModelMap map, HttpSession session) {
		try {
			Long idUser = (Long) session.getAttribute("idSessionUser");
			map.put("role", session.getAttribute("RoleName"));
			map.put("getMarcheEcole", "Place du Marché");
			map.put("getMarche", "actived");
			map.put("getProdList", "not");
			map.put("ListCatProduit", adminService.getProduitMarcheBySearch(libProd, seuilStockGl));
			map.put("NbCart", adminService.getAllCartByEcole(idUser).size());
			return "views/dashboard_h";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@PostMapping("/Gestions/Profil/Edit")
	public String editProfil(UserForm userForm, BindingResult result, ModelMap map, HttpSession session,
			HttpServletRequest request) {
		adminService.editProfilUser(userForm);
		String roles = (String) session.getAttribute("RoleName");
		if (roles.equals("ADMIN")) {
			switch (userForm.getType()) {
			case "ECOL":
				return "redirect:/Admin/Ecoles-List";
			case "PROD":
				return "redirect:/Admin/Producteurs-List";
			case "PART":
				return "redirect:/Admin/Partenaires-List";
			}
			return "";
		} else {
			map.put("editProfilOk", "actived");
			return link2("Profil", map, session);
		}
	}

	// delete product cart
	@GetMapping("/Gestions/Panier/{id}/remove/")
	public String deleteProdCart(@PathVariable("id") Long id, ModelMap map) {
		try {
			adminService.removeProdCart(id);
			return "redirect:/Gestions/Panier";
		} catch (Exception e) {
			// e.printStackTrace();
			return "redirect:/Gestions/Panier";
		}
	}

	// add produit producteur
	@GetMapping("/Gestions/Produit/{id}-{vals}/")
	public String addProdProducteur(@PathVariable Long id, @PathVariable String vals, ModelMap map,
			HttpSession session) {
		map.put("addProduitProduct", "not null");
		map.put("getCmdEtPaiementUser", "Mes Produits");
		map.put("getStock", "actived");
		switch (vals) {
		case "new":
			map.put("id", id);
			map.put("idPP", 0);
			map.put("prodBaseListe", adminService.getProduits());
			break;
		case "edit":
			ProduitProducteur pp = adminService.getProduitProducteurById(id);
			map.put("prodBas", adminService.getProduit(pp.getProduit().getId()));
			map.put("idPP", id);
			map.put("id", pp.getProducteur().getId());
			map.put("qte", pp.getQuantiteStock());
			map.put("edit", "not null");
			break;
		}
		link2("", map, session);
		map.put("getOtherList", false);
		return "views/dashboard_h";
	}

	@PostMapping("/Add_Produit/new/")
	public String addProdBasePost(@RequestParam("idProduct") Long idProduct, @RequestParam("catId") Long catId,
			@RequestParam("Qte") int Qte, @RequestParam("dateProd") String dateProd,
			@RequestParam("idProdProduct") Long idProdProduct, ModelMap map, HttpSession session) {
		ProduitProducteur produitProducteur = new ProduitProducteur();
		if (idProdProduct == 0) {
			ProduitProducteur pp = adminService.verificationProdProductExist(idProduct, catId);
			if (pp != null) {
				map.put("ProdExist", "non null");
			} else {
				adminService.AddProduitToProducteur(produitProducteur, Qte, catId, idProduct, dateProd);
				map.put("AddProdProdOK", "non null");
			}
		} else {
			ProduitProducteur produitProduct = adminService.getProduitProducteurById(idProdProduct);
			adminService.AddProduitToProducteur(produitProduct, Qte, produitProduct.getProduit().getId(), idProduct,
					dateProd);
			map.put("EditProdProdOK", "non null");
		}
		String role = (String) session.getAttribute("RoleName");
		if (role.equals("ADMIN")) {
			return "redirect:/Admin/Produit-Disponible";
		} else {
			return "redirect:/Gestions/Stock";
		}
	}

	// add product to cart
	@GetMapping("/Gestions/cart/{id}/add-product-to-cart/")
	public String addProdToCart(@PathVariable("id") Long id, ModelMap map, HttpSession session) {
		try {
			Produit produit = adminService.getProduit(id);
			boolean resp = false;
			Long idUser = (Long) session.getAttribute("idSessionUser");
			List<Cart> listCarts = adminService.getAllCartByEcole(idUser);
			if (!listCarts.isEmpty()) {
				for (Cart c : listCarts) {
					if (c.getProduit().getId() == produit.getId()) {
						resp = true;
						break;
					}
				}
			}
			if (resp == false) {
				adminService.saveCart(idUser, produit.getId(), 0);
				map.put("msg_addProdToCartOk", "ok");
				map.put("desactiver", produit.getId());
			} else {
				map.put("msg_addProdToCartKo", "ko");				
			}
			return link2("Marche", map, session);

		} catch (Exception e) {
			// e.printStackTrace();
			return "redirect:/";
		}
	}

	@PostMapping("/Gestions/checkout/order/")
	public String onlineStore(@RequestParam("idCart") Long idCart[], @RequestParam("qteCmd") int qteCmd[], ModelMap map,
			@RequestParam("dateLiv") String dateLiv[], HttpSession session) {
		try {
			Long idUser = (Long) session.getAttribute("idSessionUser");
			map.put("role", session.getAttribute("RoleName"));
			for (int i = 0; i < idCart.length; i++) {
				Cart c = _cartRepository.getOne(idCart[i]);
				Produit pro = adminService.getProduit(c.getProduit().getId());
				if (qteCmd[i] <= pro.getStockGlobal()) {
					c.setQuantite(qteCmd[i]);
					c.setDateLivraison(sdf.parse(dateLiv[i]));
					_cartRepository.saveAndFlush(c);
				} else {
					map.put("errorQtes", "Une des quantitées saissies est superieur au stock global");
					return link2("Panier", map, session);
				}
			}
			List<Cart> listPanier = adminService.getAllCartByEcole(idUser);
			int mntTotal = 0;
			for (Cart cart : listPanier) {
				mntTotal = mntTotal + (cart.getQuantite() * cart.getProduit().getPrixUnitaire());
			}
			map.put("mntTotal", mntTotal);
			map.put("listCheckout", listPanier);
			map.put("getMarcheEcole", "Place du Marché");
			map.put("getMarche", "actived");
			// return link2("Panier", map, session);
			map.put("NbCart", adminService.getAllCartByEcole(idUser).size());
			return "views/dashboard_h";
		} catch (Exception e) {
			return null;
		}
	}

	// passer cmd
	@GetMapping("/Gestions/checkout/valider/")
	public String onlineStoreValid(ModelMap map, HttpSession session) {
		try {
			Long idUser = (Long) session.getAttribute("idSessionUser");
			List<Cart> listPanier = adminService.getAllCartByEcole(idUser);
			int mntTotal = 0;
			for (Cart cart : listPanier) {
				mntTotal = cart.getQuantite() * cart.getProduit().getPrixUnitaire();
				adminService.saveLigneCommande(cart.getProduit().getId(), idUser, cart.getQuantite(), mntTotal,
						cart.getDateLivraison());
				_cartRepository.delete(cart.getIdCart());
			}
			map.put("cmdValid", "ok");
			return link2("Commandes", map, session);
		} catch (Exception e) {
			return null;
		}
	}

	// details partenaire--->
	@GetMapping("/Gestions/Partenaire/{id}/Details/")
	public String detailEcoleForPart(@PathVariable("id") Long id, ModelMap map,HttpSession session) {
		try {
			map.put("role", session.getAttribute("RoleName"));
			Ecole ec = adminService.getEcole(id);
			map.put("getCmdEtPaiementUser", "Détails pour l'école : "+ec.getIdentifiant());
			map.put("ecolePart", "actived");
			map.put("getBlocDetailsEcole", true);
			map.put("ListCmdEcoleEncours", adminService.getAllLigneCommandesEcoleEncours(id));
			map.put("ListCmdEcole",adminService.getAllLigneCommandesByEcoleN(id));
			map.put("ListPaieEcoleEff", adminService.getAllPaiementsEffectueByEcole(id));
			map.put("ListPaieEcoleRest", adminService.getAllPaiementsRestantByEcole(id));
			return "views/dashboard_h";
		} catch (Exception e) {
			// e.printStackTrace();
			return "redirect:/Gestions/Ecoles";
		}
	}
	
}
