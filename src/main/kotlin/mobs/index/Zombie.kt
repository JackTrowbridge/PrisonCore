package dev.jacktrowbridge.mobs.index

import net.kyori.adventure.text.Component
import net.minestom.server.coordinate.Pos
import net.minestom.server.entity.EntityCreature
import net.minestom.server.entity.EntityType
import net.minestom.server.instance.Instance

class Zombie(
    instance: Instance,
    spawnPosition: Pos,
) : EntityCreature(EntityType.ZOMBIE){

    private var maxHealth = 20.0
    private var health = 20.0

    init {
        setInstance(instance, spawnPosition)

        customName = Component.text("Zombie | $health/$maxHealth")
        isCustomNameVisible = true
    }

}