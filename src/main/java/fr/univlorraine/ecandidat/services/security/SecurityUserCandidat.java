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
package fr.univlorraine.ecandidat.services.security;

import java.util.Collection;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.springframework.security.core.GrantedAuthority;

/**
 * La classe utilisateur candidat de l'application
 * @author Kevin Hergalant
 *
 */
@Data
@EqualsAndHashCode(callSuper=true)
public class SecurityUserCandidat extends SecurityUser{
	
	/*** serialVersionUID */
	private static final long serialVersionUID = -2172536978450199780L;
	
	private Integer idCptMin;
	private Integer idCandidat;
	private String noDossierOPI;
	private Boolean cptMinValid;
	private String codLangue;
	private Boolean mailValid;

	public SecurityUserCandidat(String username,String displayName,Collection<? extends GrantedAuthority> authorities, Integer idCptMin, String noDossierOPI, Boolean cptMinValid, Boolean mailValid, String codLangue) {
		super(username, displayName, authorities);
		this.idCptMin = idCptMin;
		this.noDossierOPI = noDossierOPI;
		this.cptMinValid = cptMinValid;
		this.mailValid = mailValid;
		this.codLangue = codLangue;
	}
}
