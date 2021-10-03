package com.jagrosh.jmusicbot.interactions;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandClient;
import com.jagrosh.jmusicbot.utils.CommandConverter;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.hooks.EventListener;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InteractionManager {
    private final CommandClient client;

    public InteractionManager(CommandClient client) {
        this.client = client;
    }

    public CommandListUpdateAction registerCommands(Guild guild) {
        CommandListUpdateAction updateCommands = guild.updateCommands();

        List<CommandData> commands = new ArrayList<>();
        for (Command command : this.getClient().getCommands()) {
            Optional<CommandData> slashCommand = CommandConverter.toSlashCommand(command);
            slashCommand.ifPresent(commands::add);
        }

        return updateCommands.addCommands(commands);
    }

    public EventListener getListener() {
        return new InteractionListener(this);
    }

    public CommandClient getClient() {
        return client;
    }
}
