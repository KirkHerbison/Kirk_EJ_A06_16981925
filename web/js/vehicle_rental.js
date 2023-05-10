"use strict";

$(document).ready( () =>{
    $("#label_CVV_Information").click( () => {
       $("#cvv_dialog").dialog({
                show:{
                    effect: "open"
                },
                position:{
                    my: "right center",
                    at: "right bottom",
                    of: $( "label.parent" )
                }
            });
    }); 
}); // end ready
