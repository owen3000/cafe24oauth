document.addEventListener("DOMContentLoaded", function(event) {
	
	
	// display popup
	Now2Fix.modules.display = function ( box ) {
		box.showPopup = function () {
			console.log("Now2Fix.modules.display : box.show !");

			
			$.get("https://devbit004.cafe24.com/cafe24oauth_gt/assets/js/app/popup_main.html", function(data){
			    $('body').append($(data).fadeIn());
			});

		};
	};
	
}); // document.addEventListener("DOMContentLoaded", function(event)