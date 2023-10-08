package fr.azrotho.tenyen.events;

import fr.azrotho.tenyen.utils.Compteur;
import net.dv8tion.jda.api.Permission;
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

            Compteur.yes = 0;
            Compteur.no = 0;
            Compteur.users.clear();

            event.reply("TenYenGame: " + phrase)
                    .addActionRow(
                            Button.primary("yes", "Oui"),
                            Button.danger("no", "Non")
                    ).queue();
            }
        }
        if(event.getName().equals("stop")) {
            event.reply("Arrêt des votes! Résultat: Oui:" + Compteur.yes + " Non:" + Compteur.no).queue();
            Compteur.yes = 0;
            Compteur.no = 0;
            Compteur.users.clear();
        }
    }
}
