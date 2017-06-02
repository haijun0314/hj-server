
var 	session;

var SysUtil= {
	checkLogin:function (){
		session=Cookies.get("CKLHJ");
		if(session==null||session==""){
			window.location.href="login.html";
		}
	},
	login:function (){
		 
	}	
	 
	
}