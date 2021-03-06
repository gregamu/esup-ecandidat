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
package fr.univlorraine.ecandidat.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.vaadin.ui.UI;

import fr.univlorraine.ecandidat.entities.ecandidat.Civilite;
import fr.univlorraine.ecandidat.entities.ecandidat.SiScolAnneeUni;
import fr.univlorraine.ecandidat.entities.ecandidat.SiScolBacOuxEqu;
import fr.univlorraine.ecandidat.entities.ecandidat.SiScolCentreGestion;
import fr.univlorraine.ecandidat.entities.ecandidat.SiScolCommune;
import fr.univlorraine.ecandidat.entities.ecandidat.SiScolDepartement;
import fr.univlorraine.ecandidat.entities.ecandidat.SiScolDipAutCur;
import fr.univlorraine.ecandidat.entities.ecandidat.SiScolEtablissement;
import fr.univlorraine.ecandidat.entities.ecandidat.SiScolMention;
import fr.univlorraine.ecandidat.entities.ecandidat.SiScolMentionNivBac;
import fr.univlorraine.ecandidat.entities.ecandidat.SiScolPays;
import fr.univlorraine.ecandidat.entities.ecandidat.SiScolTypDiplome;
import fr.univlorraine.ecandidat.entities.ecandidat.SiScolTypResultat;
import fr.univlorraine.ecandidat.entities.ecandidat.TypeAvis;
import fr.univlorraine.ecandidat.entities.ecandidat.TypeStatut;
import fr.univlorraine.ecandidat.entities.ecandidat.TypeStatutPiece;
import fr.univlorraine.ecandidat.entities.ecandidat.TypeTraitement;
import fr.univlorraine.ecandidat.repositories.CiviliteRepository;
import fr.univlorraine.ecandidat.repositories.LangueRepository;
import fr.univlorraine.ecandidat.repositories.ParametreRepository;
import fr.univlorraine.ecandidat.repositories.SiScolAnneeUniRepository;
import fr.univlorraine.ecandidat.repositories.SiScolBacOuxEquRepository;
import fr.univlorraine.ecandidat.repositories.SiScolCentreGestionRepository;
import fr.univlorraine.ecandidat.repositories.SiScolCommuneRepository;
import fr.univlorraine.ecandidat.repositories.SiScolDepartementRepository;
import fr.univlorraine.ecandidat.repositories.SiScolDipAutCurRepository;
import fr.univlorraine.ecandidat.repositories.SiScolEtablissementRepository;
import fr.univlorraine.ecandidat.repositories.SiScolMentionNivBacRepository;
import fr.univlorraine.ecandidat.repositories.SiScolMentionRepository;
import fr.univlorraine.ecandidat.repositories.SiScolPaysRepository;
import fr.univlorraine.ecandidat.repositories.SiScolTypDiplomeRepository;
import fr.univlorraine.ecandidat.repositories.SiScolTypResultatRepository;
import fr.univlorraine.ecandidat.repositories.TypeAvisRepository;
import fr.univlorraine.ecandidat.repositories.TypeStatutPieceRepository;
import fr.univlorraine.ecandidat.repositories.TypeStatutRepository;
import fr.univlorraine.ecandidat.repositories.TypeTraitementRepository;
import fr.univlorraine.ecandidat.utils.ConstanteUtils;
import fr.univlorraine.ecandidat.utils.NomenclatureUtils;
import fr.univlorraine.ecandidat.utils.bean.presentation.SimpleBeanPresentation;


/**Gestion des tables ref
 * @author Kevin Hergalant
 */
