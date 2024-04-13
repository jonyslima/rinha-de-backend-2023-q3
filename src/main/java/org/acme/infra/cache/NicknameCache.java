package org.acme.infra.cache;

import io.quarkus.redis.datasource.ReactiveRedisDataSource;
import io.quarkus.redis.datasource.keys.ReactiveKeyCommands;
import io.quarkus.redis.datasource.value.ReactiveValueCommands;
import io.quarkus.redis.datasource.value.SetArgs;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import org.apache.commons.lang3.StringUtils;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

@ApplicationScoped
public class NicknameCache {
    private static final SetArgs EXPIRATION = new SetArgs().ex(Duration.of(3L, ChronoUnit.MINUTES));
    final ReactiveKeyCommands<String> keyCommands;
    final ReactiveValueCommands<String, String> valueCommands;

    public NicknameCache(ReactiveRedisDataSource reactive) {
        valueCommands = reactive.value(String.class);
        keyCommands = reactive.key();
    }

    public Uni<Void> set(String nickname) {
        return valueCommands.set(nickname, StringUtils.EMPTY, EXPIRATION);
    }

    public Uni<Boolean> exists(String nickname) {
        return keyCommands.exists(nickname);
    }
}
