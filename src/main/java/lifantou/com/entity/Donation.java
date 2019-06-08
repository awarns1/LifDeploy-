package lifantou.com.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "donations")
@Data
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class Donation implements Serializable {
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
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
