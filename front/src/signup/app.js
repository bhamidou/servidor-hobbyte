import { showAlert } from "../helpers/lib.js"
import {baseUrl, asyncFunc}  from  "../helpers/lib.js"

const emailUser = document.getElementById('user')
const nombre = document.getElementById('nombre')
const password = document.getElementById('password')
const signupBtn = document.getElementById('signupBtn')

signupBtn.addEventListener('click',()=>{
    const url = baseUrl+'/signup'

    const body =  JSON.stringify({
        nombre: nombre.value,
        email:emailUser.value,
        password: password.value
    })

    asyncFunc(url, "POST",body).then(res=>{
        console.log(res)
        showAlert('Se ha registrado correctamente', 'success');
        setTimeout(() => {
            location.href = '../confirm' 
        }, 500);
        location.href="../index.html"
    }).catch(err =>{
        console.log(err)
        showAlert('¡Error! Ha ocurrido un problema.', 'danger');
    })
})