const path = require('path');
const HtmlWebpackPlugin = require('html-webpack-plugin');

module.exports = {
  entry: {
    login: './src/app.js',
    signup: './src/signup/app.js',
 },
  output: {
    filename: '[name].js',
    path: path.resolve(__dirname, './dist'),
    // clean: true,
  },
  devServer: {
    static: path.resolve(__dirname,"./"),
    port: 3001,
    open: {
      target: "./src/"
    },
    headers:{
      'Access-Control-Allow-Origin':'*',
      "Access-Control-Allow-Methods": "GET, POST, PUT, DELETE, PATCH, OPTIONS",
      "Access-Control-Allow-Headers": "X-Requested-With, content-type, Authorization"
    }
  }

};