@Component
public class TableRefController {
	/* Injections */
	@Resource
	private transient ApplicationContext applicationContext;
	@Resource
	private transient CacheController cacheController;
	@Resource
	private transient ParametreController parametreController;
	@Resource
	private transient TypeAvisRepository typeAvisRepository;
	@Resource
	private transient TypeStatutPieceRepository typeStatutPieceRepository;
	@Resource
	private transient TypeStatutRepository typeStatutRepository;	
	@Resource
	private transient TypeTraitementRepository typeTraitementRepository;
	@Resource
	private transient LangueRepository langueRepository;
	@Resource
	private transient CiviliteRepository civiliteRepository;
	@Resource
	private transient ParametreRepository parametreRepository;
	@Resource
	private transient SiScolPaysRepository siScolPaysRepository;
	@Resource
	private transient SiScolDepartementRepository siScolDepartementRepository;
	@Resource
	private transient SiScolEtablissementRepository siScolEtablissementRepository;
	@Resource
	private transient SiScolCommuneRepository siScolCommuneRepository;
	@Resource
	private transient SiScolTypDiplomeRepository siScolTypDiplomeRepository;
	@Resource
	private transient SiScolCentreGestionRepository siScolCentreGestionRepository;
	
	@Resource
	private transient SiScolBacOuxEquRepository siScolBacOuxEquRepository;
	@Resource
	private transient SiScolDipAutCurRepository siScolDipAutCurRepository;
	@Resource
	private transient SiScolMentionNivBacRepository siScolMentionNivBacRepository;
	@Resource
	private transient SiScolMentionRepository siScolMentionRepository;
	@Resource
	private transient SiScolTypResultatRepository siScolTypResultatRepository;	
	@Resource
	private transient SiScolAnneeUniRepository siScolAnneeUniRepository;
	
	/**
	 * @return la liste de types d'avis
	 */
	public List<TypeAvis> getListeTypeAvisToCache() {
		return typeAvisRepository.findAll();
	}
	
	/**
	 * @return la liste de civilité
	 */
	public List<Civilite> getListeCivilteToCache() {
		return civiliteRepository.findAll();
	}
	
	/**
	 * @return la liste de types de statut de pièce
	 */
	public List<TypeStatutPiece> getListeTypeStatutPieceToCache() {
		return typeStatutPieceRepository.findAll();
	}
	
	/**
	 * @return la liste de types de statut
	 */
	public List<TypeStatut> getListeTypeStatutToCache() {
		return typeStatutRepository.findAll();
	}
	
	/**
	 * @return la liste de types de statut de pièce
	 */
	public List<TypeTraitement> getListeTypeTraitementToCache() {
		return typeTraitementRepository.findAll();
	}
	
	/**
	 * @return la liste de types de diplome
	 */
	public List<SiScolTypDiplome> getListeTypDiplomeToCache() {
		return siScolTypDiplomeRepository.findAll();
	}
	
	/**
	 * @return la liste des centres de gestion
	 */
	public List<SiScolCentreGestion> getListeCentreGestionToCache() {
		return siScolCentreGestionRepository.findAll();
	}
	
	
	/** Cherche les année univ valides
	 * @return les années univ valides
	 */
	public List<SiScolAnneeUni> getListeAnneeUnisToCache(){
		return siScolAnneeUniRepository.findAll();
	}
	
	/**
	 * @return la liste des departements apogée
	 */
	public List<SiScolDepartement> getListDepartementToCache(){
		return siScolDepartementRepository.findAll();
	}
	
	/**
	 * @return la liste des bac ou equ
	 */
	public List<SiScolBacOuxEqu> getListeBacOuxEquToCache() {
		return siScolBacOuxEquRepository.findByOrderByLibBacAsc();
	}
	
	/**
	 * @return la liste des dip aut cur
	 */
	public List<SiScolDipAutCur> getListeDipAutCurToCache() {
		return siScolDipAutCurRepository.findByOrderByLibDacAsc();
	}
	
	/**
	 * @return la liste des Mention Niv Bac
	 */
	public List<SiScolMentionNivBac> getListeMentionNivBacToCache() {
		return siScolMentionNivBacRepository.findAll();
	}
	
	/**
	 * @return la liste des Mention
	 */
	public List<SiScolMention> getListeMentionToCache() {
		return siScolMentionRepository.findAll();
	}
	
	/**
	 * @return la liste des Types de resultats
	 */
	public List<SiScolTypResultat> getListeTypeResultatToCache() {
		return siScolTypResultatRepository.findAll();
	}
	
