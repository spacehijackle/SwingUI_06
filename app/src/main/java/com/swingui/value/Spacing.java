package com.swingui.value;

import com.swingui.constant.UIDefaults;

/**
 * スペーシング（間隔）値提供クラス
 * 
 * @author t.yoshida
 */
public class Spacing
{
    /** 間隔 */
	public final int gap;

	private Spacing(int gap)
	{
		this.gap = gap;
	}

    /**
     * 指定された間隔で {@code Spacing} を生成する。
     * 
     * @param gap 間隔
     * @return {@code Spacing}
     */
	public static Spacing of(int gap)
	{
		return new Spacing(gap);
	}

    /**
     * デフォルトの間隔で {@code Spacing} を生成する。
     * 
     * @return {@code Spacing}
     */
    public static Spacing defaults()
    {
        return new Spacing(UIDefaults.COMPONENT_GAP);
    }
}
