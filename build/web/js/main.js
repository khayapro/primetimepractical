$(document).ready(function() {

  $("#submit").on("click", function(event) {
      
        var valid = validateInputs();
      
        if(valid === false || !valid){
          return false;
        }
      
        $.ajax({
          url: "GetPrimes",
          data: {lowerNumber: $("#lower-number").val(), upperNumber: $("#upper-number").val(), 
              sieve: $("#sieve").val()},
          success: function(result) {
            $("#result").html(result);
          },
          dataType: "html",
          type: "POST"
        });

        event.stopPropagation();
        event.preventDefault();
        return false;
   
  });

});


function validateInputs(){
    var lowerNumber = $("#lower-number").val();
    var upperNumber = $("#upper-number").val();

    if(lowerNumber === "" && upperNumber === ""){
        $("#lower-number-error").text("Please enter number");
        $("#upper-number-error").text("Please enter number");
        return false;
    }
    if(!$.isNumeric(lowerNumber)){
       $("#lower-number-error").text("Please enter valid number");
        return false; 
    }
    if(upperNumber === ""){
        $("#upper-number-error").text("Please enter number");
        return false;
    } 
    if(!$.isNumeric(upperNumber)){
       $("#upper-number-error").text("Please enter valid number");
        return false; 
    }
    if(parseInt(upperNumber) < parseInt(lowerNumber)){
        $("#upper-number-error").text("Upper number must be greater than Lower number");
        return false;
    }

    if(lowerNumber !== "" && upperNumber !== "") {
        $("#lower-number-error").text("");
        $("#upper-number-error").text("");
        return true;
    }
       
}


