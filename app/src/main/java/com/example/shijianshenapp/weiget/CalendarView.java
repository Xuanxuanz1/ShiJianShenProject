package com.example.shijianshenapp.weiget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shijianshenapp.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

public class CalendarView extends ConstraintLayout {

    public class DayInfo{
        public int day;
        public DayType dayType;
        @Override
        public String toString() {
            return String.valueOf(day);
        }
    }

    public enum DayType{
        DAY_TYPE_NONE(0),
        DAY_TYPE_FORE(1),
        DAY_TYPE_NOW(2),
        DAY_TYPE_NEXT(3);
        private int value;
        DayType(int value){ this.value = value; }
        public int getValue(){ return value; }
    }

    private Context context;
    private TextView tvTitle;
    private GridView gridView;
    private final Calendar calendar = Calendar.getInstance();
    private static final int MAX_DAY_COUNT = 42;//最大格子数
    private DayInfo[] dayInfos = new DayInfo[MAX_DAY_COUNT];
    private CalendarAdapter adapter;

    public CalendarView(@NonNull Context context) {
        super(context);
        init(context);
        showCalendar(calendar);
    }

    public CalendarView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
        showCalendar(calendar);
    }

    private void init(Context context){
        this.context = context;
        View rootView = View.inflate(context, R.layout.calendar,null);
        gridView = rootView.findViewById(R.id.widgetCalendar_calendar);
        tvTitle = rootView.findViewById(R.id.widgetCalendar_txtTitle);
        rootView.findViewById(R.id.widgetCalendar_imgForeYear).setOnClickListener(navigatorClickLister);
        rootView.findViewById(R.id.widgetCalendar_imgForeMonth).setOnClickListener(navigatorClickLister);
        rootView.findViewById(R.id.widgetCalendar_imgNextMonth).setOnClickListener(navigatorClickLister);
        rootView.findViewById(R.id.widgetCalendar_imgNextYear).setOnClickListener(navigatorClickLister);

        this.addView(rootView);
    }

    private OnClickListener navigatorClickLister = new OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.widgetCalendar_imgForeYear:
                    calendar.add(Calendar.YEAR,-1);
                    break;
                case R.id.widgetCalendar_imgForeMonth:
                    calendar.add(Calendar.MONTH,-1);
                    break;
                case R.id.widgetCalendar_imgNextYear:
                    calendar.add(Calendar.YEAR,1);
                    break;
                case R.id.widgetCalendar_imgNextMonth:
                    calendar.add(Calendar.MONTH,1);
                    break;
            }
            showCalendar(calendar);
        }
    };

    private void showCalendar(Calendar calendar){
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int centry = Integer.valueOf(String.valueOf(year).substring(0,2));//取年份前两位作为世纪数,世纪数-1
        int tmpYear = Integer.valueOf(String.valueOf(year).substring(2,4));//取年份后两位

        if (month  == 1 || month == 2){
//            该年的1、2月看作前一年的13月14月
            tmpYear-= 1;
            month += 12;
        }

        int firstOfWeek = (tmpYear + (tmpYear/4) + centry / 4 - 2 * centry + 26 * (month + 1) / 10) % 7;//计算本月第一天是周几
        if (firstOfWeek <= 0){
            firstOfWeek = 7 + firstOfWeek;
        }
        final  int firstDayIndex = firstOfWeek == 1 ? 7 : firstOfWeek -1 ;
        final  int dayCount = getDayCount(year,month);

//        处理本月数据
        for (int i = firstDayIndex;i < firstDayIndex + dayCount;i++){
            if (dayInfos[i] == null){
                dayInfos[i] = new DayInfo();
            }
            dayInfos[i].day = i - firstDayIndex + 1;
            dayInfos[i].dayType = DayType.DAY_TYPE_NOW;
        }

//        处理前一个月的数据
        calendar.add(Calendar.MONTH,-1);
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH) + 1;
        final int foreDayCount = getDayCount(year,month);
        for (int i = 0;i < firstDayIndex;i++){
            if (dayInfos[i] == null){
                dayInfos[i] = new DayInfo();
            }
            dayInfos[i].day = foreDayCount - firstDayIndex + i + 1;
            dayInfos[i].dayType = DayType.DAY_TYPE_FORE;
        }

