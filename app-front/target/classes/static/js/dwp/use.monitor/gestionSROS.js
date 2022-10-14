let toggle = document.getElementById("etat").innerHTML;
let machine = document.getElementById("machine").innerHTML;
const donneeTemp = document.getElementById("donneeTemp");
const donneeLumi = document.getElementById("donneeLumi");
const date_heure = document.getElementById("date_heure");
const roomId = document.getElementById("roomId").innerText;
const roomType = document.getElementById("roomType").innerText;
const deviceID = document.getElementById("deviceID").innerText;
const donneeConso = document.getElementById("donneeConso");
const url = "http://172.31.249.161:8080/"

document.querySelectorAll("#allumerEquip")
    .forEach(el => el.addEventListener("click", (e) => {
        const { id } = e.target.dataset;
        addActivity(id);
        location.reload();
    }))
document.querySelectorAll("#eteindreEquip")
    .forEach(el => el.addEventListener("click", (e) => {
        const { id } = e.target.dataset;
        addActivity(id);
        location.reload();
    }))

$("#machine").on("click", () => {
    addConsoCafe()
})

function addActivity(deviceID) {
    $.ajax({
        type: "POST",
        url: url + "deviceactivity/" + deviceID,
        contentType: "application/json",
        error: function (error) {
            console.log(error)
        }
    });
}

function addConsoCafe() {
    $.ajax({
        type: "POST",
        url: url + "addcafe/" + deviceID,
        contentType: "application/json",
        error: function (error) {
            console.log(error)
        }
    });
}

window.onload = () => {
    setInterval(retrieveRoomTemp, 2000)
    setInterval(retrieveLumiTemp, 2000)
    setInterval(retrieveDateHeureTemp, 2000)
    setInterval(retrieveRoomConso, 1000)
}

const retrieveRoomConso = () => {
    fetch(url + "consumption/room/" + roomId)
        .then(roomConso => roomConso.text())
        .then((roomConso) => {
            donneeConso.innerText = roomConso+'W'
        })
        .catch(err => console.log(err))
}


const retrieveRoomTemp = () => {
    fetch(url + "roomtemp/" + roomId)
        .then(roomTemp => roomTemp.text())
        .then((roomTemp) => {
            donneeTemp.innerText = roomTemp
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

const retrieveLumiTemp = () => {
    fetch(url + "roomlumi/" + roomId).then(roomLumi => roomLumi.text()).then((roomLumi) => {
        donneeLumi.innerText = roomLumi+"%"
    }).catch(err => console.log(err))
}

