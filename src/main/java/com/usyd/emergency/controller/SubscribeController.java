package com.usyd.emergency.controller;

import com.usyd.emergency.dto.SubscriptionDTO;
import com.usyd.emergency.pojo.ResponseResult;
import com.usyd.emergency.pojo.Subscribes;
import com.usyd.emergency.service.SubscribeService;
import com.usyd.emergency.utils.XUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/subscription")
public class SubscribeController {

    private final SubscribeService subscribeService;

    @Autowired
    public SubscribeController(SubscribeService subscribeService) {
        this.subscribeService = subscribeService;
    }

    // 创建或更新订阅
    @PostMapping
    public ResponseResult addSubscription(@RequestBody SubscriptionDTO.addSubscriptionDTO subscription) {
        Integer uid = XUtils.getUid();
        subscribeService.createSubscription(uid, subscription.disease_id);
        return new ResponseResult(200,"you have successfully subscribed");
    }

    // 获取用户的订阅列表
    @GetMapping("/user/{userId}")
    public ResponseResult getSubscriptionsByUserId(@PathVariable Integer userId) {
        List<Integer> dids = subscribeService.getDidsByUserId(userId);
        Map<String, List<Integer>> map = new HashMap<>();
        map.put("disease_ids", dids);
        return new ResponseResult(200,"retrieve successful", map);
    }

    @GetMapping()
    public ResponseResult getSubscriptions() {
        Integer uid = XUtils.getUid();
        List<Integer> dids = subscribeService.getDidsByUserId(uid);
        Map<String, List<Integer>> map = new HashMap<>();
        map.put("disease_ids", dids);
        return new ResponseResult(200,"retrieve successful", map);
    }

    // 获取订阅某疾病的用户
    @GetMapping("/disease/{diseaseId}")
    public ResponseResult getSubscriptionsByDiseaseId(@PathVariable Integer diseaseId) {
        List<Integer> uids = subscribeService.getUidsByDiseaseId(diseaseId);
        Map<String, List<Integer>> map = new HashMap<>();
        map.put("user_ids", uids);
        return new ResponseResult(200,"retrieve successful", map);
    }

    // 删除用户的订阅
    @DeleteMapping
    public ResponseResult unsubscribeByDid(@RequestBody SubscriptionDTO.deleteSubscriptionDTO subscription) {
        subscribeService.unsubscribeByDid(subscription.user_id, subscription.disease_id);
        return new ResponseResult(200,"unsubscribe successful");
    }
}