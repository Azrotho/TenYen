package events;

import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import utils.compteur;

public class OnButton extends ListenerAdapter {

    @Override
    public void onButtonInteraction(ButtonInteractionEvent event) {
        if(compteur.users.contains(event.getUser())) {
            event.reply("Vous avez déjà voté!").setEphemeral(true).queue();
        } else {
            compteur.users.add(event.getUser());
            if(event.getButton().getId().equals("yes")) {
                compteur.yes++;
                event.reply("Vous avez voté Oui!").setEphemeral(true).queue();
            }
            if(event.getButton().getId().equals("no")) {
                compteur.no++;
                event.reply("Vous avez voté Non!").setEphemeral(true).queue();
            }
        }
    }
}
