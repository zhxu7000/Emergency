package com.usyd.emergency.config.controller;

import com.usyd.emergency.pojo.Subscribes;
import com.usyd.emergency.service.SubscribeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subscriptions")
public class SubscribeController {

    private final SubscribeService subscribeService;

    @Autowired
    public SubscribeController(SubscribeService subscribeService) {
        this.subscribeService = subscribeService;
    }

    // 创建或更新订阅
    @PostMapping("/createOrUpdate")
    public Subscribes createOrUpdateSubscription(@RequestParam Integer userId, @RequestParam Integer diseaseId, @RequestParam Integer preference) {
        return subscribeService.createOrUpdateSubscription(userId, diseaseId, preference);
    }

    // 获取用户的订阅列表
    @GetMapping("/user/{userId}")
    public List<Subscribes> getSubscriptionsByUserId(@PathVariable Integer userId) {
        return subscribeService.getSubscriptionsByUserId(userId);
    }

    // 获取疾病的订阅列表
    @GetMapping("/disease/{diseaseId}")
    public List<Subscribes> getSubscriptionsByDiseaseId(@PathVariable Integer diseaseId) {
        return subscribeService.getSubscriptionsByDiseaseId(diseaseId);
    }

    // 删除用户的订阅
    @DeleteMapping("/user/{userId}")
    public void deleteSubscriptionsByUserId(@PathVariable Integer userId) {
        subscribeService.deleteSubscriptionsByUserId(userId);
    }
}
