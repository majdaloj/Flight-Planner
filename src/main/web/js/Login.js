
function httpGet(theUrl){
    const xmlHttp = new XMLHttpRequest();
    xmlHttp.open( "GET", theUrl, false ); // false for synchronous request
    xmlHttp.send( null );
    return xmlHttp.responseText;
}

function verifyInformation(username, password){
    const url= 'http://localhost:8080/login?username='+username+'&password='+password
    const done = httpGet(url)
    let signInResult;

    if(done==="true"){
        signInResult = null;
    } else {
        signInResult = "There was an error logging in.";
    }

    if(signInResult === null) {
        window.location.href = "SearchFlight.html";
    } else {
        window.alert(signInResult);
    }
}