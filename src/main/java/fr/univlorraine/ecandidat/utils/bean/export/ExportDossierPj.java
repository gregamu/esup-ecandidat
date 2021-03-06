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
package fr.univlorraine.ecandidat.utils.bean.export;

import java.io.Serializable;

import fr.univlorraine.ecandidat.utils.MethodUtils;
import lombok.Data;

/** 
 * Ojet de PJ formatté pour l'export
 * @author Kevin Hergalant
 *
 */
@Data
public class ExportDossierPj implements Serializable {
	/**serialVersionUID**/
	private static final long serialVersionUID = -4234330768123564863L;

	private String libelle;
	private String statut;
	private String comment;
	private String libFichier;
	
	public ExportDossierPj(String libelle, String statut, String comment) {
		super();
		this.libelle = MethodUtils.formatToExport(libelle);
		this.statut = MethodUtils.formatToExport(statut);
		this.comment = MethodUtils.formatToExport(comment);
		this.libFichier = "";
	}

	public ExportDossierPj() {
		super();
	}
	
}
