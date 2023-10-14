package com.usyd.emergency.service;

import com.usyd.emergency.pojo.Subscribes;
import com.usyd.emergency.repository.SubscribeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscribeService {
    private final SubscribeRepository subscribeRepository;

    @Autowired
    public SubscribeService(SubscribeRepository subscribeRepository) {
        this.subscribeRepository = subscribeRepository;
    }

    // 创建或更新订阅
    public Subscribes createOrUpdateSubscription(Integer userId, Integer diseaseId, Integer preference) {
        // 先检查是否已经存在相同的订阅记录
        Subscribes existingSubscription = subscribeRepository.findByUserIdAndDiseaseId(userId, diseaseId);

        if (existingSubscription != null) {
            // 如果已经存在相同的订阅记录，更新偏好
            existingSubscription.setPreference(preference);
            return subscribeRepository.save(existingSubscription);
        }

        // 如果不存在相同的订阅记录，则创建新的订阅
        Subscribes subscription = new Subscribes();
        subscription.setUserId(userId);
        subscription.setDiseaseId(diseaseId);
        subscription.setPreference(preference); // 设置偏好
        return subscribeRepository.save(subscription);
    }

    // 根据用户ID获取订阅列表
    public List<Subscribes> getSubscriptionsByUserId(Integer userId) {
        return subscribeRepository.findByUserId(userId);
    }

    // 根据疾病ID获取订阅列表
    public List<Subscribes> getSubscriptionsByDiseaseId(Integer diseaseId) {
        return subscribeRepository.findByDiseaseId(diseaseId);
    }

    // 删除用户的订阅
    public void deleteSubscriptionsByUserId(Integer userId) {
        List<Subscribes> subscriptions = subscribeRepository.findByUserId(userId);
        for (Subscribes subscription : subscriptions) {
            subscribeRepository.delete(subscription);
        }
    }
}
