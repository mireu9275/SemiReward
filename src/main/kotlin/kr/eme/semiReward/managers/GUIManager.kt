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
        populateGUI(player, inventory, 0)
        player.openInventory(inventory)
    }
    private fun populateGUI(player: Player, inventory: Inventory, page: Int) {
        val playerData = PlayerDataManager.getPlayerData(player.uniqueId)
        val missions = MissionManager.getMissionList()
        val startIndex = page * 5
        val endIndex = startIndex + 5

        inventory.clear()

        val baseMissionSlots = listOf(9, 11, 13, 15, 17)
        val missionSlots = baseMissionSlots.map { it + if (page % 2 == 1) 1 else 0 } // 페이지에 따라 왼쪽으로 한 칸씩 이동

        for ((index, inventorySlot) in missionSlots.withIndex()) {
            val missionIndex = startIndex + index
            if (missionIndex >= missions.size) break

            val mission = missions.getOrNull(missionIndex) ?: continue
            val isCompleted = (missionIndex + 1) in playerData.completedMissions
            val isAvailable = missionIndex == 0 || (missionIndex in playerData.completedMissions)

            val item = ItemStack(if (isCompleted || isAvailable) Material.EMERALD_BLOCK else Material.REDSTONE_BLOCK)
            val meta = item.itemMeta
            meta?.setDisplayName("§a${mission.name}")
            meta?.lore = listOf("§f${mission.description}")
            item.itemMeta = meta
            inventory.setItem(inventorySlot, item)
        }
        if (page > 0 ) {
            // 좌우 스크롤 버튼
            val leftButton = ItemStack(Material.ARROW)
            val leftMeta = leftButton.itemMeta
            leftMeta?.setDisplayName("§e왼쪽으로 이동")
            leftButton.itemMeta = leftMeta
            inventory.setItem(18, leftButton)
        }

        if (endIndex < missions.size) {
            val rightButton = ItemStack(Material.ARROW)
            val rightMeta = rightButton.itemMeta
            rightMeta?.setDisplayName("§e오른쪽으로 이동")
            rightButton.itemMeta = rightMeta
            inventory.setItem(26, rightButton)
        }
    }

    fun nextPage(player: Player) {
        PlayerDataManager.addCurrentPage(player.uniqueId)
        openMissionGUI(player)
    }

    fun previousPage(player: Player) {
        PlayerDataManager.subtractCurrentPage(player.uniqueId)
        openMissionGUI(player)
    }
}