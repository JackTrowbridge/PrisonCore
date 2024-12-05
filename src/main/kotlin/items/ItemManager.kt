package dev.jacktrowbridge.items

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import net.minestom.server.item.ItemComponent
import net.minestom.server.item.ItemStack
import net.minestom.server.tag.Tag

enum class Rarity(val displayName: String, val color: NamedTextColor) {
    COMMON("COMMON", NamedTextColor.WHITE),
    UNCOMMON("UNCOMMON", NamedTextColor.GREEN),
    RARE("RARE", NamedTextColor.BLUE),
    LEGENDARY("LEGENDARY", NamedTextColor.GOLD)
}

fun ItemStack.Builder.withRarity(rarity: Rarity): ItemStack.Builder {
    val rarityLore = Component.text(rarity.displayName)
        .color(rarity.color)
        .decorate(TextDecoration.BOLD)
        .decoration(TextDecoration.ITALIC, false)

    this.set(ItemComponent.LORE, listOf(rarityLore)) // fix this

    val rarityTag: Tag<String> = Tag.String("rarity")
    this.setTag(rarityTag, rarity.displayName)

    return this
}