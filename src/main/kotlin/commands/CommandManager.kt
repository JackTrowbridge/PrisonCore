package dev.jacktrowbridge.commands

import dev.jacktrowbridge.commands.admin.RankCommand
import dev.jacktrowbridge.commands.player.CoinCommand
import dev.jacktrowbridge.player.PlayerManager
import dev.jacktrowbridge.player.PlayerRank
import net.minestom.server.MinecraftServer
import net.minestom.server.command.CommandManager
import net.minestom.server.command.builder.Command
import net.minestom.server.event.Event
import net.minestom.server.event.EventNode
import net.minestom.server.event.player.AsyncPlayerConfigurationEvent
import net.minestom.server.event.player.AsyncPlayerPreLoginEvent

object CommandManager {

    private val commandManager = MinecraftServer.getCommandManager()

    init {
        registerCommand(CoinCommand())
        registerCommand(RankCommand())
    }

    fun handleDynamicCommandRegistration(eventNode: EventNode<Event>) {
        eventNode.addListener(AsyncPlayerConfigurationEvent::class.java) { event ->
            val player = event.player
            val prisonPlayer = PlayerManager.getPlayer(player.uuid)

            if(prisonPlayer?.rank == PlayerRank.DEFAULT){
                commandManager.unregister(RankCommand())
            }
        }
    }

    fun registerCommand(command: Command){
        commandManager.register(command)
    }

}