//        处理下一个月的数据
        for (int i = 0;i < MAX_DAY_COUNT - dayCount - firstDayIndex;i++){
            if (dayInfos[firstDayIndex + dayCount + i] == null){
                dayInfos[firstDayIndex + dayCount + i] = new DayInfo();
            }
            dayInfos[firstDayIndex + dayCount + i].day = i + 1;
            dayInfos[firstDayIndex + dayCount + i].dayType = DayType.DAY_TYPE_NEXT;
        }

//        还原月份数据
        calendar.add(Calendar.MONTH,1);
        tvTitle.setText(new SimpleDateFormat("yyyy年MM月").format(calendar.getTime()));
        adapter = new CalendarAdapter(context,dayInfos);
        gridView.setAdapter(adapter);
    }

    private boolean isLeapYear(int year){
        return !((year % 4 == 0 && year % 100 != 0)) || year % 400 ==0;
    }

//    获取某年某月有多少天
    private int getDayCount(int year,int month){
        switch (month){
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
            case 13://其实是1月，当作上一年的13月看待
                return 31;
            case 2:
            case 14://其实是2月，当作上一年的14月看
                return isLeapYear(year)?28:29;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
        }
        return 0;
    }

//    判断两个Calendar中的日期是否相等
    private boolean isDateEqual(Calendar calendar1,Calendar calendar2){
        return (calendar.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR)
                && calendar1.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH)
                && (calendar1.get(Calendar.DATE) == calendar2.get(Calendar.DATE) || calendar1.get(Calendar.DATE)-1 == calendar2.get(Calendar.DATE))
        );
    }


    public class CalendarAdapter extends BaseAdapter{

        private Context context;
        private List<DayInfo> dayInfos = new ArrayList<>();
        private boolean isQiandao = false;

        public CalendarAdapter(Context context,DayInfo[] dayInfos){
            this.context = context;
            if (dayInfos != null && dayInfos.length > 0){
                this.dayInfos.addAll(Arrays.asList(dayInfos));
            }
        }


        @Override
        public int getCount() {
            return dayInfos == null ? 0 : dayInfos.size();
        }

        @Override
        public Object getItem(int position) {
            return dayInfos.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final DayInfo data = dayInfos.get(position);
            if (convertView == null){
                convertView = new TextView(context);
                AbsListView.LayoutParams caellLayoutParms = new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT,AbsListView.LayoutParams.MATCH_PARENT);
                convertView.setLayoutParams(caellLayoutParms);
                TextView tvtCell = ((TextView) convertView);
                tvtCell.setGravity(Gravity.CENTER);
                tvtCell.setPadding(10,15,10,15);
                tvtCell.getPaint().setFakeBoldText(true);
                tvtCell.setTextSize(TypedValue.COMPLEX_UNIT_DIP,17f);
            }

            TextView txtItem = ((TextView) convertView);
            txtItem.setText(data.toString());
            if (data.dayType == DayType.DAY_TYPE_FORE || data.dayType == DayType.DAY_TYPE_NEXT){
                txtItem.setTextColor(Color.DKGRAY);
            }
            else {
                txtItem.setTextColor(Color.BLACK);
            }

            Calendar tmpCalendar = Calendar.getInstance();
            tmpCalendar.setTimeInMillis(calendar.getTimeInMillis());
            tmpCalendar.set(Calendar.DAY_OF_MONTH,data.day);
            if (isDateEqual(Calendar.getInstance(),tmpCalendar) && data.dayType == DayType.DAY_TYPE_NOW){
                txtItem.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#66aaff")));
            }
            else if (data.dayType == DayType.DAY_TYPE_NOW){
                txtItem.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
            }
            else {
                txtItem.setBackgroundDrawable(new ColorDrawable(Color.LTGRAY));
            }

            OnClickListener listener = (v)->{
              switch (data.dayType){
                  case DAY_TYPE_FORE:
//                      转跳到前一个月
                      calendar.add(Calendar.MONTH,-1);
                      showCalendar(calendar);
                      Toast.makeText(context, data.toString(), Toast.LENGTH_SHORT).show();
                      break;
                  case DAY_TYPE_NOW:
                      Toast.makeText(context, data.toString(), Toast.LENGTH_SHORT).show();
                      break;
                  case DAY_TYPE_NEXT:
//                      转跳到下一个月
                      calendar.add(Calendar.MONTH,1);
                      showCalendar(calendar);
                      Toast.makeText(context, data.toString(), Toast.LENGTH_SHORT).show();
                      break;
              }
            };
            txtItem.setOnClickListener(listener);
            return convertView;
        }
    }
}
