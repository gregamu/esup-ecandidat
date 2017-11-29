/**
 * ESUP-Portail eCandidat - Copyright (c) 2016 ESUP-Portail consortium
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied. See the License for the specific language governing permissions and limitations under the License.
 */
package fr.univlorraine.ecandidat.views;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.context.ApplicationContext;
import org.springframework.security.access.prepost.PreAuthorize;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.UI;

import fr.univlorraine.ecandidat.controllers.StatController;
import fr.univlorraine.ecandidat.controllers.UserController;
import fr.univlorraine.ecandidat.services.security.SecurityCtrCandFonc;
import fr.univlorraine.ecandidat.utils.ConstanteUtils;
import fr.univlorraine.ecandidat.utils.NomenclatureUtils;
import fr.univlorraine.ecandidat.views.template.StatViewTemplate;

/**
 * Page de gestion des parametres du centre de candidature
 *
 * @author Kevin Hergalant
 *
 */
@SpringView(name = CtrCandStatFormView.NAME)
@PreAuthorize(ConstanteUtils.PRE_AUTH_CTR_CAND)
public class CtrCandStatFormView extends StatViewTemplate implements View {

	/** serialVersionUID **/
	private static final long serialVersionUID = 8237085399556106511L;

	public static final String NAME = "ctrCandStatFormView";

	/* Injections */
	@Resource
	private transient ApplicationContext applicationContext;
	@Resource
	private transient StatController statController;
	@Resource
	private transient UserController userController;

	/* Le droit sur la vue */
	private SecurityCtrCandFonc securityCtrCandFonc;

	/**
	 * Initialise la vue
	 */
	@PostConstruct
	public void init() {
		/* Récupération du centre de canidature en cours */
		securityCtrCandFonc = userController.getCtrCandFonctionnalite(NomenclatureUtils.FONCTIONNALITE_STATS);
		if (securityCtrCandFonc.hasNoRight()) {
			return;
		}
		String title = applicationContext.getMessage(NAME + ".title", null, UI.getCurrent().getLocale()) + " - "
				+ securityCtrCandFonc.getCtrCand().getLibCtrCand();
		super.init(title, securityCtrCandFonc.getCtrCand().getCodCtrCand(),
				securityCtrCandFonc.getCtrCand().getLibCtrCand(),
				applicationContext.getMessage("stat.libHs.formation", null, UI.getCurrent().getLocale()));
		/* Mise a jour du container */
		majContainer();
	}

	/**
	 * Met a jour le container
	 */
	@Override
	protected void majContainer() {
		super.majContainer(statController.getStatFormation(getCampagne(), getDisplayHs(), securityCtrCandFonc));
	}

	@Override
	protected String getLibelleExport() {
		return applicationContext.getMessage("stat.header.formation", null, UI.getCurrent().getLocale());
	}

	@Override
	protected String getLibelleSuppExport() {
		return applicationContext.getMessage("stat.header.commission", null, UI.getCurrent().getLocale());
	}

	/**
	 * @see com.vaadin.navigator.View#enter(com.vaadin.navigator.ViewChangeListener.ViewChangeEvent)
	 */
	@Override
	public void enter(final ViewChangeEvent event) {
	}
}
