/**
 *  ESUP-Portail eCandidat - Copyright (c) 2016 ESUP-Portail consortium
 *
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package fr.univlorraine.ecandidat.views.windows;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.ApplicationContext;

import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

import fr.univlorraine.ecandidat.controllers.PieceJustifController;
import fr.univlorraine.ecandidat.entities.ecandidat.PieceJustif;
import fr.univlorraine.ecandidat.entities.ecandidat.PieceJustif_;
import fr.univlorraine.ecandidat.vaadin.components.OneClickButton;
import fr.univlorraine.ecandidat.vaadin.form.CustomBeanFieldGroup;
import fr.univlorraine.ecandidat.vaadin.form.i18n.I18nField;

/**
 * Fenêtre d'édition de pieceJustif
 * @author Kevin Hergalant
 *
 */
@Configurable(preConstruction=true)
public class PieceJustifWindow extends Window {

	/** serialVersionUID **/
	private static final long serialVersionUID = 1789664007659398677L;

	public static final String[] FIELDS_ORDER = {PieceJustif_.orderPj.getName(),PieceJustif_.codPj.getName(),PieceJustif_.libPj.getName(),PieceJustif_.tesPj.getName(),PieceJustif_.temCommunPj.getName(),PieceJustif_.temUnicitePj.getName(),PieceJustif_.temConditionnelPj.getName(),PieceJustif_.codApoPj.getName(),PieceJustif_.i18nLibPj.getName()};

	@Resource
	private transient ApplicationContext applicationContext;
	@Resource
	private transient PieceJustifController pieceJustifController;

	/* Composants */
	private CustomBeanFieldGroup<PieceJustif> fieldGroup;
	private OneClickButton btnEnregistrer;
	private OneClickButton btnAnnuler;

	/**
	 * Crée une fenêtre d'édition de pieceJustif
	 * @param pieceJustif la pieceJustif à éditer
	 */
	public PieceJustifWindow(PieceJustif pieceJustif) {
		/* Style */
		setModal(true);
		setWidth(550,Unit.PIXELS);
		setResizable(true);
		setClosable(true);

		/* Layout */
		VerticalLayout layout = new VerticalLayout();
		layout.setWidth(100, Unit.PERCENTAGE);
		layout.setMargin(true);
		layout.setSpacing(true);
		setContent(layout);

		/* Titre */
		setCaption(applicationContext.getMessage("pieceJustif.window", null, UI.getCurrent().getLocale()));

		/* Formulaire */
		fieldGroup = new CustomBeanFieldGroup<>(PieceJustif.class);
		fieldGroup.setItemDataSource(pieceJustif);
		FormLayout formLayout = new FormLayout();
		formLayout.setWidth(100, Unit.PERCENTAGE);
		formLayout.setSpacing(true);
		for (String fieldName : FIELDS_ORDER) {
			String caption = applicationContext.getMessage("pieceJustif.table." + fieldName, null, UI.getCurrent().getLocale());
			Field<?> field = fieldGroup.buildAndBind(caption, fieldName);
			field.setWidth(100, Unit.PERCENTAGE);			
			formLayout.addComponent(field);
		}
		
		((I18nField)fieldGroup.getField(PieceJustif_.i18nLibPj.getName())).addCenterListener(e-> {if(e){center();}});	

		layout.addComponent(formLayout);

		/* Ajoute les boutons */
		HorizontalLayout buttonsLayout = new HorizontalLayout();
		buttonsLayout.setWidth(100, Unit.PERCENTAGE);
		buttonsLayout.setSpacing(true);
		layout.addComponent(buttonsLayout);

		btnAnnuler = new OneClickButton(applicationContext.getMessage("btnAnnuler", null, UI.getCurrent().getLocale()), FontAwesome.TIMES);
		btnAnnuler.addClickListener(e -> close());
		buttonsLayout.addComponent(btnAnnuler);
		buttonsLayout.setComponentAlignment(btnAnnuler, Alignment.MIDDLE_LEFT);

		btnEnregistrer = new OneClickButton(applicationContext.getMessage("btnSave", null, UI.getCurrent().getLocale()), FontAwesome.SAVE);
		btnEnregistrer.addStyleName(ValoTheme.BUTTON_PRIMARY);		
		btnEnregistrer.addClickListener(e -> {
			try {
				/*Si le code de profil existe dejà --> erreur*/
				if (!pieceJustifController.isCodPjUnique((String) fieldGroup.getField(PieceJustif_.codPj.getName()).getValue(), pieceJustif.getIdPj())){
					Notification.show(applicationContext.getMessage("window.error.cod.nonuniq", null, UI.getCurrent().getLocale()), Type.WARNING_MESSAGE);
					return;
				}	
				/* Valide la saisie */
				fieldGroup.commit();
				/* Enregistre la pieceJustif saisie */
				pieceJustifController.savePieceJustif(pieceJustif);
				/* Ferme la fenêtre */
				close();
			} catch (CommitException ce) {
			}
		});
		buttonsLayout.addComponent(btnEnregistrer);
		buttonsLayout.setComponentAlignment(btnEnregistrer, Alignment.MIDDLE_RIGHT);

		/* Centre la fenêtre */
		center();
	}
}
