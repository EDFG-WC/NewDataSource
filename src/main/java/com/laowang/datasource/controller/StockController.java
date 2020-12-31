package com.laowang.datasource.controller;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class StockController {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private Redisson redisson;

    @GetMapping("/buy")
    public String reduceStock() {
        String clientId = UUID.randomUUID().toString();
        String lockKey = "product_001";
        // 加锁
        RLock lock = redisson.getLock(lockKey);
        try {
            /*Boolean result = stringRedisTemplate.opsForValue().setIfAbsent(lockKey, clientId, 30, TimeUnit.SECONDS);
            if (!result) {
                return "error";
            }*/
            lock.lock();
            int stock = Integer.parseInt(stringRedisTemplate.opsForValue().get("stock"));
            if (stock > 0) {
                int realStock = stock - 1;
                stringRedisTemplate.opsForValue().set("stock", realStock + "");
                System.out.println("扣减成功, 剩余库存:" + realStock);
            } else {
                System.out.println("扣减失败, 库存不足");
            }
        } finally {
            lock.unlock();
            if (clientId.equals(stringRedisTemplate.opsForValue().get(lockKey))) {
                stringRedisTemplate.delete(lockKey);
            }
        }
        return "end";
    }
}
