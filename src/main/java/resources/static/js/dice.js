   $('#dice').click(function(){
        $.emailId = localStorage.getItem("emailId");
        console.log($.emailId);
            $.get("/getNumberOnDice?emailId="+$.emailId, function(data, status){
                    $('p').append(data.position);
                });
    });