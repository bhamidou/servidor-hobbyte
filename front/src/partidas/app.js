import { asyncFunc, asyncFuncWithToken, baseUrl, showAlert } from "../helpers/lib.js";

const url = baseUrl+"/mis-partidas"
const tbody = document.getElementById("tbody");

const newPartida = document.getElementById("newPartida");
const inputSize = document.getElementById("inputSize")


asyncFuncWithToken(url, "GET").then( (partida) =>{
    console.log(partida)
    llenarTabla(partida);
}).catch(err=>{
    console.log(err)
    return null
})

function crearFila(partida) {
    const fila = document.createElement("tr");
    const celdaId = document.createElement("td");
    const celdaEstado = document.createElement("td");
    const celdaVer = document.createElement("td");
  
    celdaId.textContent = partida.id;
  
    let estadoTexto;
    switch (partida.estado) {
      case  0:
        estadoTexto = "Jugando";
        break;
      case  1:
        estadoTexto = "Ha perdido - finalizada";
        break;
      case  2:
        estadoTexto = "Ha ganado - finalizada";
        break;
      case  3:
        estadoTexto = "Se ha rendido - finalizada";
        break;
      default:
        estadoTexto = "Estado desconocido";
    }
    celdaEstado.textContent = estadoTexto;
  
    celdaVer.innerHTML = '<svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" viewBox="0 0 24 24"><path d="M12.015 7c4.751 0 8.063 3.012 9.504 4.636-1.401 1.837-4.713 5.364-9.504 5.364-4.42 0-7.93-3.536-9.478-5.407 1.493-1.647 4.817-4.593 9.478-4.593zm0-2c-7.569 0-12.015 6.551-12.015 6.551s4.835 7.449 12.015 7.449c7.733 0 11.985-7.449 11.985-7.449s-4.291-6.551-11.985-6.551zm-.015 3c-2.209 0-4 1.792-4 4 0 2.209 1.791 4 4 4s4-1.791 4-4c0-2.208-1.791-4-4-4z"/></svg>';
    celdaVer.addEventListener('click', function() {
      localStorage.setItem('partidaId', partida.id);
      location.href='../pruebas/'
  });
    fila.appendChild(celdaId);
    fila.appendChild(celdaEstado);
    fila.appendChild(celdaVer);
  
    return fila;
  }
  
  

function llenarTabla(partidas) {
  partidas.forEach((partida) => {
    const fila = crearFila(partida);
    tbody.appendChild(fila);
  });
}



newPartida.addEventListener('click',()=>{
  let url = baseUrl+'/partida'
  
  if(inputSize.value>1){
    url=baseUrl+'/partida/'+inputSize.value
  }

  asyncFuncWithToken(url, "POST").then(res=>{
    console.log(res)
    if(res.status == "201"){
      showAlert("Partida creada correctamente", "success")

      setTimeout(() => {
        location.reload()
      }, 1000);
    }else{
      showAlert("Ya tienes una partida creada", "danger")
    }
  }).catch(err =>{        
      console.log(err)
  })
})