	/**
	 * @return la liste des pays apogée
	 */
	public List<SiScolPays> getListPaysToCache(){
		List<SiScolPays> listeSiScolPays = new ArrayList<SiScolPays>();
		SiScolPays paysFrance = siScolPaysRepository.findByCodPay(ConstanteUtils.PAYS_CODE_FRANCE);
		if (paysFrance!=null){
			listeSiScolPays.add(paysFrance);
		}			
		listeSiScolPays.addAll(siScolPaysRepository.findByCodPayNotOrderByLibPay(ConstanteUtils.PAYS_CODE_FRANCE));
		return listeSiScolPays;
	}
	
	/**
	 * @return la france
	 */
	public SiScolPays getPaysFranceToCache() {
		List<SiScolPays> liste = cacheController.getListePays();
		if (liste!=null && liste.size()>0){
			return getPaysByCode(ConstanteUtils.PAYS_CODE_FRANCE);
		}else{
			return null;
		}
	}

	
	/** 
	 * @return Le type avis favorable
	 */
	public TypeAvis getTypeAvisFavorable(){
		return cacheController.getListeTypeAvis().stream().filter(e->e.getCodTypAvis().equals(NomenclatureUtils.TYP_AVIS_FAV)).findFirst().get();
	}
	
	/** 
	 * @return Le type avis liste completmentairee
	 */
	public TypeAvis getTypeAvisListComp(){
		return cacheController.getListeTypeAvis().stream().filter(e->e.getCodTypAvis().equals(NomenclatureUtils.TYP_AVIS_LISTE_COMP)).findFirst().get();
	}
	
	/** 
	 * @return Le type avis defavorable
	 */ 
	public TypeAvis getTypeAvisDefavorable(){
		return cacheController.getListeTypeAvis().stream().filter(e->e.getCodTypAvis().equals(NomenclatureUtils.TYP_AVIS_DEF)).findFirst().get();
	}
	
	/** 
	 * @return Le type avis preselect
	 */
	public TypeAvis getTypeAvisPreselect(){
		return cacheController.getListeTypeAvis().stream().filter(e->e.getCodTypAvis().equals(NomenclatureUtils.TYP_AVIS_PRESELECTION)).findFirst().get();
	}
	
	/**
	 * @return le type de statut en attente
	 */
	public TypeStatut getTypeStatutEnAttente() {
		return cacheController.getListeTypeStatut().stream().filter(e->e.getCodTypStatut().equals(NomenclatureUtils.TYPE_STATUT_ATT)).findFirst().get();
	}
	
	/**
	 * @return le type de statut complet
	 */
	public TypeStatut getTypeStatutComplet() {
		return cacheController.getListeTypeStatut().stream().filter(e->e.getCodTypStatut().equals(NomenclatureUtils.TYPE_STATUT_COM)).findFirst().get();
	}
	
	/**
	 * @return le type de statut complet
	 */
	public TypeStatut getTypeStatutIncomplet() {
		return cacheController.getListeTypeStatut().stream().filter(e->e.getCodTypStatut().equals(NomenclatureUtils.TYPE_STATUT_INC)).findFirst().get();
	}
	
	/**
	 * @return le type de statut complet
	 */
	public TypeStatut getTypeStatutReceptionne() {
		return cacheController.getListeTypeStatut().stream().filter(e->e.getCodTypStatut().equals(NomenclatureUtils.TYPE_STATUT_REC)).findFirst().get();
	}
	
	/**
	 * @return la liste de types de statut de pièce
	 */
	public List<TypeStatutPiece> getListeTypeStatutPieceActif() {
		return cacheController.getListeTypeStatutPiece().stream().filter(e->!e.getCodTypStatutPiece().equals(NomenclatureUtils.TYP_STATUT_PIECE_ATTENTE) && !e.getCodTypStatutPiece().equals(NomenclatureUtils.TYP_STATUT_PIECE_NON_CONCERNE)).collect(Collectors.toList());
	}
	
