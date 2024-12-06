package dev.jacktrowbridge.commands.admin

import dev.jacktrowbridge.commands.CommandManager
import dev.jacktrowbridge.player.PlayerManager
import dev.jacktrowbridge.player.PlayerRank
import net.minestom.server.MinecraftServer
import net.minestom.server.command.builder.Command
import net.minestom.server.command.builder.arguments.ArgumentType
import net.minestom.server.entity.Player

class RankCommand : Command("rank") {
    init {

        setCondition { sender, _ ->
            CommandManager.checkAdmin(sender)
        }


        setDefaultExecutor { sender, _ ->
            sender.sendMessage("Usage: /rank <player> <rank>")
        }

        addSyntax(
            { sender, context ->

                val targetName = context.get(ArgumentType.String("target"))
                val rank = context.get(ArgumentType.Enum("rank", PlayerRank::class.java))

                if(!CommandManager.checkAdminDuringCommand(sender)){
                    return@addSyntax
                }

                val targetPlayer = MinecraftServer.getConnectionManager()
                    .onlinePlayers
                    .firstOrNull() { it.username.equals(targetName, ignoreCase = true) }

                if (targetPlayer == null) {
                    sender.sendMessage("Player $targetName not found.")
                    return@addSyntax
                }

                val targetPrisonPlayer = PlayerManager.getPlayer(targetPlayer.uuid)
                if (targetPrisonPlayer != null) {
                    targetPrisonPlayer.setRank(rank)
                    sender.sendMessage("$targetName's rank is now $rank.")
                    targetPlayer.sendMessage("Your rank has been updated to $rank.")
                } else {
                    sender.sendMessage("Couldn't fetch $targetName's rank.")
                }
            }, ArgumentType.String("target"), ArgumentType.Enum("rank", PlayerRank::class.java)
        )

    }
}