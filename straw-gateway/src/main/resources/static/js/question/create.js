Vue.component('v-select', VueSelect.VueSelect);
let createQuestionApp = new Vue({
    el: '#createQuestionApp',
    data: {
        tags: [
            {label: 'Eureka', value: 1},
            {label: 'Zuul', value: 2},
            {label: 'Redis', value: 3},
            {label: 'Vue', value: 4},
            {label: 'Spring Security', value: 5}
        ],
        selectedTagIds: []
    },
    methods: {
        loadTags: function () {
            $.ajax({
                url: '/redis-tag/v1/tags',
                type: 'get',
                dataType: 'json',
                success: function (json) {
                    let list = json.data;
                    let tags = [];
                    for (let i = 0; i < list.length; i++) {
                        let op = {};
                        op.label = list[i].name;
                        op.value = list[i].id;
                        tags[i] = op;
                    }
                    createQuestionApp.tags = tags;
                }
            });
        }
    },
    created: function () {
        this.loadTags();
    }
});