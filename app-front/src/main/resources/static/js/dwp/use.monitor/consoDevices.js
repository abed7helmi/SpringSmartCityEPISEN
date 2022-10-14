const date_heure = document.getElementById("date_heure");
const roomId = document.getElementById("roomId").innerText;
const url = "http://172.31.249.161:8080/"

$("#refresh").on("click", () => {
    location.reload();
})

window.onload = () => {
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
