import { showAlert } from "../helpers/lib.js"
import {baseUrl, asyncFunc}  from  "../helpers/lib.js"

const emailUser = document.getElementById('user')
const code = document.getElementById('code')
const confirmBtn = document.getElementById('confirmBtn')
const getNewCode = document.getElementById('getNewCode')

confirmBtn.addEventListener('click',()=>{
    const url = baseUrl+'/check-code'

    const body =  JSON.stringify({
        email:emailUser.value,
        confirmation: code.value
    })

    asyncFunc(url, "POST",body).then(res=>{
        console.log(res)
        showAlert('Cuenta confirmada correctamente', 'success');

    }).catch(err =>{
        console.log(err)
        showAlert('Ha ocurrido un problema.', 'danger');
    })
})

getNewCode.addEventListener('click',()=>{
    const url = baseUrl+'/check-code'

    const body =  JSON.stringify({
        email:emailUser.value
        })

    asyncFunc(url, "POST",body).then(res=>{
        console.log(res)

        setTimeout(() => {
            location.href="../index.html"
        }, 1000);

    }).catch(err =>{
        console.log(err)
    })
})