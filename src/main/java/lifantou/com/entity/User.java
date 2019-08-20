package lifantou.com.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import javax.validation.constraints.Size;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE_USER", discriminatorType = DiscriminatorType.STRING, length = 4)
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(unique = true)
	private String identifiant;
	private String email;
	private String tel;
	private String adresse;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idUser")
	private List<AccesApp> accesAppList;
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	// @LazyCollection(LazyCollectionOption.FALSE)
	private Collection<Donation> donations = new ArrayList<>();
	@ManyToOne
	@JoinColumn(name = "region_id")
	private Region region;

	public User(String identifiant, String email, String tel, String adresse, Region region) {
		this.identifiant = identifiant;
		this.email = email;
		this.tel = tel;
		this.adresse = adresse;
		this.region = region;
	}
}
