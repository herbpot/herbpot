const express = require('express')
const multer = require("multer")
const fs = require("fs")
const path = require("path")
const cors = require("cors")

const app = express()

const upload = multer({storage:multer.diskStorage({
    destination: (req,file,cb) => {
        cb(null, "public/upload/")
    },
    filename: (req,file,cb) => {
        cb(null, Date.now()+".png")
    }})})

app.use(express.urlencoded())
app.use(express.json())
app.set('view engine','ejs')
app.engine('html',require('ejs').renderFile)
app.use(cors())

app.use((err, req, res, next) => {

    res.json({ok: false, data: err.message})

})

app.post('/post',(req,res) =>{
    // console.log(body,inner);
    // console.log("inner: ",inner);
    fs.readFile("tapList.json",(err,data) => {
        // console.log(err);
        // console.log(data);
        tapListdata = JSON.parse(data)
        const body = req.body
        
        const name = body.name
        var inner = body.test
        inner = inner.split('\n')
        console.log("inner: ",inner);
        for(let i = 0; i < tapListdata.length; i++) {
            if (tapListdata[i].name == name) {
                console.log(inner);
                for(var j=0; j < inner.length; j++){
                    console.log("inner[j]: ",inner[j]);
                    tapListdata[i].in.push((inner[j].trim()).replace("\r",""))
                }
            }
        }
        tapListdata = JSON.stringify(tapListdata)
        console.log(tapListdata);
        fs.writeFileSync("tapList.json",tapListdata)
    })
    console.log("done");
})


app.post('/up', upload.array("img"), (req,res,next) => {
    // console.log("??");
    // const files = req.files
    // const file = req.files
    // console.log(file);
    // console.log(req);
    // files.map(img => {
    //     console.log(img.filename)
    // })
})

app.get('/',(req,res) => {
    res.sendFile(__dirname+"/index.html")
})

app.listen(3000,() => {
    console.log('http://localhost:3000');
})