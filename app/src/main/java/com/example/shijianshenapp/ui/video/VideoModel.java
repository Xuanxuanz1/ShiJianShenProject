package com.example.shijianshenapp.ui.video;

import android.content.Context;

import com.ayma.base.mvp.ModelCallBack;
import com.ayma.base.mvp.ModelParamCallBack;
import com.example.shijianshenapp.R;
import com.example.shijianshenapp.base.BaseModel;
import com.example.shijianshenapp.bean.TrainBean;
import com.example.shijianshenapp.bean.TrainProgramBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VideoModel extends BaseModel implements IVideoModel {

    private List<TrainBean> list = new ArrayList<>();
    private Map<String,List<TrainProgramBean>> trainProMap = new HashMap<>();

    public VideoModel(Context context) {
        super(context);
    }


    @Override
    public void fetchVideoData(ModelCallBack modelCallBack) {

        //1
        TrainBean data1 = new TrainBean();
        data1.setHeadImg(R.drawable.train_01);
        data1.setTitle("每天坚持12分钟全身HIIT运动（疯狂燃脂，快速瘦身）");
        data1.setMessage("你们想要的第二个HIIT运动在这里，一共12分钟，这次是摇滚乐风格，希望你们能快乐地，有激情地运动。欢迎你们留言和跟我一起打卡。");
        data1.setLike(false);
        data1.setLikeNumber(147777);
        data1.setWatchNumber("586.2万");
        data1.setTime("12:43");
        list.add(data1);


        //2
        TrainBean data2 = new TrainBean();
        data2.setHeadImg(R.drawable.train_02);
        data2.setTitle("20分钟全身运动（和肥肉说拜拜，快速见效）");
        data2.setMessage("这个训练都是复合动作-这样的动作最健康，燃烧最多卡路里。男女都可以做。如果你觉得太难可以休息多一点（比如做30秒，休息30秒）每天都可以做，欢迎你们留言和打卡。");
        data2.setLike(true);
        data2.setLikeNumber(1007);
        data2.setWatchNumber("35.2万");
        data2.setTime("20:12");
        list.add(data2);

        //3
        TrainBean data3 = new TrainBean();
        data3.setHeadImg(R.drawable.train_03);
        data3.setTitle("高强度5分钟腹肌运动（快速见效）");
        data3.setMessage("我的微博：Kamil小波健身,5个动作，每个动作30秒，不休息，做两组。如果太难，你可以暂停视频休息，如果你还有力气，可以多做几组。每天做或隔一天做，加上健康的饮食，很快你可以练出腹肌。");
        data3.setLike(false);
        data3.setLikeNumber(41000);
        data3.setWatchNumber("151.9万");
        data3.setTime("05:28");
        list.add(data3);

        //4
        TrainBean data4 = new TrainBean();
        data4.setHeadImg(R.drawable.train_04);
        data4.setTitle("【每天8分钟】30天练出腹肌挑战");
        data4.setMessage("如果你想挑战30天练出腹肌，除了跟练视频，饮食也非常重要。我的建议是: 多吃蛋白质（鸡蛋，豆腐，鱼肉等），减少碳水摄入（米饭，面条等）。可以给我留言打卡，我相信你们可以做到，加油！期待你们的变化。");
        data4.setLike(false);
        data4.setLikeNumber(19200);
        data4.setWatchNumber("78.2万");
        data4.setTime("08:36");
        list.add(data4);

        //5
        TrainBean data5 = new TrainBean();
        data5.setHeadImg(R.drawable.train_05);
        data5.setTitle("9分钟全身运动 (无器材,高强度)，增肌减脂，两周见效");
        data5.setMessage("你好，这个9分钟运动一共9个动作，每个动作45秒，15秒休息，如果你们可以做完9分钟，那你们很厉害。男生女生都来挑战吧！bgm: MDK2 Theme Song");
        data5.setLike(false);
        data5.setLikeNumber(72067);
        data5.setWatchNumber("155.2万");
        data5.setTime("09:23");
        list.add(data5);

        //6
        TrainBean data6 = new TrainBean();
        data6.setHeadImg(R.drawable.train_06);
        data6.setTitle("12分钟HIIT高效全身燃脂（进阶版）");
        data6.setMessage("做这个训练前你应该先好好热身一下。如果你是健身新手我不建议你做这个训练，因为它很难-你可以找我一些更简单的跟练视频，这个训练强度很大，所以不用每天练，可以隔一天做一次，男生女生都可以跟我一起打卡。");
        data6.setLike(false);
        data6.setLikeNumber(16024);
        data6.setWatchNumber("58.8万");
        data6.setTime("12:21");
        list.add(data6);

        //7
        TrainBean data7 = new TrainBean();
        data7.setHeadImg(R.drawable.train_07);
        data7.setTitle("8分钟初级全身运动（新手友好，无器材）增肌减脂");
        data7.setMessage("你们好，这是8分钟初级全身运动，一共8个动作，适合新手，你们可以每天做，也可以隔一天做一次。如果你们觉得太简单，可以去看我的9分钟全身运动。");
        data7.setLike(false);
        data7.setLikeNumber(18099);
        data7.setWatchNumber("39.6万");
        data7.setTime("08:10");
        list.add(data7);

        //8
        TrainBean data8 = new TrainBean();
        data8.setHeadImg(R.drawable.train_08);
        data8.setTitle("5分钟肩部+二头肌运动（增加手臂力量，无健身器材）");
        data8.setMessage("你们好，这个视频我要带你们做5分钟肩部和二头肌锻炼，这些动作会增强你的手臂力量和让肩膀更好看 如果你们有哑铃，也可以直接用哑铃做。做这个运动前要热身好，每个动作都要保持背部打直。你们可以隔一天做一次。可以给我留言打卡，告诉我你们身体的变化。");
        data8.setLike(true);
        data8.setLikeNumber(2008);
        data8.setWatchNumber("55.6万");
        data8.setTime("06:23");
        list.add(data8);

        //9
        TrainBean data9 = new TrainBean();
        data9.setHeadImg(R.drawable.train_09);
        data9.setTitle("每天6分钟站着放松身体释放压力（缓解肌肉紧，适合所有人）");
        data9.setMessage("太紧的肌肉可以出各种姿势问题，让你身体痛，睡觉不好。大部分人会有紧肌肉问题，如果太严重需要看物理治疗师或一辈子觉得不舒服。这个训练会帮你预防或-如果还不是很严重-解决紧肌肉的问题。你每天做这个训练你身体会很感谢你。随时随地都可以做。除了第二个动作, 其他动作你都要慢慢，非常准确的做，感觉到拉伸。");
        data9.setLike(false);
        data9.setLikeNumber(5274);
        data9.setWatchNumber("10.7万");
        data9.setTime("06:43");
        list.add(data9);

        //10
        TrainBean data10 = new TrainBean();
        data10.setHeadImg(R.drawable.train_10);
        data10.setTitle("完整版全身热身运动（让你体力增强）");
        data10.setMessage("你好。如果你热身得好，你的体力会增强20%。怎么热身好呢？");
        data10.setLike(false);
        data10.setLikeNumber(23067);
        data10.setWatchNumber("34.9万");
        data10.setTime("03:04");
        list.add(data10);


        //训练项目
        //1、
        List<TrainProgramBean> trainPro1 = new ArrayList<>();
        TrainProgramBean tp1 = new TrainProgramBean();
        tp1.setTrainName("开合跳");
        tp1.setTrainTime("30");
        tp1.setTrainImg(R.drawable.video_0_train_0);
        tp1.setTrain(false);
        trainPro1.add(tp1);
        TrainProgramBean tp2 = new TrainProgramBean();
        tp2.setTrainName("踢腿");
        tp2.setTrainTime("30");
        tp2.setTrainImg(R.drawable.video_0_train_1);
        tp2.setTrain(false);
        trainPro1.add(tp2);
        TrainProgramBean tp3 = new TrainProgramBean();
        tp3.setTrainName("爬行俯卧撑");
        tp3.setTrainTime("30");
        tp3.setTrainImg(R.drawable.video_0_train_2);
        tp3.setTrain(false);
        trainPro1.add(tp3);
        TrainProgramBean tp4 = new TrainProgramBean();
        tp4.setTrainName("左右腿交替手触地");
        tp4.setTrainTime("30");
        tp4.setTrainImg(R.drawable.video_0_train_3);
        tp4.setTrain(false);
        trainPro1.add(tp4);
        TrainProgramBean tp5 = new TrainProgramBean();
        tp5.setTrainName("Ｘ型大跳");
        tp5.setTrainTime("30");
        tp5.setTrainImg(R.drawable.video_0_train_4);
        tp5.setTrain(false);
        trainPro1.add(tp5);
        trainProMap.put("0",trainPro1);


        //2、
        List<TrainProgramBean> trainPro2 = new ArrayList<>();
        TrainProgramBean tp6 = new TrainProgramBean();
        tp6.setTrainName("踢屁股");
        tp6.setTrainTime("45");
        tp6.setTrainImg(R.drawable.video_1_train_0);
        tp6.setTrain(false);
        trainPro2.add(tp6);
        TrainProgramBean tp7 = new TrainProgramBean();
        tp7.setTrainName("深蹲跳");
        tp7.setTrainTime("45");
        tp7.setTrainImg(R.drawable.video_1_train_1);
        tp7.setTrain(false);
        trainPro2.add(tp7);
        TrainProgramBean tp8 = new TrainProgramBean();
        tp8.setTrainName("拳击小跑");
        tp8.setTrainTime("45");
        tp8.setTrainImg(R.drawable.video_1_train_2);
        tp8.setTrain(false);
        trainPro2.add(tp8);
        TrainProgramBean tp9 = new TrainProgramBean();
        tp9.setTrainName("半波比俯卧撑");
        tp9.setTrainTime("45");
        tp9.setTrainImg(R.drawable.video_1_train_3);
        tp9.setTrain(false);
        trainPro2.add(tp9);
        TrainProgramBean tp10 = new TrainProgramBean();
        tp10.setTrainName("单腿向上跳");
        tp10.setTrainTime("45");
        tp10.setTrainImg(R.drawable.video_1_train_4);
        tp10.setTrain(false);
        trainPro2.add(tp10);
        TrainProgramBean tp11 = new TrainProgramBean();
        tp11.setTrainName("臀推");
        tp11.setTrainTime("45");
        tp11.setTrainImg(R.drawable.video_1_train_5);
        tp11.setTrain(false);
        trainPro2.add(tp11);
        TrainProgramBean tp12 = new TrainProgramBean();
        tp12.setTrainName("向上抬腿");
        tp12.setTrainTime("45");
        tp12.setTrainImg(R.drawable.video_1_train_6);
        tp12.setTrain(false);
        trainPro2.add(tp12);
        TrainProgramBean tp13 = new TrainProgramBean();
        tp13.setTrainName("交替摸腿");
        tp13.setTrainTime("45");
        tp13.setTrainImg(R.drawable.video_1_train_7);
        tp13.setTrain(false);
        trainPro2.add(tp13);
        TrainProgramBean tp14 = new TrainProgramBean();
        tp14.setTrainName("臀屈伸踢腿");
        tp14.setTrainTime("45");
        tp14.setTrainImg(R.drawable.video_1_train_8);
        tp14.setTrain(false);
        trainPro2.add(tp14);
        TrainProgramBean tp15 = new TrainProgramBean();
        tp15.setTrainName("开合跳");
        tp15.setTrainTime("45");
        tp15.setTrainImg(R.drawable.video_1_train_9);
        tp15.setTrain(false);
        trainPro2.add(tp15);
        trainProMap.put("1",trainPro2);


        //3、
        List<TrainProgramBean> trainPro3 = new ArrayList<>();
        TrainProgramBean tp16 = new TrainProgramBean();
        tp16.setTrainName("蝴蝶卷腹");
        tp16.setTrainTime("30");
        tp16.setTrainImg(R.drawable.video_2_train_0);
        tp16.setTrain(false);
        trainPro3.add(tp16);
        TrainProgramBean tp17 = new TrainProgramBean();
        tp17.setTrainName("仰卧抬腿");
        tp17.setTrainTime("30");
        tp17.setTrainImg(R.drawable.video_2_train_1);
        tp17.setTrain(false);
        trainPro3.add(tp17);
        TrainProgramBean tp18 = new TrainProgramBean();
        tp18.setTrainName("仰卧剪刀腿");
        tp18.setTrainTime("30");
        tp18.setTrainImg(R.drawable.video_2_train_2);
        tp18.setTrain(false);
        trainPro3.add(tp18);
        TrainProgramBean tp19 = new TrainProgramBean();
        tp19.setTrainName("左右交替摸腿");
        tp19.setTrainTime("30");
        tp19.setTrainImg(R.drawable.video_2_train_3);
        tp19.setTrain(false);
        trainPro3.add(tp19);
        TrainProgramBean tp20 = new TrainProgramBean();
        tp20.setTrainName("坐姿交替收腿");
        tp20.setTrainTime("30");
        tp20.setTrainImg(R.drawable.video_2_train_4);
        tp20.setTrain(false);
        trainPro3.add(tp20);
        trainProMap.put("2",trainPro3);


        //4、
        List<TrainProgramBean> trainPro4 = new ArrayList<>();
        TrainProgramBean tp21 = new TrainProgramBean();
        tp21.setTrainName("仰卧起坐+反向卷腹");
        tp21.setTrainTime("60");
        tp21.setTrainImg(R.drawable.video_3_train_0);
        tp21.setTrain(false);
        trainPro4.add(tp21);
        TrainProgramBean tp22 = new TrainProgramBean();
        tp22.setTrainName("抬腿卷腹");
        tp22.setTrainTime("60");
        tp22.setTrainImg(R.drawable.video_3_train_1);
        tp22.setTrain(false);
        trainPro4.add(tp22);
        TrainProgramBean tp23 = new TrainProgramBean();
        tp23.setTrainName("侧平板起身（左右各30s）");
        tp23.setTrainTime("60");
        tp23.setTrainImg(R.drawable.video_3_train_2);
        tp23.setTrain(false);
        trainPro4.add(tp23);
        TrainProgramBean tp24 = new TrainProgramBean();
        tp24.setTrainName("仰卧抬腿");
        tp24.setTrainTime("60");
        tp24.setTrainImg(R.drawable.video_3_train_3);
        tp24.setTrain(false);
        trainPro4.add(tp24);
        TrainProgramBean tp25 = new TrainProgramBean();
        tp25.setTrainName("仰卧腿开合");
        tp25.setTrainTime("60");
        tp25.setTrainImg(R.drawable.video_3_train_4);
        tp25.setTrain(false);
        trainPro4.add(tp24);
        TrainProgramBean tp26 = new TrainProgramBean();
        tp26.setTrainName("空中屈腿自行车");
        tp26.setTrainTime("60");
        tp26.setTrainImg(R.drawable.video_3_train_5);
        tp26.setTrain(false);
        trainPro4.add(tp26);
        TrainProgramBean tp27 = new TrainProgramBean();
        tp27.setTrainName("侧平板卷腹（左右各30s）");
        tp27.setTrainTime("60");
        tp27.setTrainImg(R.drawable.video_3_train_6);
        tp27.setTrain(false);
        trainPro4.add(tp27);
        TrainProgramBean tp28 = new TrainProgramBean();
        tp28.setTrainName("膝盖手掌平板支撑");
        tp28.setTrainTime("60");
        tp28.setTrainImg(R.drawable.video_3_train_7);
        tp28.setTrain(false);
        trainPro4.add(tp28);
        TrainProgramBean tp29 = new TrainProgramBean();
        tp29.setTrainName("折叠卷腹");
        tp29.setTrainTime("60");
        tp29.setTrainImg(R.drawable.video_3_train_8);
        tp29.setTrain(false);
        trainPro4.add(tp29);
        trainProMap.put("3",trainPro4);


        //5、
        List<TrainProgramBean> trainPro5 = new ArrayList<>();
        TrainProgramBean tp30 = new TrainProgramBean();
        tp30.setTrainName("弓步+深蹲跳");
        tp30.setTrainTime("45");
        tp30.setTrainImg(R.drawable.video_4_train_0);
        tp30.setTrain(false);
        trainPro5.add(tp30);
        TrainProgramBean tp31 = new TrainProgramBean();
        tp31.setTrainName("宽距俯卧撑x3+标准俯卧撑x3+窄距俯卧撑x3");
        tp31.setTrainTime("45");
        tp31.setTrainImg(R.drawable.video_4_train_1);
        tp31.setTrain(false);
        trainPro5.add(tp31);
        TrainProgramBean tp32 = new TrainProgramBean();
        tp32.setTrainName("单边平板支撑，20s后换边");
        tp32.setTrainTime("45");
        tp32.setTrainImg(R.drawable.video_4_train_2);
        tp32.setTrain(false);
        trainPro5.add(tp32);
        TrainProgramBean tp33 = new TrainProgramBean();
        tp33.setTrainName("跪地动态平板支撑");
        tp33.setTrainTime("45");
        tp33.setTrainImg(R.drawable.video_4_train_3);
        tp33.setTrain(false);
        trainPro5.add(tp33);
        TrainProgramBean tp34 = new TrainProgramBean();
        tp34.setTrainName("摸肩摸腿俯卧撑");
        tp34.setTrainTime("45");
        tp34.setTrainImg(R.drawable.video_4_train_4);
        tp34.setTrain(false);
        trainPro5.add(tp34);
        TrainProgramBean tp35 = new TrainProgramBean();
        tp35.setTrainName("单腿臀桥，20s后换腿");
        tp35.setTrainTime("45");
        tp35.setTrainImg(R.drawable.video_4_train_5);
        tp35.setTrain(false);
        trainPro5.add(tp35);
        TrainProgramBean tp36 = new TrainProgramBean();
        tp36.setTrainName("平板支撑，轮换手肘撑和手掌撑");
        tp36.setTrainTime("45");
        tp36.setTrainImg(R.drawable.video_4_train_6);
        tp36.setTrain(false);
        trainPro5.add(tp36);
        TrainProgramBean tp37 = new TrainProgramBean();
        tp37.setTrainName("支撑侧提膝");
        tp37.setTrainTime("45");
        tp37.setTrainImg(R.drawable.video_4_train_7);
        tp37.setTrain(false);
        trainPro5.add(tp37);
        TrainProgramBean tp38 = new TrainProgramBean();
        tp38.setTrainName("静态俯卧撑");
        tp38.setTrainTime("45");
        tp38.setTrainImg(R.drawable.video_4_train_8);
        tp38.setTrain(false);
        trainPro5.add(tp38);
        trainProMap.put("4",trainPro5);


        //6、
        List<TrainProgramBean> trainPro6 = new ArrayList<>();
        TrainProgramBean tp39 = new TrainProgramBean();
        tp39.setTrainName("高抬腿");
        tp39.setTrainTime("40");
        tp39.setTrainImg(R.drawable.video_5_train_0);
        tp39.setTrain(false);
        trainPro6.add(tp39);
        TrainProgramBean tp40 = new TrainProgramBean();
        tp40.setTrainName("俯身左右踢腿");
        tp40.setTrainTime("40");
        tp40.setTrainImg(R.drawable.video_5_train_1);
        tp40.setTrain(false);
        trainPro6.add(tp40);
        TrainProgramBean tp41 = new TrainProgramBean();
        tp41.setTrainName("弓步+深蹲跳");
        tp41.setTrainTime("40");
        tp41.setTrainImg(R.drawable.video_5_train_2);
        tp41.setTrain(false);
        trainPro6.add(tp41);
        TrainProgramBean tp42 = new TrainProgramBean();
        tp42.setTrainName("俯身左右交替摸腿");
        tp42.setTrainTime("40");
        tp42.setTrainImg(R.drawable.video_5_train_3);
        tp42.setTrain(false);
        trainPro6.add(tp42);
        trainProMap.put("5",trainPro6);


        //7、
        List<TrainProgramBean> trainPro7 = new ArrayList<>();
        TrainProgramBean tp43 = new TrainProgramBean();
        tp43.setTrainName("前后爬行俯撑");
        tp43.setTrainTime("45");
        tp43.setTrainImg(R.drawable.video_6_train_0);
        tp43.setTrain(false);
        trainPro7.add(tp43);
        TrainProgramBean tp44 = new TrainProgramBean();
        tp44.setTrainName("俯卧撑+抬腿");
        tp44.setTrainTime("45");
        tp44.setTrainImg(R.drawable.video_6_train_1);
        tp44.setTrain(false);
        trainPro7.add(tp44);
        TrainProgramBean tp45 = new TrainProgramBean();
        tp45.setTrainName("俯卧撑+抬手臂");
        tp45.setTrainTime("45");
        tp45.setTrainImg(R.drawable.video_6_train_2);
        tp45.setTrain(false);
        trainPro7.add(tp45);
        TrainProgramBean tp46 = new TrainProgramBean();
        tp46.setTrainName("人体翻滚90°");
        tp46.setTrainTime("45");
        tp46.setTrainImg(R.drawable.video_6_train_3);
        tp46.setTrain(false);
        trainPro7.add(tp46);
        TrainProgramBean tp47 = new TrainProgramBean();
        tp47.setTrainName("前后爬行俯卧撑");
        tp47.setTrainTime("45");
        tp47.setTrainImg(R.drawable.video_6_train_4);
        tp47.setTrain(false);
        trainPro7.add(tp47);
        TrainProgramBean tp48 = new TrainProgramBean();
        tp48.setTrainName("宽距俯卧撑x3+标准俯卧撑x3+窄距俯卧撑x3");
        tp48.setTrainTime("45");
        tp48.setTrainImg(R.drawable.video_6_train_5);
        tp48.setTrain(false);
        trainPro7.add(tp48);
        TrainProgramBean tp49 = new TrainProgramBean();
        tp49.setTrainName("窄手俯卧撑");
        tp49.setTrainTime("45");
        tp49.setTrainImg(R.drawable.video_6_train_6);
        tp49.setTrain(false);
        trainPro7.add(tp49);
        TrainProgramBean tp50 = new TrainProgramBean();
        tp50.setTrainName("膝盖卧撑");
        tp50.setTrainTime("45");
        tp50.setTrainImg(R.drawable.video_6_train_7);
        tp50.setTrain(false);
        trainPro7.add(tp50);
        trainProMap.put("6",trainPro7);


        //8、
        List<TrainProgramBean> trainPro8 = new ArrayList<>();
        TrainProgramBean tp51 = new TrainProgramBean();
        tp51.setTrainName("锤式卧弯举（配重8公斤）");
        tp51.setTrainTime("45");
        tp51.setTrainImg(R.drawable.video_7_train_0);
        tp51.setTrain(false);
        trainPro8.add(tp51);
        TrainProgramBean tp52 = new TrainProgramBean();
        tp52.setTrainName("正握弯举（配重8公斤）");
        tp52.setTrainTime("45");
        tp52.setTrainImg(R.drawable.video_7_train_1);
        tp52.setTrain(false);
        trainPro8.add(tp52);
        TrainProgramBean tp53 = new TrainProgramBean();
        tp53.setTrainName("反握弯举（配重8公斤）");
        tp53.setTrainTime("45");
        tp53.setTrainImg(R.drawable.video_7_train_2);
        tp53.setTrain(false);
        trainPro8.add(tp53);
        TrainProgramBean tp54 = new TrainProgramBean();
        tp54.setTrainName("正握抬臂（配重8公斤）");
        tp54.setTrainTime("45");
        tp54.setTrainImg(R.drawable.video_7_train_3);
        tp54.setTrain(false);
        trainPro8.add(tp54);
        TrainProgramBean tp55 = new TrainProgramBean();
        tp55.setTrainName("正握弯举+伸直双臂（配重8公斤）");
        tp55.setTrainTime("45");
        tp55.setTrainImg(R.drawable.video_7_train_4);
        tp55.setTrain(false);
        trainPro8.add(tp55);
        trainProMap.put("7",trainPro8);


        //9、
        List<TrainProgramBean> trainPro9 = new ArrayList<>();
        TrainProgramBean tp56 = new TrainProgramBean();
        tp56.setTrainName("左右转头");
        tp56.setTrainTime("60");
        tp56.setTrainImg(R.drawable.video_8_train_0);
        tp56.setTrain(false);
        trainPro9.add(tp56);
        TrainProgramBean tp57 = new TrainProgramBean();
        tp57.setTrainName("八字形甩手臂各30秒");
        tp57.setTrainTime("60");
        tp57.setTrainImg(R.drawable.video_8_train_1);
        tp57.setTrain(false);
        trainPro9.add(tp57);
        TrainProgramBean tp58 = new TrainProgramBean();
        tp58.setTrainName("弯腰挺身");
        tp58.setTrainTime("60");
        tp58.setTrainImg(R.drawable.video_8_train_2);
        tp58.setTrain(false);
        trainPro9.add(tp58);
        TrainProgramBean tp59 = new TrainProgramBean();
        tp59.setTrainName("全身大回环");
        tp59.setTrainTime("60");
        tp59.setTrainImg(R.drawable.video_8_train_3);
        tp59.setTrain(false);
        trainPro9.add(tp59);
        TrainProgramBean tp60 = new TrainProgramBean();
        tp60.setTrainName("腰部转动");
        tp60.setTrainTime("60");
        tp60.setTrainImg(R.drawable.video_8_train_4);
        tp60.setTrain(false);
        trainPro9.add(tp60);
        TrainProgramBean tp61 = new TrainProgramBean();
        tp61.setTrainName("双手交叉俯身转动腰部");
        tp61.setTrainTime("60");
        tp61.setTrainImg(R.drawable.video_8_train_5);
        tp61.setTrain(false);
        trainPro9.add(tp61);
        trainProMap.put("8",trainPro9);


        //10、
        List<TrainProgramBean> trainPro10 = new ArrayList<>();
        TrainProgramBean tp62 = new TrainProgramBean();
        tp62.setTrainName("肌肉升温");
        tp62.setTrainTime("45");
        tp62.setTrainImg(R.drawable.video_0_train_0);
        tp62.setTrain(false);
        trainPro10.add(tp62);
        TrainProgramBean tp63 = new TrainProgramBean();
        tp63.setTrainName("动态拉伸");
        tp63.setTrainTime("45");
        tp63.setTrainImg(R.drawable.video_4_train_0);
        tp63.setTrain(false);
        trainPro10.add(tp63);
        TrainProgramBean tp64 = new TrainProgramBean();
        tp64.setTrainName("唤醒平时不用的肌肉");
        tp64.setTrainTime("45");
        tp64.setTrainImg(R.drawable.video_8_train_1);
        tp64.setTrain(false);
        trainPro10.add(tp64);
        trainProMap.put("9",trainPro10);

        modelCallBack.requestSuccess();
    }

    @Override
    public void trainSuccess(int position,int clickPosition, ModelCallBack modelCallBack) {
        getTrainPro(position).get(clickPosition).setTrain(true);
        modelCallBack.requestSuccess();
    }

    @Override
    public TrainBean getTrainList(int position) {
        return list.get(position);
    }

    @Override
    public List<TrainProgramBean> getTrainPro(int position) {
        String number = String.valueOf(position);
        return trainProMap.get(number);
    }

}
