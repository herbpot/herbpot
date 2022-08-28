var text;
var textOb = {};
function readTextFile(file, callback) {
    var rawFile = new XMLHttpRequest();
    rawFile.overrideMimeType("application/json");
    rawFile.open("GET", file, true);
    rawFile.onreadystatechange = function() {
        if (rawFile.readyState === 4 && rawFile.status == "200") {
            callback(rawFile.responseText);
        }
    }
    rawFile.send(null);
}

readTextFile('/js/data/tapList.json',(data) =>{
  text = JSON.parse(data)
  text.map(info => {
      textOb[info.name] = info.in
  })
})

function geturl(content,con){
    url = con.split('[u]')
    // console.log(url.length);
    for(let k=1;k < url.length;k+=2){
        // console.log(k);
        const [name,url_] = url[k].split('!')
        const urlA = document.createElement('a')
        urlA.href = `"${url_}"`
        urlA.innerText = name
        // console.log(url[k-1] + urlA + url[k+1]);
        content += url[k-1]
        content += (urlA)
        content += url[k+1]
    }content += '<br>';
}
function getimg(content,con){
    url = con.split('[i]')
    for(let k=1;k < url.length;k+=2){
        console.log(url[k]);
        // console.log(k);
        const Img = document.createElement('img')
        Img.src = `${url[k]}`
        Img.classList.add('img')
        console.log(Img.src);
        content += url[k-1]
        content += (Img)
        content += url[k+1]
    }content += '<br>';
}