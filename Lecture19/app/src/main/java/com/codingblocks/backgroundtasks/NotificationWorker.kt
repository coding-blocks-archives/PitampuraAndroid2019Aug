package com.codingblocks.backgroundtasks

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class NotificationWorker(context:Context,workerParameters: WorkerParameters) : Worker(context,workerParameters) {
    override fun doWork(): Result {

        return Result.success()
    }
}