# Use case for Kotlin + Architecture components Android App
Based on https://github.com/karntrehan

## Android Architecture
### lifecycle

### ViewModel
https://developer.android.com/topic/libraries/architecture/viewmodel.html

Keep model for views.
Call db or network to manage data
Can be used for Fragments communication

### Services
* DataContract : Define every contract for Repository/Remote/Local
* Remote : In charge of getting data from webservices
* UsersServices : Interface using _Retrofit_ to call webservices
* Local : manage data from phone storage
* Repository : Manager for Remote/local data.

### LiveData

### Room

### Paging


## Dependencies

* Dagger : Dependencie injection
* stetho : Debug