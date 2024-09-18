# Implementation and Thought Process

### Data

First stage was to identify the key data components required for the app:
* List of pictures to be displayed as a grid in the "Home" tab
	* This was obtained by using the provided `PictureService`
* List of favorited pictures to be shown on the "Favorite" tab
* Determining which picture has been selected in the grid -- so that it can be shown in enlarged version, as well as highlighted in grid

These pieces of data were stored as MutableStates in a custom ViewModel that I created, so that it can be isolated from the UI-related code. `MutableState` was used since using it with combination of the `remember` function allows the composables to be re-composed, so that the data can be synced across the UI elements. 

### Home Screen

I created the composable representing the "Home" screen, which vertically consists of the selected picture information and a grid of pictures.
The selected picture portion is only shown if there is one, and that information is stored in the viewmodel, as previously mentioned. 
Once the user clicks on any picture from the grid, that becomes selected (regardless of what picture was previously selected) and shown above the grid, with a height of 35% of the total screen height, which seemed similar to what was shown in the sample video. In addition, the heart icon on the bottom right of the selected picture is clickable, and when clicked, toggles the favorited state of this photo. The heart icon drawable is also updated to be unfilled/filled to reflect the change.

### Favorites Screen

I created the composable representing the "Favorites Screen". The structure was identical to the "Home Screen": There is a selected photo portion shown (only when a photo is selected) with a favorites button on the bottom right and there is a grid of photos below it. The only difference was that the "grid of pictures" came from different sources -- the Home screen showed all pictures whereas the Favorites screen only shows the favorited ones. So I abstracted this composable only by passing in the source used for the grid of pictures, so that it can be re-used for both screens.

### Data Syncing
By using `MutableState` objects in conjunction with `remember`, data is synced across both Home and Favorites screen. Specifically, when the user favorites/unfavorites a picture, this is immediately updated across both Home and Favorites screen. I also handle specific edge cases:
* When a picture is "selected" and shown in enlarged on the favorites screen, once the user clicks on the heart icon to unfavorite it, the picture next to it is  "selected" and shown enlarged. If there are no more favorited photos, then nothing is shown. This is the behavior that was shown on the sample video.
* When a picture is "selected" and shown in enlarged on the favorites screen, user navigates back to the home screen and unfavorites the same picture, when the user goes back to the favorites screen, the unfavorited picture is no longer there and the picture next to it is selected and shown enlarged instead. If there are no more favorited pictures, then nothing is shown, as expected.
* When a picture is selected and favorited in the Home screen, and the user unfavorites it from the favorites screen, when the user navigates back to the Home screen, the heart icon is unfilled indicating that it has been unfavorited, as expected.

### Bottom Navigation bar
I used the navigation component to implement the bottom tab and handle switching between the screens. By leveraging this library, it easily handled the ability to save states when switching between the screens. Also, I opted to use this library as it was also scalable -- more tabs could be easily added without much code change.


### Code snippets used
- To find syntax for buillding a Retrofit instance https://medium.com/@pritam.karmahapatra/retrofit-in-android-with-kotlin-9af9f66a54a8
