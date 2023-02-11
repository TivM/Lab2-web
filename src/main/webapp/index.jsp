<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <script src="https://code.jquery.com/jquery-3.4.1.js"></script>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<header class="page-header">
    <div class="page-header_me">
        <span class="page-header-name">Trofimchenko Vlad</span>
        <span class="page-header-name">P32111</span>
    </div>
</header>
<main class="content">
    <header class="task-header">
        <div class="task-header-div">
            <span class="task-name">VARIANT 2734</span>
        </div>
    </header>

    <section class="section-lab">
        <form class="mainform" action="${pageContext.request.contextPath}/ControllerServlet" id="form" onsubmit="addXToRequest()">
            <div class="form">
                <p class="text-select-coordinate">Select your coordinates!</p>
                <div class="values">

                    <div class="x_value">
                        <table id="xTable">
                            <label for="xTable"> X value:</label>
                            <tr>
                                <td><input type="button" name="x-value" value="-2"></td>
                                <td><input type="button" name="x-value" value="-1.5"></td>
                                <td><input type="button" name="x-value" value="-1"></td>
                            </tr>
                            <tr>
                                <td><input type="button" name="x-value" value="-0.5"></td>
                                <td><input type="button" name="x-value" value="0" style="background-color: green"></td>
                                <td><input type="button" name="x-value" value="0.5"></td>
                            </tr>
                            <tr>
                                <td><input type="button" name="x-value" value="1"></td>
                                <td><input type="button" name="x-value" value="1.5"></td>
                                <td><input type="button" name="x-value" value="2"></td>
                            </tr>
                        </table>
                    </div>

                    <div class="y_value">
                        <label for="Y_value">Y value:</label>
                        <input type="text" maxlength="16" id="Y_value" name="Y_value" placeholder="Enter coordinate Y {-3...5}"
                               oninput="validateY(this)" required >
                    </div>

                    <div class="r_value">
                        <label for="R_value">R value:</label>
                        <input required type="text" maxlength="16" id="R_value" name="R_value" placeholder="Enter coordinate R {2...5}"
                               oninput="validateR(this)">
                    </div>


                    <!--          <div class="r_value">-->
                    <!--            <label for="R_value">R value:</label>-->
                    <!--            <select name="R_value" id="R_value" size="1">-->
                    <!--              <option disabled selected="Select R">Select coordinate R</option>-->
                    <!--              <option value="1">1</option>-->
                    <!--              <option value="1.5">1.5</option>-->
                    <!--              <option value="2">2</option>-->
                    <!--              <option value="2.5">2.5</option>-->
                    <!--              <option value="3">3</option>-->
                    <!--            </select>-->
                    <!--          </div>-->
                </div>

                <div class="Error_text" id="Error_text">
                    <span id="Y_error"></span>
                    <span id="X_error"></span>
                    <span id="R_error"></span>
                </div>

                <div class="client-button">
                    <input type="submit" class="client-button-submit" value="Submit" name="submit-button">
                    <%--                    <input class="client-button-clear" type="reset" value="Clear">--%>
                </div>
            </div>
        </form>
    </section>

    <svg height="300" width="300">
        <!--  Ось X-->
        <line x1="0" x2="300" y1="150" y2="150" stroke="black"></line>
        <!--  Ось Y-->
        <line x1="150" x2="150" y1="0" y2="300" stroke="black"></line>
        <!--Стрелочки на графике-->
        <polyline points="156 10, 150 0, 144 10" stroke="black" fill="none"></polyline>
        <polyline points="290 156, 300 150, 290 144" stroke="black" fill="none"></polyline>


        <!--    Длина положительной части оси = 1.2 * R  1.2 * R = 150 => R = 125-->
        <!--    значит отступ между начечками и отступ от начала координат = 62.5 -->
        <!--    Надпись R отстаёт на 4 px, R/2 на 8px , -R/2 на 12px   -->

        <!--Насечки на положительной части оси X    -->
        <line x1="212.5" x2="212.5" y1="155" y2="145" stroke="black"></line>
        <line x1="275" x2="275" y1="155" y2="145" stroke="black"></line>
        <!--Насечки на отрицательной части оси X-->
        <line x1="25" x2="25" y1="155" y2="145" stroke="black"></line>
        <line x1="87.5" x2="87.5" y1="155" y2="145" stroke="black"></line>
        <!--Насечки на положительной части оси Y-->
        <line x1="145" x2="155" y1="87.5" y2="87.5" stroke="black"></line>
        <line x1="145" x2="155" y1="25" y2="25" stroke="black"></line>
        <!--Насечки на отрицательной части оси Y-->
        <line x1="145" x2="155" y1="212.5" y2="212.5" stroke="black"></line>
        <line x1="145" x2="155" y1="275" y2="275" stroke="black"></line>

        <!--Подписи к насечкам    -->
        <text x="204.5" y="140">R/2</text>
        <text x="271" y="140">R</text>

        <text x="19" y="140">-R</text>
        <text x="75.5" y="140">-R/2</text>

        <text x="160" y="91.5">R/2</text>
        <text x="160" y="29">R</text>

        <text x="160" y="216.5">-R/2</text>
        <text x="160" y="279">-R</text>


        <!--Треугольник-->
        <polygon points="150 150, 150 275, 25 150"
                 fill="purple" fill-opacity="0.6" stroke="#4a90e2"></polygon>


        <!--Полуокружность-->
        <path d="M 275 150 A 125 125, 90, 0, 1, 150 275 L 150 150 Z"
              fill="orange" fill-opacity="0.6" stroke="#4a90e2"></path>

        <rect x="87.5" y="25" width="62.5" height="125" fill="yellow" fill-opacity="0.6" stroke="#4a90e2"></rect>
        <%--Cкрипт для отображения точки на графике при нажатии на него        --%>
        <%
            if(application.getAttribute("requestsCount") != null){
                for(int i = 0; i < Integer.parseInt(application.getAttribute("requestsCount").toString()); ++i){
                    double x, y, radius;
                    try {
                        x = Double.parseDouble(application.getAttribute("request" + i + "x").toString());
                        y = Double.parseDouble(application.getAttribute("request" + i + "y").toString());
                        radius = Double.parseDouble(application.getAttribute("request" + i + "r").toString());
                    } catch (NumberFormatException e){
                        continue;
                    }
                    double newX = x / radius * 125 + 150,
                            newY = 150 - y / radius * 125;
                    String fillColor = application.getAttribute("request" + i + "res").toString().equals("YES")
                            ? "green" : "red";
                    out.println(String.format("<circle r='5' cx='%s' cy='%s' fill='%s' stroke='#4a90e2'/>",
                            Double.toString(newX).replace(",", "."), Double.toString(newY).replace(",", "."), fillColor));
                }
            }
        %>

    </svg>

    <div class="Saint-P">
        <h2>Saint-Petersburg, 2022</h2>
    </div>
</main>




<script type = "text/javascript" >
    function preventBack(){window.history.forward();}
    setTimeout("preventBack()", 0);
    window.onunload=function(){null};
</script>

<script src="js/mouse.js"></script>
<script src="js/validation.js"></script>


</body>
</html>