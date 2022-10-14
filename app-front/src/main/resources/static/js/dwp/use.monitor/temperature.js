document.getElementById("temperature-slider").oninput = function () {
    tempValue = document.getElementById("temp-value").innerText = this.value+" C°";
}

var tempValue = document.getElementById("temp-value").innerText;
const donneeTemp = document.getElementById("donneeTemp");
const date_heure = document.getElementById("date_heure");
const roomId = document.getElementById("roomId").innerText;
const url = "http://172.31.249.161:8080/"

window.onload = () => {
    setInterval(retrieveRoomTemp, 2000)
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

const retrieveDateHeureTemp = () => {
    fetch(url + "roomtemp/date/" + roomId)
        .then(roomDate => roomDate.text())
        .then((roomDate) => {
            date_heure.innerText = roomDate
        })
        .catch(err => console.log(err))
}

$("#choixTemp").on("click", () => {
    changeTemp()
})

$("#eteindre").on("click", () => {
    arretChauffe()
})

const changeTemp = () => {
    let tempValueEnvoie = tempValue.split(' ');
    const rec = {method : 'POST', headers: {}}
    fetch(url+'heating/add?state=true&temp='+tempValueEnvoie[0]+'&date='+date_heure.innerText+'&idroom='+roomId, rec).then()
}

const arretChauffe = () => {
    const rec = {method : 'POST', headers: {}}
    fetch(url+'heating/off?date='+date_heure.innerText+'&idroom='+roomId, rec).then()
}