const express = require('express')
const multer = require("multer")
const fs = require("fs")
const path = require("path")
const cors = require("cors")

const port = 3000

const app = express()

const upload = multer({storage:multer.diskStorage({
    destination: (req,file,cb) => {
        cb(null, "public/img/")
    },
    filename: (req,file,cb) => {
        cb(null, Date.now()+".png")
    }})})

app.use(express.urlencoded())
app.use(express.json())
app.set('view engine','ejs')
app.engine('html',require('ejs').renderFile)
app.use(cors())
app.use(express.static(path.join(__dirname, '/public')));


app.get('/', (req,res) => {
    res.sendFile(__dirname+'/public/html/index.html')
})

app.get('/add', (req,res) => {
    res.sendFile(__dirname + '/public/html/addmain.html')
})

app.get('/add/new',(req,res) => {
    res.sendFile(__dirname+'/public/html/add.html')
})

app.post('/add/new/back',(req,res) =>{
    fs.readFile(__dirname+"/public/js/data/tapList.json",(err,data) => {
        var tapListdata = JSON.parse(data)
        const body = req.body
        
        const name = body.name
        var inner = body.inputtext
        console.log("inner: \n",inner);
        console.log(tapListdata.length);
        for(var i=0; i< tapListdata.length; i++){
            console.log("taplists",tapListdata[i],tapListdata[name]);
            if (tapListdata[i].name == name) {
                tapListdata[i].in = inner.split("\n")
            }
        }
        tapListdata = JSON.stringify(tapListdata)
        console.log(body, name, tapListdata);
        fs.writeFileSync(__dirname+"/public/js/data/tapList.json",tapListdata)
        console.log("done!");
    })
    
    res.redirect("/add")
})

app.get('/add/upload',(req,res) => {
    res.sendFile(__dirname+"/public/html/addimg.html")
})

app.post('/add/up',upload.array("img"), (req,res) => {
    var filelist = ""
    req.files.map(img => filelist += " / " + img.filename)
    res.write(`<script>alert('${filelist}')</script>`)
    res.write("<script>window.location = '../add'</script>")
})

app.get("/add/imgs", (req,res) => {
    fs.readdir("./public/img",(err,data) => {
        let imglist = {imgs: data}
        imglist = JSON.stringify(imglist)
        console.log(imglist);
        fs.writeFileSync(__dirname+"/public/js/data/imgs.json",imglist)
        res.sendFile(__dirname+"/public/html/imgs.html")

    })
})

app.listen(port, ()=> {console.log(`server is on http://localhost:${port}`)})