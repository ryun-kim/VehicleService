function onSignIn(){
    var auth2 = gapi.auth2.getAuthInstance()
    if(auth2.isSignedIn.get()){
        var profile = auth2.currentUser.get().getBasicProfile();
        googleLoginPro(profile)
        console.log('ID: ' + profile.getId()); // Do not send to your backend! Use an ID token instead.
        console.log('Name: ' + profile.getName());
        console.log('Image URL: ' + profile.getImageUrl());
        console.log('Email: ' + profile.getEmail()); // This is null if the 'email' scope is not present.
    }
}
