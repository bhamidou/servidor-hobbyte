export const baseUrl = "http://localhost:1234/graphql";

export async function graphql(query, variables = {}) {
  try {
    const response = await fetch(baseUrl, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        query,
        variables,
      }),
    });

    const data = await response.json();
    return data;
  } catch (error) {
    console.error('Error al enviar la consulta GraphQL:', error);
    return { errors: [{ message: 'Error al enviar la consulta GraphQL' }] };
  }
}


export function showAlert(message, type) {
  var alertDiv = document.createElement('div');
  alertDiv.className = 'alert alert-' + type;
  alertDiv.innerHTML = message;

  var firstH1 = document.querySelector('h1');

  if (firstH1) {
    firstH1.parentNode.insertBefore(alertDiv, firstH1.nextSibling);
  } else {
    document.body.appendChild(alertDiv);
  }
}
