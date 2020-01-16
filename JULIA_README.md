# WorldRemit Android Coding Task

## JULIA's NOTES

Yes, this project cannot be done in 3 hours.
Here's what I accomplished, and in the priority order.  Note the TODO's are in order of what I would do next...

1. Tried app, verified 100 users fetched.
2. Updated user list
    - updated the item_user adapter view to include image, id, buttons 
    - updated the UsersApapter to show data
    - updated the UsersListPresenter to have observables react to clicks and button presses
    - wired up the observables to signal when clicks are made
    - NO Error handling on the subscribe.  Added a TODO.  Not specified how errors are handled, probably should have logged!!!
    
    User List TODOs
        - Error handling / logging/ snackbar 
        - UI needs polishing, dashes on names when split
        - Tests, TESTS!!!!  Instrumentation, Junit, Espresso
             - Mock data 
             - good data, big lists, small lists, bad data, empty list, null list, etc
             - UI Tests with espresso for list, clicks, open, back, etc
           
            
    
 
3. Added user details
    
    - updated API for searching for a user (getting a fresh one in case the server changed)
    - Added UserDetailsActivity/Presenter
    - called API
    - show some info.  
    
    User Details TODOs
         - Error handling / logging/ snackbar 
         - Finish UI, add 'Following' to ui
         - Should User be persisted and passed to details instead of id?  Stale data or hit network?
         - Tests, TESTS!!
             - Mock data 
             - Good data, bad data,, null, etc
             - UI Tests with espresso for list, clicks, open, etc
    
    
 4. Screen Rotation
    TODOs
    - Make item_user sizing to screen.  As it's a constraint layout, it needs to be corrected to not use fixed sizes!
    
        
 Also, 
 - converted to Kotlin as I went.
 - updated gradle
 - added http logging for debugging
 - styles need to be added for buttons, fonts, colors, etc
 - dimens need to be added