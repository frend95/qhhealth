package com.hfkd.qhhealth.test;

import com.hfkd.qhhealth.nutritionist.model.Nutritionist;
import com.hfkd.qhhealth.nutritionist.service.NutritionistService;
import com.hfkd.qhhealth.social.model.SocialNutritionistInfo;
import com.hfkd.qhhealth.social.service.SocialNutritionistInfoService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * @author hexq
 * @date 2018/6/5 17:53
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private NutritionistService yysService;
    @Autowired
    private SocialNutritionistInfoService socialYysInfoService;

    @RequestMapping("/1")
    public void test1(int[] id) {
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
        System.out.println(Arrays.toString(id));

    }

    @RequestMapping("/2")
    public void test2(int[] id) {
        // 批量添加营养师
        String avatar = "https://cdn.v2ex.com/avatar/15a6/6a2d/246589_large.png";
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
        }


    }
}
