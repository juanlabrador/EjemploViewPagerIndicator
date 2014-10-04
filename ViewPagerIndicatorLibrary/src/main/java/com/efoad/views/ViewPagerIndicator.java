package com.efoad.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

import com.efoad.androidviewpagerindicator.R;

public class ViewPagerIndicator extends View{

	private static final int SELECTED_FACTOR = 2;
	private static final int SPACING_FACTOR = 4;
	private int numberOfItems = 0;
	private Paint paint = new Paint();
	private int color = Color.BLACK;
	private int activeItem = 0;
	private float radius = 4f;
	private ViewPager viewPager;
	
	public ViewPagerIndicator(Context context, AttributeSet attrs) {
		super(context, attrs);
		 TypedArray a = context.getTheme().obtainStyledAttributes(
			        attrs,
			        R.styleable.ViewPagerIndicator,
			        0, 0);

			   try {
			       radius = a.getDimension(R.styleable.ViewPagerIndicator_radius, 0);
			       color = a.getColor(R.styleable.ViewPagerIndicator_color, Color.BLACK);
			   } finally {
			       a.recycle();
			   }
		
		paint.setAntiAlias(true);
//		paint.setStrokeWidth(4f);
		paint.setColor(color);
		paint.setStyle(Paint.Style.FILL);
		paint.setStrokeJoin(Paint.Join.ROUND);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		float x, y;
		for (int i = 0;i<numberOfItems;i++) {
			x = (i*SELECTED_FACTOR*radius) + (i*(radius*SPACING_FACTOR)) + radius*SELECTED_FACTOR;
			y = radius*SELECTED_FACTOR;
			
			if(i == activeItem) {
				canvas.drawCircle(x ,y ,
						radius*SELECTED_FACTOR, paint);
			}else {
				canvas.drawCircle(x ,y ,
						radius, paint);
			}
			
		}
		
	}


	public void setActiveItem(int activeItem) {
		this.activeItem = activeItem;
		this.invalidate();
		this.requestLayout();
	}
	

	public ViewPager getViewPager() {
		return viewPager;
	}

	public void setViewPager(ViewPager viewPager) {
		this.viewPager = viewPager;
		this.numberOfItems = this.viewPager.getAdapter().getCount();
		this.setActiveItem(viewPager.getCurrentItem());
		this.viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				setActiveItem(position);
			}
		});
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		// x(n) = a + d(n-1)
		// n is numberOfItems
		int n = numberOfItems;
		int measuredWidth = (int) (radius*SELECTED_FACTOR + (radius*(SELECTED_FACTOR+SPACING_FACTOR))*(n-1));
		measuredWidth += radius*SELECTED_FACTOR;
		int measuredHeight = (int) (radius*SELECTED_FACTOR)*2;
		setMeasuredDimension(measuredWidth, measuredHeight );
		
	}
	



}
