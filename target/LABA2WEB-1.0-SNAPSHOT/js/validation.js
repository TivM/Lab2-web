//Валидация Y
function validateY(input) {
    input.value = input.value.replace(/[^0-9,.-]/g, '').replace(/,/, ".");
    if (isNaN(input.value) && input.value !== "-")
        input.value = "";
    if (input.value > 5)
        input.value = "";
    if (input.value < -3) {
        input.value = "";
    }
    if (input.value[0]==input.value[1] && input.value[0]=="0")
        input.value = "";
    if (input.value[1]==input.value[2]&& input.value[0]=="-"&&input.value[1]=="0")
        input.value = "";
    if (input.value.length > 8) {
        input.value = input.value.slice(0, -1);
    }
}
//Валидация R
function validateR(input) {
    input.value = input.value.replace(/[^0-9,.-]/g, '').replace(/,/, ".");
    if (isNaN(input.value) && input.value !== "-")
        input.value = "";
    if (input.value > 5)
        input.value = "";
    if (input.value < 2) {
        input.value = "";
    }

    if (input.value.length > 8) {
        input.value = input.value.slice(0, -1);
    }
}


//Функция, которая подсвечивает выбранную кнопку X
var xButtons = document.querySelectorAll("#xTable input");
xButtons.forEach(click);
function click(element) {
    element.onclick = function () {
        x = this.value;
        xButtons.forEach(function (element) {
            element.style.backgroundColor = "";
        });
        this.style.backgroundColor = "green"
    }
}