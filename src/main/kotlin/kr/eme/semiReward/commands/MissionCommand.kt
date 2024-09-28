package kr.eme.semiReward.commands

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object MissionCommand : CommandExecutor {
    override fun onCommand(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): Boolean {
        if (sender !is Player) {
            sender.sendMessage("콘솔에서는 이 명령어를 사용할 수 없습니다.")
            return true
        }
        val player = sender

        if (args.isEmpty()) {
            usage(sender)
            return true
        }

        if (args.size)

        return true
    }

    private fun usage(player: Player) {
        player.sendMessage(
                "===== 명령어 사용법 =====" +
                "/Mission Check : 미션을 확인합니다."  +
                "=======================")
    }
}