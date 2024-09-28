package kr.eme.semiReward.managers

import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

object GUIManager {
    fun openRewardGUI(player: Player, page: Int) {
        val guiSize = 3 * 9 // 3줄
        val inventory = Bukkit.createInventory(null, guiSize, "도전과제")

        // 임시 미션 아이템
        for (i in 0 until (guiSize - 9)) {
            val missionItem = ItemStack(Material.PAPER)
            val meta = missionItem.itemMeta
            meta?.setDisplayName("§f미션 ${i + 1 + (page * 18)}")
            missionItem.itemMeta = meta
            inventory.setItem(i, missionItem)
        }

        // 이전페이지, 다음페이지 설정
        // 좌우 화살표 (이전/다음 페이지)
        if (page > 0) {
            inventory.setItem(guiSize - 9, createArrowItem(Material.ARROW, "§a이전 페이지"))
        }
        inventory.setItem(guiSize - 1, createArrowItem(Material.ARROW, "§a다음 페이지"))
        player.openInventory(inventory)
    }

    private fun createArrowItem(material: Material, name: String): ItemStack {
        val arrowItem = ItemStack(material)
        val meta = arrowItem.itemMeta
        meta?.setDisplayName(name)
        arrowItem.itemMeta = meta
        return arrowItem
    }
}