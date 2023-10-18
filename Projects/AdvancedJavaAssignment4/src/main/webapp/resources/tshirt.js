/**
 * 
 */
$("#form_submit_btn").click(function(e) {
   $('input').each(function() {
        if(!$(this).val()){
			e.preventDefault()
            alert('Some fields are empty');
           return false;
        }
    });
});