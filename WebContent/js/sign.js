/**
 * 
 */
	
$(document).ready(function(){
	$(".look").on("click",function(){
		var Ano=$(this).parents("tr").find(".Ano").val();
		//window.location.href="signInAct.jsp";
		window.location.href="../signInAct?Ano="+Ano;
	});
});