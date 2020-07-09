package com.github.m4rc0sx.redis;

import com.github.m4rc0sx.RedisManager;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

import java.util.logging.Level;

public class RedisPubSub {

    private String redisHost;
    private int redisPort;

    public RedisPubSub(String host, int port) {

        this.redisHost = host;
        this.redisPort = port;

    }

    public void publish(String channel,  String message){
        try(Jedis publisher = new Jedis(this.redisHost, this.redisPort)){
            publisher.publish(channel, message);
            RedisManager.getInstance().getLogger().log(Level.INFO, "Message published!");
        } catch (Exception e) {
            RedisManager.getInstance().getLogger().log(Level.SEVERE, "Could not publish message: " + e.toString());
        }
    }

    public void subscribe(){
        Jedis subscriber = new Jedis(this.redisHost, this.redisPort);
        subscriber.connect();

        new Thread("Redis Subscriber"){
            @Override
            public void run(){
                String[] channels = {"test",};
                subscriber.subscribe(new JedisPubSub() {
                    @Override
                    public void onMessage(String channel, String message){
                        RedisManager.getInstance().getLogger().log(Level.INFO, "Message received: " + message);
                    }
                }, channels);
            }
        }.start();
    }
}
