//声明数据
const state={
  questionList:[
    {id:1,question:'你的性别是?',modelVal:'sex',type:'radio',answer:['男','女','保密']},
    {id:2,question:'你的爱好是?',modelVal:'hobby',type:'checkbox',answer:['游泳','篮球','足球']},
    {id:3,question:'请填写自我介绍!',modelVal:'remarks',type:'textarea',answer:''}
  ],
  currentPage:1
};

const mutations={
  setCurrentPage(state,data){
    state.currentPage+=data;
  }
};

export default {
  state,
  mutations
}
