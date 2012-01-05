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

package net.sf.dynamicreports.design.base.chart.plot;

import java.awt.Color;

import net.sf.dynamicreports.design.base.style.DRDesignFont;
import net.sf.dynamicreports.design.definition.chart.plot.DRIDesignAxisFormat;
import net.sf.dynamicreports.design.definition.expression.DRIDesignExpression;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class DRDesignAxisFormat implements DRIDesignAxisFormat {
	private DRIDesignExpression labelExpression;
	private DRDesignFont labelFont;
	private Color labelColor;
	private DRDesignFont tickLabelFont;
	private Color tickLabelColor;
	private String tickLabelMask;
	private Boolean verticalTickLabels;
	private Double tickLabelRotation;
	private Color lineColor;
	private DRIDesignExpression rangeMinValueExpression;
	private DRIDesignExpression rangeMaxValueExpression;

	public DRIDesignExpression getLabelExpression() {
		return labelExpression;
	}

	public void setLabelExpression(DRIDesignExpression labelExpression) {
		this.labelExpression = labelExpression;
	}

	public DRDesignFont getLabelFont() {
		return labelFont;
	}

	public void setLabelFont(DRDesignFont labelFont) {
		this.labelFont = labelFont;
	}

	public Color getLabelColor() {
		return labelColor;
	}

	public void setLabelColor(Color labelColor) {
		this.labelColor = labelColor;
	}

	public DRDesignFont getTickLabelFont() {
		return tickLabelFont;
	}

	public void setTickLabelFont(DRDesignFont tickLabelFont) {
		this.tickLabelFont = tickLabelFont;
	}

	public Color getTickLabelColor() {
		return tickLabelColor;
	}

	public void setTickLabelColor(Color tickLabelColor) {
		this.tickLabelColor = tickLabelColor;
	}

	public String getTickLabelMask() {
		return tickLabelMask;
	}

	public void setTickLabelMask(String tickLabelMask) {
		this.tickLabelMask = tickLabelMask;
	}

	public Boolean getVerticalTickLabels() {
		return verticalTickLabels;
	}

	public void setVerticalTickLabels(Boolean verticalTickLabels) {
		this.verticalTickLabels = verticalTickLabels;
	}

	public Double getTickLabelRotation() {
		return tickLabelRotation;
	}

	public void setTickLabelRotation(Double tickLabelRotation) {
		this.tickLabelRotation = tickLabelRotation;
	}

	public Color getLineColor() {
		return lineColor;
	}

	public void setLineColor(Color lineColor) {
		this.lineColor = lineColor;
	}

	public void setRangeMinValueExpression(DRIDesignExpression rangeMinValueExpression) {
		this.rangeMinValueExpression = rangeMinValueExpression;
	}

	public DRIDesignExpression getRangeMinValueExpression() {
		return rangeMinValueExpression;
	}

	public void setRangeMaxValueExpression(DRIDesignExpression rangeMaxValueExpression) {
		this.rangeMaxValueExpression = rangeMaxValueExpression;
	}

	public DRIDesignExpression getRangeMaxValueExpression() {
		return rangeMaxValueExpression;
	}
}
