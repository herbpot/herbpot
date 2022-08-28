function load(page) {
    readTextFile('/js/data/tapList.json',(text) => {
        text = JSON.parse(text)
        for (let i = 0; i < text.length; i++) {
            const e = text[i];
            if(e.name == page){
                const content = document.getElementById("inputtext")
                content.innerHTML = ""
                content.classList = ['edit']
                for (let j = 0; j < e.in.length; j++) {
                    content.innerHTML += e.in[j]+'\n';
                }
                const save = document.getElementById('name')
                save.value = page 
                // console.log(content.value);
            }
            
        }
    })
}