	/**
	 * @return le type de statut piece transmis
	 */
	public TypeStatutPiece getTypeStatutPieceTransmis() {
		return cacheController.getListeTypeStatutPiece().stream().filter(e->e.getCodTypStatutPiece().equals(NomenclatureUtils.TYP_STATUT_PIECE_TRANSMIS)).findFirst().get();
	}
	
	/**
	 * @return le type de statut piece validé
	 */
	public TypeStatutPiece getTypeStatutPieceValide() {
		return cacheController.getListeTypeStatutPiece().stream().filter(e->e.getCodTypStatutPiece().equals(NomenclatureUtils.TYP_STATUT_PIECE_VALIDE)).findFirst().get();
	}
	
	/**
	 * @return le type de statut piece validé
	 */
	public TypeStatutPiece getTypeStatutPieceRefuse() {
		return cacheController.getListeTypeStatutPiece().stream().filter(e->e.getCodTypStatutPiece().equals(NomenclatureUtils.TYP_STATUT_PIECE_REFUSE)).findFirst().get();
	}
	
	/**
	 * @return le type de statut piece validé
	 */
	public TypeStatutPiece getTypeStatutPieceAttente() {
		return cacheController.getListeTypeStatutPiece().stream().filter(e->e.getCodTypStatutPiece().equals(NomenclatureUtils.TYP_STATUT_PIECE_ATTENTE)).findFirst().get();
	}
	
	/**
	 * @return le type de statut piece validé
	 */
	public TypeStatutPiece getTypeStatutPieceNonConcerne() {
		return cacheController.getListeTypeStatutPiece().stream().filter(e->e.getCodTypStatutPiece().equals(NomenclatureUtils.TYP_STATUT_PIECE_NON_CONCERNE)).findFirst().get();
	}
	
	/**
	 * @return type de traitement en attente
	 */
	public TypeTraitement getTypeTraitementEnAttente() {
		return cacheController.getListeTypeTraitement().stream().filter(e->e.getCodTypTrait().equals(NomenclatureUtils.TYP_TRAIT_AT)).findFirst().get();
	}
	
	/**
	 * @return type de traitement en acces direct
	 */
	public TypeTraitement getTypeTraitementAccesDirect() {
		return cacheController.getListeTypeTraitement().stream().filter(e->e.getCodTypTrait().equals(NomenclatureUtils.TYP_TRAIT_AD)).findFirst().get();
	}
	
	/**
	 * @return type de traitement en acces controle
	 */
	public TypeTraitement getTypeTraitementAccesControle() {
		return cacheController.getListeTypeTraitement().stream().filter(e->e.getCodTypTrait().equals(NomenclatureUtils.TYP_TRAIT_AC)).findFirst().get();
	}
	
	/** 
	 * @param codPostal
	 * @return la Liste les communes par leur code postal
	 */
	public List<SiScolCommune> listeCommuneByCodePostal(String codPostal){
		List<SiScolCommune> listeCommune = siScolCommuneRepository.getCommuneByCodePostal(codPostal);
		listeCommune.sort((c1,c2)->c1.getLibCom().compareTo(c2.getLibCom()));
		return listeCommune;
	}
	
	/** 
	 * @param siScolDepartement
	 * @return Liste les commune par le code commune et là ou il y a des etablissments
	 */
	public List<SiScolCommune> listeCommuneByDepartement(SiScolDepartement siScolDepartement) {
		List<SiScolCommune> listeCommune = siScolCommuneRepository.getCommuneByDepartement(siScolDepartement.getCodDep());
		listeCommune.sort((c1,c2)->c1.getLibCom().compareTo(c2.getLibCom()));
		return listeCommune;
	}
	
	/**
	 * @param commune
	 * @return  Liste les etablissment par code commune
	 */
	public List<SiScolEtablissement> listeEtablissementByCommuneEnService(SiScolCommune commune) {
		return siScolEtablissementRepository.getEtablissementByCommuneEnService(commune.getCodCom(), true);
	}
	
