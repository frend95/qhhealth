package com.hfkd.qhhealth.test;

import com.hfkd.qhhealth.health.service.HealthPlanItemService;
import com.hfkd.qhhealth.nutritionist.model.NutritionistCase;
import com.hfkd.qhhealth.nutritionist.service.NutritionistCaseService;
import com.hfkd.qhhealth.nutritionist.service.NutritionistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * @author hexq
 * @date 2018/6/5 17:53
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private NutritionistCaseService caseService;
    @Autowired
    private HealthPlanItemService itemService;
    @Autowired
    private NutritionistService yysService;

    @RequestMapping("/1")
    public void test1(Integer id, BigDecimal weight) throws Exception {
        /*redis.opsForValue().set("test", "100",60*10, TimeUnit.SECONDS);//向redis里存入数据和设置缓存时间
        redis.boundValueOps("test").increment(-1);//val做-1操作
        redis.opsForValue().get("test");//根据key获取缓存中的val
        redis.boundValueOps("test").increment(1);//val +1
        redis.getExpire("test");//根据key获取过期时间
        redis.getExpire("test",TimeUnit.SECONDS);//根据key获取过期时间并换算成指定单位
        redis.delete("test");//根据key删除缓存
        redis.hasKey("546545");//检查key是否存在，返回boolean值
        redis.opsForSet().add("red_123", "1","2","3");//向指定key中存放set集合
        redis.expire("red_123",1000 , TimeUnit.MILLISECONDS);//设置过期时间
        redis.opsForSet().isMember("red_123", "1");//根据key查看集合中是否存在指定数据
        redis.opsForSet().members("red_123");//根据key获取set集合*/

    }

    @RequestMapping("/2")
    public void test2(int[] id) {
        // 批量添加营养师
        /*String avatar = "https://cdn.v2ex.com/avatar/15a6/6a2d/246589_large.png";
        for (int i = 0; i < 20; i++) {
            long baseAccount = 15000000001L;
            String name = "Qh_" + RandomStringUtils.randomAlphanumeric(4);
            Nutritionist yys = new Nutritionist();
            yys.setAccount(String.valueOf(baseAccount + i));
            yys.setAvatar(avatar);
            yys.setGender("1");
            yys.setName(name);
            yys.setStatus("1");
            yysService.insert(yys);

            SocialNutritionistInfo yysSocial = new SocialNutritionistInfo();
            yysSocial.setAvatar(avatar);
            yysSocial.setBio("五星级体重规划师;擅长代谢慢，内分泌失调，产后肥胖，中年肥胖;帮助多名客户成功瘦身，获得健康，美丽，自信");
            yysSocial.setName(name);
            yysSocial.setNutritionistId(yys.getId());
            socialYysInfoService.insert(yysSocial);
        }*/

        NutritionistCase yysCase1 = new NutritionistCase();
        yysCase1.setThumb("http://www.fox239.com/Upload/1524906761.jpg");
        yysCase1.setName("任先生");
        yysCase1.setAge("26");
        yysCase1.setHeight("173");
        yysCase1.setResult("26");
        yysCase1.setPeriod("2");
        yysCase1.setContent("陕西西安的任先生，体重170斤。因为在事业单位上班，所以平时比较忙，导致身体越来越胖。\n" +
                "经常逛街都买不到适合自己穿的衣服，而且身体也越来越差。不仅 皮肤油腻，还 掉发，俨然一个活生生的‘中年油腻男’形象。\n" +
                "2017年3月份的时候体检出患有中度脂肪肝，才开始注重自己的体重问题。尝试过好多减肥产品都没有效果。\n" +
                "直到2017年10月底的时候任先生经别人介绍选择了我们 倩狐。我接手了他的档案之后，对他的身体情况进行了详细的评估，发现他是属于 典型的虚胖亚健康体质型肥胖。\n" +
                "后面我为他量身制定了 瘦身方案。先调理代谢，改善亚健康体质，再结合了饮食方案和运动方案。\n" +
                "经过一段时间的调理之后客户非常顺利地进入了 减重期，体重一天比一天轻，肚子也神奇般得变小了。身体亚健康状态改善，脸上的气色也越来越好了。\n" +
                "客户仅花了一个月时间就 从170斤减到144斤。最重要的是身体的健康指数提升，中度脂肪肝短短1个月转为了轻度脂肪肝。\n" +
                "看到任先生减肥成功后脸上露出的喜悦，我作为他的体重规划师也替他感到高兴。");

        NutritionistCase yysCase2 = new NutritionistCase();
        yysCase2.setThumb("http://www.fox239.com/Upload/1524732517.jpg");
        yysCase2.setName("樊女士");
        yysCase2.setAge("32");
        yysCase2.setHeight("155");
        yysCase2.setResult("12");
        yysCase2.setPeriod("1");
        yysCase2.setContent("内蒙古自治区樊女士，工作以前体重都不到90斤。\n" +
                "工作后由于长期久坐不运动就开始发胖。由于体重越来越重，身材严重走样，樊女士就开始在网上寻求减肥产品，用过五六种减肥产品。这些产品都是身边的朋友介绍用的，可是当她使用之后身体均产生不适症状。\n" +
                "后面在网上接触到 倩狐后决定试一试，我在2017年12月份接手她的档案，对她的情况了解分析后发现她是属于脾胃失调。由体质代谢率太慢而引发的肥胖，要想瘦下来就得先平衡好体内的激素水平，提升代谢。\n" +
                "经过了一个月多的调理，客户体质代谢率恢复正常，体重减到了122斤，共下降12斤。\n" +
                "客户告诉我自从这段时间成功减肥后人自信了许多。皮肤变好，身材也比以前好了。");

        NutritionistCase yysCase3 = new NutritionistCase();
        yysCase3.setThumb("http://www.fox239.com/Upload/1525655295.jpg");
        yysCase3.setName("麻先生");
        yysCase3.setAge("30");
        yysCase3.setHeight("162");
        yysCase3.setResult("20");
        yysCase3.setPeriod("3");
        yysCase3.setContent("浙江金华市的麻先生，从事货车司机职业。\n" +
                "由于长期 开车熬夜，他从以前不到130斤涨到186斤。尝试过多种减肥产品和减肥方法，都没有明显的减肥效果。\n" +
                "但是这么多年以来他从未放弃过减肥。后面在网上接触到倩狐，2017年11月通过 公众号体重规划师助理推荐由我来接手。\n" +
                "经过评估后发现麻先生的 脂肪很顽固，是由脾胃失调等多种原因造成发胖。经过了2个月的针对性调理， 目前体重160斤，共减了26斤。\n" +
                "现在客户还在继续减重，他告诉我 身体明显比以前健康了许多，会继续依照我的方案进行减重的。");

        for (int i = 1; i < 21; i++) {
            yysCase1.setNutritionistId(i);
            yysCase2.setNutritionistId(i);
            yysCase3.setNutritionistId(i);
            caseService.insert(yysCase1);
            caseService.insert(yysCase2);
            caseService.insert(yysCase3);
        }
    }
}
