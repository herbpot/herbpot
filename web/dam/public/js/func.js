var text;
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
var text
readTextFile('./js/data/tapList.json',(data) => {
    text = JSON.parse(data)
})
console.log(text);
function load(page) {
        for (let i = 0; i < text.length; i++) {
            const e = text[i];
            if(e.name == page){
                const tapContent = document.getElementById("tapContent")
                const content = document.createElement('p')
                content.classList.add('inner')
                for (let j = 0; j < e.in.length; j++) {
                    // console.log(content,e.in[j].includes('[u]'));
                    if(e.in[j].includes('[u]')){
                        geturl(content,e.in[j])
                    }if(e.in[j].includes('[i]')){
                        getimg(content,e.in[j])
                    }else{
                        content.innerHTML = content.innerHTML+ e.in[j]+'<br>';
                    }
                    // console.log(e.in[j]);
                }
                tapContent.innerHTML = " "
                tapContent.appendChild(content)
            }
            
        }
}
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
        content.innerHTML += url[k-1]
        content.appendChild(urlA)
        content.innerHTML += url[k+1]
    }content.innerHTML += '<br>';
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
        content.innerHTML += url[k-1]
        content.appendChild(Img)
        content.innerHTML += url[k+1]
    }content.innerHTML += '<br>';
}