	/**
	 * @param code
	 * @return le pays equivalent au code
	 */
	public SiScolPays getPaysByCode(String code){
		if (code == null){
			return null;
		}
		List<SiScolPays> liste = cacheController.getListePays();
		if (liste==null || liste.size()==0){
			return null;
		}
		Optional<SiScolPays> fr = liste.stream().filter(e->e.getCodPay().equals(code)).findFirst();
		if (fr.isPresent()){
			return fr.get();
		}else{
			return null;
		}
	}

	/** Renvoie le departement par son code
	 * @param cod
	 * @return le departement par son code
	 */
	public SiScolDepartement getDepartementByCode(String cod) {
		if (cod==null){
			return null;
		}
		List<SiScolDepartement> liste = cacheController.getListDepartement();
		Optional<SiScolDepartement> dep = liste.stream().filter(e->e.getCodDep().equals(cod)).findFirst();
		if (dep.isPresent()){
			return dep.get();
		}else{
			return null;
		}
	}
	
	/**
	 * @param codApo
	 * @return la civilite par son code apogee
	 */
	public Civilite getCiviliteByCodeApo(String codApo){
		if (codApo==null){
			return null;
		}
		List<Civilite> liste = cacheController.getListeCivilte();
		Optional<Civilite> civ = liste.stream().filter(e->e.getCodApo().equals(codApo)).findFirst();
		if (civ.isPresent()){
			return civ.get();
		}else{
			return null;
		}
	}

	/** CHerche la commune par son code postal et son code commune
	 * @param codBdi
	 * @param codCom
	 * @return la commune
	 */
	public SiScolCommune getCommuneByCodePostalAndCodeCom(String codBdi,
			String codCom) {
		if (codBdi==null || codCom==null){
			return null;
		}
		List<SiScolCommune> listeCommuneByCodePostal = listeCommuneByCodePostal(codBdi);
		if (listeCommuneByCodePostal!=null && listeCommuneByCodePostal.size()>0){
			Optional<SiScolCommune> com = listeCommuneByCodePostal.stream().filter(e->e.getCodCom().equals(codCom)).findFirst();
			if (com.isPresent()){
				return com.get();
			}else{
				return null;
			}
		}
		return null;
	}

	/** Cherche l'etablissement par son code
	 * @param codEtb
	 * @return l'etablissement
	 */
	public SiScolEtablissement getEtablissementByCode(String codEtb) {
		if (codEtb==null){
			return null;
		}
		return siScolEtablissementRepository.findOne(codEtb);
	}

	/** Cherche le bac Ou Equ par son code
	 * @param codBac
	 * @return le bac ou equ
	 */
	public SiScolBacOuxEqu getBacOuEquByCode(String codBac) {
		if (codBac==null){
			return null;
		}
		List<SiScolBacOuxEqu> liste = cacheController.getListeBacOuxEqu();
		Optional<SiScolBacOuxEqu> bac = liste.stream().filter(e->e.getCodBac().equals(codBac)).findFirst();
		if (bac.isPresent()){
			return bac.get();
		}else{
			return null;
		}
	}

	/** Cherche la mention niveau bac par son code
	 * @param codMnb
	 * @return la mention niveau bac
	 */
	public SiScolMentionNivBac getMentionNivBacByCode(String codMnb) {
		if (codMnb==null){
			return null;
		}
		List<SiScolMentionNivBac> liste = cacheController.getListeMentionNivBac();
		Optional<SiScolMentionNivBac> mention = liste.stream().filter(e->e.getCodMnb().equals(codMnb)).findFirst();
		if (mention.isPresent()){
			return mention.get();
		}else{
			return null;
		}
	}

	/** Cherche la mention par son code
	 * @param codMen
	 * @return la mention
	 */
	public SiScolMention getMentionByCode(String codMen) {
		if (codMen==null){
			return null;
		}
		List<SiScolMention> liste = cacheController.getListeMention();
		Optional<SiScolMention> mention = liste.stream().filter(e->e.getCodMen().equals(codMen)).findFirst();
		if (mention.isPresent()){
			return mention.get();
		}else{
			return null;
		}
	}	
	
