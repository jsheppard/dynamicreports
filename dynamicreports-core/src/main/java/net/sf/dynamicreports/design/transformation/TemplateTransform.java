/**
 * DynamicReports - Free Java reporting library for creating reports dynamically
 *
 * Copyright (C) 2010 Ricardo Mariaca
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

package net.sf.dynamicreports.design.transformation;

import java.awt.Color;
import java.util.List;
import java.util.Locale;

import net.sf.dynamicreports.design.base.component.DRDesignList;
import net.sf.dynamicreports.design.base.style.DRDesignStyle;
import net.sf.dynamicreports.design.constant.DefaultStyleType;
import net.sf.dynamicreports.design.definition.DRIDesignPage;
import net.sf.dynamicreports.design.exception.DRDesignReportException;
import net.sf.dynamicreports.report.constant.CrosstabTotalPosition;
import net.sf.dynamicreports.report.constant.GroupHeaderLayout;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.constant.PageOrientation;
import net.sf.dynamicreports.report.constant.SplitType;
import net.sf.dynamicreports.report.constant.TimePeriod;
import net.sf.dynamicreports.report.constant.WhenNoDataType;
import net.sf.dynamicreports.report.defaults.Defaults;
import net.sf.dynamicreports.report.definition.DRIBand;
import net.sf.dynamicreports.report.definition.DRIColumn;
import net.sf.dynamicreports.report.definition.DRIGroup;
import net.sf.dynamicreports.report.definition.DRIMargin;
import net.sf.dynamicreports.report.definition.DRIReport;
import net.sf.dynamicreports.report.definition.DRIReportTemplate;
import net.sf.dynamicreports.report.definition.DRITemplateDesign;
import net.sf.dynamicreports.report.definition.DRIValueColumn;
import net.sf.dynamicreports.report.definition.barcode.DRIBarcode;
import net.sf.dynamicreports.report.definition.chart.DRIChart;
import net.sf.dynamicreports.report.definition.chart.dataset.DRICategoryDataset;
import net.sf.dynamicreports.report.definition.chart.dataset.DRITimeSeriesDataset;
import net.sf.dynamicreports.report.definition.chart.plot.DRIPlot;
import net.sf.dynamicreports.report.definition.component.DRIBreak;
import net.sf.dynamicreports.report.definition.component.DRIComponent;
import net.sf.dynamicreports.report.definition.component.DRIDimensionComponent;
import net.sf.dynamicreports.report.definition.component.DRIFiller;
import net.sf.dynamicreports.report.definition.component.DRIGenericElement;
import net.sf.dynamicreports.report.definition.component.DRIImage;
import net.sf.dynamicreports.report.definition.component.DRILine;
import net.sf.dynamicreports.report.definition.component.DRIList;
import net.sf.dynamicreports.report.definition.component.DRIPageXofY;
import net.sf.dynamicreports.report.definition.component.DRISubreport;
import net.sf.dynamicreports.report.definition.component.DRITextField;
import net.sf.dynamicreports.report.definition.crosstab.DRICrosstab;
import net.sf.dynamicreports.report.definition.crosstab.DRICrosstabColumnGroup;
import net.sf.dynamicreports.report.definition.crosstab.DRICrosstabRowGroup;
import net.sf.dynamicreports.report.definition.expression.DRIValueFormatter;
import net.sf.dynamicreports.report.definition.style.DRISimpleStyle;
import net.sf.dynamicreports.report.definition.style.DRIStyle;
import net.sf.dynamicreports.report.exception.DRException;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class TemplateTransform {
	private DRIReport report;
	private DesignTransformAccessor accessor;
	private DRIReportTemplate template;
	private DRITemplateDesign<?> templateDesign;

	public TemplateTransform(DesignTransformAccessor accessor) {
		this.accessor = accessor;
		this.report = accessor.getReport();
		this.template = report.getTemplate();
		this.templateDesign = report.getTemplateDesign();
	}

	public Locale getLocale() {
		if (report.getLocale() != null) {
			return report.getLocale();
		}
		if (template.getLocale() != null) {
			return template.getLocale();
		}
		return Defaults.getDefaults().getLocale();
	}

	protected boolean isShowColumnTitle() {
		if (report.getShowColumnTitle() != null) {
			return report.getShowColumnTitle();
		}
		if (template.getShowColumnTitle() != null) {
			return template.getShowColumnTitle();
		}
		return Defaults.getDefaults().isShowColumnTitle();
	}

	public String getResourceBundleName() {
		if (report.getResourceBundleName() != null) {
			return report.getResourceBundleName();
		}
		if (templateDesign.getResourceBundleName() != null) {
			return templateDesign.getResourceBundleName();
		}
		return null;
	}

	public boolean isIgnorePagination() {
		if (report.getIgnorePagination() != null) {
			return report.getIgnorePagination();
		}
		if (templateDesign.getIgnorePagination() != null) {
			return templateDesign.getIgnorePagination();
		}
		if (template.getIgnorePagination() != null) {
			return template.getIgnorePagination();
		}
		return Defaults.getDefaults().isIgnorePagination();
	}

	public WhenNoDataType getWhenNoDataType() {
		if (report.getWhenNoDataType() != null) {
			return report.getWhenNoDataType();
		}
		if (templateDesign.getWhenNoDataType() != null) {
			return templateDesign.getWhenNoDataType();
		}
		if (template.getWhenNoDataType() != null) {
			return template.getWhenNoDataType();
		}
		return Defaults.getDefaults().getWhenNoDataType();
	}

	public boolean isTitleOnANewPage() {
		if (report.getTitleOnANewPage() != null) {
			return report.getTitleOnANewPage();
		}
		if (templateDesign.getTitleOnANewPage() != null) {
			return templateDesign.getTitleOnANewPage();
		}
		if (template.getTitleOnANewPage() != null) {
			return template.getTitleOnANewPage();
		}
		return Defaults.getDefaults().isTitleOnANewPage();
	}

	public boolean isSummaryOnANewPage() {
		if (report.getSummaryOnANewPage() != null) {
			return report.getSummaryOnANewPage();
		}
		if (templateDesign.getSummaryOnANewPage() != null) {
			return templateDesign.getSummaryOnANewPage();
		}
		if (template.getSummaryOnANewPage() != null) {
			return template.getSummaryOnANewPage();
		}
		return Defaults.getDefaults().isSummaryOnANewPage();
	}

	public boolean isSummaryWithPageHeaderAndFooter() {
		if (report.getSummaryWithPageHeaderAndFooter() != null) {
			return report.getSummaryWithPageHeaderAndFooter();
		}
		if (templateDesign.getSummaryWithPageHeaderAndFooter() != null) {
			return templateDesign.getSummaryWithPageHeaderAndFooter();
		}
		if (template.getSummaryWithPageHeaderAndFooter() != null) {
			return template.getSummaryWithPageHeaderAndFooter();
		}
		return Defaults.getDefaults().isSummaryWithPageHeaderAndFooter();
	}

	public boolean isFloatColumnFooter() {
		if (report.getFloatColumnFooter() != null) {
			return report.getFloatColumnFooter();
		}
		if (templateDesign.getFloatColumnFooter() != null) {
			return templateDesign.getFloatColumnFooter();
		}
		if (template.getFloatColumnFooter() != null) {
			return template.getFloatColumnFooter();
		}
		return Defaults.getDefaults().isFloatColumnFooter();
	}

	//style
	protected DRISimpleStyle getDetailOddRowStyle() {
		if (isHighlightDetailOddRows()) {
			if (report.getDetailOddRowStyle() != null) {
				return report.getDetailOddRowStyle();
			}
			if (template.getDetailOddRowStyle() != null) {
				return template.getDetailOddRowStyle();
			}
			return Defaults.getDefaults().getDetailOddRowStyle();
		}
		return null;
	}

	protected DRISimpleStyle getDetailEvenRowStyle() {
		if (isHighlightDetailEvenRows()) {
			if (report.getDetailEvenRowStyle() != null) {
				return report.getDetailEvenRowStyle();
			}
			if (template.getDetailEvenRowStyle() != null) {
				return template.getDetailEvenRowStyle();
			}
			return Defaults.getDefaults().getDetailEvenRowStyle();
		}
		return null;
	}

	private boolean isHighlightDetailOddRows() {
		if (report.getHighlightDetailOddRows() != null) {
			return report.getHighlightDetailOddRows();
		}
		if (template.getHighlightDetailOddRows() != null) {
			return template.getHighlightDetailOddRows();
		}
		return Defaults.getDefaults().isHighlightDetailOddRows();
	}

	private boolean isHighlightDetailEvenRows() {
		if (report.getHighlightDetailEvenRows() != null) {
			return report.getHighlightDetailEvenRows();
		}
		if (template.getHighlightDetailEvenRows() != null) {
			return template.getHighlightDetailEvenRows();
		}
		return Defaults.getDefaults().isHighlightDetailEvenRows();
	}

	protected DRIStyle getTextStyle() {
		if (report.getTextStyle() != null) {
			return report.getTextStyle();
		}
		if (template.getTextStyle() != null) {
			return template.getTextStyle();
		}
		return Defaults.getDefaults().getTextStyle();
	}

	protected DRIStyle getColumnTitleStyle() {
		if (report.getColumnTitleStyle() != null) {
			return report.getColumnTitleStyle();
		}
		if (template.getColumnTitleStyle() != null) {
			return template.getColumnTitleStyle();
		}
		if (Defaults.getDefaults().getColumnTitleStyle() != null) {
			return Defaults.getDefaults().getColumnTitleStyle();
		}
		return getTextStyle();
	}

	protected DRIStyle getColumnStyle() {
		if (report.getColumnStyle() != null) {
			return report.getColumnStyle();
		}
		if (template.getColumnStyle() != null) {
			return template.getColumnStyle();
		}
		if (Defaults.getDefaults().getColumnStyle() != null) {
			return Defaults.getDefaults().getColumnStyle();
		}
		return getTextStyle();
	}

	protected DRIStyle getGroupTitleStyle() {
		if (report.getGroupTitleStyle() != null) {
			return report.getGroupTitleStyle();
		}
		if (template.getGroupTitleStyle() != null) {
			return template.getGroupTitleStyle();
		}
		if (Defaults.getDefaults().getGroupTitleStyle() != null) {
			return Defaults.getDefaults().getGroupTitleStyle();
		}
		return getTextStyle();
	}

	protected DRIStyle getGroupStyle() {
		if (report.getGroupStyle() != null) {
			return report.getGroupStyle();
		}
		if (template.getGroupStyle() != null) {
			return template.getGroupStyle();
		}
		if (Defaults.getDefaults().getGroupStyle() != null) {
			return Defaults.getDefaults().getGroupStyle();
		}
		return getTextStyle();
	}

	protected DRIStyle getSubtotalStyle() {
		if (report.getSubtotalStyle() != null) {
			return report.getSubtotalStyle();
		}
		if (template.getSubtotalStyle() != null) {
			return template.getSubtotalStyle();
		}
		if (Defaults.getDefaults().getSubtotalStyle() != null) {
			return Defaults.getDefaults().getSubtotalStyle();
		}
		return getTextStyle();
	}

	protected DRIStyle getImageStyle() {
		if (report.getImageStyle() != null) {
			return report.getImageStyle();
		}
		if (template.getImageStyle() != null) {
			return template.getImageStyle();
		}
		return Defaults.getDefaults().getImageStyle();
	}

	protected DRIStyle getChartStyle() {
		if (report.getChartStyle() != null) {
			return report.getChartStyle();
		}
		if (template.getChartStyle() != null) {
			return template.getChartStyle();
		}
		return Defaults.getDefaults().getChartStyle();
	}

	protected DRIStyle getBarcodeStyle() {
		if (report.getBarcodeStyle() != null) {
			return report.getBarcodeStyle();
		}
		if (template.getBarcodeStyle() != null) {
			return template.getBarcodeStyle();
		}
		return Defaults.getDefaults().getBarcodeStyle();
	}

	//page
	protected int getPageWidth() throws DRException {
		if (accessor.getPageWidth() != null) {
			return pageWidth(accessor.getPageWidth());
		}
		if (report.getPage().getWidth() != null) {
			return pageWidth(report.getPage().getWidth());
		}
		if (templateDesign.getPageWidth() != null) {
			return templateDesign.getPageWidth();
		}
		if (template.getPageWidth() != null) {
			return template.getPageWidth();
		}
		return Defaults.getDefaults().getPageWidth();
	}

	private int pageWidth(int width) throws DRException {
		if (templateDesign.getPageWidth() != null && templateDesign.getPageWidth() != width) {
			throw new DRException("Page width must not be different from page width of template design");
		}
		return width;
	}

	protected int getPageHeight() throws DRException {
		if (report.getPage().getHeight() != null) {
			Integer height = report.getPage().getHeight();
			if (templateDesign.getPageHeight() != null && templateDesign.getPageHeight() != height) {
				throw new DRException("Page height must not be different from page height of template design");
			}
			return height;
		}
		if (templateDesign.getPageHeight() != null) {
			return templateDesign.getPageHeight();
		}
		if (template.getPageHeight() != null) {
			return template.getPageHeight();
		}
		return Defaults.getDefaults().getPageHeight();
	}

	protected PageOrientation getPageOrientation() throws DRException {
		if (report.getPage().getOrientation() != null) {
			PageOrientation orientation = report.getPage().getOrientation();
			if (templateDesign.getPageOrientation() != null && templateDesign.getPageOrientation() != orientation) {
				throw new DRException("Page orientation must not be different from page orientation of template design");
			}
			return orientation;
		}
		if (templateDesign.getPageOrientation() != null) {
			return templateDesign.getPageOrientation();
		}
		if (template.getPageOrientation() != null) {
			return template.getPageOrientation();
		}
		return Defaults.getDefaults().getPageOrientation();
	}

	protected DRIMargin getPageMargin() throws DRException {
		if (report.getPage().getMargin() != null) {
			DRIMargin margin = report.getPage().getMargin();
			if (templateDesign.getPageMargin() != null) {
				DRIMargin templateMargin = templateDesign.getPageMargin();
				if (templateMargin.getLeft() != margin.getLeft()) {
					throw new DRException("Page left margin must not be different from page left margin of template design");
				}
				if (templateMargin.getRight() != margin.getRight()) {
					throw new DRException("Page right margin must not be different from page right margin of template design");
				}
				if (templateMargin.getTop() != margin.getTop()) {
					throw new DRException("Page top margin must not be different from page top margin of template design");
				}
				if (templateMargin.getBottom() != margin.getBottom()) {
					throw new DRException("Page bottom margin must not be different from page bottom margin of template design");
				}
			}
			return margin;
		}
		if (templateDesign.getPageMargin() != null) {
			return templateDesign.getPageMargin();
		}
		if (template.getPageMargin() != null) {
			return template.getPageMargin();
		}
		return Defaults.getDefaults().getPageMargin();
	}

	protected int getPageColumnsPerPage() throws DRException {
		if (report.getPage().getColumnsPerPage() != null) {
			Integer columnsPerPage = report.getPage().getColumnsPerPage();
			if (templateDesign.getPageColumnsPerPage() != null && templateDesign.getPageColumnsPerPage() != columnsPerPage) {
				throw new DRException("Page columns per page must not be different from page column count of template design");
			}
			return columnsPerPage;
		}
		if (templateDesign.getPageColumnsPerPage() != null) {
			return templateDesign.getPageColumnsPerPage();
		}
		if (template.getPageColumnsPerPage() != null) {
			return template.getPageColumnsPerPage();
		}
		return Defaults.getDefaults().getPageColumnsPerPage();
	}

	protected int getPageColumnSpace() throws DRException {
		if (report.getPage().getColumnSpace() != null) {
			Integer columnSpace = report.getPage().getColumnSpace();
			if (templateDesign.getPageColumnSpace() != null && templateDesign.getPageColumnSpace() != columnSpace) {
				throw new DRException("Page column space must not be different from page column spacing of template design");
			}
			return columnSpace;
		}
		if (templateDesign.getPageColumnSpace() != null) {
			return templateDesign.getPageColumnSpace();
		}
		if (template.getPageColumnSpace() != null) {
			return template.getPageColumnSpace();
		}
		return Defaults.getDefaults().getPageColumnSpace();
	}

	protected int getPageColumnWidth(DRIDesignPage page) {
		if (templateDesign.getPageColumnWidth() != null) {
			return templateDesign.getPageColumnWidth();
		}
		int columnWidth = page.getWidth() - page.getMargin().getLeft() - page.getMargin().getRight();
		columnWidth -= page.getColumnSpace() * (page.getColumnsPerPage() - 1);
		columnWidth = columnWidth / page.getColumnsPerPage();
		return columnWidth;
	}

	//column
	protected boolean isColumnPrintRepeatedDetailValues(DRIValueColumn<?> column) {
		if (column.getPrintRepeatedDetailValues() != null) {
			return column.getPrintRepeatedDetailValues();
		}
		if (template.getColumnPrintRepeatedDetailValues() != null) {
			return template.getColumnPrintRepeatedDetailValues();
		}
		return Defaults.getDefaults().isColumnPrintRepeatedDetailValues();
	}

	protected int getColumnWidth(DRIColumn<?> column, DRDesignStyle style) throws DRException {
		DRIComponent component = column.getComponent();
		if (component instanceof DRIDimensionComponent) {
			if (((DRIDimensionComponent) component).getWidth() != null) {
				return ((DRIDimensionComponent) component).getWidth();
			}
			if (component instanceof DRITextField<?>) {
				if (((DRITextField<?>) component).getColumns() != null) {
					return StyleResolver.getFontWidth(style, ((DRITextField<?>) component).getColumns());
				}
			}
		}
		else if (component instanceof DRIList) {
			DRDesignList list = accessor.getComponentTransform().list((DRIList) component, DefaultStyleType.COLUMN, null, null);
			ComponentPosition.width(list);
			return list.getWidth();
		}
		else {
			throw new DRDesignReportException("Component " + component.getClass().getName() + " not supported");
		}
		if (template.getColumnWidth() != null) {
			return template.getColumnWidth();
		}
		return Defaults.getDefaults().getColumnWidth();
	}

	//group
	protected GroupHeaderLayout getGroupHeaderLayout(DRIGroup group) {
		if (group.getHeaderLayout() != null) {
			return group.getHeaderLayout();
		}
		if (template.getGroupHeaderLayout() != null) {
			return template.getGroupHeaderLayout();
		}
		return Defaults.getDefaults().getGroupHeaderLayout();
	}

	protected boolean isGroupHideColumn(DRIGroup group) {
		if (group.getHideColumn() != null) {
			return group.getHideColumn();
		}
		if (template.getGroupHideColumn() != null) {
			return template.getGroupHideColumn();
		}
		return Defaults.getDefaults().isGroupHideColumn();
	}

	protected boolean isGroupShowColumnHeaderAndFooter(DRIGroup group) {
		if (group.getShowColumnHeaderAndFooter() != null) {
			return group.getShowColumnHeaderAndFooter();
		}
		if (template.getGroupShowColumnHeaderAndFooter() != null) {
			return template.getGroupShowColumnHeaderAndFooter();
		}
		return Defaults.getDefaults().isGroupShowColumnHeaderAndFooter();
	}

	protected int getGroupPadding(DRIGroup group) {
		if (group.getPadding() != null) {
			return group.getPadding();
		}
		if (template.getGroupPadding() != null) {
			return template.getGroupPadding();
		}
		return Defaults.getDefaults().getGroupPadding();
	}

	protected boolean isGroupStartInNewPage(DRIGroup group) {
		if (group.getStartInNewPage() != null) {
			return group.getStartInNewPage();
		}
		if (template.getGroupStartInNewPage() != null) {
			return template.getGroupStartInNewPage();
		}
		return Defaults.getDefaults().isGroupStartInNewPage();
	}

	protected boolean isGroupStartInNewColumn(DRIGroup group) {
		if (group.getStartInNewColumn() != null) {
			return group.getStartInNewColumn();
		}
		if (template.getGroupStartInNewColumn() != null) {
			return template.getGroupStartInNewColumn();
		}
		return Defaults.getDefaults().isGroupStartInNewColumn();
	}

	protected boolean isGroupReprintHeaderOnEachPage(DRIGroup group) {
		if (group.getReprintHeaderOnEachPage() != null) {
			return group.getReprintHeaderOnEachPage();
		}
		if (template.getGroupReprintHeaderOnEachPage() != null) {
			return template.getGroupReprintHeaderOnEachPage();
		}
		return Defaults.getDefaults().isGroupReprintHeaderOnEachPage();
	}

	protected boolean isGroupByDataType(DRIGroup group) {
		if (group.getGroupByDataType() != null) {
			return group.getGroupByDataType();
		}
		return Defaults.getDefaults().isGroupByDataType();
	}

	//text field
	protected int getTextFieldWidth(DRITextField<?> textField, DRDesignStyle style) {
		if (textField.getWidth() != null) {
			return textField.getWidth();
		}
		if (textField.getColumns() != null) {
			return StyleResolver.getFontWidth(style, textField.getColumns());
		}
		if (template.getTextFieldWidth() != null) {
			return template.getTextFieldWidth();
		}
		return Defaults.getDefaults().getTextFieldWidth();
	}

	protected int getTextFieldHeight(DRITextField<?> textField, DRDesignStyle style) {
		if (textField.getHeight() != null) {
			return textField.getHeight();
		}
		if (textField.getRows() != null) {
			return StyleResolver.getFontHeight(style, textField.getRows());
		}
		return StyleResolver.getFontHeight(style, 1);
	}

	protected String getTextFieldPattern(DRITextField<?> textField, DRDesignStyle style) {
		if (textField.getPattern() != null) {
			return textField.getPattern();
		}
		if (StyleResolver.getPattern(style) != null) {
			return null;//StyleResolver.getPattern(style);
		}
		if (textField.getDataType() != null) {
			return textField.getDataType().getPattern();
		}
		return null;
	}

	protected HorizontalAlignment getTextFieldHorizontalAlignment(DRITextField<?> textField, DRDesignStyle style) {
		if (textField.getHorizontalAlignment() != null) {
			return textField.getHorizontalAlignment();
		}
		if (StyleResolver.getHorizontalAlignment(style) != null) {
			return null;//StyleResolver.getHorizontalAlignment(style);
		}
		if (textField.getDataType() != null) {
			return textField.getDataType().getHorizontalAlignment();
		}
		return null;
	}

	protected DRIValueFormatter<?, ?> getTextFieldValueFormatter(DRITextField<?> textField) {
		if (textField.getValueFormatter() != null) {
			return textField.getValueFormatter();
		}
		if (textField.getDataType() != null) {
			return textField.getDataType().getValueFormatter();
		}
		return null;
	}

	protected boolean getTextFieldStretchWithOverflow(DRITextField<?> textField) {
		if (textField.getStretchWithOverflow() != null) {
			return textField.getStretchWithOverflow();
		}
		return Defaults.getDefaults().isTextFieldStretchWithOverflow();
	}

	//page x of y
	protected int getPageXofYWidth(DRIPageXofY pageXofY, DRDesignStyle style) {
		if (pageXofY.getWidth() != null) {
			return pageXofY.getWidth();
		}
		if (template.getTextFieldWidth() != null) {
			return template.getTextFieldWidth();
		}
		return Defaults.getDefaults().getTextFieldWidth();
	}

	protected int getPageXofYHeight(DRIPageXofY pageXofY, DRDesignStyle style) {
		if (pageXofY.getHeight() != null) {
			return pageXofY.getHeight();
		}
		return StyleResolver.getFontHeight(style, 1);
	}

	protected HorizontalAlignment getPageXofYHorizontalAlignment(DRIPageXofY pageXofY, DRDesignStyle style) {
		if (pageXofY.getHorizontalAlignment() != null) {
			return pageXofY.getHorizontalAlignment();
		}
		if (StyleResolver.getHorizontalAlignment(style) != null) {
			return StyleResolver.getHorizontalAlignment(style);
		}
		return Defaults.getDefaults().getPageXofYHorizontalAlignment();
	}

	//image
	protected int getImageWidth(DRIImage image) {
		if (image.getWidth() != null) {
			return image.getWidth();
		}
		if (template.getImageWidth() != null) {
			return template.getImageWidth();
		}
		return Defaults.getDefaults().getImageWidth();
	}

	protected int getImageHeight(DRIImage image) {
		if (image.getHeight() != null) {
			return image.getHeight();
		}
		if (template.getImageHeight() != null) {
			return template.getImageHeight();
		}
		return Defaults.getDefaults().getImageHeight();
	}

	//filler
	protected int getFillerWidth(DRIFiller filler) {
		if (filler.getWidth() != null) {
			return filler.getWidth();
		}
		return Defaults.getDefaults().getFillerWidth();
	}

	protected int getFillerHeight(DRIFiller filler) {
		if (filler.getHeight() != null) {
			return filler.getHeight();
		}
		return Defaults.getDefaults().getFillerHeight();
	}

	//line
	protected int getLineWidth(DRILine line) {
		if (line.getWidth() != null) {
			return line.getWidth();
		}
		return Defaults.getDefaults().getLineWidth();
	}

	protected int getLineHeight(DRILine line) {
		if (line.getHeight() != null) {
			return line.getHeight();
		}
		return Defaults.getDefaults().getLineHeight();
	}

	//break
	protected int getBreakWidth(DRIBreak breakComponent) {
		return Defaults.getDefaults().getBreakWidth();
	}

	protected int getBreakHeight(DRIBreak breakComponent) {
		return Defaults.getDefaults().getBreakHeight();
	}

	//generic element
	protected Integer getGenericElementWidth(DRIGenericElement genericElement) {
		if (genericElement.getWidth() != null) {
			return genericElement.getWidth();
		}
		return Defaults.getDefaults().getGenericElementWidth();
	}

	protected Integer getGenericElementHeight(DRIGenericElement genericElement) {
		if (genericElement.getHeight() != null) {
			return genericElement.getHeight();
		}
		return Defaults.getDefaults().getGenericElementHeight();
	}

	//list
	protected int getListGap(DRIList list) {
		if (list.getGap() != null) {
			return list.getGap();
		}
		if (template.getListgap() != null) {
			return template.getListgap();
		}
		return Defaults.getDefaults().getListgap();
	}

	//chart
	protected int getChartWidth(DRIChart chart) {
		if (chart.getWidth() != null) {
			return chart.getWidth();
		}
		if (template.getChartWidth() != null) {
			return template.getChartWidth();
		}
		return Defaults.getDefaults().getChartWidth();
	}

	protected int getChartHeight(DRIChart chart) {
		if (chart.getHeight() != null) {
			return chart.getHeight();
		}
		if (template.getChartHeight() != null) {
			return template.getChartHeight();
		}
		return Defaults.getDefaults().getChartHeight();
	}

	protected List<Color> getChartSeriesColors(DRIPlot plot) {
		if (plot.getSeriesColors() != null && !plot.getSeriesColors().isEmpty()) {
			return plot.getSeriesColors();
		}
		if (template.getChartSeriesColors() != null && !template.getChartSeriesColors().isEmpty()) {
			return template.getChartSeriesColors();
		}
		return Defaults.getDefaults().getChartSeriesColors();
	}

	protected boolean isChartCategoryDatasetUseSeriesAsCategory(DRICategoryDataset dataset) {
		if (dataset.getUseSeriesAsCategory() != null) {
			return dataset.getUseSeriesAsCategory();
		}
		return Defaults.getDefaults().isChartCategoryDatasetUseSeriesAsCategory();
	}

	protected TimePeriod getChartTimeSeriesDatasetTimePeriodType(DRITimeSeriesDataset dataset) {
		if (dataset.getTimePeriodType() != null) {
			return dataset.getTimePeriodType();
		}
		return Defaults.getDefaults().getChartTimeSeriesDatasetTimePeriodType();
	}

	//barcode
	protected int getBarcodeWidth(DRIBarcode barcode) {
		if (barcode.getWidth() != null) {
			return barcode.getWidth();
		}
		if (template.getBarcodeWidth() != null) {
			return template.getBarcodeWidth();
		}
		return Defaults.getDefaults().getBarcodeWidth();
	}

	protected int getBarcodeHeight(DRIBarcode barcode) {
		if (barcode.getHeight() != null) {
			return barcode.getHeight();
		}
		if (template.getBarcodeHeight() != null) {
			return template.getBarcodeHeight();
		}
		return Defaults.getDefaults().getBarcodeHeight();
	}

	//subreport
	protected int getSubreportWidth(DRISubreport subreport) {
		if (subreport.getWidth() != null) {
			return subreport.getWidth();
		}
		if (template.getSubreportWidth() != null) {
			return template.getSubreportWidth();
		}
		return Defaults.getDefaults().getSubreportWidth();
	}

	protected int getSubreportHeight(DRISubreport subreport) {
		if (subreport.getHeight() != null) {
			return subreport.getHeight();
		}
		if (template.getSubreportHeight() != null) {
			return template.getSubreportHeight();
		}
		return Defaults.getDefaults().getSubreportHeight();
	}

	//crosstab
	protected int getCrosstabWidth(DRICrosstab crosstab) {
		if (crosstab.getWidth() != null) {
			return crosstab.getWidth();
		}
		if (template.getCrosstabWidth() != null) {
			return template.getCrosstabWidth();
		}
		return Defaults.getDefaults().getCrosstabWidth();
	}

	protected int getCrosstabHeight(DRICrosstab crosstab) {
		if (crosstab.getHeight() != null) {
			return crosstab.getHeight();
		}
		if (template.getCrosstabHeight() != null) {
			return template.getCrosstabHeight();
		}
		return Defaults.getDefaults().getCrosstabHeight();
	}

	public CrosstabTotalPosition getCrosstabColumnGroupTotalPosition(DRICrosstabColumnGroup<?> columnGroup) {
		if (!isCrosstabColumnGroupShowTotal(columnGroup)) {
			return null;
		}
		if (columnGroup.getTotalPosition() != null) {
			return columnGroup.getTotalPosition();
		}
		return Defaults.getDefaults().getCrosstabColumnGroupTotalPosition();
	}

	public CrosstabTotalPosition getCrosstabRowGroupTotalPosition(DRICrosstabRowGroup<?> rowGroup) {
		if (!isCrosstabRowGroupShowTotal(rowGroup)) {
			return null;
		}
		if (rowGroup.getTotalPosition() != null) {
			return rowGroup.getTotalPosition();
		}
		return Defaults.getDefaults().getCrosstabRowGroupTotalPosition();
	}

	public boolean isCrosstabColumnGroupShowTotal(DRICrosstabColumnGroup<?> columnGroup) {
		if (columnGroup.getShowTotal() != null) {
			return columnGroup.getShowTotal();
		}
		return Defaults.getDefaults().isCrosstabColumnGroupShowTotal();
	}

	public boolean isCrosstabRowGroupShowTotal(DRICrosstabRowGroup<?> rowGroup) {
		if (rowGroup.getShowTotal() != null) {
			return rowGroup.getShowTotal();
		}
		return Defaults.getDefaults().isCrosstabRowGroupShowTotal();
	}

	public int getCrosstabColumnGroupHeaderHeight(DRICrosstabColumnGroup<?> columnGroup) {
		if (columnGroup.getHeaderHeight() != null) {
			return columnGroup.getHeaderHeight();
		}
		return Defaults.getDefaults().getCrosstabColumnGroupHeaderHeight();
	}

	public int getCrosstabColumnGroupTotalHeaderWidth(DRICrosstabColumnGroup<?> columnGroup) {
		if (columnGroup.getTotalHeaderWidth() != null) {
			return columnGroup.getTotalHeaderWidth();
		}
		return Defaults.getDefaults().getCrosstabColumnGroupTotalHeaderWidth();
	}

	public int getCrosstabRowGroupHeaderWidth(DRICrosstabRowGroup<?> rowGroup) {
		if (rowGroup.getHeaderWidth() != null) {
			return rowGroup.getHeaderWidth();
		}
		return Defaults.getDefaults().getCrosstabRowGroupHeaderWidth();
	}

	public int getCrosstabRowGroupTotalHeaderHeight(DRICrosstabRowGroup<?> rowGroup) {
		if (rowGroup.getTotalHeaderHeight() != null) {
			return rowGroup.getTotalHeaderHeight();
		}
		return Defaults.getDefaults().getCrosstabRowGroupTotalHeaderHeight();
	}

	public int getCrosstabCellWidth(DRICrosstab crosstab) {
		if (crosstab.getCellWidth() != null) {
			return crosstab.getCellWidth();
		}
		return Defaults.getDefaults().getCrosstabCellWidth();
	}

	public int getCrosstabCellHeight(DRICrosstab crosstab) {
		if (crosstab.getCellHeight() != null) {
			return crosstab.getCellHeight();
		}
		return Defaults.getDefaults().getCrosstabCellHeight();
	}

	//split
	protected SplitType getTitleSplitType(DRIBand band) {
		return getSplitType(band, template.getTitleSplitType(), Defaults.getDefaults().getTitleSplitType());
	}

	protected SplitType getPageHeaderSplitType(DRIBand band) {
		return getSplitType(band, template.getPageHeaderSplitType(), Defaults.getDefaults().getPageHeaderSplitType());
	}

	protected SplitType getPageFooterSplitType(DRIBand band) {
		return getSplitType(band, template.getPageFooterSplitType(), Defaults.getDefaults().getPageFooterSplitType());
	}

	protected SplitType getColumnHeaderSplitType(DRIBand band) {
		return getSplitType(band, template.getColumnHeaderSplitType(), Defaults.getDefaults().getColumnHeaderSplitType());
	}

	protected SplitType getColumnFooterSplitType(DRIBand band) {
		return getSplitType(band, template.getColumnFooterSplitType(), Defaults.getDefaults().getColumnFooterSplitType());
	}

	protected SplitType getGroupHeaderSplitType(DRIBand band) {
		return getSplitType(band, template.getGroupHeaderSplitType(), Defaults.getDefaults().getGroupHeaderSplitType());
	}

	protected SplitType getGroupFooterSplitType(DRIBand band) {
		return getSplitType(band, template.getGroupFooterSplitType(), Defaults.getDefaults().getGroupFooterSplitType());
	}

	protected SplitType getDetailSplitType(DRIBand band) {
		return getSplitType(band, template.getDetailSplitType(), Defaults.getDefaults().getDetailSplitType());
	}

	protected SplitType getLastPageFooterSplitType(DRIBand band) {
		return getSplitType(band, template.getLastPageFooterSplitType(), Defaults.getDefaults().getLastPageFooterSplitType());
	}

	protected SplitType getSummarySplitType(DRIBand band) {
		return getSplitType(band, template.getSummarySplitType(), Defaults.getDefaults().getSummarySplitType());
	}

	protected SplitType getNoDataSplitType(DRIBand band) {
		return getSplitType(band, template.getNoDataSplitType(), Defaults.getDefaults().getNoDataSplitType());
	}

	protected SplitType getBackgroundSplitType(DRIBand band) {
		return getSplitType(band, template.getBackgroundSplitType(), Defaults.getDefaults().getBackgroundSplitType());
	}

	private SplitType getSplitType(DRIBand band, SplitType templateSplitType, SplitType splitType) {
		if (band.getSplitType() != null) {
			return band.getSplitType();
		}
		if (templateSplitType != null) {
			return templateSplitType;
		}
		if (splitType != null) {
			return splitType;
		}
		if (template.getDefaultSplitType() != null) {
			return template.getDefaultSplitType();
		}
		return Defaults.getDefaults().getDefaultSplitType();
	}
}
