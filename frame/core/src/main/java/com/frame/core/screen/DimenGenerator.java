package com.frame.core.screen;


import java.io.File;

/**
 * title:运行main方法进行生成文件
 * describe:
 *
 * @author zhou
 * @date 2019-01-05 11:09
 */
public class DimenGenerator {

	/**
	 * 设计稿尺寸(将自己设计师的设计稿的宽度填入)
	 */
	private static final int DESIGN_WIDTH = 375;

	/**
	 * 设计稿的高度(将自己设计师的设计稿的高度填入)
	 */
	private static final int DESIGN_HEIGHT = 667;

	public static void main(String[] args) {
		// 如果无法运行 在idea/gradle.xml <GradleProjectSettings>第一行加入<option name="delegatedBuild" value="false" />
		//     求得最小宽度
		int smallest = DESIGN_WIDTH > DESIGN_HEIGHT ? DESIGN_HEIGHT : DESIGN_WIDTH;
		DimenTypes[] values = DimenTypes.values();
		for (DimenTypes value : values) {
			File file = new File("");
			DimenFileUtils.makeAll(smallest, value, file.getAbsolutePath());
		}

	}

}
