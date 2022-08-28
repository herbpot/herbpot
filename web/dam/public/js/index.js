function detectMobileDevice(agent) {
    const mobileRegex = [
      /Android/i,
      /iPhone/i,
      /iPad/i,
      /iPod/i,
      /BlackBerry/i,
      /Windows Phone/i
    ]
  
    return mobileRegex.some(mobile => agent.match(mobile))
  }
  
  const isMobile = detectMobileDevice(window.navigator.userAgent)
  
  if (isMobile) {
      alert('모바일은 디자인이 미완성 입니다...\n 양해 바랍니다')
  } else {
    console.log('current device is not mobile')
  }
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

const tapList = document.getElementById("tapList")
readTextFile('/js/data/tapList.json',(data) => {
    console.log(data);
    const text = JSON.parse(data)

    console.log(text);
    for (let i = 0; i < text.length; i++) {
        const e = text[i];
        console.log(e);
        const liE = document.createElement('li')
        const aE = document.createElement('a')
        const aclassList = aE.classList
        aclassList.add("u-active-white")
        aclassList.add("u-button-style")
        aclassList.add("u-tab-link")
        aclassList.add("u-tap-link-2")
        aclassList.add("pink-bgd")
        aE.classList = aclassList
        aE.href = `javascript:load("${e.name}")`
        aE.innerHTML = e.name
        aE.style.fontFamily = 'Dongle'
        aE.style.fontSize = '30px'
        liE.appendChild(aE)
        liE.classList.add("u-tab-item")
        tapList.appendChild(liE)
        console.log(liE,i);
    }
})

function resize(e){
    if(window.innerWidth <= 770){
        alert('작은 창에서의 디자인이 개발중입니다!')
    }
}

try {
    function add() {
        location.href += 'back'
    }
    const submit = document.getElementById("submit")
    submit.addEventListener("click",add)
    console.log("submitter!");
} catch (error) {
    
}
try {
    function gotoadd() {
        location.href += "add"
    }
    const goto = document.getElementById("goadd")
    goto.addEventListener("click",gotoadd)
} catch {
    
}


window.addEventListener('resize',resize)
