package kr.eme.semiReward.objects

data class Mission(
    val name: String,
    val description: String,
    val isCompleted: Boolean = false
)
