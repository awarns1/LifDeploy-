package lifantou.com.service;

import javax.servlet.http.HttpServletRequest;

public interface LifantouService {
	/*cette methode doit être executé une fois et au premier lancement 
	/* de l'application 
	/* elle va permetre de remplire les tables qui doivent être préremplie de la base de données 
	 * */
	public void initAPP(HttpServletRequest request);
	public void activeAllAccount();
}
