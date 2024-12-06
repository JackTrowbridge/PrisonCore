package dev.jacktrowbridge.commands

import dev.jacktrowbridge.commands.admin.RankCommand
import dev.jacktrowbridge.commands.admin.SpawnCommand
import dev.jacktrowbridge.commands.player.CoinCommand
import dev.jacktrowbridge.player.PlayerManager
import dev.jacktrowbridge.player.PlayerRank
import net.minestom.server.MinecraftServer
import net.minestom.server.command.builder.Command
import net.minestom.server.entity.Player
import net.minestom.server.event.Event
import net.minestom.server.event.EventNode
import net.minestom.server.event.player.AsyncPlayerConfigurationEvent

object CommandManager {

    private val commandManager = MinecraftServer.getCommandManager()

    init {
        registerCommand(CoinCommand())
        registerAdminCommands()
    }

    fun handleDynamicCommandRegistration(eventNode: EventNode<Event>) {
        eventNode.addListener(AsyncPlayerConfigurationEvent::class.java) { event ->
            val player = event.player
            val prisonPlayer = PlayerManager.getPlayer(player.uuid)

            if(prisonPlayer?.rank == PlayerRank.DEFAULT){
                unregisterAdminCommands()
            }
        }
    }

    fun checkAdmin(sender: Any): Boolean{
        if (sender is Player) {
            val prisonPlayer = PlayerManager.getPlayer(sender.uuid)
            return prisonPlayer?.rank == PlayerRank.ADMIN
        } else {
            return true
        }
    }

    fun checkAdminDuringCommand(sender: Any): Boolean{
        if (sender is Player) {
            val prisonPlayer = PlayerManager.getPlayer(sender.uuid)
            if (prisonPlayer?.rank != PlayerRank.ADMIN) {
                sender.sendMessage("You do not have permission to execute this command.")
                return false
            }
        }
        return true
    }

    fun registerAdminCommands(){
        registerCommand(RankCommand())
        registerCommand(SpawnCommand())
    }
    fun unregisterAdminCommands(){
        unregisterCommand(RankCommand())
        unregisterCommand(SpawnCommand())
    }

    fun registerCommand(command: Command){
        commandManager.register(command)
    }

    fun unregisterCommand(command: Command){
        commandManager.unregister(command)
    }

}