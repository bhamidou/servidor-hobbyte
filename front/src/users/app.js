import { asyncFuncWithToken, baseUrl, showAlert } from "../helpers/lib.js";

let url = baseUrl+'/users'
const tbody = document.getElementById('tbody');

asyncFuncWithToken(url, "GET").then(res=>{
    console.log(res)
    res.forEach(user => {
        const tr = document.createElement('tr');
        const tdId = document.createElement('td');
        const tdNombre = document.createElement('td');
        const tdEmail = document.createElement('td');
        const tdDelete = document.createElement('td');

        tdId.textContent = user.userId;
        tdNombre.textContent = user.nombre;
        tdEmail.textContent = user.email;

        tdDelete.innerHTML = '<svg xmlns="http://www.w3.org/2000/svg" x="0px" y="0px" width="30" height="30" viewBox="0 0 30 30"> <path d="M 13 3 A 1.0001 1.0001 0 0 0 11.986328 4 L 6 4 A 1.0001 1.0001 0 1 0 6 6 L 24 6 A 1.0001 1.0001 0 1 0 24 4 L 18.013672 4 A 1.0001 1.0001 0 0 0 17 3 L 13 3 z M 6 8 L 6 24 C 6 25.105 6.895 26 8 26 L 22 26 C 23.105 26 24 25.105 24 24 L 24 8 L 6 8 z"></path>  </svg>'
        tdDelete.addEventListener('click', () => deleteUser(user.userId));

        tr.appendChild(tdId);
        tr.appendChild(tdNombre);
        tr.appendChild(tdEmail);
        tr.appendChild(tdDelete);

        tbody.appendChild(tr);
    });
})

function deleteUser(userId) {
    const deleteUrl = `${baseUrl}/user/${userId}`;
    asyncFuncWithToken(deleteUrl, "DELETE").then(res => {
       if(res.status==200){
           showAlert(res.message, 'success')
           setTimeout(() => {
            location.reload()
          }, 1000);
        }else{
           showAlert(res.message, 'danger')
        }
    }).catch(error => {
        console.log(error);
    });
}