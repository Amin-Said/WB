package me.aminsaid.core

import me.aminsaid.core.utils.getDayOfWeek
import me.aminsaid.core.utils.getDayOfWeekWithTime
import org.junit.Assert.assertEquals
import org.junit.Test

class StringExtensionsTest {

    @Test
    fun `getDayOfWeek should return correct day of the week`() {
        val dateString = "2023-10-05T14:30:00-07:00" // Replace with your test date string
        val expectedDayOfWeek = "Thursday"

        val result = dateString.getDayOfWeek()

        assertEquals(expectedDayOfWeek, result)
    }

    @Test
    fun `getDayOfWeekWithTime should return correct day of the week with time`() {
        val dateString = "2023-10-05T14:30:00+02:00" // Replace with your test date string
        val expectedDayOfWeekWithTime = "Thursday, 14:30 PM"

        val result = dateString.getDayOfWeekWithTime()

        assertEquals(expectedDayOfWeekWithTime, result)
    }
}
