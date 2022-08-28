const answer = document.getElementById('Qanswer')
const submiter = document.getElementById('submiter')
const result = document.getElementById('result')

openans = () => {
    result.innerHTML = Qanswer
}

check = () => {
    if(answer.value == Qanswer) {
        result.innerHTML = '정답입니다!'
    }else {
        result.innerHTML = '오답입니다 </br><button id="open">(해설보기)</button>'
        var open = document.getElementById('open')
        open.addEventListener('click',openans)
    }
}

submiter.addEventListener('click',check)