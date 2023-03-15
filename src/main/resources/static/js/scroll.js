var button = document.getElementById('slide');
button.onclick = function () {
    var container = document.getElementById('container');
    sideScroll(container,'right',25,50,3);
};

var back = document.getElementById('slideBack');
back.onclick = function () {
    var container = document.getElementById('container');
    sideScroll(container,'left',25,50,3);
};

function sideScroll(element,direction,speed,distance,step){
    scrollAmount = 0;
    var slideTimer = setInterval(function(){
        if(direction == 'left'){
            element.scrollLeft -= step;
        } else {
            element.scrollLeft += step;
        }
        scrollAmount += step;
        if(scrollAmount >= distance){
            window.clearInterval(slideTimer);
        }
    }, speed);
}

const rightBtn = document.querySelector('#right-button');
const leftBtn = document.querySelector('#left-button');

rightBtn.addEventListener("click", function(event) {
  const conent = document.querySelector('#content');
  conent.scrollLeft += 300;
  event.preventDefault();
});

leftBtn.addEventListener("click", function(event) {
  const conent = document.querySelector('#content');
  conent.scrollLeft -= 300;
  event.preventDefault();
});