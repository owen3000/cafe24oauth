/*var MYAPP = MYAPP || {};

MYAPP.make_namespace = function(ns_string) {
	var sections = ns_string.split('.'), parent = MYAPP, i;

	if (sections[0] === 'MYAPP') {
		sections = sections.slice(1);
	}

	for (i = 0; i < sections.length; i += 1) {
		if(parent[sections[i]] !== 'undefined') {
			parent[sections[i]] = {};
		}
		
		parent = parent[sections[i]];
	}
	
	return parent;
};

(function(){
	var ns = MYAPP.make_namespace('MYAPP.ADMIN.APP.TEST_SCRIPT');
	
	ns.init = function() {
		ns.print_hello();
	}
	
	ns.print_hello = function(){
		console.log('hello');
	}
})();

var temp = function(){
	$('a').bind('click', function(){
		console.log('clicked');
	});
};

window.onload = function() {
	var check_jquery = setInterval(function(){
		if(typeof windows.jQuery !== 'undefined') {
			temp();
			clearInterval(check_jquery);
		}
	}, 100);
}*/

/*document.addEventListener("DOMContentLoaded", function(event) {
	
	   alert(1);
	      var n2f_node = document.createElement("H1");
	   	    var n2f_textnode = document.createTextNode("삽입한 태그 입니다.");
	   	    n2f_node.appendChild(n2f_textnode);
	   	    document.getElementsByTagName("body")[0].appendChild(n2f_node);
	   	 alert(2);
});
	 */

/*$( document ).ready(function() {*/