	/** Cherche le type de resultat par son code
	 * @param codTre
	 * @return le type de resultat
	 */
	public SiScolTypResultat getTypeResultatByCode(String codTre) {
		if (codTre==null){
			return null;
		}
		List<SiScolTypResultat> liste = cacheController.getListeTypeResultat();
		Optional<SiScolTypResultat> result = liste.stream().filter(e->e.getCodTre().equals(codTre)).findFirst();
		if (result.isPresent()){
			return result.get();
		}else{
			return null;
		}
	}
	
	/**
	 * @param codCGE
	 * @return le SiScolCentreGestion lie au code CGE
	 */
	public SiScolCentreGestion getSiScolCentreGestionByCode(String codCGE){
		if (codCGE==null){
			return null;
		}
		List<SiScolCentreGestion> liste = cacheController.getListeCentreGestion();
		Optional<SiScolCentreGestion> result = liste.stream().filter(e->e.getCodCge().equals(codCGE)).findFirst();
		if (result.isPresent()){
			return result.get();
		}else{
			return null;
		}
	}
	
	/**
	 * @param codTpd
	 * @return le SiScolTypDiplome lie au code tpd
	 */
	public SiScolTypDiplome getSiScolTypDiplomeByCode(String codTpd){
		if (codTpd==null){
			return null;
		}
		List<SiScolTypDiplome> liste = cacheController.getListeTypDiplome();
		Optional<SiScolTypDiplome> result = liste.stream().filter(e->e.getCodTpdEtb().equals(codTpd)).findFirst();
		if (result.isPresent()){
			return result.get();
		}else{
			return null;
		}
	}
	
	/**
	 * @return la liste de type "obtenu" du cursus externe
	 */
	public List<SimpleBeanPresentation> getListeObtenuCursus(){
		List<SimpleBeanPresentation> liste = new ArrayList<SimpleBeanPresentation>();
		liste.add(new SimpleBeanPresentation(ConstanteUtils.CURSUS_EXTERNE_OBTENU, applicationContext.getMessage("cursusexterne.obtenu.choix.obtenu", null,  UI.getCurrent().getLocale())));
		liste.add(new SimpleBeanPresentation(ConstanteUtils.CURSUS_EXTERNE_NON_OBTENU, applicationContext.getMessage("cursusexterne.obtenu.choix.nonobtenu", null,  UI.getCurrent().getLocale())));
		liste.add(new SimpleBeanPresentation(ConstanteUtils.CURSUS_EXTERNE_EN_COURS, applicationContext.getMessage("cursusexterne.obtenu.choix.encours", null,  UI.getCurrent().getLocale())));
		return liste;
	}
	
	/**
	 * @param code
	 * @return le libelle du "obtenu" du cursus externe
	 */
	public String getLibelleObtenuCursusByCode(String code){
		if (code == null){
			return null;
		}else if (code.equals(ConstanteUtils.CURSUS_EXTERNE_OBTENU)){
			return applicationContext.getMessage("cursusexterne.obtenu.choix.obtenu.lib", null,  UI.getCurrent().getLocale());
		}else if (code.equals(ConstanteUtils.CURSUS_EXTERNE_NON_OBTENU)){
			return applicationContext.getMessage("cursusexterne.obtenu.choix.nonobtenu.lib", null,  UI.getCurrent().getLocale());
		}else if (code.equals(ConstanteUtils.CURSUS_EXTERNE_EN_COURS)){
			return applicationContext.getMessage("cursusexterne.obtenu.choix.encours.lib", null,  UI.getCurrent().getLocale());
		}
		return null;
	}
	
	/**
	 * @return le bac 'sans bac'
	 */
	public SiScolBacOuxEqu getBacNoBac(){
		String codeSansBac = parametreController.getSiscolCodeSansBac();
		if (codeSansBac!=null && !codeSansBac.equals("")){
			Optional<SiScolBacOuxEqu> bacOpt = cacheController.getListeBacOuxEqu().stream().filter(e->e.getCodBac().equals(codeSansBac)).findFirst();
			if (bacOpt.isPresent()){
				return bacOpt.get();
			}
		}
		return null;
	}
}
