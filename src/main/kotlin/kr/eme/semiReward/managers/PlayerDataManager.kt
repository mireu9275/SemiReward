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
            playerDataMap[uuid] = PlayerData(uuid, completedMissions)
        }
    }

    fun savePlayerData() {
        for ((uuid, data) in playerDataMap) {
            config.set("$uuid.completedMissions", data.completedMissions.toList())
        }
        config.save(dataFile)
    }

    fun getPlayerData(uuid: UUID): PlayerData = playerDataMap.computeIfAbsent(uuid) { PlayerData(uuid) }
}