package com.jagrosh.jmusicbot.interactions;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandListener;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class InteractionListener extends ListenerAdapter {
    private final InteractionManager manager;

    public InteractionListener(InteractionManager manager) {
        this.manager = manager;
    }

    @Override
    public void onSlashCommand(@NotNull net.dv8tion.jda.api.events.interaction.SlashCommandEvent event) {
        CommandListener listener = manager.getClient().getListener();
        Command command = null;
        for (Command currentCommand : manager.getClient().getCommands()) {
            if (currentCommand.getName().equalsIgnoreCase(event.getName())) command = currentCommand;
        }

        SlashCommandEvent commandEvent = new SlashCommandEvent(event, this.getManager().getClient());

        if (command == null) return;
        if (listener != null) listener.onCommand(commandEvent, command);
        command.run(commandEvent);
    }

    public InteractionManager getManager() {
        return manager;
    }
}
