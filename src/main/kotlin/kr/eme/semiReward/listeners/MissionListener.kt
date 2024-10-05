package kr.eme.semiReward.listeners

import kr.eme.semiReward.managers.GUIManager
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent

object MissionListener : Listener {
    @EventHandler
    fun onPlayerInteract(event: PlayerInteractEvent) {
        val player = event.player
        if (event.item?.type == Material.BOOK)
            GUIManager.openMissionGUI(player)
    }
}