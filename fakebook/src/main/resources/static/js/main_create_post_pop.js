let thePOP =document.querySelector('.creatr-post-pop'),
    closePOP =document.querySelector('.closr-create-post'),
    clickPOP =document.querySelector('.create-post'),
    overlayPst =document.querySelector('.overlay-post'),
    add_board_image =document.querySelector('#add_board_image'),
    board_image_filename = document.querySelector('#board_image_filename'),
    board_image_file =document.querySelector('#board_image_file'),
    post_bcontent =document.querySelector('#post_bcontent'),
    post_bwriter_profile =document.querySelector('#post_bwriter_profile'),
    post_bwriter =document.querySelector('#post_bwriter'),
    publish_post =document.querySelector('#publish_post'),
    create_chat_pop =document.querySelector('.messenger-pop'),
    close_chat_pop =document.querySelector('.close-chat-pop'),
    chat_to =document.querySelector('#chat_to'),
    chat_to_profile =document.querySelector('#chat_to_profile'),
    me_name =document.querySelector('#me_name')

close_chat_pop.onclick = ()=>{
    create_chat_pop.classList.remove('active')
}

function chatting_image(e) {
    var formData = new FormData();
    var me = me_name.innerText.toLowerCase();
    var my = e.target.id.toLowerCase();
    var my_profile = e.target.src.split('/')[4];

    console.log("me : " + me);
    console.log("my : " + my);
    console.log("my_profile : " + my_profile);

    chat_to.innerText = my;
    chat_to_profile.src="http://chanhee-kit.s3-website.ap-northeast-2.amazonaws.com/static/"+my_profile;
    create_chat_pop.classList.add('active')
}

function chatting_name(e) {
    var formData = new FormData();
    var me = me_name.innerText.toLowerCase();
    var my = e.target.innerHTML.toLowerCase();
    var my_profile = e.target.id;
    console.log("me : " + me);
    console.log("my : " + my);
    console.log("my_profile : "+ my_profile);

    create_chat_pop.classList.add('active')

}


function make_friends_image(e) {
    var formData = new FormData();
    var me = me_name.innerText.toLowerCase();
    var my = e.target.alt.toLowerCase();
    var my_profile = e.target.src.split('/')[4];

    console.log("me : " + me);
    console.log("my : " + my);
    console.log("my_profile : " + my_profile);
    formData.append("me", me);
    formData.append("my", my);
    formData.append("my_profile", my_profile);

    //실제 업로드 부분
    //upload ajax
    if(me===my) {
        console.log("ignored");
    } else {
        $.ajax({
            url: '/friends/register',
            processData: false,
            contentType: false,
            data: formData,
            type: 'POST',
            dataType: 'json',
            success: function (result) {
                console.log(result);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.log(textStatus);
            }

        }); //$.ajax
    }
}


function make_friends_name(e) {
    var formData = new FormData();
    var me = me_name.innerText.toLowerCase();
    var my = e.target.innerHTML.toLowerCase();
    var my_profile = e.target.id;
    console.log("me : " + me);
    console.log("my : " + my);
    console.log("my_profile : "+ my_profile);
    formData.append("me", me);
    formData.append("my", my);
    formData.append("my_profile", my_profile);

    if(me===my) {
        console.log("ignored");
    } else {
        $.ajax({
            url: '/friends/register',
            processData: false,
            contentType: false,
            data: formData,
            type: 'POST',
            dataType:'json',
            success: function(result){
                console.log(result);
            },
            error: function(jqXHR, textStatus, errorThrown){
                console.log(textStatus);
            }

        }); //$.ajax
    }

}

clickPOP.onclick = ()=>{
    thePOP.classList.add('active')
    overlayPst.classList.add('active')
}

closePOP.onclick = ()=>{
    thePOP.classList.remove('active')
    overlayPst.classList.remove('active')

}

overlayPst.onclick = ()=>{
    thePOP.classList.remove('active')
    overlayPst.classList.remove('active')
}


add_board_image.onclick = ()=>{
    board_image_file.click()
}

board_image_file.onchange = ()=>{
    board_image_filename.innerHTML=board_image_file.files[0].name
}

publish_post.onclick = ()=>{
    var formData = new FormData();

    var files = board_image_file.files;

    for (var i = 0; i < files.length; i++) {
        console.log(files[i]);
        formData.append("uploadFiles", files[i]);
        formData.append("bcontent", post_bcontent.value);
        formData.append("bwriter", post_bwriter.value);
        formData.append("bwriter_profile", post_bwriter_profile.src.split("/")[4]);

    }

    //실제 업로드 부분
    //upload ajax
    $.ajax({
        url: '/uploadPost',
        processData: false,
        contentType: false,
        data: formData,
        type: 'POST',
        dataType:'json',
        success: function(result){
            console.log(result);
        },
        error: function(jqXHR, textStatus, errorThrown){
            console.log(textStatus);
        }

    }); //$.ajax



    thePOP.classList.remove('active')
    overlayPst.classList.remove('active')
}








