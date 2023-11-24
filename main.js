// 'use strict';
// let inputBox = document.getElementById('inputField');// input value
// let addToDo = document.getElementById('AddToList');//button
// let toDoList = document.getElementById('List');//todo list

// function addTodoItem(){
//     let list = document.createElement('li');//li tag만들기
//     if(!inputBox.value){
//         alert('내용을 입력해 주세요!');
//     }
//     else{
//         list.innerText = inputBox.value;
//         toDoList.appendChild(list);
//         inputBox.value="";
//     }

//     list.addEventListener('click',function(){
//         list.style.textDecoration = 'line-through';
//     });
//     list.addEventListener('dblclick',function(){
//         toDoList.removeChild(list);
//     });
// }

// addToDo.addEventListener('click',addTodoItem);
// inputBox.addEventListener('keyup',function(event){
//     if(event.key==='Enter'){
//         addTodoItem();
//     }
// });
'use strict';
//input value, button, list
let inputBox = document.getElementById('inputField');
let addToDo = document.getElementById('AddToList');
let toDoList = document.getElementById('List');

addToDo.addEventListener('click',addTodoitem);
inputBox.addEventListener('keyup',function(event){
    if(event.key==='Enter'){
        addTodoitem();
    }
});

function addTodoitem(){
    let list = document.createElement('li');

    if(!inputBox.value){
        alert('내용을 입력해주세요');
    }
    else{
        list.innerText = inputBox.value;
        toDoList.appendChild(list);
        inputBox.value='';

        let removeButton = document.createElement('button');
        removeButton.textContent='REMOVE';

        let itemcontainer = document.createElement('div');
        itemcontainer.appendChild(list);
        itemcontainer.appendChild(removeButton);

        toDoList.appendChild(itemcontainer);
        removeButton.addEventListener('click',function(){
            toDoList.removeChild(itemcontainer);
        })

    }
    list.addEventListener('click',function(){
        list.style.textDecoration='line-through';
    });
    // list.addEventListener('dblclick',function(){
    //     toDoList.removeChild(list);
    // })
    
}