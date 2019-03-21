const webpack = require('webpack');
const path = require('path');

const config = {
    entry:   path.join(__dirname, 'src/main/resources/static/src/app.js'),
    output: {
        path: path.join(__dirname,'src/main/resources/static'),
        filename: 'bundle.js'
    },
    resolve: {
        alias: {
            src: path.join(__dirname, 'src/main/resources/static/src')
        }
    },
    module: {
        rules:  [
            {
                test: /\.jsx?/,
                exclude: /node_modules/,
                use: ["babel-loader"]
            },
            {
                test: /\.css$/,
                use: ["style-loader", "css-loader"]
            }
        ]
    }
};

module.exports = config;