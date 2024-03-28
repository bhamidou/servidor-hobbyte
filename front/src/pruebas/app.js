import { asyncFuncWithToken, baseUrl, showAlert } from "../helpers/lib.js";

const url =
  baseUrl + "/partida/" + JSON.parse(localStorage.getItem("partidaId"));

  const heroes = baseUrl + '/mis-heroes/'+JSON.parse(localStorage.getItem("partidaId"));
  const check = baseUrl+'/mis-partidas'

const btnRenrise = document.getElementById("rendirse")

btnRenrise.addEventListener('click', ()=>{
    let rend = baseUrl+'/rendirse/'+JSON.parse(localStorage.getItem("partidaId"));
    asyncFuncWithToken(rend, "GET").then(res=>{
        console.log(res)
        if(res.status==200){
            showAlert(res.message,'success')
        }else{
            showAlert(res.message, 'danger')
        }
    })
})

asyncFuncWithToken(url, "GET").then((data) => {
    asyncFuncWithToken(heroes, "GET").then(res=>{
        fillTableWithData(res)
        asyncFuncWithToken(check,"GET").then(res=>{
            console.log(res)
        
            res.forEach(element =>{
                if(element.id == localStorage.getItem("partidaId") ){
                    if(element.estado == 1){
                        btnRenrise.remove()
                        showAlert("Esta partida ya se ha terminado, y ha perdio", "danger")
                    }else if(element.estado == 2){
                        btnRenrise.remove()
                        showAlert("Esta partida ya se ha terminado, y ha ganado", "success")
                    }else if(element.estado == 3){
                      btnRenrise.remove()
                        showAlert("Esta partida ya se ha terminado, y se ha rendido", "danger")
                    }
                }
            })
        
          })
    })
 
  const tbody = document.getElementById("tbody");
    console.log(data)
  data.forEach((item) => {
    const tr = document.createElement("tr");

    const estadoCell = document.createElement("td");

    switch (item.destapada) {
      case  0:
        estadoCell.textContent = "Sin destapar";
        break;
      case  1:
        estadoCell.textContent = "Perdida";
        break;
      case  2:
        estadoCell.textContent = "Ganada";
        break;
      default:
        estadoCell.textContent = "Estado desconocido";
    }
    tr.appendChild(estadoCell);

    const tipo = document.createElement("td");
    tipo.textContent = item.tipo;
    tr.appendChild(tipo);

    const esfuerzoCell = document.createElement("td");
    esfuerzoCell.textContent = item.capacidad;
    tr.appendChild(esfuerzoCell);

    const destaparCell = document.createElement("td");
    if (item.destapada <=  0) {
      const button = document.createElement("button");
      button.textContent = "Destapar";
      destaparCell.appendChild(button);
      
      button.addEventListener('click', function() {
        destaparPrueba(item.id);
      });

      tr.appendChild(destaparCell);
      
    }else{
        const p = document.createElement("p");
        p.textContent = "Destapada";
        destaparCell.appendChild(p);
        tr.appendChild(destaparCell);
    }

    tbody.appendChild(tr);
  });
});


function destaparPrueba(pruebaID) {
    const url = baseUrl +'/partida/'+pruebaID+ "/destapar";
  
    asyncFuncWithToken(url, "GET").then((response) => {
      console.log(response);

      if(response.status == 401){
        showAlert(response.message, "danger")
      }
      if(response.status == 200){
        showAlert(response.message, "success")

      }

      if(response.status == 400){
        showAlert(response.message,'danger')
        setTimeout(() => {
          location.href="../partidas"
        }, 1000);
      }else{
        setTimeout(() => {
          location.reload()
        }, 1000);
      }
        
    }).catch((error) => {
      console.log(error);
    });
  }

  function fillTableWithData(data) {
    var tbody = document.getElementById('thereos');
  
    data.forEach(function(item) {
      var tr = document.createElement('tr');
  
      var tdNombre = document.createElement('td');
      tdNombre.textContent = item.nombre;
      tr.appendChild(tdNombre);
  
      var tdTipoPrueba = document.createElement('td');
      tdTipoPrueba.textContent = item.tipo_prueba;
      tr.appendChild(tdTipoPrueba);

      var tdEsfuerzo = document.createElement('td');
      tdEsfuerzo.textContent = item.capacidad;
      tr.appendChild(tdEsfuerzo);
  
      tbody.appendChild(tr);
    });
  }
  



