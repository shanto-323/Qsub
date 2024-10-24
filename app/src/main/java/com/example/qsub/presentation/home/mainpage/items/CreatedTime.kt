package com.example.qsub.presentation.home.mainpage.items

import java.util.concurrent.TimeUnit

fun createdTime(created : Long) : String {
  val currentTime = System.currentTimeMillis()
  val timeDifference = currentTime - created

  val minutes = TimeUnit.MILLISECONDS.toMinutes(timeDifference)
  val hours = TimeUnit.MILLISECONDS.toHours(timeDifference)
  val days = TimeUnit.MILLISECONDS.toDays(timeDifference)
  val months = days / 30
  val years = days / 365

  return when {
    minutes < 1 -> "Just now"
    minutes < 60 -> "$minutes minute${if (minutes > 1) "s" else ""} ago"
    hours < 24 -> "$hours hour${if (hours > 1) "s" else ""} ago"
    days < 30 -> "$days day${if (days > 1) "s" else ""} ago"
    months < 12 -> "$months month${if (months > 1) "s" else ""} ago"
    else -> "$years year${if (years > 1) "s" else ""} ago"
  }
}