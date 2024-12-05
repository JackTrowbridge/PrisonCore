package dev.jacktrowbridge.player

import dev.jacktrowbridge.items.Rarity
import dev.jacktrowbridge.items.withRarity
import net.kyori.adventure.text.Component
import net.minestom.server.coordinate.Pos
import net.minestom.server.entity.Player
import net.minestom.server.event.Event
import net.minestom.server.event.EventNode
import net.minestom.server.event.player.AsyncPlayerConfigurationEvent
import net.minestom.server.instance.InstanceContainer
import net.minestom.server.item.ItemComponent
import net.minestom.server.item.ItemStack
import net.minestom.server.item.Material
import java.util.UUID
import java.util.concurrent.ConcurrentHashMap

object PlayerManager {
    private val players = ConcurrentHashMap<UUID, PPlayer>()

    fun addPlayer(player: Player){
        val prisonPlayer = players.computeIfAbsent(player.uuid){
            attemptLoadPlayerFromDatabase(player)
        }
        prisonPlayer.updatePlayer(player)
    }

    fun removePlayer(player: Player){
        players.remove(player.uuid)
    }

    fun getPlayer(player: Player): PPlayer? {
        return players[player.uuid]
    }
    fun getPlayer(uuid: UUID): PPlayer? {
        return players[uuid]
    }

    fun getAllPlayers(): Collection<PPlayer> {
        return players.values
    }

    fun attach(eventNode: EventNode<Event>, defaultInstance: InstanceContainer){
        eventNode.addListener(AsyncPlayerConfigurationEvent::class.java){ event ->
            val player = event.player
            event.spawningInstance = defaultInstance
            player.respawnPoint = Pos(0.0, 1.0, 0.0)
            addPlayer(player)
            val item = ItemStack.builder(Material.STONE)
                .set(ItemComponent.ITEM_NAME, Component.text("Test Item"))
                .withRarity(Rarity.LEGENDARY)
                .build()
            player.inventory.addItemStack(item)
        }
    }

    private fun attemptLoadPlayerFromDatabase(player: Player): PPlayer {
        // Load player from database
        return PPlayer(player)
    }
}