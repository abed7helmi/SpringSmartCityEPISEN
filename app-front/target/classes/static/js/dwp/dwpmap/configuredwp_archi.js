
$(function() {

    var mapAreas = new Map();
    initArea(mapAreas);

    var confirmButton = document.getElementById("confirm");

    confirmButton.addEventListener("click",function(){
        if(configured){
            $.ajax({
                type: "DELETE",
                url: "http://172.31.249.161:8080/room/deleteAll/"+id_dwp,
                contentType: "application/json",
                success: function (data) {
                  console.log("Deleted dwp content succesfully.");
                  configMap(mapAreas);
                }
            });
        }
        else{
            configMap(mapAreas);
        }

    });

    if(configured){
        var deleteButton = document.getElementById("delete");
        deleteButton.addEventListener("click", function(){
            location.href="/deleteConfig?id_dwp="+id_dwp;
        });
    }

    var returnButton = document.getElementById("return");
    returnButton.addEventListener("click", function(){
        location.href="/chooseArchi?action="+action+"&id_dwp="+id_dwp+"&building_name="+building_name+"&floor_name="+floor_number;
    });

    var randomizeButton = document.getElementById("randomize");

    randomizeButton.addEventListener("click", function(){
        randomizeConfig(mapAreas);
    });

});
function configMap(mapAreas){
    $.ajax({
        type: "POST",
        url: "http://172.31.249.161:8080/dwp/configMap/"+id_dwp+"/"+architecture,
        contentType: "application/json",
        data: JSON.stringify(Object.fromEntries(mapAreas)),
        success: function (data) {
            if(architecture == 1){
                location.href="/dwpmap1/findPath/"+building_name+"/"+floor_number+"/"+id_dwp;
            }
            else {
                location.href="/dwpmap2/findPath/"+building_name+"/"+floor_number+"/"+id_dwp;
            }
        }
    });
}
function randomizeConfig(mapAreas){
    var areas = document.getElementById("selectArea");
    var initialSelectedArea = areas.options[areas.selectedIndex].value;
    initializeMapByRemovingAllElements(mapAreas);
    $("#selectArea > option").each(function() {
        areas.value = this.value;
        var listIdRoomFull = [0];
        var idNumber = 0;
        var max = 0;
        var area_type;

        if(this.value.includes("AO")){
            max = 4;
            area_type = "office";
        }
        else if(this.value.includes("AU")){
            max = 4;
            area_type = "utils";
        }

        verifyListIdRoomFull(listIdRoomFull, "#"+area_type+"-rooms", this.value);

        while(listIdRoomFull.length != max + 1) {
            idNumber = 0;
            while(true){
                idNumber =  Math.floor(Math.random() *max) + 1;
                if(!listIdRoomFull.includes(idNumber)){
                    break;
                }
            }
            $("#"+area_type+"-"+idNumber).click();
            verifyListIdRoomFull(listIdRoomFull, "#"+area_type+"-rooms", this.value);
        }

    });

    areas.value = initialSelectedArea;
    verifyButtons(initialSelectedArea.toLowerCase(), mapAreas);
    colorSelectedArea(initialSelectedArea.toLowerCase());
}

function initializeMapByRemovingAllElements(mapAreas){
    var areas = document.getElementById("selectArea");
    $("#selectArea > option").each(function() {
        areas.value = this.value;
        var listRoom = mapAreas.get(this.value.toLowerCase());
        var selector;

        if(this.value.includes("AO")){
            selector = "#office-rooms";
        }
        else if(this.value.includes("AU")){
            selector = "#utils-rooms";
        }

        $(selector).find(".roomBtn").each(function(){
            while(roomExistsInList(listRoom, $(this).attr("type-room"))){
                $(this).find(".minus").click();
            }
        });
    });
}

function roomExistsInList(listRoom, type_room){
    for(var i = 0;i<listRoom.length;i++){
        if(listRoom[i].type_room == type_room){
            return true;
        }
    }
    return false;
}

