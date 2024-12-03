package dev.jacktrowbridge.player

import net.minestom.server.entity.Player

enum class PlayerRank{
    DEFAULT,
    VIP,
    ADMIN
}

class PPlayer(private var player: Player) {
    var coins: Int = 10
        private set
    var rank: PlayerRank = PlayerRank.ADMIN
        private set

    fun addCoins(amount: Int) {
        coins += amount
    }
    fun removeCoins(amount: Int) {
        coins -= amount
    }
    fun setCoins(amount: Int) {
        coins = amount
    }

    fun setRank(rank: PlayerRank) {
        this.rank = rank
    }

    fun updatePlayer(newPlayer: Player) {
        this.player = newPlayer
    }

    fun getPlayer(): Player = player

}

fun Player.asPPlayer() = PPlayer(this)