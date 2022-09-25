$(function () {
    $("#form-total").steps({
        headerTag: "h2",
        bodyTag: "section",
        transitionEffect: "fade",
        enableAllSteps: true,
        autoFocus: true,
        transitionEffectSpeed: 500,
        titleTemplate: '<div class="title">#title#</div>',
        labels: {
            previous: 'Back',
            next: '<i class="zmdi zmdi-chevron-right"></i>',
            finish: '<i class="zmdi zmdi-chevron-right"></i>',
            current: ''
        },
        onStepChanging: function (event, currentIndex, newIndex) {
            var fullname = $('#first_name').val() + ' ' + $('#last_name').val();
            var email = $('#email').val();
            var phone = $('#phone').val();
            var address = $('#address').val();
            var gender = $('form input[type=radio]:checked').val();
            var account_name = $('#account_name').val();
            var account_number = $('#account_number').val();

            $('#fullname-val').text(fullname);
            $('#email-val').text(email);
            $('#phone-val').text(phone);
            $('#address-val').text(address);
            $('#gender-val').text(gender);
            $('#account-name-val').text(account_name);
            $('#account-number-val').text(account_number);

            return true;
        }
    });
});

function updateInformation(phone, email){
    // update user information with filled in items

    const url= 'http://localhost:8080/updateProfile?phone='+phone+'&email='+email
    const done = httpGet(url)
    let updatedProfileResults;

    if(done==="true"){
        updatedProfileResults = null;
    } else {
        updatedProfileResults = "There was an error updating your information.";
    }

    if(updatedProfileResults === null) {
        window.location.href = "SearchFlight.html";
    } else {
        window.alert(updatedProfileResults);
    }
}
