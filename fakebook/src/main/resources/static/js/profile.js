let img_profile =document.querySelector('#img_profile'),
    img_profile_img =document.querySelector('#img_profile_img'),
    profilePOP =document.querySelector('.edit-profile-pop'),
    closeprofilePOP =document.querySelector('.closr-edit-profile'),
    add_profile_image =document.querySelector('#add_profile_image'),
    profile_image_filename = document.querySelector('#profile_image_filename'),
    profile_image_file =document.querySelector('#profile_image_file'),

    profile_image_btn =document.querySelector('#profile_image_btn')


img_profile.onclick = ()=>{
    profilePOP.classList.add('active')
    overlayPst.classList.add('active')
}

closeprofilePOP.onclick = ()=>{
    profilePOP.classList.remove('active')
    overlayPst.classList.remove('active')

}

add_profile_image.onclick = ()=>{
    profile_image_file.click()
}


profile_image_file.onchange = ()=>{
    profile_image_filename.innerHTML=profile_image_file.files[0].name
}

profile_image_btn.onclick = ()=>{
    profilePOP.classList.remove('active')
    overlayPst.classList.remove('active')

    var formData = new FormData();

    var files = profile_image_file.files;

    for (var i = 0; i < files.length; i++) {
        console.log(files[i]);
        formData.append("uploadFiles", files[i]);
    }

    //실제 업로드 부분
    //upload ajax
    $.ajax({
        url: '/uploadAjax',
        processData: false,
        contentType: false,
        data: formData,
        type: 'POST',
        dataType:'json',
        success: function(result){
            console.log(result);

            img_profile_img.src="http://chanhee-kit.s3-website.ap-northeast-2.amazonaws.com/static/"+result[0].uuid+"_"+result[0].fileName

        },
        error: function(jqXHR, textStatus, errorThrown){
            console.log(textStatus);
        }

    }); //$.ajax
}















