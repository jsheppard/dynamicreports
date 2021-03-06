/**
 * DynamicReports - Free Java reporting library for creating reports dynamically
 *
 * Copyright (C) 2010 - 2016 Ricardo Mariaca
 * http://www.dynamicreports.org
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

package net.sf.dynamicreports.design.base.barcode;

import net.sf.dynamicreports.design.definition.barcode.DRIDesignPostnetBarcode;
import net.sf.dynamicreports.report.constant.BarcodeBaselinePosition;
import net.sf.dynamicreports.report.constant.Constants;

/**
 * @author Ricardo Mariaca (r.mariaca@dynamicreports.org)
 */
public class DRDesignPostnetBarcode extends DRDesignChecksumBarcode implements DRIDesignPostnetBarcode {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

	private Boolean displayChecksum;
	private Double shortBarHeight;
	private BarcodeBaselinePosition baselinePosition;
	private Double intercharGapWidth;

	public DRDesignPostnetBarcode() {
		super("POSTNET");
	}

	@Override
	public Boolean getDisplayChecksum() {
		return displayChecksum;
	}

	public void setDisplayChecksum(Boolean displayChecksum) {
		this.displayChecksum = displayChecksum;
	}

	@Override
	public Double getShortBarHeight() {
		return shortBarHeight;
	}

	public void setShortBarHeight(Double shortBarHeight) {
		this.shortBarHeight = shortBarHeight;
	}

	@Override
	public BarcodeBaselinePosition getBaselinePosition() {
		return baselinePosition;
	}

	public void setBaselinePosition(BarcodeBaselinePosition baselinePosition) {
		this.baselinePosition = baselinePosition;
	}

	@Override
	public Double getIntercharGapWidth() {
		return intercharGapWidth;
	}

	public void setIntercharGapWidth(Double intercharGapWidth) {
		this.intercharGapWidth = intercharGapWidth;
	}
}
