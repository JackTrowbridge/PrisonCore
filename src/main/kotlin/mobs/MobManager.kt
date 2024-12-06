package dev.jacktrowbridge.mobs

import dev.jacktrowbridge.mobs.index.Zombie
import net.minestom.server.entity.Player

enum class Mobs() {
    ZOMBIE
}

class MobManager {

    fun spawnMob(mob: Mobs, amount: Int, player: Player) {
        for (i in 0 until amount) {
            when (mob) {
                Mobs.ZOMBIE -> {
                    Zombie(player.instance, player.position)
                }
            }
        }
    }

}