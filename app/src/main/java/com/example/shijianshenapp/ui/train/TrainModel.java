package com.example.shijianshenapp.ui.train;

import android.content.Context;

import com.ayma.base.mvp.ModelParamCallBack;
import com.example.shijianshenapp.R;
import com.example.shijianshenapp.base.BaseModel;
import com.example.shijianshenapp.bean.TrainBean;

import org.litepal.Operator;

import java.util.ArrayList;
import java.util.List;

public class TrainModel extends BaseModel implements ITrainModel {
    public TrainModel(Context context) {
        super(context);
    }

    @Override
    public void fetchTrainData(ModelParamCallBack<List<TrainBean>> modelParamCallBack) {
        List<TrainBean> list = Operator.findAll(TrainBean.class);

        if (list.isEmpty()){

            //1
            TrainBean data1 = new TrainBean();
            data1.setHeadImg(R.drawable.train_01);
            data1.setTitle("每天坚持12分钟全身HIIT运动（疯狂燃脂，快速瘦身）");
            data1.setLike(false);
            data1.setLikeNumber(147777);
            data1.setWatchNumber("586.2万");
            data1.setTime("12:43");
            data1.setMessage("你们想要的第二个HIIT运动在这里，一共12分钟，这次是摇滚乐风格，希望你们能快乐地，有激情地运动。欢迎你们留言和跟我一起打卡。");
            list.add(data1);

            //2
            TrainBean data2 = new TrainBean();
            data2.setHeadImg(R.drawable.train_02);
            data2.setTitle("20分钟全身运动（和肥肉说拜拜，快速见效）");
            data2.setLike(true);
            data2.setLikeNumber(1007);
            data2.setWatchNumber("35.2万");
            data2.setTime("20:12");
            data2.setMessage("这个训练都是复合动作-这样的动作最健康，燃烧最多卡路里。男女都可以做。如果你觉得太难可以休息多一点（比如做30秒，休息30秒）每天都可以做，欢迎你们留言和打卡。");
            list.add(data2);

            //3
            TrainBean data3 = new TrainBean();
            data3.setHeadImg(R.drawable.train_03);
            data3.setTitle("高强度5分钟腹肌运动（快速见效）");
            data3.setLike(false);
            data3.setLikeNumber(41000);
            data3.setWatchNumber("151.9万");
            data3.setTime("05:28");
            data3.setMessage("我的微博：Kamil小波健身,5个动作，每个动作30秒，不休息，做两组。如果太难，你可以暂停视频休息，如果你还有力气，可以多做几组。每天做或隔一天做，加上健康的饮食，很快你可以练出腹肌。");
            list.add(data3);

            //4
            TrainBean data4 = new TrainBean();
            data4.setHeadImg(R.drawable.train_04);
            data4.setTitle("【每天8分钟】30天练出腹肌挑战");
            data4.setLike(false);
            data4.setLikeNumber(19200);
            data4.setWatchNumber("78.2万");
            data4.setTime("08:36");
            data4.setMessage("如果你想挑战30天练出腹肌，除了跟练视频，饮食也非常重要。我的建议是: 多吃蛋白质（鸡蛋，豆腐，鱼肉等），减少碳水摄入（米饭，面条等）。可以给我留言打卡，我相信你们可以做到，加油！期待你们的变化。");
            list.add(data4);

            //5
            TrainBean data5 = new TrainBean();
            data5.setHeadImg(R.drawable.train_05);
            data5.setTitle("9分钟全身运动 (无器材,高强度)，增肌减脂，两周见效");
            data5.setLike(false);
            data5.setLikeNumber(72067);
            data5.setWatchNumber("155.2万");
            data5.setTime("09:23");
                data5.setMessage("你好，这个9分钟运动一共9个动作，每个动作45秒，15秒休息，如果你们可以做完9分钟，那你们很厉害。男生女生都来挑战吧！bgm: MDK2 Theme Song");
            list.add(data5);

            //6
            TrainBean data6 = new TrainBean();
            data6.setHeadImg(R.drawable.train_06);
            data6.setTitle("12分钟HIIT高效全身燃脂（进阶版）");
            data6.setLike(false);
            data6.setLikeNumber(16024);
            data6.setWatchNumber("58.8万");
            data6.setTime("12:21");
            data6.setMessage("做这个训练前你应该先好好热身一下。如果你是健身新手我不建议你做这个训练，因为它很难-你可以找我一些更简单的跟练视频，这个训练强度很大，所以不用每天练，可以隔一天做一次，男生女生都可以跟我一起打卡。");
            list.add(data6);

            //7
            TrainBean data7 = new TrainBean();
            data7.setHeadImg(R.drawable.train_07);
            data7.setTitle("8分钟初级全身运动（新手友好，无器材）增肌减脂");
            data7.setLike(false);
            data7.setLikeNumber(18099);
            data7.setWatchNumber("39.6万");
            data7.setTime("08:10");
                data7.setMessage("你们好，这是8分钟初级全身运动，一共8个动作，适合新手，你们可以每天做，也可以隔一天做一次。如果你们觉得太简单，可以去看我的9分钟全身运动。");
            list.add(data7);

            //8
            TrainBean data8 = new TrainBean();
            data8.setHeadImg(R.drawable.train_08);
            data8.setTitle("5分钟肩部+二头肌运动（增加手臂力量，无健身器材）");
            data8.setLike(true);
            data8.setLikeNumber(2008);
            data8.setWatchNumber("55.6万");
            data8.setTime("06:23");
                data8.setMessage("你们好，这个视频我要带你们做5分钟肩部和二头肌锻炼，这些动作会增强你的手臂力量和让肩膀更好看 如果你们有哑铃，也可以直接用哑铃做。做这个运动前要热身好，每个动作都要保持背部打直。你们可以隔一天做一次。可以给我留言打卡，告诉我你们身体的变化。");
            list.add(data8);

            //9
            TrainBean data9 = new TrainBean();
            data9.setHeadImg(R.drawable.train_09);
            data9.setTitle("每天6分钟站着放松身体释放压力（缓解肌肉紧，适合所有人）");
            data9.setLike(false);
            data9.setLikeNumber(5274);
            data9.setWatchNumber("10.7万");
            data9.setTime("06:43");
                data9.setMessage("太紧的肌肉可以出各种姿势问题，让你身体痛，睡觉不好。大部分人会有紧肌肉问题，如果太严重需要看物理治疗师或一辈子觉得不舒服。这个训练会帮你预防或-如果还不是很严重-解决紧肌肉的问题。你每天做这个训练你身体会很感谢你。随时随地都可以做。除了第二个动作, 其他动作你都要慢慢，非常准确的做，感觉到拉伸。");
            list.add(data9);

            //10
            TrainBean data10 = new TrainBean();
            data10.setHeadImg(R.drawable.train_10);
            data10.setTitle("完整版全身热身运动（让你体力增强）");
            data10.setLike(false);
            data10.setLikeNumber(23067);
            data10.setWatchNumber("34.9万");
            data10.setTime("03:04");
            data10.setMessage("你好。如果你热身得好，你的体力会增强20%。怎么热身好呢？");
            list.add(data10);

            Operator.saveAll(list);
        }

        modelParamCallBack.requestSuccess(list);
    }
}
