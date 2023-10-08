package fr.azrotho.tenyen.events;

import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import fr.azrotho.tenyen.utils.Compteur;

public class OnButton extends ListenerAdapter {

    @Override
    public void onButtonInteraction(ButtonInteractionEvent event) {
        if(Compteur.users.contains(event.getUser())) {
            event.reply("Vous avez déjà voté!").setEphemeral(true).queue();
        } else {
            Compteur.users.add(event.getUser());
            if(event.getButton().getId().equals("yes")) {
                Compteur.yes++;
                event.reply("Vous avez voté Oui!").setEphemeral(true).queue();
            }
            if(event.getButton().getId().equals("no")) {
                Compteur.no++;
                event.reply("Vous avez voté Non!").setEphemeral(true).queue();
            }
        }
    }
}
