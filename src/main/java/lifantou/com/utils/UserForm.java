package lifantou.com.utils;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.*;

@Data
@NoArgsConstructor
@Getter @Setter
public class UserForm {
	//user
	private Long id;
	@Size(min=3)
    private String identifiant;
	@NotNull
	@Size(min=3)
    private String username;
	@Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    private String email;
	@Size(min=3)
    private String password;
	@Size(min=3)
    private String confirmPassword;
	@Size(min=3)
    private String tel;
	@Size(min=3)
    private String adresse;
    private Long idRegion;
    private String type;
    //pour le producteur
    private String nomProduct;
    private String prenomProduct;
    private double longitudeProduct;
    private double latitudeProduct;
    //pour le partenaire
    private String nomStructure;
    private String domaineActivite;
    //pour l'ecole
    private String nomEcole;
	private String departement;
	private double longitudeEcole;
	private double latitudeEcole;
	private Integer nbEleve;
	private String nomGest;
	private String prenomGest;
	private String activiteGest;
	private Integer budgetAnnuel;
		
	public UserForm(Long id,String identifiant,String email,  String tel, String adresse,String type,
			Long idRegion, String nomProduct, String prenomProduct, double longitudeProduct,
			double latitudeProduct, String nomStructure, String domaineActivite, String nomEcole, String departement,
			double longitudeEcole, double latitudeEcole, Integer nbEleve, String nomGest, String prenomGest,
			String activiteGest, Integer budgetAnnuel) {
		super();		
		this.id = id;
		this.identifiant = identifiant;
		this.email = email;
		this.tel = tel;
		this.adresse = adresse;
		this.type = type;
		this.idRegion = idRegion;
		this.nomProduct = nomProduct;
		this.prenomProduct = prenomProduct;
		this.longitudeProduct = longitudeProduct;
		this.latitudeProduct = latitudeProduct;
		this.nomStructure = nomStructure;
		this.domaineActivite = domaineActivite;
		this.nomEcole = nomEcole;
		this.departement = departement;
		this.longitudeEcole = longitudeEcole;
		this.latitudeEcole = latitudeEcole;
		this.nbEleve = nbEleve;
		this.nomGest = nomGest;
		this.prenomGest = prenomGest;
		this.activiteGest = activiteGest;
		this.budgetAnnuel = budgetAnnuel;
	} 
	
	
}