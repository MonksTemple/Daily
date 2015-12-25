package com.example.component;

import java.util.Calendar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

import com.example.util.CalendarLunarUtil;
import com.example.util.CustomDate;
import com.example.util.DateUtil;

public class CalendarView extends View {

	private static final String TAG = "CalendarView";
	/**
	 * 两种模式 （月份和星期）
	 */
	public static final int MONTH_STYLE = 0;

	private static final int TOTAL_COL = 7;
	private static final int TOTAL_ROW = 6;

	private Paint mCirclePaint;
	private Paint mTextPaint;
	private int mViewWidth;
	private int mViewHight;
	private int mCellSpace;
	private Row rows[] = new Row[TOTAL_ROW];
	private static CustomDate mShowDate;//自定义的日期  包括year month day
	public static int style = MONTH_STYLE;
	private static final int WEEK = 7;
	private CallBack mCallBack;//回调
	private int touchSlop;
	private boolean callBackCellSpace;
	boolean isTurnSpecialDay;
	//private CustomDate lastShowDate;

	public interface CallBack {

		void clickDate(CustomDate date);//回调点击的日期

		void onMesureCellHeight(int cellSpace);//回调cell的高度确定slidingDrawer高度

		void changeDate(CustomDate date);//回调滑动viewPager改变的日期
	}

	public CalendarView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);

	}

	public CalendarView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);

	}

	public CalendarView(Context context) {
		super(context);
		init(context);
	}

	public CalendarView(Context context, int style, CallBack mCallBack) {
		super(context);
		CalendarView.style = style;
		this.mCallBack = mCallBack;
		init(context);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		for (int i = 0; i < TOTAL_ROW; i++) {
			if (rows[i] != null)
				rows[i].drawCells(canvas);
		}
	}

	private void init(Context context) {
		mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mCirclePaint.setStyle(Paint.Style.FILL);
		mCirclePaint.setColor(Color.parseColor("#31A9A5"));
		touchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
		initDate();
	}

	private void initDate() {
		if (style == MONTH_STYLE) {
			mShowDate =new CustomDate();
		} 
		fillDate();
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		mViewWidth = w;
		mViewHight = h;
		mCellSpace = Math.min(mViewHight / TOTAL_ROW, mViewWidth / TOTAL_COL);
		if (!callBackCellSpace) {
			
			mCallBack.onMesureCellHeight(mCellSpace);
			callBackCellSpace = true;
		}
		mTextPaint.setTextSize(mCellSpace / 3);
	}

	private Cell mClickCell;
	private float mDownX;
	private float mDownY;