function verifyListIdRoomFull(listIdRoomFull, selector, selectedArea){
    selectedArea = selectedArea.toLowerCase();
    var areaDiv = document.getElementById(selectedArea);
    var capacity = areaDiv.getAttribute("capacity");

    $(selector).find(".roomBtn").each(function(){
        if(selectedArea != "au2"){
            var width = $(this).attr("width")*32;
            if(capacity < width){
                var idNumber = $(this).find(".plus").attr("id");
                idNumber = parseInt(idNumber.charAt(idNumber.length - 1));
                if(!listIdRoomFull.includes(idNumber)){
                    listIdRoomFull.push(idNumber);
                }
            }
        }
        else {
            var width = $(this).attr("width")*32;
            if((width==480 && capacity == -1) || (width!=480)){
                var idNumber = $(this).find(".plus").attr("id");
                idNumber = parseInt(idNumber.charAt(idNumber.length - 1));
                if(!listIdRoomFull.includes(idNumber)){
                    listIdRoomFull.push(idNumber);
                }
            }
        }
    });
}
function initArea(mapAreas){
    var areas = document.getElementById("selectArea");
    areas.addEventListener("change", function(){

        var label = areas.options[areas.selectedIndex].value;
        var office_rooms = document.getElementById("office-rooms");
        var utils_rooms = document.getElementById("utils-rooms");

        if(label.includes("AO")){
            if(office_rooms.style.display === "none"){
                utils_rooms.style.display = "none";
                office_rooms.style.display = "block";
            }
        }
        else{
            if(utils_rooms.style.display === "none"){
                office_rooms.style.display = "none";
                utils_rooms.style.display = "block";
            }
        }
        verifyButtons(label.toLowerCase(),mapAreas);
        colorSelectedArea(label.toLowerCase());
    });
    $.ajax({
        url: 'http://172.31.249.161:8080/dwp/areas/'+architecture,
        headers: {
            'Content-Type': 'application/json'
        },
        type: 'GET',
        dataType: 'json',
        success: function (data) {

            for(var i=0;i<data.length;i++){

                var option = document.createElement("option");
                option.value = data[i].label;
                option.text =  data[i].area_name;
                areas.appendChild(option);
                if(architectureStatus == "newArchitecture"){
                    var areaElement = document.createElement("div");
                    areaElement.innerHTML = data[i].area_name;
                    areaElement.setAttribute("id",(data[i].label).toLowerCase()+"-gap");
                    var areaDiv = document.getElementById((data[i].label).toLowerCase());
                    areaDiv.appendChild(areaElement);
                    mapAreas.set((data[i].label).toLowerCase(),[{"type_room": data[i].label, "position": 1}]);
                }
                else if(architectureStatus == "oldArchitecture"){

                    var areaDiv = document.getElementById((data[i].label).toLowerCase());
                    var capacity = areaDiv.getAttribute("capacity");

                    if((data[i].label).toLowerCase() != "au2"){
                        var roomToFillTheGap = document.createElement("div");
                        var direction = areaDiv.getAttribute("direction");
                        roomToFillTheGap.setAttribute("style",direction+":0px;");
                        roomToFillTheGap.setAttribute("id",(data[i].label).toLowerCase()+"-gap");
                        areaDiv.appendChild(roomToFillTheGap);
                    }

                    var listRoom = [];

                    $("#"+(data[i].label).toLowerCase()).find(".room").each(function(){
                        listRoom.push({
                            "type_room": $(this).data("name-room"),
                            "width": $(this).data("width"),
                            "height": $(this).data("height"),
                            "position": listRoom.length + 1,
                            "name-room": $(this).data("type-room"),
                            "id_hallway": areaDiv.getAttribute("hallway"),
                            "id_dwp_area": areaDiv.getAttribute("id-area")
                        });
                        capacity = capacity - $(this).data("width")*32 - 1;
                    });
                    areaDiv.setAttribute("capacity",capacity);
                    mapAreas.set((data[i].label).toLowerCase(),listRoom);
                }
            }
           /* const obj = mapAreas.get("ao1")[0];
            console.log(obj.type_room+" | "+obj.position);*/
            var event = new Event('change');
            // Dispatch it.
            areas.dispatchEvent(event);

            initRoomsButtons(mapAreas);
        },
        error: function () {
            alert("Erreur")
        }
    });
}
function colorSelectedArea(selectedArea){
    $("#selectArea > option").each(function() {
        if(selectedArea == this.value.toLowerCase()){
            $("#"+selectedArea+" > div").css("background","rgb(153 230 207)");
        }
        else{
            $("#"+this.value.toLowerCase()+" > div").css("background","white");
        }
    });
}
function initRoomsButtons(mapAreas){

    $("html body").find(".roomBtn").each(function(){
        var room = $(this);
        $(this).find(".plus").on("click",room,function(){

            var areas = document.getElementById("selectArea");
            var selectedArea = areas.options[areas.selectedIndex].value.toLowerCase();
            var listRoom = mapAreas.get(selectedArea);
            var areaDiv = document.getElementById(selectedArea);
            var direction = areaDiv.getAttribute("direction");

            if(listRoom[0].type_room.includes("AO") || listRoom[0].type_room.includes("AU")){
                listRoom.pop();
            }
            var position = listRoom.length + 1;
            listRoom.push({
                    "type_room": room.attr("type-room"),
                    "width": room.attr("width"),
                    "height": room.attr("height"),
                    "position": position,
                    "name-room": room.attr("name-room"),
                    "id_hallway": areaDiv.getAttribute("hallway"),
                    "id_dwp_area": areaDiv.getAttribute("id-area")
            });

            document.getElementById(selectedArea+"-gap").remove();

            if(direction == "horizontal"){
                direction = "width";
            }
            else {
                direction = "height";
            }

            var roomElement = document.createElement("div");
            roomElement.innerHTML = room.attr("name-room");
            if(selectedArea != "au2") {
                roomElement.setAttribute("style", direction + ":" + room.attr("width") * 32 + "px;");
            }
            else {
                roomElement.setAttribute("style", direction + ":500px;");
            }
            areaDiv.appendChild(roomElement);

            if(selectedArea != "au2"){
                fillTheGap(selectedArea,room.attr("width"),direction);
            }
            else {
                areaDiv.setAttribute("capacity",-1);
            }
            verifyButtons(selectedArea,mapAreas);
            colorSelectedArea(selectedArea);
        });

        $(this).find(".minus").on("click",room,function(){
            var areas = document.getElementById("selectArea");
            var selectedArea = areas.options[areas.selectedIndex].value.toLowerCase();
            var listRoom = mapAreas.get(selectedArea);
            var areaDiv = document.getElementById(selectedArea);
            var direction = areaDiv.getAttribute("direction");
            if(direction == "horizontal"){
                direction = "width";
            }
            else {
                direction = "height";
            }

            if((listRoom.length == 1) && !(listRoom[0].type_room.includes("AO")
                || listRoom[0].type_room.includes("AU"))){
                if(selectedArea != "au2"){

                    var areaElement = document.getElementById(selectedArea+"-gap");
                    areaElement.innerHTML = areas.options[areas.selectedIndex].text;
                    areaElement.removeAttribute("style");
                }
                else{
                    var areaElement = document.createElement("div");
                    areaElement.innerHTML = areas.options[areas.selectedIndex].text;
                    areaElement.setAttribute("id",selectedArea+"-gap");
                    areaDiv.appendChild(areaElement);
                }

                listRoom.push({"type_room": selectedArea.toUpperCase(), "position": 1});
            }
            for( var i = listRoom.length - 1; i >= 0; i--){
                listRoom[i].position -= 1;
                if(listRoom[i].type_room == room.attr("type-room")){
                    listRoom.splice(i,1);
                    areaDiv.children[i].remove();
                    var capacity = parseInt(areaDiv.getAttribute("capacity"),10);
                    capacity = capacity + room.attr("width")*32 + 1;

                    areaDiv.setAttribute("capacity", capacity);
                    if(selectedArea != "au2"){
                        document.getElementById(selectedArea+"-gap").setAttribute("style",direction+":"+capacity+"px;");
                    }
                    break;
                }
            }
            verifyButtons(selectedArea,mapAreas);
            colorSelectedArea(selectedArea);
        });
    });
}
function verifySpaces(){
    var bool = true;
    $("#selectArea > option").each(function(){
        var selectedArea = this.value.toLowerCase();
        var areaDiv = document.getElementById(selectedArea);
        var capacity = areaDiv.getAttribute("capacity");

        if(capacity < 32){
            var direction = areaDiv.getAttribute("direction");

            if(direction == "horizontal"){
                direction = "width";
            }
            else {
                direction = "height";
            }
            if(selectedArea != "au2"){
                document.getElementById(selectedArea+"-gap").setAttribute("style",direction+":0px;");
            }

        }
        else {
            bool = false;
        }
    });

    if(bool){
        document.getElementById("confirm").disabled = false;
    }
    else{
        document.getElementById("confirm").disabled = true;
    }
}
function verifyButtons(selectedArea, mapAreas){

        var areaDiv = document.getElementById(selectedArea);
        var capacity = areaDiv.getAttribute("capacity");
        var selector;
        if(selectedArea.includes("ao")){
            selector = "#office-rooms";
        }
        else{
            selector = "#utils-rooms";
        }
        $(selector).find(".roomBtn").each(function(){
            if(selectedArea != "au2"){
                var width = $(this).attr("width")*32;
                if(capacity < width){
                    $(this).find(".plus").css("pointer-events","none");
                    $(this).find(".plus").css("background-color","#edf1f2");
                    $(this).find(".plus").css("color","black");

                }
                else {
                    $(this).find(".plus").removeAttr("style");
                }
            }
            else {
                var width = $(this).attr("width")*32;
                if((width==480 && capacity == -1) || (width!=480)){
                    $(this).find(".plus").css("pointer-events","none");
                    $(this).find(".plus").css("background-color","#edf1f2");
                    $(this).find(".plus").css("color","black");
                }
                else if(width==480 && capacity != 0){
                    $(this).find(".plus").removeAttr("style");
                }
            }

            verifyMinusButton(selectedArea, mapAreas, $(this));
        });

    verifySpaces()
}
function verifyMinusButton(selectedArea, mapAreas, room){
    var listRoom = mapAreas.get(selectedArea);
    for(var i = 0;i<listRoom.length;i++){
        if(listRoom[i].type_room == room.attr("type-room")){
            room.find(".minus").removeAttr("style");
            return;
        }
    }
    room.find(".minus").css("pointer-events","none");
    room.find(".minus").css("background-color","#edf1f2");
    room.find(".minus").css("color","black");
}
function fillTheGap(selectedArea, room_width,direction){
    var areaDiv = document.getElementById(selectedArea);
    var roomToFillTheGap = document.createElement("div");
    var capacity = areaDiv.getAttribute("capacity");
    capacity = capacity - room_width*32 - 1;
    roomToFillTheGap.setAttribute("style",direction+":"+capacity+"px;");
    roomToFillTheGap.setAttribute("id",selectedArea+"-gap");
    areaDiv.appendChild(roomToFillTheGap);
    areaDiv.setAttribute("capacity",capacity);
}
function update() {
    $.get("configureMap").done(function(fragment) { // get from controller
        $("#configureMap").replaceWith(fragment); // update snippet of page
    });
}
