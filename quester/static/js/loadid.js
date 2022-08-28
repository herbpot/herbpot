var submiter = document.getElementById('submiter')
var id = document.getElementById('id')
change = () => {
  if(!id.value == '' || !id.value == null){
    submiter.href = `/q/${id.value}`
  }
}
id.addEventListener('change',change)
