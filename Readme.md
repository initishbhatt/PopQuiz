
# PopQuiz

Sample App showcasing Kotlin, RxJava2, Dagger2, MVP with clean Architecture

## The approach(Clean Architecture + MVP)
The project is split into the following packages:
- data - has the network and the store for the app
- ui - Android UI(Activities,Fragment,Dialogs)
- presentation - consists of presenters and services(presenter is responsible to update the view and and interacts with the data layer through the services)

## This project uses:
- [RxJava2](https://github.com/ReactiveX/RxJava),[RxAndroid](https://github.com/ReactiveX/RxAndroid) and [RxKotlin](https://github.com/ReactiveX/Rxkotlin)
- [Retrofit](https://github.com/square/retrofit)/ [OkHttp](https://github.com/square/okhttp)
- [Moshi](https://github.com/square/moshi)
- [Dagger 2](https://google.github.io/dagger/) 
- [Timber](https://github.com/JakeWharton/timber)
- [Mockito](http://site.mockito.org/)
- [DataBinding](https://developer.android.com/topic/libraries/data-binding/index.html)
- [Constraint layout](https://developer.android.com/training/constraint-layout/index.html)
- [Testing Support Library](https://developer.android.com/topic/libraries/testing-support-library/index.html)
