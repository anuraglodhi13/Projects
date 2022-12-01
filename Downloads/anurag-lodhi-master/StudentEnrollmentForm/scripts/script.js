function triggerScript() {
    document.getElementById('my-form').addEventListener('submit', addRow);
}
function addRow(event) {
    console.log("hi");
    event.preventDefault();
    if(event.target.checkValidity() === false) return;
    var name = document.getElementById("Name").value;
    var email = document.getElementById("Email").value;
    var website = document.getElementById("Website").value;
    var imageLink = document.getElementById("Image-Link").value;
    var gender = $('input[name=Gender]:checked').val()
    var languageArray = []
    $("input:checkbox[name=language]:checked").each(function () {
        languageArray.push($(this).val());
    });
    var language = languageArray.join(',');
    var tbodyEl = document.querySelector("tbody");
   
    let row = document.createElement("tr");

    let column1 = document.createElement("td");
    column1.innerHTML = `
  <div class="fade-animation">
    ${name} 
    <br>
    ${gender}
    <br>
    ${email}
    <br>
    <a href="${website}" target="_blank">${website}</a>
    <br>
     ${language} 
    </div>`;
    let column2 = document.createElement("td");
    column2.innerHTML = `
    <div class = "fade-animation">
    <img src=${imageLink} width="100" height="100" >
    `;
    column2.style.width = '100px';
    column2.style.height = '100px';
   
    row.appendChild(column1);

    row.appendChild(column2);
    //appends the row to the table
    document.querySelector("#studentId").appendChild(row);
}

(function () {
    'use strict'

    
    var forms = document.querySelectorAll('.needs-validation')
    
   
    Array.prototype.slice.call(forms)
        .forEach(function (form) {
            form.addEventListener('submit', function (event) {
                var failed = false;

                if ($("[name='language']:checked").length == 0) {
                    $("[name='language']").attr('required', true);
                    failed = true;
                }
                else {
                    $("[name='language']").attr('required', false);
                }

                if (form.checkValidity() === false) {
                    failed = true;
                }

                if (failed == true) {
                    event.preventDefault();
                    event.stopPropagation();
                }

                form.classList.add('was-validated');
            }, false)
        })
}
)()

function removeValidation() {
    form = document.getElementById('my-form');
    form.classList.remove('was-validated');
}

function deRequire(c) {
    objClass = document.getElementsByClassName(c);
  
    var atLeastOneChecked = false; 
    for (i = 0; i < objClass.length; i++) {
      if (objClass[i].checked === true) {
        atLeastOneChecked = true;
      }
    }
  
    if (atLeastOneChecked === true) {
      for (i = 0; i < objClass.length; i++) {
        objClass[i].required = false;
      }
    } else {
      for (i = 0; i < objClass.length; i++) {
        objClass[i].required = true;
      }
    }
  }