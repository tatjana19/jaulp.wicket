/**
 * Copyright (C) 2010 Asterios Raptis
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.alpharogroup.wicket.components.sign.in.password.change;

import java.io.Serializable;

/**
 * The Class ChangePasswordModel captures the data for change the password of a user.
 */
public class ChangePasswordModel implements Serializable
{

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/** The current password of a user. */
	private String currentPassword = "";

	/** The new password of a user. */
	private String newPassword = "";

	/** The repeated new password of a user. */
	private String repeatNewPassword = "";

	/**
	 * Gets the current password of a user.
	 *
	 * @return the current password of a user.
	 */
	public String getCurrentPassword()
	{
		return currentPassword;
	}

	/**
	 * Gets the new password of a user.
	 *
	 * @return the new password of a user
	 */
	public String getNewPassword()
	{
		return newPassword;
	}

	/**
	 * Gets the repeated new password of a user.
	 *
	 * @return the repeated new password of a user.
	 */
	public String getRepeatNewPassword()
	{
		return repeatNewPassword;
	}

	/**
	 * Sets the current password of a user.
	 *
	 * @param currentPassword
	 *            the new current password of a user.
	 */
	public void setCurrentPassword(final String currentPassword)
	{
		this.currentPassword = currentPassword;
	}

	/**
	 * Sets the new password of a user.
	 *
	 * @param newPassword
	 *            the new new password of a user.
	 */
	public void setNewPassword(final String newPassword)
	{
		this.newPassword = newPassword;
	}

	/**
	 * Sets the repeated new password of a user.
	 *
	 * @param repeatNewPassword
	 *            the new repeated new password of a user.
	 */
	public void setRepeatNewPassword(final String repeatNewPassword)
	{
		this.repeatNewPassword = repeatNewPassword;
	}

}
