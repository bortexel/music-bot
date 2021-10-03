package com.jagrosh.jmusicbot.utils;

import com.jagrosh.jdautilities.command.Command;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class CommandConverter {
    public static Optional<CommandData> toSlashCommand(Command command) {
        if (!isSupported(command)) return Optional.empty();
        return Optional.of(new CommandData(command.getName(), command.getHelp()).addOptions(parseOptions(command.getArguments())));
    }

    public static boolean isSupported(Command command) {
        switch (command.getName().toLowerCase(Locale.ROOT)) {
            case "shutdown":
            case "setgame":
            case "ping":
                return false;
            default:
                return true;
        }
    }

    public static List<OptionData> parseOptions(String options) {
        List<OptionData> result = new ArrayList<>();
        if (options == null) return result;
        for (String arg : options.split(" ")) {
            boolean required = arg.startsWith("<");
            String name = arg.substring(1, arg.length() - 1);
            result.add(getOption(name, required));
        }
        return result;
    }

    public static OptionData getOption(String name, boolean required) {
        String[] parts = name.split("\\|");
        OptionType type = OptionType.STRING;
        String description = String.join(" or ", parts);

        switch (name) {
            case "volume":
                description = "New volume (0-150)";
                type = OptionType.INTEGER;
                break;
            case "position":
                description = "Position to skip to";
                type = OptionType.INTEGER;
                break;
            case "channel|NONE":
                description = "Text or voice channel";
                type = OptionType.CHANNEL;
                required = false;
                break;
            case "role|NONE":
                description = "Role";
                type = OptionType.ROLE;
                required = false;
                break;
            case "user":
                description = "User";
                type = OptionType.USER;
                break;
            case "off|all|single":
                // For /repeat command
                name = "value";
                break;
        }

        if (name.contains("|")) name = parts[0];
        return new OptionData(type, name, description, required);
    }
}