document.addEventListener("DOMContentLoaded", function(event) {
	function show_popup(){

		var now2fix_popdup =
			"<div id = \"pop_container\">\r\n" + 
			"        <div id = \"header\">\r\n" + 
			"            <h3 class=\"title text\">현재 가장 많이 보고 있는 상품</h3>\r\n" + 
			"            <div class=\"minimize\">\r\n" + 
			"                <a href=\"#\"><span><i class=\"fas fa-angle-double-down\"></i></span></a>\r\n" + 
			"            </div>\r\n" + 
			"        </div>\r\n" + 
			"        <div id = \"content\">\r\n" + 
			"            <div class = \"left\">\r\n" + 
			"                <div id=\"product_info\">\r\n" + 
			"                    <div class=\"img\"><a href=\"#\"><div class=\"img_wrap\"><img src=\"http://utkg3000.cafe24.com/web/product/medium/201806/18_shop1_15294548615333.jpg\"></div></a></div>\r\n" + 
			"                    <!--<div class=\"img\"><a href=\"#\"><div class=\"img_wrap\"><img src=\"assets/img/product2.jpg\"></div></a></div>-->\r\n" + 
			"                    <div class=\"name\"><a href=\"#\"><span class=\"name text\"><strong>밀리터리 토트백</strong></span></a></div>\r\n" + 
			"                    <div class=\"price\"><a href=\"#\"><span class=\"price text\">52,200원</span></a></div>\r\n" + 
			"                </div>\r\n" + 
			"            </div>\r\n" + 
			"            <div class = \"right\">\r\n" + 
			"                <div id=\"comment\"><span class=\"text\">현재 쇼핑몰에서 고객들이 가장 많이 보고 있는 상품입니다.</span></div>\r\n" + 
			"                <div id=\"icon_bar\" class=\"icon_bar\">\r\n" + 
			"                    <span class=\"icon_wrap watcher\">\r\n" + 
			"                        <span class=\"left\">\r\n" + 
			"                            <i class=\"fas fa-user\"></i>\r\n" + 
			"                        </span>\r\n" + 
			"                        <span class=\"right\">\r\n" + 
			"                        <strong>98</strong>\r\n" + 
			"                        </span>\r\n" + 
			"                        </span>\r\n" + 
			"                    <span class=\"icon_wrap cart\">\r\n" + 
			"                        <!--<img  class=\"icon\" src=\"assets/img/cart.jpg\"/>-->\r\n" + 
			"                        <span class=\"left\">\r\n" + 
			"                        <i class=\"fas fa-shopping-cart\"></i>\r\n" + 
			"                            </span>\r\n" + 
			"                        <span class=\"right\">\r\n" + 
			"                            <strong>999+</strong>\r\n" + 
			"                        </span>\r\n" + 
			"                    </span>\r\n" + 
			"                </div>\r\n" + 
			"            </div>\r\n" + 
			"        </div>\r\n" + 
			"        <div class=\"arrow_bar\">\r\n" + 
			"            <a href=\"#\"><span><i class=\"fas fa-angle-left\"></i></span></a>\r\n" + 
			"            <a href=\"#\"><span><i class=\"fas fa-angle-right\"></i></span></a>\r\n" + 
			"        </div>\r\n" + 
			"    </div>"+
			"<style>\r\n" + 
			"\r\n" + 
			"        body{\r\n" + 
			"            sbackground: #888888;\r\n" + 
			"            sbackground: #000;\r\n" + 
			"        }\r\n" + 
			"\r\n" + 
			"        #pop_container *{\r\n" + 
			"            margin:0;\r\n" + 
			"            padding:0;\r\n" + 
			"            font-size:12px;\r\n" + 
			"        }\r\n" + 
			"\r\n" + 
			"        #pop_container a{\r\n" + 
			"            height:inherit;\r\n" + 
			"        }\r\n" + 
			"\r\n" + 
			"        #pop_container .text{\r\n" + 
			"            font-family: \"Nanum Gothic script=all rev=1\", sans-serif;\r\n" + 
			"            color:#333333;\r\n" + 
			"        }\r\n" + 
			"\r\n" + 
			"        #pop_container a, #pop_container a:hover, #pop_container a:link, #pop_container a:visited{\r\n" + 
			"            text-decoration: none;\r\n" + 
			"            color:#666;\r\n" + 
			"        }\r\n" + 
			"\r\n" + 
			"        #pop_container, #pop_container div{\r\n" + 
			"            box-sizing: border-box;\r\n" + 
			"            color:#444;\r\n" + 
			"        }\r\n" + 
			"        #pop_container{\r\n" + 
			"            z-index:300;\r\n" + 
			"            width:322px;\r\n" + 
			"            height:192px;\r\n" + 
			"            sbackground:yellow;\r\n" + 
			"            border:1px solid #eee;\r\n" + 
			"            background:#fff;\r\n" + 
			"            border-radius: 2px;\r\n" + 
			"            overflow: hidden;\r\n" + 
			"            position:fixed;\r\n" + 
			"            opacity: 0.95;\r\n" + 
			"            /*왼쪽 위*/\r\n" + 
			"            /*left:5px;\r\n" + 
			"            top:5px;*/\r\n" + 
			"\r\n" + 
			"            /*왼쪽 아래*/\r\n" + 
			"            left:5px;\r\n" + 
			"            bottom:5px;\r\n" + 
			"\r\n" + 
			"            /*오른쪽 위*/\r\n" + 
			"            /*\r\n" + 
			"            right:5px;\r\n" + 
			"            top:5px;\r\n" + 
			"            */\r\n" + 
			"\r\n" + 
			"            /*오른쪽 아래*/\r\n" + 
			"            /*\r\n" + 
			"            right:5px;\r\n" + 
			"            bottom:5px;\r\n" + 
			"            */\r\n" + 
			"            box-shadow: 0px 2px 10px 0px #888;\r\n" + 
			"        }\r\n" + 
			"\r\n" + 
			"        #pop_container #header, #pop_container #content{\r\n" + 
			"            padding-left:12px;\r\n" + 
			"            padding-right:12px;\r\n" + 
			"        }\r\n" + 
			"\r\n" + 
			"        #pop_container #header{\r\n" + 
			"            height:38px;\r\n" + 
			"            opacity: 0.6;\r\n" + 
			"            background: #f6f6f6;\r\n" + 
			"            border-bottom :1px solid #ddd;\r\n" + 
			"            padding-top:8px;\r\n" + 
			"        }\r\n" + 
			"\r\n" + 
			"        #pop_container #header .title{\r\n" + 
			"            font-family: \"Nanum Gothic script=all rev=1\";\r\n" + 
			"            font-size:16px;\r\n" + 
			"            sbackground: purple;\r\n" + 
			"\r\n" + 
			"        }\r\n" + 
			"\r\n" + 
			"        #pop_container #header .minimize{\r\n" + 
			"            position: absolute;\r\n" + 
			"            right: 6px;\r\n" + 
			"            top:10px;\r\n" + 
			"        }\r\n" + 
			"\r\n" + 
			"        #pop_container #header .minimize a{\r\n" + 
			"            display: block;\r\n" + 
			"        }\r\n" + 
			"\r\n" + 
			"        #pop_container #header .minimize span{\r\n" + 
			"            padding:5px 8px 4px;\r\n" + 
			"            sborder: 1px solid #333;\r\n" + 
			"            vertical-align: middle;\r\n" + 
			"        }\r\n" + 
			"\r\n" + 
			"        #pop_container #header .minimize i{\r\n" + 
			"            font-size: 18px;\r\n" + 
			"        }\r\n" + 
			"\r\n" + 
			"        #pop_container #content{\r\n" + 
			"            height: 152px;\r\n" + 
			"            sbackground: red;\r\n" + 
			"            padding-top:6px;\r\n" + 
			"        }\r\n" + 
			"        #pop_container #content > .left, #content > .right{\r\n" + 
			"            float:left;\r\n" + 
			"        }\r\n" + 
			"        #pop_container #content > .left{\r\n" + 
			"            sbackground: grey;\r\n" + 
			"            width: 100px;\r\n" + 
			"            height: 100%;\r\n" + 
			"        }\r\n" + 
			"\r\n" + 
			"        #pop_container #content > .right{\r\n" + 
			"            sbackground: purple;\r\n" + 
			"            height: 100%;\r\n" + 
			"            width: 196px;\r\n" + 
			"            padding:0px 0px 0px 16px;\r\n" + 
			"        }\r\n" + 
			"\r\n" + 
			"        #pop_container #content > .right > *{\r\n" + 
			"            sbackground: red;\r\n" + 
			"        }\r\n" + 
			"\r\n" + 
			"        #pop_container #product_info .img{\r\n" + 
			"            width:100px;\r\n" + 
			"            height:100px;\r\n" + 
			"            smargin:auto;\r\n" + 
			"            sbackground: #ddd;\r\n" + 
			"            sborder: 1px solid #ddd;\r\n" + 
			"        }\r\n" + 
			"\r\n" + 
			"        #pop_container .img_wrap{\r\n" + 
			"            width:inherit;\r\n" + 
			"            height:inherit;\r\n" + 
			"            display: table-cell;\r\n" + 
			"            vertical-align: middle;\r\n" + 
			"        }\r\n" + 
			"\r\n" + 
			"        #pop_container #product_info .img  .img_wrap  img{\r\n" + 
			"            max-width: 100%;\r\n" + 
			"            max-height: 100%;\r\n" + 
			"            width:auto;\r\n" + 
			"            height: auto;\r\n" + 
			"            display: block;\r\n" + 
			"            margin: auto auto;\r\n" + 
			"        }\r\n" + 
			"\r\n" + 
			"        #pop_container #product_info  .name, #product_info  .price{\r\n" + 
			"            text-align: center;\r\n" + 
			"            font-size:1.2em;\r\n" + 
			"            font-weight: bold;\r\n" + 
			"        }\r\n" + 
			"        #pop_container #product_info  .price{\r\n" + 
			"            color:#0078ff;\r\n" + 
			"            font-weight: 800;\r\n" + 
			"        }\r\n" + 
			"\r\n" + 
			"        #pop_container .right > div{\r\n" + 
			"            margin-bottom:4px !important;\r\n" + 
			"            sbackground: aqua;\r\n" + 
			"            sborder:1px solid red;\r\n" + 
			"        }\r\n" + 
			"\r\n" + 
			"        #pop_container .icon_bar {\r\n" + 
			"            width: 100%;\r\n" + 
			"        }\r\n" + 
			"        #pop_container .icon_bar > span{\r\n" + 
			"            max-width: 100%;\r\n" + 
			"            margin: auto !important;\r\n" + 
			"            display:inline-block;\r\n" + 
			"            text-align: center;\r\n" + 
			"        }\r\n" + 
			"        #pop_container .icon_bar .icon{\r\n" + 
			"            width:20px;\r\n" + 
			"            height:20px;\r\n" + 
			"        }\r\n" + 
			"\r\n" + 
			"        #pop_container .arrow_bar{\r\n" + 
			"            position: absolute;\r\n" + 
			"            sbackground: red;\r\n" + 
			"            right:6px;\r\n" + 
			"            bottom: 4px;\r\n" + 
			"        }\r\n" + 
			"        #pop_container .arrow_bar span{\r\n" + 
			"            display: inline-block;\r\n" + 
			"            padding: 4px 6px;\r\n" + 
			"            sborder: 1px solid #ddd;\r\n" + 
			"            sbackground: yellow;\r\n" + 
			"        }\r\n" + 
			"\r\n" + 
			"        #pop_container .arrow_bar span:hover, #header .minimize span:hover{\r\n" + 
			"            color:#cc4444;\r\n" + 
			"        }\r\n" + 
			"\r\n" + 
			"        #pop_container .arrow_bar span:hover{\r\n" + 
			"            sbackground: #eee;\r\n" + 
			"        }\r\n" + 
			"\r\n" + 
			"        #pop_container .arrow_bar span i{\r\n" + 
			"            font-size: 20px;\r\n" + 
			"        }\r\n" + 
			"\r\n" + 
			"        #pop_container #comment{\r\n" + 
			"            height:64px;\r\n" + 
			"            sbackground: red;\r\n" + 
			"            padding: 2px;\r\n" + 
			"        }\r\n" + 
			"\r\n" + 
			"        #pop_container .icon_wrap{\r\n" + 
			"            display: inline-block;\r\n" + 
			"            border:1px solid black;\r\n" + 
			"            border-radius: 3px;\r\n" + 
			"            overflow: hidden;\r\n" + 
			"            cursor: pointer;\r\n" + 
			"        }\r\n" + 
			"\r\n" + 
			"        #pop_container .icon_wrap > span{\r\n" + 
			"            display: inline-block;\r\n" + 
			"        }\r\n" + 
			"\r\n" + 
			"        #pop_container .icon_wrap strong,#pop_container .icon_wrap i{\r\n" + 
			"            font-size: 12px;\r\n" + 
			"        }\r\n" + 
			"        #pop_container .icon_wrap i{\r\n" + 
			"            color:#fff;\r\n" + 
			"            font-size: 12px;\r\n" + 
			"        }\r\n" + 
			"        #pop_container .icon_wrap strong{\r\n" + 
			"            font-family: \"Nanum Gothic script=all rev=1\", sans-serif;\r\n" + 
			"\r\n" + 
			"        }\r\n" + 
			"\r\n" + 
			"\r\n" + 
			"        /*에메랄드/레드 진함*/\r\n" + 
			"        #pop_container .icon_wrap.watcher{\r\n" + 
			"            border-color: #15aabf;\r\n" + 
			"            background-color: #15aabf;\r\n" + 
			"            color: #15aabf;\r\n" + 
			"        }\r\n" + 
			"\r\n" + 
			"        #pop_container .icon_wrap.cart{\r\n" + 
			"            border-color: #e64980;\r\n" + 
			"            background-color: #e64980;\r\n" + 
			"            color: #e64980;\r\n" + 
			"        }\r\n" + 
			"\r\n" + 
			"\r\n" + 
			"        #pop_container .icon_wrap .left, #pop_container .icon_wrap .right{\r\n" + 
			"            padding:4px 10px;\r\n" + 
			"        }\r\n" + 
			"\r\n" + 
			"        #pop_container .icon_wrap .right{\r\n" + 
			"            background-color: #fff;\r\n" + 
			"            min-width: 28px;\r\n" + 
			"            max-width: 28px;\r\n" + 
			"        }\r\n" + 
			"\r\n" + 
			"\r\n" + 
			"    </style>\r\n" + 
			"	\r\n" + 
			"	    <link rel=\"stylesheet\" href=\"https://use.fontawesome.com/releases/v5.1.0/css/all.css\" integrity=\"sha384-lKuwvrZot6UHsBSfcMvOkWwlCMgc0TaWr+30HWe3a4ltaBwTZhyTEggF5tJv8tbt\" crossorigin=\"anonymous\">\r\n" + 
			"    <link href=\"https://fonts.googleapis.com/css?family=Black+Han+Sans|Do+Hyeon|Gothic+A1|Nanum+Gothic|Stylish\" rel=\"stylesheet\">";
		
		$("body").append(now2fix_popdup);

	   }; //function show_popup()
	   window.setTimeout( show_popup, 500 );
});
/*});*/