"use strict";
$(document).ready( () =>{
    $("#privacy_policy").click( () => {
       $("#privacy_dialog").dialog({
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


