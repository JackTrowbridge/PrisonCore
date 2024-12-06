package dev.jacktrowbridge.commands.admin

import dev.jacktrowbridge.commands.CommandManager
import dev.jacktrowbridge.mobs.MobManager
import dev.jacktrowbridge.mobs.Mobs
import net.minestom.server.command.builder.Command
import net.minestom.server.command.builder.arguments.ArgumentType
import net.minestom.server.entity.Player

class SpawnCommand : Command("spawn"){

    init {

        setCondition { sender, _ ->
            CommandManager.checkAdmin(sender)
        }

        setDefaultExecutor{sender, _ ->
            sender.sendMessage("Usage: /spawn <mob> <amount>")
        }

        addSyntax(
            {sender, context ->

                val mob = context.get(ArgumentType.Enum("mob", Mobs::class.java))
                val amount = context.get(ArgumentType.Integer("amount"))

                if(!CommandManager.checkAdminDuringCommand(sender)){
                    return@addSyntax
                }

                MobManager().spawnMob(mob, amount, sender as Player)

            }, ArgumentType.Enum("mob", Mobs::class.java), ArgumentType.Integer("amount")
        )

    }

}