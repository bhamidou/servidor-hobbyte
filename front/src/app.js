import {baseUrl, asyncFunc, showAlert}  from  "./helpers/lib.js"

const emailUser = document.getElementById('user')
const password = document.getElementById('password')
const loginBtn = document.getElementById('loginBtn')

loginBtn.addEventListener('click',()=>{
    const url = baseUrl+'/login'

    const body =  JSON.stringify({
        email:emailUser.value,
        password: password.value
    })

    asyncFunc(url, "POST",body).then(res=>{
        console.log(res)
        if(res.token != null){
            localStorage.setItem('user', JSON.stringify(res))
            showAlert("Ha iniciado sesión correctamente", "success")

            setTimeout(() => {
                location.href="../src/partidas"
            }, 2000);
        }else{
            showAlert("Su cuenta está sin activar", "danger")
        }
    }).catch(err =>{        
        console.log(err)
    })
})