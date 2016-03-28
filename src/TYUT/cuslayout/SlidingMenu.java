package TYUT.cuslayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.hardware.display.DisplayManagerCompat;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;


public class SlidingMenu extends HorizontalScrollView
{
   
	/**
	 * 灞忓箷瀹藉害
	 */
	private int mScreenWidth;
	/**
	 * dp
	 */
	private int mMenuRightPadding;
	/**
	 * 鑿滃崟鐨勫搴�
	 */
	
	//菜单宽度
	private int mMenuWidth;
	//屏高
	private int mScreenHeight;
	//屏宽
	private int mHalfMenuWidth;
	//
	private DisplayMetrics outMetrics;
	//是否弹出
	private boolean isOpen=false;
	//
	private boolean once=false,twoce=false;

	public SlidingMenu(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		WindowManager wm=(WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
		outMetrics=new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(outMetrics);
		mScreenHeight=outMetrics.heightPixels;
		
		mScreenWidth=outMetrics.widthPixels;
		Log.i("屏款",mScreenWidth+"");
		//dp转px
		mMenuRightPadding=mScreenWidth/5;
		
	}
	 @Override  
	    protected void onScrollChanged(int l, int t, int oldl, int oldt)  
	    {  

  
	        float scale = l * 1.0f / mMenuWidth;  
	        float leftScale = 1 - 0.3f * scale;  
	        float rightScale = 0.8f + scale * 0.2f;  
	        
	        LinearLayout wrapper = (LinearLayout) getChildAt(0);
			ViewGroup menu = (ViewGroup) wrapper.getChildAt(0);
			ViewGroup content = (ViewGroup) wrapper.getChildAt(1);
			
			
			/*menu.setScaleX(leftScale);  
			menu.setScaleY(leftScale);  
			menu.setAlpha(0.6f + 0.4f * (1 - scale));  
			menu.setTranslationX(mMenuWidth * scale * 0.6f);*/  
	  
			content.setPivotX(0);  
			content.setPivotY( content.getHeight() / 2);  
			content.setScaleX(rightScale);  
			content.setScaleY(rightScale);  
	  
	    }  
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
	{
		
		
		/**
		 * 鏄剧ず鐨勮缃竴涓搴�
		 */
		if (!once)
		{
			LinearLayout wrapper = (LinearLayout) getChildAt(0);
			ViewGroup menu = (ViewGroup) wrapper.getChildAt(0);
			ViewGroup content = (ViewGroup) wrapper.getChildAt(1);

			mMenuWidth = mScreenWidth - mMenuRightPadding*2;
			//mHalfMenuWidth = mMenuWidth / 10;
			android.widget.LinearLayout.LayoutParams menulay=(android.widget.LinearLayout.LayoutParams) menu.getLayoutParams();
			menulay.width = mMenuWidth;
			android.widget.LinearLayout.LayoutParams contentlay=(android.widget.LinearLayout.LayoutParams) content.getLayoutParams();
			contentlay.width = mScreenWidth;
			
			menu.setLayoutParams(menulay);
			content.setLayoutParams(contentlay);
			
			
			/*ViewGroup.LayoutParams wrapperlay= (ViewGroup.LayoutParams) wrapper.getLayoutParams();
			wrapperlay.width=0;
			
			wrapper.setLayoutParams(wrapperlay);
			
			ViewGroup.LayoutParams slidlay=(ViewGroup.LayoutParams)this.getLayoutParams();
			slidlay.width=mScreenWidth;
			this.setLayoutParams(slidlay);*/
			once = true;
			
		}
		
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		this.smoothScrollTo(mMenuWidth, 0);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b)
	{
		super.onLayout(changed, l, t, r, b);
		if (changed)
		{
			// 灏嗚彍鍗曢殣钘�
			this.smoothScrollTo(mMenuWidth, 0);

		}
		
	}
	//触摸效果
	@Override
	public boolean onTouchEvent(MotionEvent ev)
	{
		int action = ev.getAction();
		switch (action)
		{
		// Up手指触摸结束后
		case MotionEvent.ACTION_UP:
			
			int scrollX = getScrollX();
			Log.i("滑到",scrollX+"");
			if (scrollX< mMenuWidth/5*4&&!isOpen)
			{
				
				openMenu();
			} else if(scrollX>mMenuWidth/5&&isOpen)
			{
				
				closeMenu();
			
				
				
			}else{
				if(isOpen){
					this.smoothScrollTo(0, 0);
					
				}else{
					this.smoothScrollTo(mMenuWidth, 0);
				}
				
				
			}
			
			return true;
		}
		return super.onTouchEvent(ev);
	}

	/**
	 * 鎵撳紑鑿滃崟
	 */
	public void openMenu()
	{
		if (isOpen)
			return;
		this.smoothScrollTo(0, 0);
		isOpen = true;
	}

	/**
	 * 鍏抽棴鑿滃崟
	 */
	public void closeMenu()
	{
		if (isOpen)
		{
			this.smoothScrollTo(mMenuWidth, 0);
			isOpen = false;
		}
	}

	/**
	 * 鍒囨崲鑿滃崟鐘舵��
	 */
	public void toggle()
	{
		if (isOpen)
		{
			closeMenu();
		} else
		{
			openMenu();
		}
	}

}
