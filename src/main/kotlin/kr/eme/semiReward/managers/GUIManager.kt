package kr.eme.semiReward.managers

import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack

object GUIManager {
    fun openMissionGUI(player: Player) {
        // 추후 config 로 빠질 가능성 있는 변수들...
        val missionGUISize = 3 * 9
        val missionGUITitle = "§6§l미션"
        val inventory = Bukkit.createInventory(null, missionGUISize, missionGUITitle)
    }
    private fun populateGUI(player: Player, inventory: Inventory) {
        val playerData = PlayerDataManager.getPlayerData(player.uniqueId)
        MissionManager.getMissionList().forEachIndexed { index, mission ->
            val isCompleted = (index + 1) in playerData.completedMissions
            val item = ItemStack(if (isCompleted) Material.EMERALD_BLOCK else Material.REDSTONE_BLOCK)
            val meta = item.itemMeta
            meta?.setDisplayName("§a${mission.name}")
            meta?.lore = listOf(mission.description)
            item.itemMeta = meta
            inventory.setItem(index, item)
        }

        // 좌우 스크롤 버튼
        val leftButton = ItemStack(Material.ARROW)
        val leftMeta = leftButton.itemMeta
        leftMeta?.setDisplayName("§e왼쪽으로 이동")
        leftButton.itemMeta = leftMeta
        inventory.setItem(18, leftButton)

        val rightButton = ItemStack(Material.ARROW)
        val rightMeta = rightButton.itemMeta
        rightMeta?.setDisplayName("§e오른쪽으로 이동")
        rightButton.itemMeta = rightMeta
        inventory.setItem(26, leftButton)
    }
}