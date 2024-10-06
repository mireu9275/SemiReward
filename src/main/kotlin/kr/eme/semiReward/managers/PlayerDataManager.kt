package kr.eme.semiReward.managers

import kr.eme.semiReward.main
import kr.eme.semiReward.objects.PlayerData
import org.bukkit.configuration.file.YamlConfiguration
import java.io.File
import java.util.UUID

object PlayerDataManager {
    private val playerDataMap = HashMap<UUID, PlayerData>()
    private lateinit var dataFile: File
    private lateinit var config: YamlConfiguration

    fun addCurrentPage(uuid: UUID) {
        val playerData = getPlayerData(uuid)
        playerData.currentPage += 1
        playerDataMap[uuid] = playerData
        println(getPlayerData(uuid))
    }
    fun subtractCurrentPage(uuid: UUID) {
        val playerData = getPlayerData(uuid)
        if (playerData.currentPage <= 0) return
        playerData.currentPage -= 1
        playerDataMap[uuid] = playerData
    }

    fun loadPlayerData() {
        dataFile = File(main.dataFolder, "playerData.yml")
        if (!dataFile.exists()) {
            dataFile.parentFile.mkdirs()
            dataFile.createNewFile()
        }

        config = YamlConfiguration.loadConfiguration(dataFile)

        for (key in config.getKeys(false)) {
            val uuid = UUID.fromString(key)
            val completedMissions = config.getIntegerList("$key.completedMissions").toMutableSet()
            val currentPage = config.getInt("$key.currentPage", 0)
            playerDataMap[uuid] = PlayerData(uuid, completedMissions, currentPage)
        }
    }

    fun savePlayerData() {
        for ((uuid, data) in playerDataMap) {
            config.set("$uuid.completedMissions", data.completedMissions.toList())
            config.set("$uuid.currentPage", data.currentPage)
        }
        config.save(dataFile)
    }

    fun getPlayerData(uuid: UUID): PlayerData = playerDataMap.computeIfAbsent(uuid) { PlayerData(uuid) }
}