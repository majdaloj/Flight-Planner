function cancelSignUp() {
    window.location.href = "LoginPage.html";
}
function httpGet(theUrl){
    const xmlHttp = new XMLHttpRequest();
    xmlHttp.open( "GET", theUrl, false ); // false for synchronous request
    xmlHttp.send( null );
    return xmlHttp.responseText;
}
function signUp(id, psw, psw_repeat, email, phone) {
    // Add user account information here and verify the format of inputs.
    // If successful, return null. If unsuccessful, return error string.
    const url= 'http://localhost:8080/createAccount?username='+id+'&password='+psw+'&repeatPassword='+psw_repeat+'&email='+email+'&phoneNumber='+phone
    console.log(url)
    const done = httpGet(url)

    let signUpResult;

    // TODO: To be replaced with java
    if(done==="Account Created Successfully"){
        signUpResult = null;
    } else {
        signUpResult = done;
    }

    if(signUpResult === null) {
        window.location.href = "LoginPage.html";
    } else {
        window.alert(signUpResult);
    }
}