/*
 * 
 * 触摸事件为了确定点击的位置日期
 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			mDownX = event.getX();
			mDownY = event.getY();
			break;
		case MotionEvent.ACTION_UP:
			float disX = event.getX() - mDownX;
			float disY = event.getY() - mDownY;
			if (Math.abs(disX) < touchSlop && Math.abs(disY) < touchSlop) {
				int col = (int) (mDownX / mCellSpace);
				int row = (int) (mDownY / mCellSpace);
				measureClickCell(col, row);
			}
			break;
		}
		return true;
	}

	private void measureClickCell(int col, int row) {
		if (col >= TOTAL_COL || row >= TOTAL_ROW)
			return;
		if (mClickCell != null) {
			rows[mClickCell.j].cells[mClickCell.i] = mClickCell;
		}
		if (rows[row] != null) {
			mClickCell = new Cell(rows[row].cells[col].date,
					rows[row].cells[col].state, rows[row].cells[col].i,
					rows[row].cells[col].j);
			rows[row].cells[col].state = State.CLICK_DAY;
			CustomDate date = rows[row].cells[col].date;
			date.week = col;
			mCallBack.clickDate(date);
			//clickDay=date.day;
		//	lastShowDate=date;
			invalidate();
		}
	}

	// 组
	class Row {
		public int j;

		Row(int j) {
			this.j = j;
		}

		public Cell[] cells = new Cell[TOTAL_COL];

		public void drawCells(Canvas canvas) {
			for (int i = 0; i < cells.length; i++) {
				if (cells[i] != null)
					cells[i].drawSelf(canvas);
			}

		}
	}

	// 单元格
	class Cell {
		public CustomDate date;
		public State state;
		public int i;
		public int j;

		public Cell(CustomDate date, State state, int i, int j) {
			super();
			this.date = date;
			this.state = state;
			this.i = i;
			this.j = j;
		}


		// 绘制一个单元格 如果颜色需要自定义可以修改
		public void drawSelf(Canvas canvas) {
			switch (state) {
			case CURRENT_MONTH_DAY:
				mTextPaint.setColor(Color.parseColor("#A0000000"));
				break;
			case NEXT_MONTH_DAY:
			case PAST_MONTH_DAY:
				mTextPaint.setColor(Color.parseColor("#40000000"));
				break;
			case TODAY:
				mTextPaint.setColor(Color.parseColor("#31A9A5"));
				break;
			case CLICK_DAY:
				mTextPaint.setColor(Color.parseColor("#fffffe"));
				canvas.drawCircle((float) (mCellSpace * (i + 0.5)),
						(float) ((j + 0.5) * mCellSpace), mCellSpace / 2,
						mCirclePaint);
				break;
			}
			// 绘制文字
			mTextPaint.setTextSize(50);
			String content = date.day+"";
			canvas.drawText(content,
					(float) ((i+0.5) * mCellSpace - mTextPaint.measureText(content)/2),
					(float) ((j + 0.7) * mCellSpace - mTextPaint.measureText(
							content, 0, 1) / 2), mTextPaint);
			
			if(state!=State.CLICK_DAY){
			Calendar cal=Calendar.getInstance();
			cal.set(Calendar.YEAR, date.year);
			cal.set(Calendar.MARCH,date.month-1);
			cal.set(Calendar.DAY_OF_MONTH,date.day);
			CalendarLunarUtil cu=new CalendarLunarUtil(cal);
			content=cu.toString();
			mTextPaint.setColor(Color.parseColor("#40000000"));
			mTextPaint.setTextSize(25);
			canvas.drawText(content,
					(float) ((i+0.5) * mCellSpace - mTextPaint.measureText(content)/2),
					(float) ((j + 0.7) * mCellSpace - mTextPaint.measureText(
							content, 0, 1) / 2)+30, mTextPaint);
			}
		}
	}
/**
 * 
 * @author huang
 * cell的state
 *当前月日期，过去的月的日期，下个月的日期，今天，点击的日期
 *
 */
	enum State {
		CURRENT_MONTH_DAY, PAST_MONTH_DAY, NEXT_MONTH_DAY, TODAY, CLICK_DAY;
	}

	/**
	 * 填充日期的数据
	 */
	private void fillDate() {
		if (style == MONTH_STYLE) {
			fillMonthDate();
		}
		mCallBack.changeDate(mShowDate);
	}

	/**
	 * 填充星期模式下的数据 默认通过当前日期得到所在星期天的日期，然后依次填充日期
	 */
	private void fillWeekDate() {
		int lastMonthDays = DateUtil.getMonthDays(mShowDate.year, mShowDate.month-1);
		rows[0] = new Row(0);
		int day = mShowDate.day;
		for (int i = TOTAL_COL -1; i >= 0 ; i--) {
			day -= 1;
			if (day < 1) {
				day = lastMonthDays;
			}
			CustomDate date = CustomDate.modifiDayForObject(mShowDate, day);
			if (DateUtil.isToday(date)) {
				mClickCell = new Cell(date, State.TODAY, i, 0);
				date.week = i;
				mCallBack.clickDate(date);
				rows[0].cells[i] =  new Cell(date, State.CLICK_DAY, i, 0);
				continue;
			}
			rows[0].cells[i] = new Cell(date, State.CURRENT_MONTH_DAY,i, 0);
		}
	}

	/**
	 * 填充月份模式下数据 通过getWeekDayFromDate得到一个月第一天是星期几就可以算出所有的日期的位置 然后依次填充
	 * 这里最好重构一下
	 */
	private void fillMonthDate() {
		int monthDay;
		if(!isTurnSpecialDay){
			monthDay = DateUtil.getCurrentMonthDay();
		}
		else{
			monthDay=mShowDate.day;
		}
		Calendar cal=Calendar.getInstance();
		int today=cal.get(Calendar.DAY_OF_MONTH);
		int month=cal.get(Calendar.MONTH);
		int year=cal.get(Calendar.YEAR);
		int lastMonthDays = DateUtil.getMonthDays(mShowDate.year, mShowDate.month - 1);
		int currentMonthDays = DateUtil.getMonthDays(mShowDate.year, mShowDate.month);
		int firstDayWeek = DateUtil.getWeekDayFromDate(mShowDate.year, mShowDate.month);
		boolean isCurrentMonth = false;
		if (DateUtil.isCurrentMonth(mShowDate)) {
			isCurrentMonth = true;
		}
		boolean isTodayMonth=false;
		if((month==(mShowDate.month-1))&&(year==mShowDate.year)){
			isTodayMonth=true;
		}
		int day = 0;
		for (int j = 0; j < TOTAL_ROW; j++) {
			rows[j] = new Row(j);
			for (int i = 0; i < TOTAL_COL; i++) {
				int postion = i + j * TOTAL_COL;
				if (postion >= firstDayWeek
						&& postion < firstDayWeek + currentMonthDays) {
					day++;
					if ((day == monthDay)||(day==today)) {
						CustomDate date = CustomDate.modifiDayForObject(mShowDate, day);
						if(isCurrentMonth)
							mClickCell = new Cell(date,State.CURRENT_MONTH_DAY, i,j);
						if(day==monthDay){
						//	System.out.println("monthday----------"+monthDay);
							mClickCell = new Cell(date,State.CURRENT_MONTH_DAY, i,j);
						}
						if((day==today)&&isTodayMonth){
							mClickCell = new Cell(date,State.TODAY, i,j);
						}
						date.week = i;
						mCallBack.clickDate(date);
						//System.out.println("fillMonth --->"+clickDay);
					//	lastShowDate=date;
						if(day==monthDay)
							rows[j].cells[i] = new Cell(date,State.CLICK_DAY, i,j);
						else
							rows[j].cells[i] = new Cell(date,State.CURRENT_MONTH_DAY, i,j);
						continue;
					}
					rows[j].cells[i] = new Cell(CustomDate.modifiDayForObject(mShowDate, day),
							State.CURRENT_MONTH_DAY, i, j);
				} else if (postion < firstDayWeek) {
					rows[j].cells[i] = new Cell(new CustomDate(mShowDate.year, mShowDate.month-1, lastMonthDays - (firstDayWeek- postion - 1)), State.PAST_MONTH_DAY, i, j);
				} else if (postion >= firstDayWeek + currentMonthDays) {
					rows[j].cells[i] = new Cell((new CustomDate(mShowDate.year, mShowDate.month+1, postion - firstDayWeek - currentMonthDays + 1)), State.NEXT_MONTH_DAY, i, j);
				}
			}
		}
	}

	public void update() {
		fillDate();
		invalidate();
	}
	
	public void backToday(){
		initDate();
		invalidate();
	}
	//切换style
	public void switchStyle(int style) {
		CalendarView.style = style;
		if (style == MONTH_STYLE) {
			update();
		}
	}
//向右滑动
	public void rightSilde() {
		if (style == MONTH_STYLE) {
			
			if (mShowDate.month == 12) {
				mShowDate.month = 1;
				mShowDate.year += 1;
			} else {
				mShowDate.month += 1;
			}
			//mShowDate.day = lastShowDate.day;
		}
		//System.out.println("mShowDate.day--->"+mShowDate.day);
		isTurnSpecialDay=false;
		update();
	//	isTurnSpecialDay=false;
	}
//向左滑动
	public void leftSilde() {
		
		if (style == MONTH_STYLE) {
			if (mShowDate.month == 1) {
				mShowDate.month = 12;
				mShowDate.year -= 1;
			} else {
				mShowDate.month -= 1;
			}
		//	mShowDate.day = lastShowDate.day;
		} 
//		isTurnSpecialDay=true;
		isTurnSpecialDay=false;
		update();
//		isTurnSpecialDay=false;
	}
	
	public void turnToSpeDay(CustomDate date){
		mShowDate.year=date.year;
		mShowDate.month=date.month;
		mShowDate.day=date.day;
		isTurnSpecialDay=true;
		update();
		isTurnSpecialDay=false;
	}
}
