/**
 * DynamicReports - Free Java reporting library for creating reports dynamically
 *
 * Copyright (C) 2010 - 2012 Ricardo Mariaca
 * http://dynamicreports.sourceforge.net
 *
 * This file is part of DynamicReports.
 *
 * DynamicReports is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * DynamicReports is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with DynamicReports. If not, see <http://www.gnu.org/licenses/>.
 */

package net.sf.dynamicreports.adhoc.configuration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class AdhocFilter implements Cloneable, Serializable {
	private static final long serialVersionUID = 1L;

	private List<AdhocRestriction> restrictions;

	public AdhocFilter() {
		restrictions = new ArrayList<AdhocRestriction>();
	}

	public List<AdhocRestriction> getRestrictions() {
		return restrictions;
	}

	public void addRestriction(AdhocRestriction restriction) {
		this.restrictions.add(restriction);
	}

	public void setRestrictions(List<AdhocRestriction> restrictions) {
		this.restrictions = restrictions;
	}

	@Override
	public boolean equals(Object obj) {
		boolean equals = super.equals(obj);
		if (!equals) {
			return false;
		}
		if (obj == null)
			return false;
		if (!(obj instanceof AdhocFilter))
			return false;

		AdhocFilter object = (AdhocFilter) obj;
		if (!(restrictions == null ? object.getRestrictions() == null : restrictions.equals(object.getRestrictions()))) {
			return false;
		}

		return true;
	}

	@Override
	public AdhocFilter clone() {
		AdhocFilter clone;
		try {
			clone = (AdhocFilter) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException(e);
		}

		if (restrictions != null) {
			clone.restrictions = new ArrayList<AdhocRestriction>();
			for (AdhocRestriction adhocRestriction : restrictions) {
				clone.addRestriction(adhocRestriction.clone());
			}
		}

		return clone;
	}

}
