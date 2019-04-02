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
    performance: {
        hints: false
    },
    module: {
        rules:  [
            {
                test: /\.jsx?/,
                exclude: /node_modules/,
                loader: ["babel-loader"]
            },
            {
                test: /\.css$/,
                use: ["style-loader", "css-loader"]
            },
            {
<<<<<<< HEAD
                test: /\.(ttf|eot|svg|woff(2)?)(\S+)?$/,
                loader: 'file-loader?publicPath=/&name=fonts/[name].[ext]'
=======
                test: /\.scss$/,
                use: ["sass-loader"]
            },
            {
                test: /.(png|jpg|jpeg|gif|svg|woff|woff2|ttf|eot)$/,
                use: "url-loader?limit=100000"
>>>>>>> a
            }
        ]
    }
};

module.exports = config;