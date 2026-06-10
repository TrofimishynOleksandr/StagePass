package com.stagepass.listener;

import com.stagepass.shared.identity.TicketId;
import com.stagepass.usecase.ExpireBookingUseCase;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

@Component
public class RedisKeyExpirationListener extends KeyExpirationEventMessageListener {
    private final ExpireBookingUseCase expireBookingUseCase;

    public RedisKeyExpirationListener(RedisMessageListenerContainer listenerContainer,
                                      ExpireBookingUseCase expireBookingUseCase) {
        super(listenerContainer);
        this.expireBookingUseCase = expireBookingUseCase;
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        String expiredKey = message.toString();
        expireBookingUseCase.execute(new TicketId(expiredKey));
    }
}
