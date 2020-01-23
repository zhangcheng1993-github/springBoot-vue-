import productData from '../../../static/product.js';

//声明取出重复方法
function getFilterArray(array) {
  const res= [];
  const json = {};
  array.forEach((item,index)=>{
    const _self=item;
    if (!json[_self]) {
      res.push(_self);
      json[_self]=1
    }
  })
  return res;
};


//声明数据
const state={
  productList:[],
  carList:[]
};

//声明数据赋值方法
const mutations={
  setAllProduct(state,data){
    state.productList=data;
  },
  addCar(state,data){
    let val=state.carList.find((item)=>item.id==data);
    if (val!=undefined){
      val.count++;
    }else {
      state.carList.push({
        id:data,
        count:1
      })
    }
  },
  restCar(state,data){
    data.forEach((item)=>{
      let index=state.carList.findIndex((val)=>item.id==val.id);
      state.carList.splice(index,1);
    });
  }
};

//声明数据逻辑方法
const actions={
  getAllProduct(concent){
    setTimeout(()=>{
      concent.commit('setAllProduct',productData);
    },50);
  }
};

//声明视图
const getters={
    colorAll:(state)=>{
      let color=state.productList.map((item)=>item.color);
      return  getFilterArray(color);
    },
    brandAll:(state)=>{
      let brand=state.productList.map((item)=>item.brand);
      return getFilterArray(brand);
    }
};

export default {
  state,
  mutations,
  actions,
  getters
}


