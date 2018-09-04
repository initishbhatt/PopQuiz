package com.initishbhatt.popquiz.util

import io.reactivex.Scheduler
import io.reactivex.schedulers.TestScheduler

/**
 * @author nitishbhatt
 */
open class BaseTest {
    protected var testScheduler = TestScheduler()

    protected var schedulersProvider: SchedulerProvider = object : SchedulersProviderImpl() {

        override fun io(): Scheduler {
            return testScheduler
        }

        override fun computation(): Scheduler {
            return testScheduler
        }

        override fun ui(): Scheduler {
            return testScheduler
        }
    }
}