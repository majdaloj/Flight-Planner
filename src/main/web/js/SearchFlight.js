function httpGet(theUrl){
    const xmlHttp = new XMLHttpRequest();
    xmlHttp.open( "GET", theUrl, false ); // false for synchronous request
    xmlHttp.send( null );
    return xmlHttp.responseText;
}





function searchForFlights(departure, destination, date){
    let url = 'http://localhost:8080/searchFlight?date='+date+'&departure='+departure+'&destination='+destination
    let done = httpGet(url)
    console.log(departure);
    console.log(destination);
    console.log(date);
    console.log(done)


    if(done === '') {
        window.location.href = "SelectFlight.html";
    } else {
        window.alert(done);
    }

}

function viewProfile() {
    window.location.href = "UpdateProfilePage.html";
}