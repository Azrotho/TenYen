package fr.azrotho.tenyen.events;

import fr.azrotho.tenyen.utils.compteur;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

public class OnCommand extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equals("ping")) {
            event.reply("Pong!").queue();
        }
        if (event.getName().equals("start")) {
            if(!event.getMember().hasPermission(Permission.ADMINISTRATOR)){
                event.reply("Vous n'avez pas la permission d'utiliser cette commande!").setEphemeral(true).queue();
                return;
            }else{
            String phrase = event.getOption("phrase").getAsString();

            compteur.yes = 0;
            compteur.no = 0;
            compteur.users.clear();

            event.reply("TenYenGame: " + phrase)
                    .addActionRow(
                            Button.primary("yes", "Oui"),
                            Button.danger("no", "Non")
                    ).queue();
            }
        }
        if(event.getName().equals("stop")) {
            event.reply("Arrêt des votes! Résultat: Oui:" + compteur.yes + " Non:" + compteur.no).queue();
            compteur.yes = 0;
            compteur.no = 0;
            compteur.users.clear();
        }
    }
}
