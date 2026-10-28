package id.ac.polbeng.erikaardini.threadrunnable

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import kotlinx.coroutines.delay

class MyWorker(context: Context, workerParams: WorkerParameters) :
    CoroutineWorker(context, workerParams) {

    companion object {
        private val TAG: String = MyWorker::class.java.name
        const val COUNTER = "counter"
        const val PROGRESS = "progress"
        const val MESSAGE = "message"
    }

    override suspend fun doWork(): Result {
        val counter = inputData.getInt(COUNTER, 0)
        Log.d(TAG, "Start counter...")
        for (i in 1..counter) {
            Log.d(TAG, "Counter-$i")
            setProgress(workDataOf(PROGRESS to i))
            delay(1000)
        }
        Log.d(TAG, "Finish counter!")
        val outputData = workDataOf(MESSAGE to "Finish counter!")
        return Result.success(outputData)
    }
}