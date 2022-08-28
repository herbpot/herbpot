const express = require("express");
const fs = require('fs');

const app = express();
const port = 8080;


app.use('/static', express.static(`./static`))

app.get('/', (req,res) => {
    fs.readFile(`static/html/main.html`,'UTF-8', (err,data) => {
        if(err) {
            res.send(err)
            console.log(err);
        }
        else {
            res.writeHead(200, {'Content-Type':'text/html'})
            res.write(data)
            res.end()
        }
    })

})

app.get('/planer', (req,res) => {
    fs.readFile(`static/html/planer.html`,'UTF-8', (err,data) => {
        if(err) {
            res.send(err)
            console.log(err);
        }
        else {
            res.writeHead(200, {'Content-Type':'text/html'})
            res.write(data)
            res.end()
        }
    })
})

app.listen(port, () => {
    console.log("start server");
})