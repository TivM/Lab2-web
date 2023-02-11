let x = 0;
var form = document.getElementsByTagName("form")[0];


function addXToRequest() {
    var xElement = document.createElement("input");
    xElement.type = "text";
    xElement.name = "X_value";
    xElement.value = x;
    xElement.hidden = true;
    form.appendChild(xElement);
    return true;
}

function getMousePosition(svg, event) {
    var rect = svg.getBoundingClientRect();
    return {
        x: event.clientX - rect.left,
        y: event.clientY - rect.top
    };
}


var svg = document.getElementsByTagName("svg")[0];
svg.addEventListener("click", function (ev) {
    var pos = getMousePosition(svg, ev);
    // var radiusElement = document.getElementById("R_value");
    // var radius = radiusElement.options[radiusElement.selectedIndex].value;
    var radius = document.getElementById("R_value").value;
    if (radius == "") {
        // radiusElement.reportValidity();
        alert("Введите R!");
    } else {
        x = (pos.x - 150) / 125 * radius;
        document.getElementById("Y_value").value = (150 - pos.y) / 125 * radius;
        document.querySelector("input[type='submit']").click();
    }
})
