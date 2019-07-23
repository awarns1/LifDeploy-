package lifantou.com.paydunya;

import javax.servlet.http.HttpServletRequest;

import lifantou.com.entity.FaireUnDon;

public interface IConfigPayDunya {
	// void configPaydunyaSetup();
	// void configPaydunyaCheckoutStore();

	public String baseConfig(FaireUnDon faireUnDon, HttpServletRequest request);

	public void verifEtatPaiement(String token);
}
