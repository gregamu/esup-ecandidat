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
package fr.univlorraine.ecandidat.services.file;

import java.io.InputStream;
import java.io.Serializable;

import fr.univlorraine.ecandidat.entities.ecandidat.Candidature;
import fr.univlorraine.ecandidat.entities.ecandidat.Fichier;
import fr.univlorraine.ecandidat.utils.ByteArrayInOutStream;

public interface FileManager extends Serializable{
	
	/**
	 * @return le type de dematerialisation
	 */
	public String getType();
	
	/**
	 * test le mode de dematerialisation
	 */
	public Boolean testSession();

	/**Creéé un fichier provenant d'une fenetre d'upload
	 * @param file
	 * @param mimeType
	 * @param filename
	 * @param length
	 * @param typeFichier
	 * @param prefixe
	 * @param candidature
	 * @param commune
	 * @return le fichier
	 * @throws FileException
	 */
	public FileCustom createFileFromUpload(ByteArrayInOutStream file, String mimeType, String filename, long length, String typeFichier, String prefixe, Candidature candidature, Boolean commune) throws FileException;


	/** Supprime un fichier
	 * @param fichier
	 * @param sendErrorLog si une erreur est loguée
	 * @throws FileException
	 */
	public void deleteFile(Fichier fichier, Boolean sendErrorLog) throws FileException;

	/** Recupere un flux permettant de telecharger un fichier
	 * @param file
	 * @return l'InputStream du fichier
	 * @throws FileException
	 */
	public InputStream getInputStreamFromFile(Fichier file, Boolean logAction) throws FileException;
	
	/**
	 * @param file
	 * @return true si le fichier exist
	 * @throws FileException
	 */
	public Boolean existFile(Fichier file) throws FileException;

	/** Supprime le dossier de la campagne
	 * @param codCampagne
	 * @return true si ok
	 */
	public Boolean deleteCampagneFolder(String codCampagne);
	
}
