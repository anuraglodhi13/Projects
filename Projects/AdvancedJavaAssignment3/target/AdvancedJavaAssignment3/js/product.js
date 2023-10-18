function validateImageSize(input) {
  const fileSize = input.files[0].size / 1024 / 1024; // in MiB
  if (fileSize > 1) {
	alert("File size can't be greater than 1 mb. Try Again !!!");
     window.location=window.location;
  } else {
    return
  }
};


$(".edit").on("click",function(){
	glObj = $(this).parents("tr.product-table");
	str = glObj.children("td.image-data").html();
});


function edit_row(no) {
 document.getElementById("edit_button"+no).style.display="none";
 document.getElementById("save_button"+no).style.display="inherit";
 var title=document.getElementById("title"+no);
 var quantity=document.getElementById("quantity"+no);
 var size=document.getElementById("size"+no);
 var preview=document.getElementById("preview"+no);
	
 var title_data=title.innerHTML;
 var quantity_data=quantity.innerHTML;
 var size_data=size.innerHTML;
 var preview_data = preview.innerHTML;
 
 title.innerHTML="<input type='text' id='title_text"+no+"' value='"+title_data+"'>";
 quantity.innerHTML="<input type='text' id='quantity_text"+no+"' value='"+quantity_data+"'>";
 size.innerHTML="<input type='text' id='size_text"+no+"' value='"+size_data+"'>";
 preview.innerHTML="<input type='file' onchange='validateImageSize(this)' id='preview_text"+no+"' value='"+preview_data+"'>";
 
}


function save_row(no)
{
 var title_val=document.getElementById("title_text"+no).value;
 var quantity_val=document.getElementById("quantity_text"+no).value;
 var size_val=document.getElementById("size_text"+no).value;
 var preview_val=document.getElementById("preview_text"+no).value.replace("C:\\fakepath\\", "");

 var regex = /<img[^>]*?src\s*=\s*[""']?([^'"" >]+?)[ '""][^>]*?>/g
 var src = regex.exec(str);

    
 if(preview_val === "") {
	preview_val = null;
	}
	
	 
	
	$.ajax({
		url : 'ProductEdit',
		type : 'POST',
		data:{"imageName":src[1],"title":title_val,"quantity":quantity_val,"size":size_val,"imageData":preview_val},
		success: function () {
                window.location=window.location;
            }
	});
}


$(".delete").on("click",function(){
	glObj = $(this).parents("tr.product-table");
	str = glObj.children("td.image-data").html(); 
	var regex = /<img[^>]*?src\s*=\s*[""']?([^'"" >]+?)[ '""][^>]*?>/g
	var src = regex.exec(str);
	$.ajax({
		url : 'ProductDelete',
		type : 'POST',
		data:{"imageName":src[1]},
		success: function () {
                window.location=window.location;
            }
	});
});

function validateSize(input) {	
                if (isNaN(input)) {
                    document.getElementById(
                      "numberText").innerHTML =
                      "<b>"+
                      "Please enter Numeric value !!" + "</b>";
                    return false;
                } 
                return true;
            }
function validateQuantity (input) {
	if (isNaN(input)) {
                    document.getElementById(
                      "numberText1").innerHTML =
                      "<b>"+
                      "Please enter Numeric value !!" + "</b>";
                    return false;
                } 
                return true;
	 
}


$('#form_submit_btn').click(function(){
    $('input').each(function() {
        if(!$(this).val()){
            alert('Some fields are empty');
             window.location=window.location;
           return false;
        }
    });
});
