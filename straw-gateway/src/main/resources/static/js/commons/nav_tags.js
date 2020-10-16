let tagsApp = new Vue({
    el: '#tagsApp',
    data: {
        tags: [
            {id: 7, name: 'Spring'},
            {id: 8, name: 'SpringMVC'},
            {id: 9, name: 'MyBatis'},
            {id: 10, name: 'SpringBoot'},
            {id: 11, name: 'SpringCloud'}
        ]
    },
    methods:{
        loadTags: function () {
            // alert("准备加载标签列表数据......");
            $.ajax({
                url: '/redis-tag/v1/tags',
                type: 'get',
                dataType: 'json',//json,text,xml >>> 取值取决于content-type
                success: function (json) {  // 回调函数
                    tagsApp.tags = json.data;
                }
            });
        }
    },
    created: function () {
        this.loadTags();
    }
});