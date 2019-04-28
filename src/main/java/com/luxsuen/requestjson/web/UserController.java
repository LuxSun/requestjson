package com.luxsuen.requestjson.web;

import com.luxsuen.jsonutil.util.JsonUtil;
import com.luxsuen.requestjson.entity.User;
import com.luxsuen.requestjson.annotation.RequestJson;
import com.luxsuen.requestjson.util.MapWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user/")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /*
     * @Author Lux Sun
     * @Description: 测试（以大括号开始） @PathVariable & @RequestJson + 一步获取分参后的value
     * @Param: [userId, userDel, userAccount, scoreArray, money, user1, user2, user3, userList, mapWrapper1, mapWrapper2]
     * @Return: java.lang.Object
     */
    @RequestMapping(value = "info/{userId}", method = RequestMethod.POST)
    @ResponseBody
    public Object checkRequestJsonByBeginBigBrace(
                                                @PathVariable(value = "userId") Integer userId,
                                                @RequestJson(value = "userDel") Boolean userDel,
                                                @RequestJson(value = "userAccount") String userAccount,
                                                @RequestJson(value = "scoreArray") Double[] scoreArray,
                                                @RequestJson(value = "money") BigDecimal money,
                                                @RequestJson(value = "user1") User user1,
                                                @RequestJson(value = "user2") User user2,
                                                @RequestJson(value = "user3") User user3,
                                                @RequestJson(value = "userList") List<User> userList,
                                                @RequestJson(value = "mapWrapper1") MapWrapper mapWrapper1,
                                                @RequestJson(value = "mapWrapper2") MapWrapper mapWrapper2) {
        // 获取实体类 & 实体类数组
        Object user = JsonUtil.getJsonEntityByKeyArray(mapWrapper1.getMapWrapper(), User.class, "k8");
        Object userlist = JsonUtil.getJsonEntityListByKeyArray(mapWrapper1.getMapWrapper(), User.class, "k9");

        // 获取普通类型 & 集合
        Object integer = JsonUtil.getJsonValueByKeyArray(mapWrapper1.getMapWrapper(), Integer.class, "k9", "0", "userId");
        Object string = JsonUtil.getJsonValueByKeyArray(mapWrapper1.getMapWrapper(), String.class, "k2");
        Object bool = JsonUtil.getJsonValueByKeyArray(mapWrapper1.getMapWrapper(), Boolean.class, "k3");
        Object array1 = JsonUtil.getJsonValueByKeyArray(mapWrapper1.getMapWrapper(), List.class, "k4");
        Object map1 = JsonUtil.getJsonValueByKeyArray(mapWrapper1.getMapWrapper(), Map.class, "k5");
        Object map2 = JsonUtil.getJsonValueByKeyArray(mapWrapper1.getMapWrapper(), Map.class, "k6");
        Object array2 = JsonUtil.getJsonValueByKeyArray(mapWrapper1.getMapWrapper(), List.class, "k7");
        return null;
    }

    /*
     * @Author Lux Sun
     * @Description: 测试（以中括号开始）
     * @Param: [mapWrapper3]
     * @Return: java.lang.Object
     */
    @RequestMapping(value = "info", method = RequestMethod.POST)
    @ResponseBody
    public Object checkRequestJsonByBeginMidBrace(@RequestJson(value = "mapWrapper3") MapWrapper mapWrapper3) {
        return null;
    }

    /*
     * @Author Lux Sun
     * @Description: 测试（只有大括号）
     * @Param: [arr]
     * @Return: java.lang.Object
     */
    @RequestMapping(value = "onlyBigBrace", method = RequestMethod.POST)
    @ResponseBody
    public Object checkRequestJsonByOnlyBigBrace(@RequestJson(value = "name") String name) {
        return null;
    }

    /*
     * @Author Lux Sun
     * @Description: 测试（只有中括号）
     * @Param: [arr]
     * @Return: java.lang.Object
     */
    @RequestMapping(value = "onlyMidBrace", method = RequestMethod.POST)
    @ResponseBody
    public Object checkRequestJsonByOnlyMidBrace(@RequestJson(value = "arr") Integer[] arr) {
        return null;
    }

    /*
     * @Author Lux Sun
     * @Description: Required 测试（只有大括号）
     * @Param: [arr]
     * @Return: java.lang.Object
     */
    @RequestMapping(value = "requiredOnlyBigBrace", method = RequestMethod.POST)
    @ResponseBody
    public Object checkRequestJsonByRequiredOnlyBigBrace(@RequestJson(value = "name", required = false) String name) {
        return null;
    }
}