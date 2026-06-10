package com.stagepass.redis;

import com.stagepass.port.TicketReservationChecker;
import com.stagepass.shared.identity.TicketId;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RedisTicketReservationChecker implements TicketReservationChecker {
    private final StringRedisTemplate redisTemplate;

    @Override
    public boolean isReserved(TicketId ticketId) {
        return redisTemplate.hasKey(ticketId.value());
    }
}
