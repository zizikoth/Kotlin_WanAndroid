package com.frame.core.screen;

/**
 * title:进行一些适配，基本应该是足够了
 * describe:
 *
 * @author zhou
 * @date 2019-01-05 11:09
 */
public enum DimenTypes {

	//适配Android 3.2以上   大部分手机的sw值集中在  300-460之间
	DP_sw__320(320),
	DP_sw__360(360),
	DP_sw__380(380),
	DP_sw__384(384),
	DP_sw__390(390),
	DP_sw__391(391),
	DP_sw__392(392),
	DP_sw__393(393),
	DP_sw__400(400),
	DP_sw__410(410),
	DP_sw__411(411),
	DP_sw__430(430),
	DP_sw__432(432),
	DP_sw__480(480);


	private int swWidthDp;

	DimenTypes(int swWidthDp) {
		this.swWidthDp = swWidthDp;
	}

	public int getSwWidthDp() {
		return swWidthDp;
	}

}
