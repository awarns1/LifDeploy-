package lifantou.com.paydunya;

import javax.servlet.http.HttpServletRequest;

import lifantou.com.entity.Donation;

public interface IConfigPayDunya {	
	//void configPaydunyaSetup();
	//void configPaydunyaCheckoutStore();
	
	public String baseConfig(Donation donation ,HttpServletRequest request);
	public void verifEtatPaiement(String token);
}
