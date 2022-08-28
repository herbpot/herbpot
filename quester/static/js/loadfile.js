//console.log(id);
const questname = document.getElementById('Qname')
const questText = document.getElementById('Qtext')
const answerText = document.getElementById('Qanswer')

var file = file.split('\n')
const Qname = file[0]
const Qtext = file[1]
const Qanswer = file[2]

console.log(file);
console.log(Qname);
console.log(Qtext);
console.log(Qanswer);


questname.value = Qname
questText.innerHTML = Qtext
