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
package fr.univlorraine.ecandidat.services.ldap;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Entité Ldap : People
 */
@Data @EqualsAndHashCode(of="uid")
public class PeopleLdap implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 430282979875502022L;

	private String[] objectClass;
	private String displayName;
	private String sn;
	private String cn;
	private String uid;
	private String mail;
	private String supannEtuId;
	private String supannCivilite;
	private String givenName;

	public PeopleLdap(){}

	public PeopleLdap(String uid, String displayName, String sn, String cn,
			String mail, String supannEtuId, String supannCivilite,
			String givenName) {
		super();
		this.uid = uid;
		this.displayName = displayName;
		this.sn = sn;
		this.cn = cn;		
		this.mail = mail;
		this.supannEtuId = supannEtuId;
		this.supannCivilite = supannCivilite;
		this.givenName = givenName;
	}
	
	

}
