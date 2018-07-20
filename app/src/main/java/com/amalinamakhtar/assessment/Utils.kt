package com.amalinamakhtar.assessment

class Utils {

    companion object {
        @JvmStatic
        fun parseSchedules(name: String, schedules: String): RestaurantModel {

            val datesArray = schedules.split(Regex("\\s/"))
            val schedulesArray = arrayListOf<String>()

            datesArray.forEach {
                val value = it.trim()
                schedulesArray.add(value)
            }

            val finalName = name.trim().removePrefix("[").removeSuffix("\"").removePrefix("\"")
            val finalDaysArray = arrayListOf<String>()
            var hours = ""

            schedulesArray.forEach {
                val tempDaysArray = arrayListOf<String>()

                if (it.contains(", ")) {
                    val scheduleDays = it.substring(0, 13)
                    val commaIndex = scheduleDays.trim().indexOf(", ")
                    val secondSpaceIndex = it.indexOf(" ", it.indexOf(" ") + 1)

                    tempDaysArray.add(scheduleDays.substring(0, commaIndex).trim())
                    tempDaysArray.add(scheduleDays.substring(commaIndex + 1, secondSpaceIndex).trim())
                    hours = it.substring(13, (it.lastIndex + 1))
                            .trim()
                            .removeSuffix("]")
                            .removeSuffix("\"")
                } else {
                    tempDaysArray.add(it.substring(0, it.indexOf(" ")).trim())
                    hours = it.substring(it.indexOf(" "), (it.lastIndex + 1))
                            .trim()
                            .removeSuffix("]")
                            .removeSuffix("\"")
                }
                for (days: String in tempDaysArray) {
                    if (days.length == 3) {
                        finalDaysArray.add("${days.substring(0, 3)} $hours")
                    } else {
                        finalDaysArray.add("${days.substring(0, 3)} $hours")
                        finalDaysArray.add("${days.substring(4, 7)} $hours")
                    }
                }
            }

            var finalSchedule = ""
            for (item: String in finalDaysArray) {
                finalSchedule = finalSchedule.plus("$item\n")
            }
            return RestaurantModel(finalName, finalSchedule)
        }
    }
}