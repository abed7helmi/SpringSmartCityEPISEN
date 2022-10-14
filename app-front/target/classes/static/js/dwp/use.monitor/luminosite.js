document.getElementById("luminosity-slider").oninput = function () {
   lumiValue = document.getElementById("lumi-value").innerText = this.value+" %";
}

var lumiValue = document.getElementById("lumi-value").innerText;
const donneeLumi = document.getElementById("donneeLumi");
const outdoorLumi = document.getElementById("outdoorLumi");
const date_heure = document.getElementById("date_heure");
const roomId = document.getElementById("roomId").innerText;
const url = "http://172.31.249.161:8080/"

window.onload = () => {
    setInterval(retrieveLumiTemp, 2000)
    setInterval(retrieveDateHeureTemp, 2000)
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

$("#choixLumi").on("click", () => {
    changeLumi()
})

const changeLumi = () => {
    let lumiValueEnvoie = lumiValue.split(' ');
    const rec = {method : 'POST', headers: {}}
    console.log(lumiValueEnvoie)
   // if(lumiValueEnvoie > outdoorLumi) {
        fetch(url+'lumi/add?state=true&lumi='+lumiValueEnvoie[0]+'&date='+date_heure.innerText+'&idroom='+roomId, rec).then()
  /* } else {

    }*/
}