# Use case for Kotlin + Architecture components Android App
https://github.com/karntrehan/Posts
## Android Architecture
### lifecycle

### ViewModel
https://developer.android.com/topic/libraries/architecture/viewmodel.html

* Keep model for views.
* Call db or network to manage data
* Can be used for Fragments communication

### Services
* DataContract : Define every contract for Repository/Remote/Local
* Remote : In charge of getting data from webservices
* UsersServices : Interface using _Retrofit_ to call webservices
* Local : manage data from phone storage
* Repository : Manager for Remote/local data.

### LiveData
Not used here because of use of RxJava.

It enables watching viewmodel data and automatically bind to view.

### Room
https://developer.android.com/topic/libraries/architecture/room.html
 1. Add annotations to your data
 2. Create a DAO interface with @Dao with SQL queries
 3. Create a Database wich extends RoomDatabase (see NotesDB)

### Paging
Not used for now.

## Dependencies

* [Dagger](http://square.github.io/dagger/) : Dependencies injection

   Based on modules and Components. Here, there is a common CoreComponent which provides basics elements (Http Request, Session, Storage, Context, ...). 
   
   Then, each application part define it's specifics injection based on a dedicated Scope. This parts is composed of a specific component/module.
* [Stetho](http://facebook.github.io/stetho/) : Debug
* [Retrofit](http://square.github.io/retrofit/) : Simple Http Client for Android. Just, create Java interface with specific annotation, and it generates request code. 
* [OkHttp](http://square.github.io/okhttp/) : Http client innjected in Retrofit. Useful for HTTP/2 and SPDY support, pluggable interceptors 
   Required for Stetho
* [Gson](https://github.com/google/gson) : Deserialize json responses
* [Picasso](http://square.github.io/picasso/) : Image downloader


# WIP
 * Post notes/messages
 * enable an automatic refresh 
 * loader when requesting data
 * Images on user