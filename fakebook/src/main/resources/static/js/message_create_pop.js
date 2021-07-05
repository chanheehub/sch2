let publish_post =document.querySelector('#publish_post'),
    me_name =document.querySelector('#me_name')

board_image_file.onchange = ()=>{
    board_image_filename.innerHTML=board_image_file.files[0].name
}

