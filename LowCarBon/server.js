const express = require('express')
const app = express()

var isopen = false
var carbonmin = 0

app.get('/',(req,res) => {
    const query_ = req.query
    if(query_.minu != undefined){
        console.log(query_.length);
        carbonmin += parseInt(query_.minu)
    }
    if(query_.isOn != undefined){
        isopen = query_.isOn
    }
    console.log(query_,isopen,carbonmin);
    res.render('main.ejs',{carbonmin : carbonmin, isopen : isopen})
})

app.listen(3033,() => {
    console.log("server is on in port : 3033")
})