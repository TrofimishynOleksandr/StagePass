package com.stagepass.redis;

import com.stagepass.port.TicketReservation;
import com.stagepass.shared.identity.EventId;
import com.stagepass.shared.identity.TicketId;
import com.stagepass.shared.identity.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
@RequiredArgsConstructor
public class RedisTicketReservation implements TicketReservation {
    private final StringRedisTemplate redisTemplate;

    @Override
    public boolean reserve(EventId eventId, TicketId ticketId, UserId userId, Duration ttl) {
        Boolean result = redisTemplate.opsForValue()
                .setIfAbsent(eventId.value() + ticketId.value(), userId.value(), ttl);
        return Boolean.TRUE.equals(result);
    }

    @Override
    public void release(TicketId ticketId) {
        redisTemplate.delete(ticketId.value());
    }

    @Override
    public boolean isReserved(TicketId ticketId) {
        return redisTemplate.hasKey(ticketId.value());
    }
}
