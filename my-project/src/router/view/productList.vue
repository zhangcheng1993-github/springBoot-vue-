<template>
    <div class="productList-main">
      <div class="shopList-conterll-item">
        <div class="shopList-item">
          <el-tag>排序:</el-tag>
          <el-tag type="info" :class="[{itemClsAct:nowSort.indexOf('sales')>-1},'itemCls']" @click="handleSort('sales')">价格<span v-if="nowSort.indexOf('ASC')>-1">↓</span><span v-else>↑</span></el-tag>
          <el-tag type="info" :class="[{itemClsAct:nowSort.indexOf('cost')>-1},'itemCls']" @click="handleSort('cost')">销量<span v-if="nowSort.indexOf('ASC')>-1">↓</span><span v-else>↑</span></el-tag>
        </div>
        <div class="shopList-item">
          <el-tag>颜色:</el-tag>
          <el-tag :class="[{itemClsAct:nowColor.indexOf(item)>-1},'itemCls']" type="info" @click="handleClick(item)" name="item" :key="item" v-for="item in colorAll">{{item}}</el-tag>
        </div>
        <div class="shopList-item">
          <el-tag>品牌:</el-tag>
          <el-tag :class="[{itemClsAct:nowBrand==item},'itemCls']" type="info" @click="handleBrandClick(item)" name="item" :key="item" v-for="item in brandAll">{{item}}</el-tag>
        </div>
      </div>
      <div class="shopList-product">
        <product v-for="item in fiferList" :key="item.id" :item="item"></product>
      </div>
    </div>
</template>

<script>
   import product from '../../components/product.vue';
    export default {
        name: "productList",
        components:{
          product
        },
        data(){
            return{
              nowColor:[],
              nowBrand:'',
              nowSort:''
            }
        },
      computed:{
        productList(){
          return this.$store.state.shopStore.productList
        },
        colorAll(){
          return this.$store.getters.colorAll;
        },
        brandAll(){
          return this.$store.getters.brandAll;
        },
        fiferList(){
          let list=[...this.productList];
          //按颜色筛选
          if(this.nowColor.length!=0){
            list=list.filter((item)=>{
              var obj=this.nowColor.find((value)=>value==item.color);
              if (obj!=undefined) return  item;
            })
          }
          //按品牌筛选
          if (this.nowBrand!=''){
            list=list.filter((item)=>item.brand==this.nowBrand);
          }

          //排序
          if (this.nowSort!=''){
              //按价格排序
              if (this.nowSort.indexOf('sales')>-1){
                list=list.sort((a,b)=>{
                  return this.nowSort.indexOf("ASC")>-1?a.sales-b.sales:b.sales-a.sales;
                });
              }else {
                //按销量排序
                list=list.sort((a,b)=>{
                  return this.nowSort.indexOf("ASC")>-1?a.cost-b.cost:b.cost-a.cost;
                });
              }
          }
          return list;
        }
      },
      methods:{
          getAllProduct(){
            this.$store.dispatch('getAllProduct');
          },
          handleClick(itemVal){
            var findIndex=this.nowColor.findIndex((item)=>item==itemVal);
            if (findIndex>-1){
              this.nowColor.splice(findIndex,1);
            }else{
              this.nowColor.push(itemVal);
            }
          },
          handleBrandClick(itemVal){
            this.nowBrand=this.nowBrand==itemVal?'':itemVal;
          },
          handleSort(type){
              //如果是按价格排序
              if (type=='sales') {
                this.nowSort=this.nowSort==''?'salesASC':(this.nowSort=='salesASC'?'salesDESC':'salesASC');
              }
              //如果是按销量排序
              if (type=='cost') {
                this.nowSort=this.nowSort==''?'costASC':(this.nowSort=='costASC'?'costDESC':'costASC');
              }
          }
      },
      created(){
        this.getAllProduct();
      }
    }
</script>

<style scoped>
  .itemCls{
    cursor: pointer;
    margin: 0px 2px;
  }
  .itemClsAct{
    background-color: #ffbd6c;
  }

  .shopList-item{
    margin: 5px auto;
    display: flex;
    flex-flow:row  wrap;
    justify-content: start;
    align-items:center;
  }


  .shopList-product{
    display: flex;
    flex-flow:row  wrap;
    justify-content: start;
    align-items:center;
  }
</style>
