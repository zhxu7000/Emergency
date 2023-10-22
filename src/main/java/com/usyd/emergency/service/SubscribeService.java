package com.usyd.emergency.service;

import com.usyd.emergency.constant.XError;
import com.usyd.emergency.exception.ConflictException;
import com.usyd.emergency.pojo.Subscribes;
import com.usyd.emergency.repository.DiseaseRepository;
import com.usyd.emergency.repository.SubscribeRepository;
import com.usyd.emergency.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class SubscribeService {
    private final SubscribeRepository subscribeRepository;
    private final DiseaseRepository diseaseRepository;

    private final UserRepository userRepository;

    @Autowired
    public SubscribeService(SubscribeRepository subscribeRepository,
                            DiseaseRepository diseaseRepository,
                            UserRepository userRepository) {
        this.subscribeRepository = subscribeRepository;
        this.diseaseRepository = diseaseRepository;
        this.userRepository = userRepository;
    }

    // 创建或更新订阅
    public Subscribes createSubscription(Integer userId, Integer diseaseId) {
        if (diseaseRepository.findById(diseaseId) == null || userRepository.findById(userId) == null) {
            throw new ConflictException(XError.DATABASE_ERROR.getCode(), "subscription can not be created due to invalid uid or did");
        }
        // 先检查是否已经存在相同的订阅记录
        Subscribes subscription = new Subscribes();
        subscription.setDiseaseId(diseaseId);
        subscription.setUserId(userId);
        // 如果不存在相同的订阅记录，则创建新的订阅
        return subscribeRepository.save(subscription);
    }

    // 根据用户ID获取订阅列表
    public List<Integer> getDidsByUserId(Integer userId) {
        if ( userRepository.findById(userId) == null) {
            throw new ConflictException(XError.DATABASE_ERROR.getCode(), "invalid uid");
        }
        List<Subscribes> subs = subscribeRepository.findByUserId(userId).get();
        List<Integer> dids = subs.stream().map(e -> e.getDiseaseId()).collect(Collectors.toList());
        return dids;
    }

    // 根据疾病ID获取订阅列表
    public List<Integer> getUidsByDiseaseId(Integer diseaseId) {
        if (diseaseRepository.findById(diseaseId) == null) {
            throw new ConflictException(XError.DATABASE_ERROR.getCode(), "invalid did");
        }
        List<Subscribes> subs= subscribeRepository.findByDiseaseId(diseaseId);
        List<Integer> uids = subs.stream().map(e -> e.getUserId()).collect(Collectors.toList());
        return uids ;
    }

    // 删除用户的订阅
    public void unsubscribeByDid(Integer userId, Integer diseaseId) {
        if ( userRepository.findById(userId) == null) {
            throw new ConflictException(XError.DATABASE_ERROR.getCode(), "invalid uid");
        }
        if (diseaseRepository.findById(diseaseId) == null) {
            throw new ConflictException(XError.DATABASE_ERROR.getCode(), "invalid did");
        }
        subscribeRepository.deleteByDiseaseId(diseaseId);
    }
}