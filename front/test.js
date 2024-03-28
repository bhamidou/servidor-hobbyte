const https = require('https');
const fs = require('fs');


const options = {
  key: fs.readFileSync('./privbadrweb.key'),
  cert: fs.readFileSync('./badrweb.crt')
};

const server = https.createServer(options, (req, res) => {
  res.writeHead(200);
  res.end('Hola, mundo!\n');
});

server.listen(8443, () => {
  console.log('Servidor HTTPS escuchando en el puerto  8443');
});