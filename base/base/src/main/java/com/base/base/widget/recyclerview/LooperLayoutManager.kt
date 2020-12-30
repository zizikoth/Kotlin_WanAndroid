package com.base.base.widget.recyclerview

import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2020-03-22 13:16
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class LooperLayoutManager : RecyclerView.LayoutManager() {

    private val tag = this.javaClass.simpleName
    private var looperEnable = true
    private var scrollEnable = true

    fun setLooperEnable(looperEnable: Boolean) {
        this.looperEnable = looperEnable
    }

    fun setScrollEnable(scrollVertical: Boolean) {
        this.scrollEnable = scrollVertical
    }

    override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams {
        return RecyclerView.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

    override fun canScrollHorizontally(): Boolean {
        return scrollEnable
    }

    override fun canScrollVertically(): Boolean {
        return scrollEnable
    }

    override fun onLayoutChildren(recycler: RecyclerView.Recycler, state: RecyclerView.State) {
        if (itemCount <= 0) {
            return
        }
        //preLayout主要支持动画，直接跳过
        if (state.isPreLayout) {
            return
        }
        //将视图分离放入scrap缓存中，以准备重新对view进行排版
        detachAndScrapAttachedViews(recycler)
        var autalLenght = 0
        for (i in 0 until itemCount) {
            //初始化，将在屏幕内的view填充
            val itemView = recycler.getViewForPosition(i)
            addView(itemView)
            //测量itemView的宽高
            measureChildWithMargins(itemView, 0, 0)
            val width = getDecoratedMeasuredWidth(itemView)
            val height = getDecoratedMeasuredHeight(itemView)
            //根据itemView的宽高进行布局
            autalLenght += if (scrollEnable) {
                layoutDecorated(itemView, 0, autalLenght, width, autalLenght + height)
                height
            } else {
                layoutDecorated(itemView, autalLenght, 0, autalLenght + width, height)
                width
            }


            //如果当前布局过的itemView的宽度或高度总和大于RecyclerView的宽（水平）或高（垂直），则不再进行布局
            if (scrollEnable) {
                if (autalLenght > getHeight()) {
                    break
                }
            } else {
                if (autalLenght > getWidth()) {
                    break
                }
            }
        }
    }

    override fun scrollHorizontallyBy(
        dx: Int,
        recycler: RecyclerView.Recycler,
        state: RecyclerView.State
    ): Int {
        if (scrollEnable) {
            return super.scrollHorizontallyBy(dx, recycler, state)
        }
        //1.左右滑动的时候，填充子view
        val travl = fillHorizontal(dx, recycler)
        if (travl == 0) {
            return 0
        }

        //2.滚动
        offsetChildrenHorizontal(travl * -1)

        //3.回收已经离开界面的
        recyclerHorizontalHideView(dx, recycler)
        return travl
    }


    override fun scrollVerticallyBy(
        dy: Int,
        recycler: RecyclerView.Recycler,
        state: RecyclerView.State
    ): Int {
        if (!scrollEnable) {
            return super.scrollVerticallyBy(dy, recycler, state)
        }

        //1.上下滑动的时候，填充子view
        val travl = fillVertical(dy, recycler)
        if (travl == 0) {
            return 0
        }

        //2.滚动
        offsetChildrenVertical(travl * -1)

        //3.回收已经离开界面的
        recyclerVerticalHideView(dy, recycler)
        return travl
    }

    /**
     * 左右滑动的时候，填充
     */
    private fun fillHorizontal(dx: Int, recycler: RecyclerView.Recycler): Int {
        var dX = dx
        if (dX > 0) {
            //标注1.向左滚动
            val lastView = getChildAt(childCount - 1) ?: return 0
            val lastPos = getPosition(lastView)
            //标注2.可见的最后一个itemView完全滑进来了，需要补充新的
            if (lastView.right < width) {
                var scrap: View? = null
                //标注3.判断可见的最后一个itemView的索引，
                // 如果是最后一个，则将下一个itemView设置为第一个，否则设置为当前索引的下一个
                if (lastPos == itemCount - 1) {
                    if (looperEnable) {
                        scrap = recycler.getViewForPosition(0)
                    } else {
                        dX = 0
                    }
                } else {
                    scrap = recycler.getViewForPosition(lastPos + 1)
                }
                if (scrap == null) {
                    return dX
                }
                //标注4.将新的itemViewadd进来并对其测量和布局
                addView(scrap)
                measureChildWithMargins(scrap, 0, 0)
                val width = getDecoratedMeasuredWidth(scrap)
                val height = getDecoratedMeasuredHeight(scrap)
                layoutDecorated(
                    scrap, lastView.right, 0,
                    lastView.right + width, height
                )
                return dX
            }
        } else {
            //向右滚动
            val firstView = getChildAt(0) ?: return 0
            val firstPos = getPosition(firstView)
            if (firstView.left >= 0) {
                var scrap: View? = null
                if (firstPos == 0) {
                    if (looperEnable) {
                        scrap = recycler.getViewForPosition(itemCount - 1)
                    } else {
                        dX = 0
                    }
                } else {
                    scrap = recycler.getViewForPosition(firstPos - 1)
                }
                if (scrap == null) {
                    return 0
                }
                addView(scrap, 0)
                measureChildWithMargins(scrap, 0, 0)
                val width = getDecoratedMeasuredWidth(scrap)
                val height = getDecoratedMeasuredHeight(scrap)
                layoutDecorated(
                    scrap, firstView.left - width, 0,
                    firstView.left, height
                )
            }
        }
        return dX
    }

    /**
     * 上下滑动的时候，填充
     */
    private fun fillVertical(
        dy: Int,
        recycler: RecyclerView.Recycler
    ): Int {
        var dY = dy
        if (dY > 0) {
            //标注1.向上滚动
            val lastView = getChildAt(childCount - 1) ?: return 0
            val lastPos = getPosition(lastView)
            //标注2.可见的最后一个itemView完全滑进来了，需要补充新的
            if (lastView.bottom < height) {
                var scrap: View? = null
                //标注3.判断可见的最后一个itemView的索引，
                // 如果是最后一个，则将下一个itemView设置为第一个，否则设置为当前索引的下一个
                if (lastPos == itemCount - 1) {
                    if (looperEnable) {
                        scrap = recycler.getViewForPosition(0)
                    } else {
                        dY = 0
                    }
                } else {
                    scrap = recycler.getViewForPosition(lastPos + 1)
                }
                if (scrap == null) {
                    return dY
                }
                //标注4.将新的itemViewadd进来并对其测量和布局
                addView(scrap)
                measureChildWithMargins(scrap, 0, 0)
                val width = getDecoratedMeasuredWidth(scrap)
                val height = getDecoratedMeasuredHeight(scrap)
                layoutDecorated(
                    scrap, 0, lastView.bottom,
                    width, lastView.bottom + height
                )
                return dY
            }
        } else {
            //向下滚动
            val firstView = getChildAt(0) ?: return 0
            val firstPos = getPosition(firstView)
            if (firstView.top >= 0) {
                var scrap: View? = null
                if (firstPos == 0) {
                    if (looperEnable) {
                        scrap = recycler.getViewForPosition(itemCount - 1)
                    } else {
                        dY = 0
                    }
                } else {
                    scrap = recycler.getViewForPosition(firstPos - 1)
                }
                if (scrap == null) {
                    return 0
                }
                addView(scrap, 0)
                measureChildWithMargins(scrap, 0, 0)
                val width = getDecoratedMeasuredWidth(scrap)
                val height = getDecoratedMeasuredHeight(scrap)
                layoutDecorated(
                    scrap, 0, firstView.top - height,
                    width, firstView.top
                )
            }
        }
        return dY
    }

    /**
     * 回收界面不可见的view
     */
    private fun recyclerHorizontalHideView(
        dx: Int,
        recycler: RecyclerView.Recycler
    ) {
        for (i in 0 until childCount) {
            val view = getChildAt(i) ?: continue
            if (dx > 0) {
                //向左滚动，移除一个左边不在内容里的view
                if (view.right < 0) {
                    removeAndRecycleView(view, recycler)
                    Log.d(tag, "循环: 移除 一个view  childCount=$childCount")
                }
            } else {
                //向右滚动，移除一个右边不在内容里的view
                if (view.left > width) {
                    removeAndRecycleView(view, recycler)
                    Log.d(tag, "循环: 移除 一个view  childCount=$childCount")
                }
            }
        }
    }

    /**
     * 回收界面不可见的view
     */
    private fun recyclerVerticalHideView(dy: Int, recycler: RecyclerView.Recycler) {
        for (i in 0 until childCount) {
            val view = getChildAt(i) ?: continue
            if (dy > 0) {
                //向上滚动，移除一个上边不在内容里的view
                if (view.bottom < 0) {
                    removeAndRecycleView(view, recycler)
                    Log.d(tag, "循环: 移除 一个view  childCount=$childCount")
                }
            } else {
                //向下滚动，移除一个下边不在内容里的view
                if (view.top > height) {
                    removeAndRecycleView(view, recycler)
                    Log.d(tag, "循环: 移除 一个view  childCount=$childCount")
                }
            }
        }
    }

}