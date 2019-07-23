package lifantou.com.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "faireundon")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FaireUnDon implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private Long id;
    @Temporal(TemporalType.DATE)
    private Date date;
    private String nomComplet;
    private String description;
    private double montant;
    private String token;
    private String email;
    private String tel;
    private String adresse;
    private Long user;
}
