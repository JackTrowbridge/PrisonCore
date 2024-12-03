package dev.jacktrowbridge

import dev.jacktrowbridge.commands.CommandManager
import dev.jacktrowbridge.commands.player.CoinCommand
import dev.jacktrowbridge.player.PlayerManager
import net.minestom.server.MinecraftServer
import net.minestom.server.instance.block.Block

fun main() {
    // Initialize the server
    val minecraftServer = MinecraftServer.init()

    val globalEventNode = MinecraftServer.getGlobalEventHandler()

    val instanceManager = MinecraftServer.getInstanceManager()
    val instanceContainer = instanceManager.createInstanceContainer()

    PlayerManager.attach(globalEventNode, instanceContainer)

    instanceContainer.setGenerator { unit ->
        unit.modifier().fillHeight(0, 1, Block.STONE)
    }

    CommandManager.handleDynamicCommandRegistration(globalEventNode)

    // Register Events (set spawn instance, teleport player at spawn)
    // Start the server
    minecraftServer.start("0.0.0.0", 25565)
}