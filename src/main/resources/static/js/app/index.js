var main = {
    init : function(){
       var _this = this;

       $('#btn-save').on('click', function(){
           _this.save();
       });

       $('#btn-update').on('click', function(){
           _this.update();
       });
    },

    save : function(){
        var data = {
            title : $('#title').val(),
            content : $('#content').val(),
            author : $('#author').val()
        };

        $.ajax({
            url : '/api/v1/posts',
            type : 'POST',
            data : JSON.stringify(data),
            dataType : 'json',
            contentType : 'application/json; charset=utf-8'
        }).done(function(){
            alert('글이 등록되었습니다');
            window.location.href = '/';
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
    },

    update : function(){
        var data = {
            title : $('#title').val(),
            content : $('#content').val()
        }
        $.ajax({
            url : '/api/v1/posts/' + $('#id').val(),
            type : 'PUT',
            dataType : 'json',
            contentType : 'application/json; charset=utf-8',
            data : JSON.stringify(data)
        }).done(function(){
            alert('글 수정이 완료되었습니다');
            window.location.href = '/';
        }).fail(function(error){
            alert(JSON.stringify(error));
        })
    }
}

main.init();