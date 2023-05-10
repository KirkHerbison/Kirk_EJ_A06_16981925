"use strict";

$(document).ready( () =>{
    $("#label_password_policy").click( () => {
       $("#pass_dialog").dialog({
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
    
    $("#label_username_policy").click( () => {
       $("#username_dialog").dialog({
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
    
    $("#show").click( () => {
        showPassword();
    });
    
    
}); // end ready

const showPassword = () =>{
    
    let localReference = $("#password"); 
    localReference.is("input[type='password']") ? 
    localReference.attr('type', 'text') : 
    $("#password").attr('type', 'password');
};