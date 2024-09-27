package kr.eme.semiReward

import org.bukkit.plugin.java.JavaPlugin

class SemiReward : JavaPlugin() {
    override fun onEnable() {
        main = this
        super.onEnable()
    }
    override fun onDisable() {
        super.onDisable()
    }
}