package com.vteam.cars.plugin.excel.exp.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Excel模板图片坐标信息 .<br>
 *
 * @author olivia.liu
 * @date 2022/8/5 9:54
 */
@Getter
@Setter
public class ExcelPicturePosition {

	/**
	 * 插入图片位置起始单元格内的x坐标（0 ~ 1023）
	 */
	private int dxStart;
	
	/**
	 * 插入图片位置起始单元格内的y坐标（0 ~ 255）
	 */
	private int dyStart;
	
	/**
	 * 插入图片位置结束单元格中的x坐标（0 ~ 1023）
	 */
	private int dxEnd;
	
	/**
	 * 插入图片位置结束单元格中的y坐标（0 ~ 255）
	 */
	private int dyEnd;

	/**
	 * 插入图片位置起始列（基于0）
	 */
	private short colStart;
	
	/**
	 * 插入图片位置起始行（基于0）
	 */
	private int rowStart;
	
	/**
	 * 插入图片位置结束列（基于0）
	 */
	private short colEnd;
	
	/**
	 * 插入图片位置结束行（基于0）
	 */
	private int rowEnd;
	
	/**
	 * 图片uuid
	 */
	private String pictureUuid;
	
	public ExcelPicturePosition() {
		super();
	}
	public ExcelPicturePosition(int dxStart, int dyStart, int dxEnd, int dyEnd, short colStart, int rowStart, short colEnd, int rowEnd) {
		super();
		this.dxStart = dxStart;
		this.dyStart = dyStart;
		this.dxEnd = dxEnd;
		this.dyEnd = dyEnd;
		this.colStart = colStart;
		this.rowStart = rowStart;
		this.colEnd = colEnd;
		this.rowEnd = rowEnd;
	}

}
