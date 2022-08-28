const express = require('express')
const app = express()
const multer = require('multer')
const fs = require('fs')

var idList = []
var idList = fs.readFileSync('./static/server/id.txt').toString('utf-8').split('\n')
//console.log(idList);
const view = __dirname+'/static/views/'

app.use('/static',express.static('./static'));
app.use('/uploads', express.static('./uploads'));
app.use(express.urlencoded())
app.use(express.json())

app.set('view engine','ejs')
app.engine('html',require('ejs').renderFile)

const upload = {
    'multer': multer({storage: multer.diskStorage({
        destination: (req,file,cb) => {
            cb(null, 'uploads/')},
        filename: (req,file,cb) => {
            upload['ider']()
            var id = upload['id']
            // console.log(id.toString());
            cb(null, id.toString()+'.jpg')
        }
    })}),
    'ider':function ider() {
        var i = getid()
        idList.push(i)
        var idData = ''
        for(l in idList) {
            idData += '\n'+idList[l]
        }
        fs.writeFileSync(`./static/server/id.txt`,idData)
        //console.log(i);
        upload['id'] = i
    },
    'id': ''
}

getid = () => {
    var i = Math.floor(Math.random()*100000)
    if(idList === i) {
        getid()
    }else{
        return i
    }
}

app.get('/', (req,res) => {
    res.sendFile(view+'Home.html')
})

app.get('/q/:id', (req,res) => {
    const id = req.params['id']
    var file = ''
    try {
        file = fs.readFileSync(`./uploads/${id}.txt`)
        //console.log(file);
        //console.log(fs.readFileSync(`./uploads/${id}.txt`));
        
    } catch (error) {
        //console.log(error);
        res.redirect('/404')
        return
    }
    res.render(view+'load.html',{id:id, file:file})
    return
    
})

app.get('/qlist',(req,res) => {
    var ids = fs.readFileSync('./static/server/id.txt').toString('utf-8').split('\n')
    res.render(view+'qlist.html', {ids,ids})
})

app.get('/upq',(req,res) => {
    res.sendFile(view+'upload.html')
})

app.get('/q', (req,res) => {
    res.render(view+'noid.html')
})

app.post('/uploadBack',upload['multer'].single('img'),(req,res,next) => {
    // res.render('upload.html', { file:req.file, files:null });
    var id = upload['id']
    var data = req.body
    //console.log(req.file);
    //console.log(data);
    //console.log(data.Qname, data.Qtext,data.Qanswer);
    var setdata = `${data.Qname}\n${data.Qtext}\n${data.Qanswer}\n`
    fs.appendFileSync(__dirname+`\\uploads\\${id}.txt`,setdata)   
    res.redirect('/')
})

app.get('/404',(req,res)=>{
    res.status(400).sendFile(view+'page.html')
});


app.listen(3000,() => {
    console.log('server is on http://localhost:3000');
})