package kr.eme.semiReward

import kr.eme.semiReward.listeners.MissionGUIListener
import kr.eme.semiReward.listeners.MissionListener
import kr.eme.semiReward.managers.PlayerDataManager
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class SemiReward : JavaPlugin() {
    override fun onEnable() {
        super.onEnable()
        main = this
        Bukkit.getLogger().info("SemiReward Plugin Enabled")
        server.pluginManager.registerEvents(MissionListener, this)
        server.pluginManager.registerEvents(MissionGUIListener, this)
        PlayerDataManager.loadPlayerData()
    }
    override fun onDisable() {
        super.onDisable()
        Bukkit.getLogger().info("SemiReward Plugin Disabled")
        PlayerDataManager.savePlayerData()
    }
}