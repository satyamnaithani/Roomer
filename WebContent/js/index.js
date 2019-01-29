
		function login(){
			document.getElementById("contain").style.display = "none"; 
			document.getElementById("Login").style.display = "block"; 
			document.getElementById("Register").style.display = "none"; 
		var nodes = document.getElementById("contain").getElementsByTagName('*');
for(var i = 0; i < nodes.length; i++){
     nodes[i].style.display = "none"; 
}
}


function register(){
			document.getElementById("contain").style.display = "none"; 
			document.getElementById("Login").style.display = "none"; 
			document.getElementById("Register").style.display = "block"; 
		var nodes = document.getElementById("contain").getElementsByTagName('*');
for(var i = 0; i < nodes.length; i++){
     nodes[i].style.display = "none"; 
}
}




