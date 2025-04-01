一个使用方便且美观的时间选择器
这个模块不强制使用，根据个人需求而定，有时候使用原生方式在操作上更为方便快捷
注意事项：
    1.传入的startDate以及endDate如果为字符串类型则必须为yyyy-MM-dd HH:mm，否则不会弹出；
    2.如需修改选择器的字体颜色请修改res/values/colors.xml下的 date_picker_text_light、date_picker_text_dark的颜色值；
###使用示例一：
    private CustomDatePicker endDatePicker;//结束时间选择器
    if (endDatePicker == null) {
            endDatePicker = new CustomDatePicker(this, new CustomDatePicker.Callback() {
                @Override
                public void onTimeSelected(long timestamp) {
                    if (DateTimeUtils.compareDate4(DateTimeUtils.parseToDate(tvStartTime.getText().toString(), "yyyy-MM-dd\nHH:mm"), new Date(timestamp))) {
                        showToast("结束时间不能小于开始时间");
                        return;
                    }
                    tvEndTime.setText(DateTimeUtils.parseDate(timestamp, "yyyy-MM-dd\nHH:mm"));
                }
            }, "2020-04-10 00:00", "2021-04-10 00:00");//此种方式要求时间格式为：yyyy-MM-dd HH:mm
            // 是否允许点击屏幕或物理返回键关闭
            endDatePicker.setCancelable(true);
            // 是否显示时和分
            endDatePicker.setCanShowPreciseTime(true);
            // 是否允许循环滚动
            endDatePicker.setScrollLoop(true);
            // 是否允许滚动动画
            endDatePicker.setCanShowAnim(true);
        }
        endDatePicker.show(startDate);
        
###使用实例二：
    private CustomDatePicker endDatePicker;//结束时间选择器
    if (endDatePicker == null) {
            endDatePicker = new CustomDatePicker(this, new CustomDatePicker.Callback() {
                @Override
                public void onTimeSelected(long timestamp) {
                    if (DateTimeUtils.compareDate4(DateTimeUtils.parseToDate(tvStartTime.getText().toString(), "yyyy-MM-dd\nHH:mm"), new Date(timestamp))) {
                        showToast("结束时间不能小于开始时间");
                        return;
                    }
                    tvEndTime.setText(DateTimeUtils.parseDate(timestamp, "yyyy-MM-dd\nHH:mm"));
                }
            }, 1586771854, System.currentTimeMillis());
            // 是否允许点击屏幕或物理返回键关闭
            endDatePicker.setCancelable(true);
            // 是否显示时和分
            endDatePicker.setCanShowPreciseTime(true);
            // 是否允许循环滚动
            endDatePicker.setScrollLoop(true);
            // 是否允许滚动动画
            endDatePicker.setCanShowAnim(true);
        }
        endDatePicker.show(startDate);
