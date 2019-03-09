import android.arch.lifecycle.LiveData
import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.util.Log
import com.test.admin.testproj.MainActivity
import com.test.admin.testproj.tests.kotlin.abstracts.Person
import com.test.admin.testproj.tests.kotlin.coroutines.asyncIO
import com.test.admin.testproj.tests.stackoverflow.TestJava
import io.reactivex.Single
import kotlinx.coroutines.*
import kotlinx.coroutines.selects.select
import org.json.JSONObject
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.coroutines.CoroutineContext


fun stackoverflowTest() {
    val jav = TestJava.StackOverFlow()


    Log.e("WWW", "WWW result=$")

}


class NotificationProducer {

    fun produce() {
        Log.e("WWW", "WWW You have a message!")
    }
}


fun some(userName: String) {
}



class Presenter : CoroutineScope {
    private var job: Job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Default + job

    //private var scope = CoroutineScope(Dispatchers.Main + job)

    fun cancel() {
        job.cancel()
    }

    fun schedule() = launch {
        var seconds = 1
        var s = 1
        val producer = NotificationProducer()
        while (true) {
            Log.e("WWW", "WWW ...............")

            delay(1000)

            if (seconds++ == 5) {
                producer.produce()
                seconds = 1
            }

            s++
            if (s == 30) {
                cancel()
            }
        }
    }
}














