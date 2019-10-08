 $(window).on("load", GetAllProperties);
            function GetAllProperties() {
	            $.emailId = localStorage.getItem("emailId");
	            $.get("/getProfile?emailId=" + $.emailId, function (data, status) {
	                if(data){
		                $('h2').append(data.Profile[0].name)
		            }else{
		                 $('h2').append("User not found")
		            }
	            });
            }