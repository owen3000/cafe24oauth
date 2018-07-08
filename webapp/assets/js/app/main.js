document.addEventListener("DOMContentLoaded", function(event) {
	
	// Cehck jQuery load
	!function(){
		var checker = setInterval(function() {
			if ( typeof jQuery == "undefined" ) {
				// jQuery is not loaded
				console.log("jQuery is not loaded");
			}
			else {
				console.log("jQuery is loaded");
				clearInterval( checker );
				
				// show popup
				Now2Fix( "display", function ( box ) {
					box.showPopup();
				});
			}
		}, 100 );
	}(); // !function(){
	
}); // document.addEventListener("DOMContentLoaded", function(event)