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
});