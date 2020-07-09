package com.github.m4rc0sx;

import com.github.m4rc0sx.command.CmdPublish;
import com.github.m4rc0sx.redis.RedisPubSub;
import org.bukkit.plugin.java.JavaPlugin;


public class RedisManager extends JavaPlugin {

    private static RedisManager singleInstance;
    public static RedisManager getInstance() { return singleInstance; }

    private static final String REDIS_HOST = "localhost";
    private static final int REDIS_PORT = 6379;

    private RedisPubSub redisPubSub;

    @Override
    public void onEnable() {

        singleInstance = this;

        this.redisPubSub = new RedisPubSub(REDIS_HOST, REDIS_PORT);

        // Enable this in case you want to enable the subscriber.
        //this.redisPubSub.subscribe();

        getLogger().info("RedisManger by: M4RC0Sx - ENABLED");
        this.getCommand("redispub").setExecutor(new CmdPublish());

    }

    public RedisPubSub getRedisPubSub() {
        return this.redisPubSub;
    }

}
