package kr.eme.semiReward.listeners

import kr.eme.semiReward.managers.MissionManager
import kr.eme.semiReward.managers.PlayerDataManager
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent

object MissionGUIListener : Listener {
    @EventHandler
    fun onInventoryClick(event: InventoryClickEvent) {
        if (event.view.title != "§6미션") return
        event.isCancelled = true

        val player = event.whoClicked as Player
        val uuid = player.uniqueId
        val clickedItem = event.currentItem ?: return
        when (clickedItem.type) {
            Material.REDSTONE_BLOCK -> {
                val mission = MissionManager.getMission(event.slot + 1) ?: return
                val playerData = PlayerDataManager.getPlayerData(uuid)
                if (event.slot +1 !in playerData.completedMissions) {
                    player.sendMessage("§e${mission.name} 시작")
                }
            }
            Material.EMERALD_BLOCK -> {
                player.sendMessage("§e이미 완료된 미션입니다.")
            }
            Material.ARROW -> {
                if (event.slot == 18) {
                    player.sendMessage("§e왼쪽으로 이동")
                }
                else if (event.slot == 26) {
                    player.sendMessage("§e오른쪽으로 이동")
                }
            }
            else -> {}
        }
    }
}