$(".button").click(function () {
    $(".possibi.allumer").toggleClass("clicked");
})


let toggle = document.getElementById("etat").innerHTML;
let color = document.getElementById("divAllumer").style.color;
let PC_Room = document.getElementById("PC_Room").innerHTML;
const donneeTemp = document.getElementById("donneeTemp");
const donneeLumi = document.getElementById("donneeLumi");
const donneeConso = document.getElementById("donneeConso");
const date_heure = document.getElementById("date_heure");
const roomId = document.getElementById("roomId").innerText;
const url = "http://172.31.249.161:8080/"

$("#divAllumer").on("click", () => {
    toggleEquipment()
})

function toggleEquipment() {
    $.ajax({
        type: "POST",
        url: url + "deviceactivity/" + PC_Room,
        contentType: "application/json",
        error: function (error) {
            console.log(error)
        }
    }).done(function () {
        setDivAllumer();
    });
}

function setDivAllumer() {
    toggle = !toggle;
    if (toggle) {
        color = 'red';
        document.getElementById("divAllumer").innerText = "Allumer"
        document.getElementById("divAllumer").style.backgroundColor = 'red'
    } else {
        color = 'green';
        document.getElementById("divAllumer").innerText = "Eteindre"
        document.getElementById("divAllumer").style.backgroundColor = 'green'
    }
}

window.onload = () => {
    setInterval(retrieveRoomTemp, 2000)
    setInterval(retrieveLumi, 2000)
    setInterval(retrieveRoomConso, 1000)
    setInterval(retrieveDateHeureTemp, 2000)
}

const retrieveRoomTemp = () => {
    fetch(url + "roomtemp/" + roomId)
        .then(roomTemp => roomTemp.text())
        .then((roomTemp) => {
            donneeTemp.innerText = roomTemp
        })
        .catch(err => console.log(err))
}

const retrieveRoomConso = () => {
    fetch(url + "consumption/room/" + roomId)
        .then(roomConso => roomConso.text())
        .then((roomConso) => {
            donneeConso.innerText = roomConso+'W'
        })
        .catch(err => console.log(err))
}

const retrieveDateHeureTemp = () => {
    fetch(url + "roomtemp/date/" + roomId)
        .then(roomDate => roomDate.text())
        .then((roomDate) => {
            date_heure.innerText = roomDate
        })
        .catch(err => console.log(err))
}

const retrieveLumi = () => {
    fetch(url + "roomlumi/" + roomId).then(roomLumi => roomLumi.text()).then((roomLumi) => {
        donneeLumi.innerText = roomLumi+"%"
    }).catch(err => console.log(err))
}
