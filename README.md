# Dogs App

Small application that consumes the [Dog Api](https://thedogapi.com/). 

## TODO
* Add footer with a retry button when the pagination fails 
* Sort alphabetically the list elements in the home tab. Currently that menu item option is disabled
* Refactor pagination to use a `RxRemoteMediator` to handle network/db pagination
* Add offline support when when we search by breed name and when we open the breed detail page
* Add testing
* Create a custom `RxJava2CallAdapterFactory` to handle network errors. Currently the application has a helper class
to get the exception errors but this shouldn't be the way to do this
* Scroll to the top of the list when we click in the bottom nav position and we are currently in that position
* Create a delegate to handle view binding in fragments to avoid boilerplate code

**Note:** The Paging 3 currently has no support to sort the elements alphabetically as mentioned [here](https://issuetracker.google.com/issues/175430431).
Probably this will have to be made in the repository side.