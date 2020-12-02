function iniciarMap(){
    var coord = {lat: 40.4148857, lng: -3.7030986};
    var map = new google.maps.Map(document.getElementById('map'),{
        zoom: 10,
        center: coord
    });
    var marker = new google.maps.Marker({
        position: coord,
        map: map
    });
}