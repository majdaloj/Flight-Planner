function httpGet(theUrl){
    const xmlHttp = new XMLHttpRequest();
    xmlHttp.open( "GET", theUrl, false ); // false for synchronous request
    xmlHttp.send( null );
    return xmlHttp.responseText;
}


function displayFlights(){

    let url = 'http://localhost:8080/viewRouteHistory'
    let jsondata = httpGet(url);
    let rawData2 = JSON.parse(jsondata);

    let rawData = [{"departureAirport": {"city": "Toronto", "iataCode": "001"}, "destinationAirport": {"city": "Vancouver", "iataCode": "003"}, "departureDate": "12/15/2021", "flights": [{"departureDate": "12/18/2021", "plane": {"brandName": "boeing", "seatCount": 100, "firstClassSeats": 10, "economySeats": 80, "hasVacantSeats": true}, "price": 2.0, "duration": 5.0, "sourceAirport": {"city": "Toronto", "iataCode": "code1"}, "destinationAirport": {"city": "Vancouver", "iataCode": "code2"} }], "price": 2.0, "duration": 5.0, "id": "0"}];
    // Parsing through the placeholder data and turning into html content
    rawData = rawData2
    let content = `<div class="container" id="div-container">`
    let flightDetails;

    for (let i = 0; i < rawData.length; i++){
        content += `<div class="col-xs-12 col-md-6">
            <div class="prod-info-main prod-wrap clearfix">
                <div class="row">
                    <div class="col-md-4 col-sm-12 col-xs-12">
                        <div class="product-image">
                            <img src="images/plane.jpg" alt="194x228" class="img-responsive"/>
                        </div>
                    </div>
                    <div class="col-md-7 col-sm-12 col-xs-12">
                        <div class="product-detail">
                            <h5 class="name">
                                <a>
                                    Route #`+rawData[i]['id']+`<span>Estimated Flight Duration: `+
                                    rawData[i]['duration']+` hours</span>
                                </a>
                            </h5>
                            <p class="price-container">
                                <span>$`+rawData[i]['price']+`</span>
                            </p>
                            <span class="tag1"></span>
                        </div>
                        <div class="description">
                            <p>This route had `+rawData[i]['flights'].length+` total flight(s). The first flight
                            departed on `+rawData[i]['departureDate']+` and landed in `+
                            rawData[i]['flights'][0]['destinationAirport']['city']+`.</p>
                        </div>
                        <div class="product-info smart-form">
                            <div class="row">
                                <div class="col-md-12">
                                    <button type="button" class="btn btn-danger" onClick="deleteFlight(`+rawData[i]['id']+`)">Delete Flight</button>
                                    <a type="button" href="#popup`+i+`" class="btn btn-info">More info</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
`
    }

    for (let i = 0; i < rawData.length; i++) {
        flightDetails = ``;
        for (let j = 0; j < rawData[i]['flights'].length; j++){
            flightDetails += `
                $`+rawData[i]['flights'][j]['price']+`, `+rawData[i]['flights'][j]['duration']+` hours, 
                `+rawData[i]['flights'][j]['departureDate'].toLocaleString()+`: `+
                rawData[i]['flights'][j]['sourceAirport']['city']+` (`+
                rawData[i]['flights'][j]['sourceAirport']['iataCode']+`) &rarr; `+
                rawData[i]['flights'][j]['destinationAirport']['city']+` (`+
                rawData[i]['flights'][j]['destinationAirport']['iataCode']+`)
            `;
        }

        content += `
            <div id="popup`+i+`" class="overlay">
                <div class="popup">
                    <h2>Route #`+rawData[i]['id']+`</h2>
                    <a class="close" href="#">&times;</a>
                    <div class="content">
                        <div class="product-detail">
                            <h5 class="name">
                                <a>
                                    <span>Estimated Flight Duration: `+
                                    rawData[i]['duration']+` hours</span>
                                </a>
                            </h5>
                            <p class="price-container">
                                <span>Estimated Price: $`+rawData[i]['price']+`</span>
                            </p>
                            <p style="font-size: 16px; padding-top: 10px;">
                                Departure Airport: `+rawData[i]['departureAirport']['city']+` (`+
                                rawData[i]['departureAirport']['iataCode']+`)
                            </p>
                            <p style="font-size: 16px;">
                                Destination Airport: `+rawData[i]['destinationAirport']['city']+` (`+
                                rawData[i]['destinationAirport']['iataCode']+`)
                            </p>
                            `+flightDetails+`
                        </div>
                    </div>
                </div>
            </div>
        `;
    }




    let htmlDom = new DOMParser().parseFromString(content, "text/html");

    const stack = document.body;

    stack.appendChild(htmlDom.documentElement);
}

displayFlights();

function deleteFlight(routeId) {
    // TODO: Delete this flight from the user's history
    // TODO: make sure the list displayed updates with the deleted flight
    let url = 'http://localhost:8080/deleteRoute?id='+routeId
    let jsondata = httpGet(url);
    document.location.reload(true)
    let rawData2 = JSON.parse(jsondata);

}

function returnToProfile() {
    window.location.href = "UpdateProfilePage.html";
}