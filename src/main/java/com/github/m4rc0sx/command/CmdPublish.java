package com.github.m4rc0sx.command;

import com.github.m4rc0sx.RedisManager;
import com.github.m4rc0sx.redis.RedisPubSub;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdPublish implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            return false; // We don´t want players to execute redis publish commands.
        }

        RedisPubSub redisPubSub = RedisManager.getInstance().getRedisPubSub();

        if (args.length <= 0) {
            sender.sendMessage("You must supply at least one argument!");
            return false;
        }

        StringBuilder sb = new StringBuilder();
        for(String str: args)
            sb.append(str).append(" ");

        redisPubSub.publish("test", sb.substring(0, sb.length() - 1));
        return true;
    }

}
