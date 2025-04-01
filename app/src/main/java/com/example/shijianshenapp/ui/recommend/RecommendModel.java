package com.example.shijianshenapp.ui.recommend;

import android.content.Context;

import com.ayma.base.mvp.ModelParamCallBack;
import com.example.shijianshenapp.R;
import com.example.shijianshenapp.base.BaseModel;
import com.example.shijianshenapp.bean.RecommendBean;
import com.example.shijianshenapp.bean.RecommendDetailBean;

import org.litepal.Operator;

import java.util.ArrayList;
import java.util.List;

public class RecommendModel extends BaseModel implements IRecommendModel {


    public RecommendModel(Context context) {
        super(context);
    }

    @Override
    public void fetchRecommendData(ModelParamCallBack<List<RecommendBean>> modelParamCallBack) {
        List<RecommendBean> list = Operator.findAll(RecommendBean.class);
        if (list.isEmpty()){
            List<RecommendDetailBean> beans = new ArrayList<>();

            String[] titles = new String[]{
                    "没时间去健身房？学会街健、自重训练，练出好身材！",
                    "推动全民健身，英派斯打造良好运动氛围",
                    "健身请避开这几个误区，否则你相当于白练了！",
                    "冬日健身热情高,开启全民健身之旅！",
                    "奔跑吧，少年！快来打卡民族特色健身操舞",
                    "立足体育惠民，青岛市市北区双山街道打造全民健身的“幸福版图”",
                    "缺少大块时间健身？几分钟高强度锻炼也有益",
                    "《2021中国健身行业数据报告》发布全国健身会员数连续5年增长"
            };

            String[] message = new String[]{
                    "那么，自重训练时应该注意什么，如何才能更好地锻炼呢？\n01 保持耐心\n 每个人都渴望速成，渴望能像视频中的大神一样做出酷炫的动作，但还是请你保持耐心，毕竟俄挺、龙旗这样的高难度动作，可不是看几个视频，做几个练习动作就能学会的。\n  同样，你所羡慕的那些肌肉、身材也是一点点积累、雕琢的结果，不要上来就拼命的训练，每次都练到力竭，这样只会磨损你的耐心。\n  很多人开始热情满满，中间看不到明显的效果就放弃了，所以，还请在开始之前做好准备，不要想着一口气吃成大胖子。\n02 饮食搭配\n  自重训练往往是一个人的路途，很同意就放飞自我，从而也很容易忽视饮食的作用，影响健身的效率。\n 其实无论健身房健身，或是街头健身，饮食都是不能忽视的方面，像是高蛋白的饮食，减少脂肪、糖类的摄入量，合理地规划自己的食谱……\n",
                    "  在“健康中国”战略的指导下,越来越多的群众开始重视运动健身,不少政企也同步将注意力转移在企业健身房的打造上,为更多职工提供便利、科学的运动方式。作为国内健康产业开拓者的英派斯,坚持创新突破,研发并生产出一套现代化、智慧化、多样化的智慧健身服务平台,致力于构建更高水平的全民健身公共服务体系。\n  全场景体育建设,争做行业标杆\n  12月8日,青岛市委宣传部组织20余家驻青中央、省级媒体及青岛本地媒体记者,参观考察青岛英派斯健康科技股份有限公司,并进行座谈。在座谈会过程中,与会领导对英派斯相关工作表示了高度肯定,同时也希望全新产业园能够加快建设的步伐。在未来,英派斯可以成为青岛品牌的新名片、健身器材行业的新标杆,为全民健身事业助力。",
                    "误区1、健身不拉伸\n  健身不拉伸的人后果相当严重，健身前不拉伸，健身的过程中容易出现肌肉拉伤、扭伤的问题，健身后不拉伸，容易出现肌肉酸疼，恢复速度慢，肌肉比较柴的问题。\n  因此健身前后拉伸是非常重要的，健身前拉伸以动态拉伸为主，可以提升关节的润滑度，慢慢提升体温，让身体逐渐进入运动状态，从而提升健身效果，更加安全的锻炼。健身后拉伸以静态拉伸为主，拉伸肌肉，可以减缓酸痛感，促进乳酸代谢，有助于身体的恢复。\n  误区2、健身后胡吃海喝\n  努力健身锻炼一小时的热量消耗不如胡吃海喝一顿摄入的热量高，如果你健身后没有管住嘴，选择大吃一顿，这样是无法瘦下来，练出好身材的，反而可能变胖。\n  减脂人群一定要管住嘴，控制每天的卡路里摄入在合理的范围内，而增肌人群要合理提升热量摄入，同时做到干净饮食，健身后可以适当加餐，补充优质蛋白食物，才能增肌的同时减少脂肪的堆积。",
                    "  12月21日下午，嘉兴市南湖区凌公塘公园内，晴冷的天气没有阻挡住市民健身的热情。 \n  据气象部门消息，受新一波冷空气影响，嘉兴市将有较明显的降温和风力增大过程，未来几天晨温又将破冰点。",
                    "运动之前要记得热身哟\n 热身的好处有哪些？\n 1.运动前热身，可以促进血液循环，让身体更快适应运动强度；\n 2.充分活动身体关节和肌肉，预防运动损伤；\n 3.做好心理准备，提升运动表现。\n如果运动前热身不到位，不仅需要更长时间进入运动状态，而且肌肉紧张、关节僵硬，会大大增加运动中抽筋、拉伤的风险。因此，运动前的热身对于远离运动伤痛至关重要！\n运动前的热身应该怎么做呢？\n 首先，可以做一些简单的身体动作，如：慢跑、高抬腿等，促进心率提高，刺激呼吸频率，提前适应运动状态。此外，进行动态拉伸也是个不错的选择，激活运动所需要的肌肉，并适当增加关节活动度，预防抽筋、拉伤等情况。\n奔跑吧，少年！\n一起运动，一起健康\n让我们动起来！",
                    "  近日，一条条崭新的健身路径在新都心亮相，新增的健身活动场所正在如火如荼地建设中……今年以来，青岛市市北区双山街道牢牢把握“办实事”基本着力点，紧扣群众关心关切，牢固树立“体育为民、体育惠民”的理念，坚持走进群众，充分了解群众健身诉求，把文体设施建设作为民生工程、健康工程来抓，有效促进辖区范围内全民健身活动的蓬勃发展，不断增强群众的获得感、幸福感、安全感，打造全民健身的“幸福版图”。\n体育设施安全保障持续强化\n  “以前晚上没有活动的地方，只能窝在家里看电视耍手机，现在装了这个，大人能锻炼，小孩能娱乐，别提有多好了。”在台柳路280号党建广场上，家住保儿馨都的郭先生边拉伸着腿边说道。今年，双山街道在党建广场、万科金色城品小区、保儿馨都小区及万科中心等地新增了健身路径，扩大健身器材覆盖面，提高群众健身便利度。目前，双山街道共有5条健身步道，32处文体活动广场，总面积达25860平方米，其中篮球场5个、足球场3个，羽毛球场和乒乓球场各1个，均有专人定期巡查、管理规范。“15分钟健身圈”日益完善，构建了覆盖全面的全民健身设施网络。",
                    "  新华社专电 因为缺少大块空闲时间健身而放弃锻炼?澳大利亚一项研究显示，即使每天只花几分钟时间进行高强度锻炼，比如快走、干体力活，哪怕是和孩子或宠物玩耍，都可能有助降低死于癌症或心脏病的风险。\n  悉尼大学研究人员随访超过2.5万名平均年龄61.8岁的英国居民，其中约56%为女性，平均随访时间6.9年。所有研究对象自述随访期间没有健身习惯，不过，其中一些人每天会有简短、剧烈的活动形式，如跑着赶公交车、走楼梯或做需要较多体力的家务活。\n  研究人员发现，哪怕每次只持续一两分钟，这些较高强度的活动也能令身体获得与经常健身类似的益处：与没有间歇性剧烈活动相比，每天分三次进行总长4至6分钟剧烈活动，关联心血管疾病死亡风险降低多至49%，关联癌症及全因死亡风险降低多至40%。研究显示，活动总时间越长越好：与不做任何剧烈活动的人相比，每天多达11次的短时剧烈活动关联心血管疾病死亡的风险降低65%，关联癌症死亡的风险降低49%。",
                    "  新华社北京6月13日电 《2021年度中国健身行业数据报告·五周年珍藏版》（以下简称“报告”）以及《2021中国健身产业城市景气指数排行榜》13日发布，该报告由中国健美协会指导，上海体育学院经济管理学院、三体云动和万博宣伟联合制作。报告对2021年度中国健身行业的现状与数据进行了复盘和回顾，并对行业未来的发展趋势提出了猜想和预测。\n  报告显示，2021年中国健身行业逐步回暖，健身会员和健身人口渗透率呈现出连续五年增长的态势，截至2021年12月，全国健身会员达到7513万人，相比2020年增长了6.89%，健身人口渗透率也从2017年的3.46%增长至2021年的5.37%。但由于部分地区仍然受到新冠肺炎疫情的影响，2021年全国广义健身类场馆数约14.9万家，相比2020年下滑了5.1%，其中商业健身俱乐部41917家，环比下滑5.39%，健身工作室51939家，环比下滑3.01%。"

            };

            int[] image = new int[]{
                    R.drawable.recommend_head_1,
                    R.drawable.recommend_head_2,
                    R.drawable.recommend_head_3,
                    R.drawable.recommend_head_4,
                    R.drawable.recommend_head_5,
                    R.drawable.recommend_head_6,
                    R.drawable.recommend_head_7,
                    R.drawable.recommend_head_8
            };
            for (int i = 0;i< titles.length;i++){
                list.add(new RecommendBean(titles[i],image[i]));
            }



            RecommendDetailBean data1 = new RecommendDetailBean();
            data1.setTitle(titles[0]);
            data1.setMessage(message[0]);
            beans.add(data1);
            RecommendDetailBean data2 = new RecommendDetailBean();
            data2.setTitle(titles[1]);
            data2.setMessage(message[1]);
            beans.add(data2);
            RecommendDetailBean data3 = new RecommendDetailBean();
            data3.setTitle(titles[2]);
            data3.setMessage(message[2]);
            beans.add(data3);
            RecommendDetailBean data4 = new RecommendDetailBean();
            data4.setTitle(titles[3]);
            data4.setMessage(message[3]);
            beans.add(data4);
            RecommendDetailBean data5 = new RecommendDetailBean();
            data5.setTitle(titles[4]);
            data5.setMessage(message[4]);
            beans.add(data5);
            RecommendDetailBean data6 = new RecommendDetailBean();
            data6.setTitle(titles[5]);
            data6.setMessage(message[5]);
            beans.add(data6);
            RecommendDetailBean data7 = new RecommendDetailBean();
            data7.setTitle(titles[6]);
            data7.setMessage(message[6]);
            beans.add(data7);
            RecommendDetailBean data8 = new RecommendDetailBean();
            data8.setTitle(titles[7]);
            data8.setMessage(message[7]);
            beans.add(data8);
            Operator.saveAll(beans);



//        Operator.deleteAll(RecommendBean.class);
//        Operator.deleteAll(RecommendDetailBean.class);
            Operator.saveAll(list);
        }

        modelParamCallBack.requestSuccess(list);
    }
}
