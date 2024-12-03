package dev.jacktrowbridge.commands.player

import dev.jacktrowbridge.commands.CommandManager
import dev.jacktrowbridge.player.PlayerManager
import net.minestom.server.command.builder.Command
import net.minestom.server.entity.Player

class CoinCommand : Command("coins"){
    init {


        setDefaultExecutor { sender, _ ->
            if(sender is Player){
                val prisonPlayer = PlayerManager.getPlayer(sender.uuid)
                if (prisonPlayer != null){
                    sender.sendMessage("You have ${prisonPlayer.coins} coins.")
                } else {
                    sender.sendMessage("Couldn't fetch your coins.")
                }
            }else{
                sender.sendMessage("You must be a player to execute this command.")
            }
        }
    }
}