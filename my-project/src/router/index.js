import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'

Vue.use(Router)

//配置路由路径
const RoutersPath=[
  {
    path: '*',
    meta:{
      title:'总列表'
    },
    redirect: '/productList'
  },
  {
    path: '/productList',
    name:'productList',
    meta:{
      title:'商品列表',
      show:true
    },
    component:(resolve)=>require(['./view/productList.vue'],resolve)
  },{
    path: '/carList',
    name:'carList',
    meta:{
      title:'购物车',
      show:true
    },
    component:(resolve)=>require(['./view/carList.vue'],resolve)
  },{
    path: '/productInfo',
    name:'productInfo',
    meta:{
      title:'商品详情'
    },
    component:(resolve)=>require(['./view/productInfo.vue'],resolve)
  },{
    path: '/question',
    name:'question',
    meta:{
      title:'问卷调查',
      show:true
    },
    component:(resolve)=>require(['./view/question.vue'],resolve)
  }

]

//配置路由属性
const routerConfig={
  routes:RoutersPath,
  mode:'history',
  base:__dirname
}

//实例化一个router对象
const router=new Router(routerConfig);

//声明钩子函数
router.beforeEach((to,from,next)=>{
  window.document.title=to.meta.title;
  next();
});

router.afterEach((to,from,next)=>{
  window.scroll(0,0);
});

export default router;
