document.addEventListener("DOMContentLoaded", function(event) {
	
	
	// 생성자 작성 ( Sandbox pattern 적용 )
	function Now2Fix(){
		// arguments 를 배열로 변경
		var args = Array.prototype.slice.call(arguments);
		
		// 마지막 인자의 콜백 함수 추출
		var callback = args.pop();
		
		// 모듈은 배열 or 개별 인자로 전달 될 수 있음
		var modules = (args[0] && typeof args[0] === "string") ? args : args[0];
		var i;
		
		// new 를 강제하는 패턴 ( 함수가 생성자로 호출되도록 보장 )
		if ( (!this instanceof Now2Fix ) ) {
			return new Now2Fix( modules, callback );
		}
		
		// this 에 필요한 프로퍼티 추가. 
		this.a = 1;
		
		// 코어 "this" 객체에 모듈 추가
		// 모듈이 없거나 "*" 이면 모든 모듈을 사용
		if ( !modules || modules === "*" || modules[0] === "*" ) {
			modules = [];
			for ( i in Now2Fix.modules ){
				if ( Now2Fix.modules.hasOwnProperty( i ) ) {
					modules.push( i ); 
				}
			}
		}
		
		// 필요한 모듈들 초기화
		for ( i = 0 ; i < modules.length ; i += 1 ) {
			Now2Fix.modules[ modules[ i ] ]( this );
		}
		
		// 콜백 함수 호출
		callback( this );
	};
	
	// 필요한 프로토타입 프로퍼티 추가
	Now2Fix.prototype = {
			appName : "The promotion of purchases",
			version : "1.0",
			getName : function () {
				return this.appName;
			}
	};
	
	// 모듈 추가
	Now2Fix.modules = {};

	
}); // document.addEventListener("DOMContentLoaded", function(event)
