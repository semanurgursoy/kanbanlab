let preveiwContainer = document.querySelector('.cards-preview');
let previewBox = preveiwContainer.querySelectorAll('.preview');

document.querySelectorAll('.card-container .card').forEach(card =>{
  card.onclick = () =>{
    preveiwContainer.style.display = 'flex';
    let name = card.getAttribute('id');
    previewBox.forEach(preview =>{
      let target = preview.getAttribute('target');
      if(name == target){
        preview.classList.add('active');
      }
    });
  };
});

previewBox.forEach(close =>{
  close.querySelector('.fa-close').onclick = () =>{
    close.classList.remove('active');
    preveiwContainer.style.display = 'none';
  };
});