package com.jagrosh.jmusicbot.interactions;

import com.jagrosh.jdautilities.command.CommandClient;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class SlashCommandEvent extends CommandEvent {
    private final net.dv8tion.jda.api.events.interaction.SlashCommandEvent event;

    public SlashCommandEvent(MessageReceivedEvent event, String args, CommandClient client, net.dv8tion.jda.api.events.interaction.SlashCommandEvent slashEvent) {
        super(event, args, client);
        this.event = slashEvent;
    }

    public SlashCommandEvent(net.dv8tion.jda.api.events.interaction.SlashCommandEvent event, CommandClient client) {
        super(null, null, client);
        this.event = event;
    }

    @Override
    public String getArgs() {
        List<String> args = new ArrayList<>();
        for (OptionMapping option : event.getOptions()) args.add(option.getAsString());
        return String.join(" ", args);
    }

    @Override
    public void linkId(Message message) { }

    @Override
    public void reply(String message) {
        event.deferReply().queue((s) -> event.getHook().sendMessage(message).queue());
    }

    @Override
    public void reply(String message, Consumer<Message> success) {
        event.deferReply().queue((s) -> event.getHook().sendMessage(message).queue(success));
    }

    @Override
    public void reply(String message, Consumer<Message> success, Consumer<Throwable> failure) {
        event.deferReply().queue((s) -> event.getHook().sendMessage(message).queue(success, failure));
    }

    @Override
    public void reply(MessageEmbed embed) {
        event.deferReply().queue((s) -> event.getHook().sendMessageEmbeds(embed).queue());
    }

    @Override
    public void reply(MessageEmbed embed, Consumer<Message> success) {
        event.deferReply().queue((s) -> event.getHook().sendMessageEmbeds(embed).queue(success));
    }

    @Override
    public void reply(MessageEmbed embed, Consumer<Message> success, Consumer<Throwable> failure) {
        event.deferReply().queue((s) -> event.getHook().sendMessageEmbeds(embed).queue(success, failure));
    }

    @Override
    public void reply(Message message) {
        event.deferReply().queue((s) -> event.getHook().sendMessage(message).queue());
    }

    @Override
    public void reply(Message message, Consumer<Message> success) {
        event.deferReply().queue((s) -> event.getHook().sendMessage(message).queue(success));
    }

    @Override
    public void reply(Message message, Consumer<Message> success, Consumer<Throwable> failure) {
        event.deferReply().queue((s) -> event.getHook().sendMessage(message).queue(success, failure));
    }

    @Override
    public void reply(File file, String filename) {
        throw new IllegalStateException();
    }

    @Override
    public void reply(String message, File file, String filename) {
        throw new IllegalStateException();
    }

    @Override
    public void replyFormatted(String format, Object... args) {
        throw new IllegalStateException();
    }

    @Override
    public void replyOrAlternate(MessageEmbed embed, String alternateMessage) {
        throw new IllegalStateException();
    }

    @Override
    public void replyOrAlternate(String message, File file, String filename, String alternateMessage) {
        throw new IllegalStateException();
    }

    @Override
    public void replyInDm(String message) {
        event.deferReply(true).queue((s) -> event.getHook().sendMessage(message).queue());
    }

    @Override
    public void replyInDm(String message, Consumer<Message> success) {
        event.deferReply(true).queue((s) -> event.getHook().sendMessage(message).queue(success));
    }

    @Override
    public void replyInDm(String message, Consumer<Message> success, Consumer<Throwable> failure) {
        event.deferReply(true).queue((s) -> event.getHook().sendMessage(message).queue(success, failure));
    }

    @Override
    public void replyInDm(MessageEmbed embed) {
        event.deferReply(true).queue((s) -> event.getHook().sendMessageEmbeds(embed).queue());
    }

    @Override
    public void replyInDm(MessageEmbed embed, Consumer<Message> success) {
        event.deferReply(true).queue((s) -> event.getHook().sendMessageEmbeds(embed).queue(success));
    }

    @Override
    public void replyInDm(MessageEmbed embed, Consumer<Message> success, Consumer<Throwable> failure) {
        event.deferReply(true).queue((s) -> event.getHook().sendMessageEmbeds(embed).queue(success, failure));
    }

    @Override
    public void replyInDm(Message message) {
        event.deferReply(true).queue((s) -> event.getHook().sendMessage(message).queue());
    }

    @Override
    public void replyInDm(Message message, Consumer<Message> success) {
        event.deferReply(true).queue((s) -> event.getHook().sendMessage(message).queue(success));
    }

    @Override
    public void replyInDm(Message message, Consumer<Message> success, Consumer<Throwable> failure) {
        event.deferReply(true).queue((s) -> event.getHook().sendMessage(message).queue(success, failure));
    }

    @Override
    public void replyInDm(String message, File file, String filename) {
        throw new IllegalStateException();
    }

    @Override
    public void replySuccess(String message) {
        this.reply(message);
    }

    @Override
    public void replySuccess(String message, Consumer<Message> queue) {
        this.reply(message, queue);
    }

    @Override
    public void replyWarning(String message) {
        this.reply(message);
    }

    @Override
    public void replyWarning(String message, Consumer<Message> queue) {
        this.reply(message, queue);
    }

    @Override
    public void replyError(String message) {
        this.replyInDm(message);
    }

    @Override
    public void replyError(String message, Consumer<Message> queue) {
        this.replyInDm(message, queue);
    }

    @Override
    public void reactSuccess() {
        throw new IllegalStateException();
    }

    @Override
    public void reactWarning() {
        throw new IllegalStateException();
    }

    @Override
    public void reactError() {
        throw new IllegalStateException();
    }

    @Override
    public SelfUser getSelfUser() {
        return event.getJDA().getSelfUser();
    }

    @Override
    public Member getSelfMember() {
        return this.event.getGuild() == null ? null : this.event.getGuild().getSelfMember();
    }

    @Override
    public User getAuthor() {
        return event.getUser();
    }

    @Override
    public MessageChannel getChannel() {
        return event.getChannel();
    }

    @Override
    public ChannelType getChannelType() {
        return event.getChannelType();
    }

    @Override
    public Guild getGuild() {
        return event.getGuild();
    }

    @Override
    public JDA getJDA() {
        return event.getJDA();
    }

    @Override
    public Member getMember() {
        return event.getMember();
    }

    @Override
    public Message getMessage() {
        return null;
    }

    @Override
    public PrivateChannel getPrivateChannel() {
        return event.getPrivateChannel();
    }

    @Override
    public long getResponseNumber() {
        return event.getResponseNumber();
    }

    @Override
    public TextChannel getTextChannel() {
        return event.getTextChannel();
    }

    @Override
    public boolean isFromType(ChannelType channelType) {
        return event.getChannel().getType() == channelType;
    }

    @Override
    public boolean isOwner() {
        return event.getUser().getId().equals(this.getClient().getOwnerId());
    }
}
