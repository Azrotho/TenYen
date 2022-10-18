package fr.azrotho.tenyen;

import fr.azrotho.tenyen.events.OnButton;
import fr.azrotho.tenyen.events.OnCommand;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;
import fr.azrotho.tenyen.utils.compteur;

import java.util.EnumSet;

import static net.dv8tion.jda.api.interactions.commands.OptionType.*;

public class Main {

    public static void main(String[] args) {
        JDA jda = JDABuilder.createDefault("UR_TOKEN", EnumSet.noneOf(GatewayIntent.class))
                .addEventListeners(new OnButton())
                .addEventListeners(new OnCommand())
                .setActivity(net.dv8tion.jda.api.entities.Activity.playing("Cache des pièces sous la serviette <3"))
                .setStatus(OnlineStatus.DO_NOT_DISTURB)
                .build();

        compteur.yes = 0;
        compteur.no = 0;
        compteur.users.add(jda.getUserById("789777759106105355"));

        CommandListUpdateAction commands = jda.updateCommands();
        commands.addCommands(
                Commands.slash("ping", "Pong!")
        );
        commands.addCommands(
                Commands.slash("start", "Starts a game")
                        .addOptions(new OptionData(STRING, "phrase", "La phrase à dire")
                                .setRequired(true))
                        .setGuildOnly(true)
        );
        commands.addCommands(
                Commands.slash("stop", "Stops a game")
                        .setGuildOnly(true)
        );
        commands.queue();
    }
}
